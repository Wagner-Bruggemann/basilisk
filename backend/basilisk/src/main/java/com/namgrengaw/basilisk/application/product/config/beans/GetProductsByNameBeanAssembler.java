package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByNameInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByNameOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.GetProductsByNameUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductsByNameBeanAssembler {

    @Bean
    public GetProductsByNameInputGateway getProductsByNameInputGatewayAssemble(
            GetProductsByNameOutputGateway getProductsByNameOutputGateway) {
        return new GetProductsByNameUsecase(getProductsByNameOutputGateway);
    }

}
