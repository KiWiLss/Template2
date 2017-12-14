package com.magicsoft.template2.utils.http;

public class ServerException extends Exception{

    public String message;

    public ServerException(String message) {
        super(message);
    }
}