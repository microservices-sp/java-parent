package org.microservices_sp.sample_jersey;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

/**
 * @author Andre Dias (andre.dias@summa.com.br)
 *
 * @since 2016-09-14
 * @version 1.0
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@NoArgsConstructor
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}