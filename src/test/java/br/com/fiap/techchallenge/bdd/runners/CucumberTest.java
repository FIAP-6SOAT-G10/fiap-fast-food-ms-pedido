package br.com.fiap.techchallenge.bdd.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/br/com/fiap/techchallenge/bdd/features",
        glue = {"br.com.fiap.techchallenge.bdd.steps", "br.com.fiap.techchallenge.bdd.config"},
        plugin = {"pretty", "json:target/cucumber.json"}
)
public class CucumberTest {
}
