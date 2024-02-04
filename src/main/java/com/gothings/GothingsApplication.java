package com.gothings;

import com.gothings.configurations.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class GothingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GothingsApplication.class, args);
    }

}
