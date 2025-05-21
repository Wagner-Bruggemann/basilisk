package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.FindAllProductsInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.FindAllProductsOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.FindAllProductsUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindAllProductsBeanAssembler {

    @Bean
    public FindAllProductsInputGateway findAllProductsInputGatewayAssemble(FindAllProductsOutputGateway findAllProductsOutputGateway) {
        return new FindAllProductsUsecase(findAllProductsOutputGateway);
    }

}
