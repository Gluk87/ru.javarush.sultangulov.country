package ru.javarush.country.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String status;
    private String statusDescription;
}
