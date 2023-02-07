package ru.javarush.country.entity.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountResponse extends Response {
    private Integer totalCount;
}
