package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByStatusInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByStatusOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.GetProductsByStatusUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductsByStatusBeanAssembler {

    @Bean
    public GetProductsByStatusInputGateway getProductsByStatusInputGatewayAssemble(
            GetProductsByStatusOutputGateway getProductsByStatusOutputGateway) {
        return new GetProductsByStatusUsecase(getProductsByStatusOutputGateway);
    }

}
