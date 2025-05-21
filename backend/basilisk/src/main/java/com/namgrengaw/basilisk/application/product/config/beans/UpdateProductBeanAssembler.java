package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.UpdateProductInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.UpdateProductOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.UpdateProductUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateProductBeanAssembler {

    @Bean
    public UpdateProductInputGateway updateProductInputGatewayAssemble(
            UpdateProductOutputGateway updateProductOutputGateway,
            GetProductByIdOutputGateway getProductByIdOutputGateway
    ) {
        return new UpdateProductUsecase(updateProductOutputGateway, getProductByIdOutputGateway);
    }

}
