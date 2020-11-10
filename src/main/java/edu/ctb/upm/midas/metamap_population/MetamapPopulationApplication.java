package edu.ctb.upm.midas.metamap_population;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MetamapPopulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetamapPopulationApplication.class, args);
    }

}
