package com.htwy.springcloud.controller;

import com.htwy.springcloud.entity.Dept;
import com.htwy.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    //private static final String URL_8001 = "http://localhost:8001";
    //整合进eureka中，通过服务名称获取服务，用于ribbon负载均衡
    private static final String URL_8001 = "http://SERVICECLOUD-DEPT";

    @Autowired
    private DeptClientService deptClientService;


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        //原负载 调用以Ribbon + restTemplate
        //return restTemplate.postForObject(URL_8001 + "/dept/add", dept, Boolean.class);
        //现以Feign直接找接口，符合面向接口开发的形式；Fegin中有有集成Ribbon
        return deptClientService.add(dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        //return restTemplate.getForObject(URL_8001 + "/dept/get/" + id, Dept.class);
        return  deptClientService.get(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        //return restTemplate.getForObject(URL_8001 + "/dept/list", List.class);
        return deptClientService.list();
    }

    //测试@EnableDiscoveryClient,消费端可以调用服务发现 
    @RequestMapping(value = "/consumer/dept/discovery")
    public Object discovery() {
        return restTemplate.getForObject(URL_8001 + "/dept/discovery", Object.class);
    }
}
