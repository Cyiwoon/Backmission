package com.example.demo.runner;


import com.example.demo.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Order(2) // () 숫자가 낮을수록 우선순위 높음    // DatabaseRunner 우선선위가 더 높음
public class MyRunner implements ApplicationRunner {

    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Value("${myboot.fullName}")
    private String fullName;

    @Autowired
    private Environment environment;

    @Autowired
    private Customer customer;


    Logger logger = LoggerFactory.getLogger(MyRunner.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //info
        logger.info("Logger 클래스이름 {}", logger.getClass().getName());
        logger.info("Customer 현재모드= {}", customer.getName() );//,뒤의 값이 {}안에 들어온다.
        logger.info("Port Number = {}", environment.getProperty("local.server.port"));
        logger.info("myboot.name = {}", name);
        logger.info("myboot.age = {}", age);
        logger.info("myboot.fullName = {}", fullName);
        logger.info(">>> Git Test");

        //debug
        logger.debug("VM Argument foo = {}, Program Arguement bar = {}",
                args.containsOption("foo"),
                args.containsOption("bar")
                );
    }
}