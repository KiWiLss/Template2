package com.magicsoft.template2.utils.http;

public class ServerException extends Exception{

    public String message;
    int code;

    public ServerException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ServerException(String message) {
        super(message);
    }
}