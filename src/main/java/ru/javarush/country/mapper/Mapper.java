package ru.javarush.country.mapper;

import ru.javarush.country.entity.Response;

public class Mapper {
    protected static final String STATUS_SUCCESS = "SUCCESS";
    protected static final String STATUS_ERROR = "ERROR";
    protected static final String STATUS_SUCCESS_DESCR = "Операция прошла успешно";

    public Response convertError(Exception exception) {
        Response response = new Response();
        response.setStatus(STATUS_ERROR);
        response.setStatusDescription(exception.getMessage());
        return response;
    }


}
