package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.CreateProductOutputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.CreateProductInputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.CreateProductUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateProductBeanAssembler {

    @Bean
    public CreateProductInputGateway createProductInputGatewayAssemble(
            CreateProductOutputGateway createProductOutputGateway) {
        return new CreateProductUsecase(createProductOutputGateway);
    }

}
