package com.grupo3a.ecommercefrutos2.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Not authorized")
public class NotAuthorizedException extends RuntimeException {
}
