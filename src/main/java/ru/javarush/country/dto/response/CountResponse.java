package ru.javarush.country.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountResponse extends Response {
    private Integer totalCount;
}
