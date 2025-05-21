package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.DeleteProductByIdInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.DeleteProductByIdOutputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductByIdOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.DeleteProductByIdUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteProductByIdBeanAssembler {

    @Bean
    public DeleteProductByIdInputGateway deleteProductByIdInputGatewayAssemble(
            DeleteProductByIdOutputGateway deleteProductByIdOutputGateway,
            GetProductByIdOutputGateway getProductByIdOutputGateway
    ) {
        return new DeleteProductByIdUsecase(deleteProductByIdOutputGateway, getProductByIdOutputGateway);
    }

}
