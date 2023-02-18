package ru.javarush.country.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Language {
    private String language;
    private Boolean isOfficial;
    private BigDecimal percentage;
}
