package com.zserg.jpairsubs.opensubtitles;

public class OpensubtitlesServiceException extends Exception {

    public OpensubtitlesServiceException(String message){
        super(message);
    }
    public OpensubtitlesServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
