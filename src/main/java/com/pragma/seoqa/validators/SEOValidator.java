package com.pragma.seoqa.validators;

import com.pragma.seoqa.checklist.SEOChecklist;
import com.pragma.seoqa.models.SEOCheckResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Validador dedicado para aplicar las reglas de SEO-QA a los elementos web.
 * Analiza la página y genera resultados para cada métrica del checklist.
 */
public class SEOValidator {

    private final WebDriver driver;
    private final SEOChecklist checklist;

    public SEOValidator(WebDriver driver) {
        this.driver = driver;
        this.checklist = new SEOChecklist();
    }

    public List<SEOCheckResult> validateAll() {
        List<SEOCheckResult> results = new ArrayList<>();
        String currentUrl = driver.getCurrentUrl();

        results.add(validateTitle(currentUrl));
        results.add(validateMetaDescription(currentUrl));
        results.add(validateH1(currentUrl));
        results.add(validateHeadingHierarchy(currentUrl));
        results.add(validateImageAltTexts(currentUrl));
        results.add(validateCanonicalTag(currentUrl));
        results.add(validateRobotsMeta(currentUrl));
        results.add(validateOpenGraphTags(currentUrl));
        results.add(validateSchemaMarkup(currentUrl));
        results.add(validateInternalLinks(currentUrl));

        return results;
    }

    private SEOCheckResult validateTitle(String url) {
        try {
            String title = driver.getTitle();
            if (title == null || title.isBlank()) {
                return SEOCheckResult.fail("title", url, "La página no tiene título");
            }
            if (title.length() < 10 || title.length() > 70) {
                return SEOCheckResult.warning("title", url, 
                    "El título tiene " + title.length() + " caracteres (recomendado: 50-60)", 7);
            }
            return SEOCheckResult.pass("title", url, "Título presente: " + title);
        } catch (Exception e) {
            return SEOCheckResult.fail("title", url, "Error al validar título: " + e.getMessage());
        }
    }

    private SEOCheckResult validateMetaDescription(String url) {
        try {
            WebElement metaDesc = driver.findElement(By.xpath("//meta[@name='description']"));
            String content = metaDesc.getAttribute("content");
            
            if (content == null || content.isBlank()) {
                return SEOCheckResult.fail("meta_description", url, "Meta descripción vacía o ausente");
            }
            if (content.length() < 120 || content.length() > 160) {
                return SEOCheckResult.warning("meta_description", url,
                    "La descripción tiene " + content.length() + " caracteres (recomendado: 150-160)", 7);
            }
            return SEOCheckResult.pass("meta_description", url, "Meta descripción válida con " + content.length() + " caracteres");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return SEOCheckResult.fail("meta_description", url, "Meta descripción no encontrada");
        } catch (Exception e) {
            return SEOCheckResult.fail("meta_description", url, "Error al validar meta descripción: " + e.getMessage());
        }
    }

    private SEOCheckResult validateH1(String url) {
        try {
            List<WebElement> h1Elements = driver.findElements(By.tagName("h1"));
            
            if (h1Elements.isEmpty()) {
                return SEOCheckResult.fail("h1", url, "No se encontró ningún encabezado H1");
            }
            if (h1Elements.size() > 1) {
                return SEOCheckResult.warning("h1", url, 
                    "Se encontraron " + h1Elements.size() + " elementos H1 (se recomienda solo uno)", 10);
            }
            
            String h1Text = h1Elements.get(0).getText();
            if (h1Text.isBlank()) {
                return SEOCheckResult.fail("h1", url, "El H1 está vacío");
            }
            return SEOCheckResult.pass("h1", url, "H1 válido: " + h1Text.substring(0, Math.min(50, h1Text.length())));
        } catch (Exception e) {
            return SEOCheckResult.fail("h1", url, "Error al validar H1: " + e.getMessage());
        }
    }

    private SEOCheckResult validateHeadingHierarchy(String url) {
        try {
            boolean hasH1 = !driver.findElements(By.tagName("h1")).isEmpty();
            boolean hasH2 = !driver.findElements(By.tagName("h2")).isEmpty();
            
            if (!hasH1) {
                return SEOCheckResult.fail("h2_h6", url, "Sin H1 no hay jerarquía de encabezados");
            }
            if (!hasH2) {
                return SEOCheckResult.warning("h2_h6", url, "No se encontraron encabezados H2", 5);
            }
            return SEOCheckResult.pass("h2_h6", url, "Jerarquía de encabezados presente");
        } catch (Exception e) {
            return SEOCheckResult.fail("h2_h6", url, "Error al validar jerarquía: " + e.getMessage());
        }
    }

