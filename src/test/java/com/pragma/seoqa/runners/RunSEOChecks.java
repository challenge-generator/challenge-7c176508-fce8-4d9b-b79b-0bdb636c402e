package com.pragma.seoqa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.junit5.Serenity;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber-report.html"
    },
    features = "src/test/resources/features",
    glue = {"com.pragma.seoqa.steps"},
    tags = "@seo",
    dryRun = false
)
public class RunSEOChecks {
}