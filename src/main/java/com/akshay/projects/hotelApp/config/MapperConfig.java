package com.akshay.projects.hotelApp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.matcher.StringMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

   @Bean
   public ModelMapper modelMapper() {
       return new ModelMapper();
   }
}
