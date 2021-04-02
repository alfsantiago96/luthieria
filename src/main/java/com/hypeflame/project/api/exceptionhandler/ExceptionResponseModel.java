package com.hypeflame.project.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExceptionResponseModel {

     private Integer Status;
     private LocalDateTime dateTime;
     private String title;
     private List<Field> fieldsList;

     @Getter
     @Setter
     @AllArgsConstructor
     public static class Field{

         private String field;
         private String message;
     }

}
