package org.guerrer0jaguar.inventory.merger.integration.provider.a;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("providerA")
public interface EndPointA {

    @RequestMapping( method = RequestMethod.GET, value = "/products")
    List<ProductA> getProducts();
}