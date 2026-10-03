package com.pragma.seoqa.checklist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Define el checklist de SEO-QA con métricas clave y criterios de aceptación.
 * Esta clase centraliza las validaciones mínimas que deben aplicarse a cada página web.
 */
public class SEOChecklist {

    private final Map<String, SEOMetric> metrics;

    public SEOChecklist() {
        this.metrics = new HashMap<>();
        initializeMetrics();
    }

    private void initializeMetrics() {
        metrics.put("title", new SEOMetric(
            "title",
            "Presencia de título",
            "La página debe tener un título en la etiqueta <title>",
            10,
            true
        ));

        metrics.put("meta_description", new SEOMetric(
            "meta_description",
            "Meta descripción",
            "La página debe tener una meta descripción con 150-160 caracteres",
            10,
            true
        ));

        metrics.put("h1", new SEOMetric(
            "h1",
            "Encabezado H1",
            "La página debe tener exactamente un H1 con palabras clave relevantes",
            15,
            true
        ));

        metrics.put("h2_h6", new SEOMetric(
            "h2_h6",
            "Jerarquía de encabezados",
            "Los encabezados deben seguir una jerarquía lógica sin saltos",
            10,
            false
        ));

        metrics.put("alt_images", new SEOMetric(
            "alt_images",
            "Texto alternativo en imágenes",
            "Todas las imágenes deben tener atributo alt",
            15,
            false
        ));

        metrics.put("canonical", new SEOMetric(
            "canonical",
            "Etiqueta canónica",
            "La página debe tener una URL canónica para evitar contenido duplicado",
            5,
            false
        ));

        metrics.put("robots_meta", new SEOMetric(
            "robots_meta",
            "Meta robots",
            "La página debe tener directivas robots apropiadas",
            5,
            false
        ));

        metrics.put("open_graph", new SEOMetric(
            "open_graph",
            "Etiquetas Open Graph",
            "La página debe tener etiquetas OG para redes sociales",
            10,
            false
        ));

        metrics.put("schema_markup", new SEOMetric(
            "schema_markup",
            "Markup Schema.org",
            "La página debe incluir datos estructurados Schema.org",
            10,
            false
        ));

        metrics.put("internal_links", new SEOMetric(
            "internal_links",
            "Enlaces internos",
            "La página debe tener enlaces internos relevantes",
            10,
            false
        ));
    }

    public SEOMetric getMetric(String key) {
        return metrics.get(key);
    }

    public List<SEOMetric> getAllMetrics() {
        return new ArrayList<>(metrics.values());
    }

    public List<SEOMetric> getRequiredMetrics() {
        return metrics.values().stream()
            .filter(SEOMetric::isRequired)
            .toList();
    }

    public int getMaxScore() {
        return metrics.values().stream()
            .mapToInt(SEOMetric::getWeight)
            .sum();
    }

    public record SEOMetric(
        String key,
        String name,
        String description,
        int weight,
        boolean required
    ) {
        public String getAcceptanceCriteria() {
            return String.format("[%s] %s (Peso: %d puntos)%s",
                key, description, weight, required ? " - REQUERIDO" : "");
        }
    }
}