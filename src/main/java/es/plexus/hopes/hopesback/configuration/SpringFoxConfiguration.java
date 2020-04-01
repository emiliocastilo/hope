package es.plexus.hopes.hopesback.configuration;

import es.plexus.hopes.hopesback.HopesBackApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfiguration {

    /**
     * Ui config ui configuration.
     *
     * @return the ui configuration
     */
    @Bean
    UiConfiguration uiConfig() {

        return UiConfigurationBuilder.builder().validatorUrl("validatorUrl").build();
    }

    /**
     * Application api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket applicationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        HopesBackApplication.class.getPackage().getName() + ".controller"))
                .paths(PathSelectors.any())
                .build().pathMapping("/");
    }
}
