package com.pragma.seoqa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Runner de Cucumber con integración Serenity para ejecución de pruebas SEO-QA.
 * Configura el entorno de ejecución y genera reportes HTML con Serenity.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.pragma.seoqa.steps"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    tags = "@seo_check",
    dryRun = false,
    strict = true,
    monochrome = false
)
public class RunCucumberTest {
    
    private static final List<String> REQUIRED_FEATURE_TAGS = List.of(
        "@seo_check",
        "@smoke",
        "@regression"
    );
    
    private static final String REPORT_DIRECTORY = "target/site/serenity";
    private static final String CUCUMBER_REPORT_PATH = "target/cucumber-reports";
    
    public RunCucumberTest() throws InitializationError {
        super();
        validateEnvironment();
    }
    
    private void validateEnvironment() {
        String javaVersion = System.getProperty("java.version");
        if (javaVersion == null || javaVersion.isEmpty()) {
            throw new IllegalStateException("Java version no detectada en el sistema");
        }
        
        String[] versionParts = javaVersion.split("\\.");
        int majorVersion = Integer.parseInt(versionParts[0]);
        
        if (majorVersion < 17) {
            throw new IllegalStateException(
                "Se requiere Java 17 o superior. Versión detectada: " + javaVersion
            );
        }
        
        validateReportDirectories();
    }
    
    private void validateReportDirectories() {
        String userDir = System.getProperty("user.dir");
        if (userDir == null || userDir.isEmpty()) {
            throw new IllegalStateException("Directorio de trabajo no disponible");
        }
        
        List<String> expectedDirs = Arrays.asList(
            "src/test/resources/features",
            "src/test/java/com/pragma/seoqa/steps"
        );
        
        java.io.File baseDir = new java.io.File(userDir);
        for (String dir : expectedDirs) {
            java.io.File checkDir = new java.io.File(baseDir, dir);
            if (!checkDir.exists()) {
                throw new IllegalStateException(
                    "Directorio requerido no encontrado: " + dir
                );
            }
        }
    }
    
    /**
     * Obtiene las etiquetas de features disponibles para ejecución.
     * @return Lista de etiquetas configuradas
     */
    public static List<String> getAvailableTags() {
        return REQUIRED_FEATURE_TAGS;
    }
    
    /**
     * Obtiene la ruta del directorio de reportes de Serenity.
     * @return Ruta del directorio de reportes
     */
    public static String getReportDirectory() {
        return REPORT_DIRECTORY;
    }
    
    /**
     * Obtiene la ruta del directorio de reportes de Cucumber.
     * @return Ruta del directorio de reportes Cucumber
     */
    public static String getCucumberReportPath() {
        return CUCUMBER_REPORT_PATH;
    }
    
    /**
     * Valida que las etiquetas de ejecución estén correctamente configuradas.
     * @param tags Etiquetas a validar
     * @return true si las etiquetas son válidas
     */
    public static boolean validateTags(String tags) {
        if (tags == null || tags.isEmpty()) {
            return false;
        }
        
        return Arrays.stream(tags.split(","))
            .map(String::trim)
            .allMatch(tag -> tag.startsWith("@"));
    }
    
    /**
     * Obtiene las opciones de configuración de Cucumber para este runner.
     * @return Representación en string de las opciones configuradas
     */
    public String getCucumberOptions() {
        return String.format(
            "Features: %s | Glue: %s | Tags: %s | Reportes: %s, %s",
            "src/test/resources/features",
            "com.pragma.seoqa.steps",
            "@seo_check",
            REPORT_DIRECTORY,
            CUCUMBER_REPORT_PATH
        );
    }
}