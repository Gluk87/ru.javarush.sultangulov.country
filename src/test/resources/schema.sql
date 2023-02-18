create schema if not exists world;

CREATE TABLE if not exists world.city
(
    id         bigint,
    name       varchar(35),
    country_id bigint,
    district   varchar(20),
    population bigint
);

CREATE TABLE if not exists world.country
(
    id              bigint,
    code            varchar(3),
    code_2          varchar(2),
    name            varchar(52),
    continent       bigint,
    region          varchar(26),
    surface_area    decimal(10, 1),
    indep_year      smallint,
    population      bigint,
    life_expectancy decimal(3, 1),
    gnp             decimal(10, 2),
    gnpo_id         decimal(10, 2),
    local_name      varchar(45),
    government_form varchar(45),
    head_of_state   varchar(60),
    capital         bigint
);

CREATE TABLE if not exists world.country_language
(
    id          bigint,
    country_id  bigint,
    language    varchar(30),
    is_official smallint,
    percentage  decimal(4, 1)
);
