package es.plexus.hopes.hopesback.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class SpringFoxConfigurationTest {

    @InjectMocks
    private SpringFoxConfiguration springFoxConfiguration;

    /**
     * Ui config should create ui api configuration.
     */
    @Test
    public void uiConfigShouldCreateUIApiConfiguration() {

        final UiConfiguration uiConfiguration = this.springFoxConfiguration.uiConfig();

        Assert.assertNotNull(uiConfiguration);
    }

    /**
     * Configuration api should create api configuration.
     */
    @Test
    public void configurationApiShouldCreateApiConfiguration() {

        final Docket docket = this.springFoxConfiguration.applicationApi();

        Assert.assertNotNull(docket);
    }
}
