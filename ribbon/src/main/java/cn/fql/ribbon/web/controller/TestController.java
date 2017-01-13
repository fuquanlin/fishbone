package cn.fql.ribbon.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by fuquanlin on 2017/1/13.
 */
@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return getTest("fishbone");
    }

    public String getTest(String service) {
        return restTemplate.getForEntity("http://fishbone/print", String.class).getBody();

//        List<ServiceInstance> list = client.getInstances(service);
//        if (list != null && list.size() > 0 ) {
//            URI uri = list.get(0).getUri();
//            if (uri !=null ) {
//                return restTemplate.getForObject(uri+"/print",String.class);
//            }
//        }
//        return null;
    }
}
