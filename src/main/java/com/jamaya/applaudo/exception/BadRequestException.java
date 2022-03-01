package com.jamaya.applaudo.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