    private SEOCheckResult validateImageAltTexts(String url) {
        try {
            List<WebElement> images = driver.findElements(By.tagName("img"));
            
            if (images.isEmpty()) {
                return SEOCheckResult.pass("alt_images", url, "No hay imágenes en la página");
            }
            
            long imagesWithoutAlt = images.stream()
                .filter(img -> {
                    String alt = img.getAttribute("alt");
                    String ariaLabel = img.getAttribute("aria-label");
                    return (alt == null || alt.isBlank()) && (ariaLabel == null || ariaLabel.isBlank());
                })
                .count();
            
            if (imagesWithoutAlt > 0) {
                return SEOCheckResult.fail("alt_images", url, 
                    imagesWithoutAlt + " de " + images.size() + " imágenes sin texto alternativo");
            }
            return SEOCheckResult.pass("alt_images", url, "Todas las " + images.size() + " imágenes tienen alt text");
        } catch (Exception e) {
            return SEOCheckResult.fail("alt_images", url, "Error al validar imágenes: " + e.getMessage());
        }
    }

    private SEOCheckResult validateCanonicalTag(String url) {
        try {
            List<WebElement> canonical = driver.findElements(By.xpath("//link[@rel='canonical']"));
            
            if (canonical.isEmpty()) {
                return SEOCheckResult.warning("canonical", url, "Etiqueta canónica no encontrada", 3);
            }
            
            String href = canonical.get(0).getAttribute("href");
            if (href == null || href.isBlank()) {
                return SEOCheckResult.fail("canonical", url, "Etiqueta canónica sin href");
            }
            return SEOCheckResult.pass("canonical", url, "URL canónica: " + href);
        } catch (Exception e) {
            return SEOCheckResult.fail("canonical", url, "Error al validar canónica: " + e.getMessage());
        }
    }

    private SEOCheckResult validateRobotsMeta(String url) {
        try {
            List<WebElement> robots = driver.findElements(By.xpath("//meta[@name='robots']"));
            
            if (robots.isEmpty()) {
                return SEOCheckResult.warning("robots_meta", url, "Meta robots no encontrada (asumiendo index,follow)", 3);
            }
            
            String content = robots.get(0).getAttribute("content");
            return SEOCheckResult.pass("robots_meta", url, "Directiva robots: " + content);
        } catch (Exception e) {
            return SEOCheckResult.fail("robots_meta", url, "Error al validar robots: " + e.getMessage());
        }
    }

    private SEOCheckResult validateOpenGraphTags(String url) {
        try {
            List<WebElement> ogTags = driver.findElements(By.xpath("//meta[starts-with(@property, 'og:')]"));
            
            if (ogTags.isEmpty()) {
                return SEOCheckResult.warning("open_graph", url, "No se encontraron etiquetas Open Graph", 5);
            }
            
            long hasOgTitle = ogTags.stream()
                .anyMatch(tag -> "og:title".equals(tag.getAttribute("property")));
            long hasOgDesc = ogTags.stream()
                .anyMatch(tag -> "og:description".equals(tag.getAttribute("property")));
            long hasOgImage = ogTags.stream()
                .anyMatch(tag -> "og:image".equals(tag.getAttribute("property")));
            
            if (hasOgTitle == 0 || hasOgDesc == 0 || hasOgImage == 0) {
                return SEOCheckResult.warning("open_graph", url, 
                    "Faltan algunas etiquetas OG (title=" + hasOgTitle + ", desc=" + hasOgDesc + ", img=" + hasOgImage + ")", 6);
            }
            return SEOCheckResult.pass("open_graph", url, "Etiquetas OG completas: " + ogTags.size() + " etiquetas");
        } catch (Exception e) {
            return SEOCheckResult.fail("open_graph", url, "Error al validar OG: " + e.getMessage());
        }
    }

    private SEOCheckResult validateSchemaMarkup(String url) {
        try {
            List<WebElement> schemaScripts = driver.findElements(
                By.xpath("//script[@type='application/ld+json']"));
            
            if (schemaScripts.isEmpty()) {
                return SEOCheckResult.warning("schema_markup", url, "No se encontró markup Schema.org", 5);
            }
            
            return SEOCheckResult.pass("schema_markup", url, "Schema.org encontrado: " + schemaScripts.size() + " scripts");
        } catch (Exception e) {
            return SEOCheckResult.fail("schema_markup", url, "Error al validar schema: " + e.getMessage());
        }
    }

    private SEOCheckResult validateInternalLinks(String url) {
        try {
            String domain = new java.net.URL(url).getHost();
            List<WebElement> links = driver.findElements(By.tagName("a"));
            
            if (links.isEmpty()) {
                return SEOCheckResult.fail("internal_links", url, "No se encontraron enlaces en la página");
            }
            
            long internalLinks = links.stream()
                .filter(link -> {
                    String href = link.getAttribute("href");
                    return href != null && href.contains(domain);
                })
                .count();
            
            if (internalLinks == 0) {
                return SEOCheckResult.warning("internal_links", url, "No se encontraron enlaces internos", 5);
            }
            return SEOCheckResult.pass("internal_links", url, "Enlaces internos encontrados: " + internalLinks);
        } catch (Exception e) {
            return SEOCheckResult.fail("internal_links", url, "Error al validar enlaces: " + e.getMessage());
        }
    }

    public SEOChecklist getChecklist() {
        return checklist;
    }
}