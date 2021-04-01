package com.hypeflame.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

    //ModelMapper Resource Methods
    //private ObjectModel toModel(Object Object){
    //     return modelMapper.map(object, ObjectModel.class);
    // }
    //
    // private List<ObjectModel> toCollectionModel(List<Object> objectList){
    //     return objectList.stream()
    //             .map(object -> toModel(object))
    //             .collect(Collectors.toList());
    // }

}
