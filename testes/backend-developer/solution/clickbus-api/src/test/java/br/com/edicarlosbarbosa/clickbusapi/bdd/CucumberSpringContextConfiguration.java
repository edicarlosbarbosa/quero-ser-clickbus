package br.com.edicarlosbarbosa.clickbusapi.bdd;

import br.com.edicarlosbarbosa.clickbusapi.ClickbusApiApplication;
import br.com.edicarlosbarbosa.clickbusapi.helper.TemplateHelper;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ClickbusApiApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringContextConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);

    @LocalServerPort
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
        RestAssured.defaultParser = Parser.JSON;
        FixtureFactoryLoader.loadTemplates(TemplateHelper.TEMPLATE_PACKAGE);
    }
}
