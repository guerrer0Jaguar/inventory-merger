package org.guerrer0jaguar.inventory.merger.integration.provider.b;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("providerB")
public interface EndpointB {

    @RequestMapping( method = RequestMethod.GET, value = "/products")
    List<ProductB> getProducts();
}