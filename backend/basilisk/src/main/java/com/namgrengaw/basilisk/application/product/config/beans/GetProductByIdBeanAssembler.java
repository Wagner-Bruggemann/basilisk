package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductByIdInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.GetProductByIdUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductByIdBeanAssembler {

    @Bean
    public GetProductByIdInputGateway getProductByIdInputGatewayAssemble(GetProductByIdOutputGateway getProductByIdOutputGateway) {
        return new GetProductByIdUsecase(getProductByIdOutputGateway);
    }

}
