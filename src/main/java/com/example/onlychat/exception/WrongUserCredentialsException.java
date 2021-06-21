package com.example.onlychat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Wrong user credentials")
public class WrongUserCredentialsException extends RuntimeException{
}
