package org.guerrer0jaguar.inventory.merger.integration.provider.b;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "providerB", url = "${feign.client.config.providerB.url}")
public interface EndpointB {
    
    @RequestMapping( method = RequestMethod.GET, value = "/products")        
    ProductBResponseWrapper getProducts(@RequestHeader("User-Agent") String agent);
}