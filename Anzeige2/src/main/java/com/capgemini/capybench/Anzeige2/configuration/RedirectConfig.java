package com.capgemini.capybench.Anzeige2.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("KapibaraForwardziara")
public class RedirectConfig {

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public RedirectInterceptor redirectInterceptor(RedirectingProperties redirectingProperties, ObjectMapper mapper) {
        return new RedirectInterceptor(redirectingProperties, mapper);
    }

    @Bean
    public Advisor redirectAdvisor(RedirectInterceptor interceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                "@annotation(com.capgemini.capybench.Anzeige2.configuration.RedirectSsl)");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, interceptor); // Interceptor
        return advisor;
    }
}
