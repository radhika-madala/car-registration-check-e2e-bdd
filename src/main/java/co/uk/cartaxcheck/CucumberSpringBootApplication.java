/**
 * PROTECTIVELY MARKED: IL2 - PROTECT
 * Copyright (C) 2020, MOPAC (Mayor's Officer for Policing and Crime), All Rights Reserved
 * <p>
 * Author: Saireddy Surakanti
 */
package co.uk.cartaxcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("co.*")
@SpringBootApplication
public class CucumberSpringBootApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringBootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CucumberSpringBootApplication.class, args);
    }

}
