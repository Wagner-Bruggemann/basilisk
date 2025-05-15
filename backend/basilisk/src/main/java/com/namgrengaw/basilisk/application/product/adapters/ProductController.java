package com.namgrengaw.basilisk.application.product.adapters;

import com.namgrengaw.basilisk.application.product.adapters.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/catalog/product")
public class ProductController {

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(List.of(
                new ProductDto(
                        "as23wefiu-vonjvr-wujdnio",
                        "Product Mock A",
                        "Description of a product mock a."),
                new ProductDto(
                        "61svfsdb-oijl-sfdvnib",
                        "Product Mock B",
                        "Description of a product mock b."),
                new ProductDto(
                        "w541dv56vsd-51svbf-61svb",
                        "Product Mock C",
                        "Description of a product mock c.")
            )
        );
    }

}
