package com.capgemini.capybench.Anzeige2.dto;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class PersonDto {
    long id;
    @NonNull String name;
    @NonNull String imageUrl;
}
