package com.htwy.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        //Ribbon常用的几种负载方式，默认轮询
        //RoundRobinRule() 轮询
        //RandomRule() 随机
        //RetryRule() 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内会进行重试，获取可用的服务
        //return new RandomRule(); //重新定义为随机
        return new RoundRobinRule_ZDY();//使用自定义的轮询策略，每台服务器要求被访问5次后，才会轮询到下一台服务器
    }
}
