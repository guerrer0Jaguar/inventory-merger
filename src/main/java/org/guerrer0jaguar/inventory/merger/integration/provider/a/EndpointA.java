package org.guerrer0jaguar.inventory.merger.integration.provider.a;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "providerA", url = "${feign.client.config.providerA.url}")
public interface EndpointA {

    @RequestMapping( method = RequestMethod.GET, value = "/products")
    List<ProductA> getProducts(@RequestHeader("User-Agent") String agent);
}