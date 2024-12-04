package com.ecommerce.hamroDaraz.DTO;

import ch.qos.logback.core.status.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponseDTO {

    private String apiPath;
    private HttpStatus statusCode;
    private String message;

    @JsonFormat(pattern = "yyyy:MM:dd HH:mm:SS")
    private LocalDateTime exceptionTime;
}
