package br.com.edicarlosbarbosa.clickbusapi.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"br.com.edicarlosbarbosa.clickbusapi.bdd.step"},
        features = "src/test/resources/feature"
)
public class IntegrationTest {

}
