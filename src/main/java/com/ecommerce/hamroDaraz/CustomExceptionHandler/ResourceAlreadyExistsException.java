package com.ecommerce.hamroDaraz.CustomExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException  extends RuntimeException{

        String resourceName;

        String fieldName;

        long fieldValue;

        String stringFieldValue;

        String message;

        public ResourceAlreadyExistsException(String resourceName, String fieldName, long fieldValue){
            super(String.format("%s already exists with same %s: %s",resourceName,fieldName,fieldValue));
            this.resourceName = resourceName;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }

        public ResourceAlreadyExistsException(String resourceName, String fieldName, String stringFieldValue){
            super(String.format("%s already exists with same %s: %s",resourceName,fieldName,stringFieldValue));
            this.resourceName = resourceName;
            this.fieldName =fieldName;
            this.stringFieldValue = stringFieldValue;
        }

        public ResourceAlreadyExistsException(String message){
            super(String.format(message));
            this.message=message;
        }
    }
