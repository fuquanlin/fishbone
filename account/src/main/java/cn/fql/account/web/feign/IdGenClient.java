package cn.fql.account.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fuquanlin on 30/11/2017.
 */
@FeignClient(name = "idgen-service")
public interface IdGenClient {

    @RequestMapping(value = "/idgen", method = RequestMethod.GET)
    String getId();
}
