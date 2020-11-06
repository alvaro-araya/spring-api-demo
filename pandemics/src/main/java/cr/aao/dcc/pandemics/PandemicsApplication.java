/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
package cr.aao.dcc.pandemics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;

@SpringBootApplication
public class PandemicsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PandemicsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Filter OpenFilter() {
        /* Fix de no session en JPA + GraphQL Lazy Fetch */
        return new OpenEntityManagerInViewFilter();
    }
}
