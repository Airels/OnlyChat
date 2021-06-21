package com.example.onlychat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not authenticated")
public class UserNotConnectedException extends RuntimeException {
}
