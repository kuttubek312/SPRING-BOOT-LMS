package com.peaksoft.springbootlms.exception.handrel;

import com.peaksoft.springbootlms.exception.ExceptionResponse;
import com.peaksoft.springbootlms.exception.not_found.CompanyNotFoundException;
import org.springframework.http.HttpStatus;

public class GlobalExceptionHandler {

    public ExceptionResponse handlerCompanyNotFoundException(CompanyNotFoundException ex) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                ex.getClass().getSimpleName(),
                ex.getMessage()
        );
    }
}
