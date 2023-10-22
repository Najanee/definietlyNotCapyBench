package com.capgemini.capybench.Anzeige2.configuration;

import com.capgemini.capybench.Anzeige2.dto.PostDto;
import com.capgemini.capybench.Anzeige2.dto.TopicDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class RedirectInterceptor implements MethodInterceptor, Ordered {

        private int order = HIGHEST_PRECEDENCE;

        private final RedirectingProperties redirectingProperties;

        private ObjectMapper mapper;

        public RedirectInterceptor(RedirectingProperties redirectingProperties, ObjectMapper mapper) {
            this.redirectingProperties = redirectingProperties;
            this.mapper = mapper;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {

            String suffix = invocation.getMethod().getDeclaringClass().getAnnotation(RequestMapping.class).value()[0];
            String url = redirectingProperties.getUrl() + suffix;

            RestTemplate restTemplate = new RestTemplate();
            String result = "";
            if(invocation.getMethod().getAnnotation(GetMapping.class) != null) {
                Parameter[] parameters = invocation.getMethod().getParameters();
                if (parameters.length > 0) {
                    url += "?";
                    for (int i = 0; i < parameters.length; i++) {
                        url += parameters[i].getName() + "=" + invocation.getArguments()[i];
                        if(i != parameters.length - 1) { // nie ma sensu dodawać & na końcu URL, bo nie ma więcej parametrów
                            url += "&";
                        }
                    }
                }
                result = restTemplate.getForObject(url, String.class);
            } else if(invocation.getMethod().getAnnotation(PostMapping.class) != null) {
                Object caughtObject = invocation.getArguments()[0];

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(caughtObject), headers);

                result = restTemplate.postForObject(url, request, String.class);
            }

            return ResponseEntity.ok(result);
        }

        @Override
        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
}
