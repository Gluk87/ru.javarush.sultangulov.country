package ru.javarush.country.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private Integer requestId;
    private String status;
    private String statusDescription;
}
