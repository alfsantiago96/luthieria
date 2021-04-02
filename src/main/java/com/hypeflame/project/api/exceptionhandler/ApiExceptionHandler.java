package com.hypeflame.project.api.exceptionhandler;

import com.hypeflame.project.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handlerDomain(DomainException ex, WebRequest webRequest){
        var status = HttpStatus.BAD_REQUEST;

        var exceptionResponseModel = new ExceptionResponseModel();
        exceptionResponseModel.setStatus(status.value());
        exceptionResponseModel.setTitle(ex.getMessage());
        exceptionResponseModel.setDateTime(LocalDateTime.now());

        return handleExceptionInternal(ex,exceptionResponseModel, new HttpHeaders(),status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var fieldList = new ArrayList<ExceptionResponseModel.Field>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError)error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fieldList.add(new ExceptionResponseModel.Field(name, message));
        }

        var exceptionResponseModel = new ExceptionResponseModel();
        exceptionResponseModel.setStatus(status.value());
        exceptionResponseModel.setDateTime(LocalDateTime.now());
        exceptionResponseModel.setTitle("Invalid information, fill the blanks correctly and try again.");
        exceptionResponseModel.setFieldsList(fieldList);

        return super.handleExceptionInternal(ex, exceptionResponseModel, headers,status,request);

    }
}
