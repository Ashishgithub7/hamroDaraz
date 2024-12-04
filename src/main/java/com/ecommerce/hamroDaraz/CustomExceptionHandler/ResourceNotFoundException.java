package com.ecommerce.hamroDaraz.CustomExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;

    String fieldName;

    long fieldValue;

    String stringFieldValue;

    String message;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String stringFieldValue){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,stringFieldValue));
        this.resourceName = resourceName;
        this.fieldName =fieldName;
        this.stringFieldValue = stringFieldValue;
    }

    public ResourceNotFoundException(String message){
        super(String.format(message));
        this.message=message;
    }
}
