package com.learn.restfulwebservices.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFountException extends RuntimeException {

    public UserNotFountException(String msg) {
        super(msg);
    }

}
