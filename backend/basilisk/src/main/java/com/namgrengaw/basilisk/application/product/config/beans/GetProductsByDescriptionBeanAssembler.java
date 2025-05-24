package com.namgrengaw.basilisk.application.product.config.beans;

import com.namgrengaw.basilisk.application.product.core.ports.input.GetProductsByDescriptionInputGateway;
import com.namgrengaw.basilisk.application.product.core.ports.output.GetProductsByDescriptionOutputGateway;
import com.namgrengaw.basilisk.application.product.core.usecases.GetProductsByDescriptionUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetProductsByDescriptionBeanAssembler {

    @Bean
    public GetProductsByDescriptionInputGateway getProductsByDescriptionUsecaseAssemble(GetProductsByDescriptionOutputGateway getProductsByDescriptionOutputGateway) {
        return new GetProductsByDescriptionUsecase(getProductsByDescriptionOutputGateway);
    }

}
