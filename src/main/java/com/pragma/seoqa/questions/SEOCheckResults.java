package com.pragma.seoqa.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import com.pragma.seoqa.models.SEOCheckResult;
import com.pragma.seoqa.models.SEOCheckResult.CheckStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Question de Screenplay para recuperar y evaluar los resultados de las validaciones de SEO-QA.
 * Permite al actor verificar el estado de las verificaciones realizadas.
 */
public class SEOCheckResults implements Question<List<SEOCheckResult>> {

    private final String context;

    public SEOCheckResults(String context) {
        this.context = context;
    }

    public static SEOCheckResults of(String context) {
        return new SEOCheckResults(context);
    }

    public static SEOCheckResults lastAnalysis() {
        return new SEOCheckResults("last_analysis");
    }

    @Override
    public List<SEOCheckResult> answeredBy(Actor actor) {
        Map<String, Object> contextData = actor.recalls(context);
        if (contextData == null) {
            throw new IllegalStateException(
                "No se encontraron resultados de SEO para el contexto: " + context
            );
        }

        @SuppressWarnings("unchecked")
        List<SEOCheckResult> results = (List<SEOCheckResult>) contextData.get("results");
        return results != null ? results : List.of();
    }

    public int totalChecks() {
        return this.answeredBy(null).size();
    }

    public int passedChecks() {
        return (int) this.answeredBy(null).stream()
            .filter(r -> r.toSummary().contains("PASS"))
            .count();
    }

    public int failedChecks() {
        return (int) this.answeredBy(null).stream()
            .filter(r -> r.toSummary().contains("FAIL"))
            .count();
    }

    public int warningChecks() {
        return (int) this.answeredBy(null).stream()
            .filter(r -> r.toSummary().contains("WARNING"))
            .count();
    }

    public boolean hasFailures() {
        return failedChecks() > 0;
    }

    public boolean hasWarnings() {
        return warningChecks() > 0;
    }

    public boolean allPassed() {
        return failedChecks() == 0 && warningChecks() == 0;
    }

    public List<SEOCheckResult> getFailedResults() {
        return this.answeredBy(null).stream()
            .filter(r -> r.toSummary().contains("FAIL"))
            .collect(Collectors.toList());
    }

    public List<SEOCheckResult> getPassedResults() {
        return this.answeredBy(null).stream()
            .filter(r -> r.toSummary().contains("PASS"))
            .collect(Collectors.toList());
    }

    public List<SEOCheckResult> getWarningResults() {
        return this.answeredBy(null).stream()
            .filter(r -> r.toSummary().contains("WARNING"))
            .collect(Collectors.toList());
    }

    public Map<CheckStatus, Long> getStatusSummary() {
        return this.answeredBy(null).stream()
            .collect(Collectors.groupingBy(
                result -> {
                    String summary = result.toSummary();
                    if (summary.contains("PASS")) return CheckStatus.PASS;
                    if (summary.contains("FAIL")) return CheckStatus.FAIL;
                    return CheckStatus.WARNING;
                },
                Collectors.counting()
            ));
    }

    public String getSummaryReport() {
        List<SEOCheckResult> results = this.answeredBy(null);
        if (results.isEmpty()) {
            return "No se encontraron resultados de validación SEO.";
        }

        StringBuilder report = new StringBuilder();
        report.append("=== Resumen de Validación SEO ===\n");
        report.append(String.format("Total de verificaciones: %d\n", results.size()));
        report.append(String.format("Pasadas: %d\n", passedChecks()));
        report.append(String.format("Fallidas: %d\n", failedChecks()));
        report.append(String.format("Advertencias: %d\n\n", warningChecks()));

        report.append("=== Detalle de Resultados ===\n");
        for (SEOCheckResult result : results) {
            report.append(result.toSummary()).append("\n");
        }

        return report.toString();
    }

    public boolean containsMetric(String metricName) {
        return this.answeredBy(null).stream()
            .anyMatch(r -> r.toSummary().toLowerCase().contains(metricName.toLowerCase()));
    }

    public SEOCheckResult getFirstFailure() {
        return getFailedResults().isEmpty() ? null : getFailedResults().get(0);
    }
}