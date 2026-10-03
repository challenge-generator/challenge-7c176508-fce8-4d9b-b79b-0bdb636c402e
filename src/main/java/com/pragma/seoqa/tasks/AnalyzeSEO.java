package com.pragma.seoqa.tasks;

import com.pragma.seoqa.models.SEOCheckResult;
import com.pragma.seoqa.validators.SEOValidator;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;

import java.util.List;

/**
 * Tarea Screenplay que orquesta la ejecución de las validaciones de SEO-QA
 * en una página web específica. Implementa el patrón Screenplay para mantener
 * separadas las interacciones de la lógica de negocio.
 */
public class AnalyzeSEO implements Task {

    private final Target urlTarget;
    private String explicitUrl;

    private AnalyzeSEO(Target urlTarget, String explicitUrl) {
        this.urlTarget = urlTarget;
        this.explicitUrl = explicitUrl;
    }

    public static AnalyzeSEO thePage(Target urlTarget) {
        return new AnalyzeSEO(urlTarget, null);
    }

    public static AnalyzeSEO theUrl(String url) {
        return new AnalyzeSEO(null, url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String targetUrl = resolveUrl(actor);
        
        actor.attemptsTo(
            net.serenitybdd.screenplay.actions.Open.url(targetUrl)
        );
        
        WebDriver driver = actor.usingAbilityTo(BrowseTheWeb.class).getDriver();
        
        SEOValidator validator = new SEOValidator(driver);
        List<SEOCheckResult> results = validator.validateAll();
        
        actor.remember("SEO_RESULTS", results);
        actor.remember("ANALYZED_URL", targetUrl);
        
        logResults(actor, results, targetUrl);
    }

    private <T extends Actor> String resolveUrl(T actor) {
        if (explicitUrl != null) {
            return explicitUrl;
        }
        if (urlTarget != null) {
            return urlTarget.resolveFor(actor).getText();
        }
        throw new IllegalStateException("Se debe proporcionar una URL o un Target válido");
    }

    private void logResults(Actor actor, List<SEOCheckResult> results, String url) {
        long passed = results.stream()
            .filter(r -> r.toSummary().contains("PASS"))
            .count();
        long failed = results.stream()
            .filter(r -> r.toSummary().contains("FAIL"))
            .count();
        long warnings = results.stream()
            .filter(r -> r.toSummary().contains("WARNING"))
            .count();
        
        actor.attemptsTo(
            net.serenitybdd.screenplay.actions.Log.message(
                "========================================\n" +
                "SEO QA Analysis Results for: " + url + "\n" +
                "========================================\n" +
                "Checks Passed: " + passed + "\n" +
                "Warnings: " + warnings + "\n" +
                "Failed: " + failed + "\n" +
                "Total Checks: " + results.size() + "\n" +
                "========================================"
            )
        );
    }

    public AnalyzeSEO withExplicitUrl(String url) {
        this.explicitUrl = url;
        return this;
    }
}