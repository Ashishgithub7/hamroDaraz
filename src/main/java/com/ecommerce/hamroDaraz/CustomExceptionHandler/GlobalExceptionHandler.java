package com.ecommerce.hamroDaraz.CustomExceptionHandler;

import com.ecommerce.hamroDaraz.DTO.ExceptionResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        private final WebRequest webRequest;

        public GlobalExceptionHandler(@Qualifier("webRequest") WebRequest webRequest) {
            this.webRequest = webRequest;
        }

//      This exception is invoked whenever resourceNotFoundException occurs
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
            ExceptionResponseDTO exceptionDTO = new ExceptionResponseDTO(
                    webRequest.getDescription(false),
                    HttpStatus.NOT_FOUND,
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
        }

//      This exception is invoked whenever resourceAlreadyExistsException occurs
        @ExceptionHandler(ResourceAlreadyExistsException.class)
        public ResponseEntity<ExceptionResponseDTO> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
            ExceptionResponseDTO exceptionDTO = new ExceptionResponseDTO(
                    webRequest.getDescription(false),
                    HttpStatus.CONFLICT,
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDTO);
        }

        //@Valid uses this method
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
        {
            Map<String,String> resp=new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error)->
            {
                String filedName=((FieldError)error).getField();
                String message=error.getDefaultMessage();
                resp.put(filedName,message);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }


        //Catching any other exception globally
        //handle globalexception i.e juna sukai exception pani handle gardenxa if exception handle garana class haru or method pahila hainxa vanew
        @ExceptionHandler(Exception.class)//exception.class le baki raheko juna sukai exception lai ni handle gardenxa
        public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest webRequest)
        {
            ExceptionResponseDTO exceptionResponseDto=new ExceptionResponseDTO(webRequest.getDescription(false),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ex.getMessage(),
                    LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDto);
        }


    }