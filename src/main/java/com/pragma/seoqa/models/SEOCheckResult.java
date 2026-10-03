package com.pragma.seoqa.models;

import java.util.Objects;

/**
 * Modelo de datos para registrar los resultados de cada validación del checklist SEO.
 * Utiliza record para inmutabilidad y simplicidad.
 */
public record SEOCheckResult(
    String metric,
    CheckStatus status,
    String url,
    String observation,
    Integer score
) {
    /**
     * Estados posibles de una validación SEO.
     */
    public enum CheckStatus {
        PASS("Pass"),
        FAIL("Fail"),
        WARNING("Warning"),
        NOT_APPLICABLE("Not Applicable");

        private final String displayName;

        CheckStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    /**
     * Constructor con validaciones básicas para garantizar consistencia de datos.
     */
    public SEOCheckResult {
        Objects.requireNonNull(metric, "La métrica no puede ser nula");
        Objects.requireNonNull(status, "El estado no puede ser nulo");
        Objects.requireNonNull(url, "La URL no puede ser nula");
        if (score != null && (score < 0 || score > 100)) {
            throw new IllegalArgumentException("El puntaje debe estar entre 0 y 100");
        }
    }

    /**
     * Crea un resultado con estado PASS y puntaje máximo.
     */
    public static SEOCheckResult pass(String metric, String url, String observation) {
        return new SEOCheckResult(metric, CheckStatus.PASS, url, observation, 100);
    }

    /**
     * Crea un resultado con estado FAIL y puntaje cero.
     */
    public static SEOCheckResult fail(String metric, String url, String observation) {
        return new SEOCheckResult(metric, CheckStatus.FAIL, url, observation, 0);
    }

    /**
     * Crea un resultado con estado WARNING y puntaje parcial.
     */
    public static SEOCheckResult warning(String metric, String url, String observation, int score) {
        return new SEOCheckResult(metric, CheckStatus.WARNING, url, observation, score);
    }

    /**
     * Devuelve una representación resumida del resultado para logs.
     */
    public String toSummary() {
        return String.format("[%s] %s - %s: %s (Score: %d)",
            status.getDisplayName(),
            metric,
            url,
            observation,
            score != null ? score : 0);
    }
}