# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/seoqa/runners/RunSEOChecks.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/features/seo_checks.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/seoqa/steps/SEOSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/data/test_urls.csv` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/seoqa/SEOCheckTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `RunCucumberTest.java`
- `src/test/java/com/pragma/seoqa/SEOCheckTest.java`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/com/pragma/seoqa/steps/SEOSteps.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/seoqa/models/SEOCheckResult.java` — `CheckStatus.getDisplayName`: Se invoca `getDisplayName` sobre `CheckStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.12`: net.serenity-bdd:serenity-bom declara la version 4.1.12, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Seniority Senior

### Brecha de conocimiento
Realiza validaciones mínimas de SEO-QA y socializa los resultados en los proyectos, apoyado en el checklist diseñado para este propósito

### Misión / candidato
Candidato con experiencia senior en automatización de pruebas.

### Reto
- Tema: SEO Testing Básico
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de Validaciones Básicas de SEO-QA
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración y Checklist de SEO-QA — objetivo: Identificar las métricas clave de SEO y crear un checklist para validaciones mínimas. — entregable (NO resolver): Checklist de SEO-QA con métricas y criterios de aceptación.
- Fase 2: Implementación de Validaciones en Proyecto — objetivo: Aplicar el checklist de SEO-QA en un proyecto real y documentar los resultados. — entregable (NO resolver): Reporte de resultados de las validaciones de SEO-QA en el proyecto seleccionado.
- Fase 3: Socialización de Resultados y Mejoras — objetivo: Socializar los resultados con el equipo de desarrollo y proponer mejoras basadas en los hallazgos. — entregable (NO resolver): Presentación y documentación de los resultados de las validaciones de SEO-QA y las mejoras implementadas.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.pragma</groupId>
    <artifactId>seoqa</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <serenity.version>4.1.12</serenity.version>
        <cucumber.version>7.15.0</cucumber.version>
        <selenium.version>4.18.1</selenium.version>
        <junit.version>5.10.0</junit.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-bom</artifactId>
                <version>${serenity.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Serenity BDD -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-junit5</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-webdriver</artifactId>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>${cucumber.version}</version>
        </dependency>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Rest Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.3.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <testFailureIgnore>false</testFailureIgnore>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/seoqa/models/SEOCheckResult.java ===
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

// === ARCHIVO: src/test/java/com/pragma/seoqa/runners/RunCucumberTest.java ===
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

// === ARCHIVO: src/main/java/com/pragma/seoqa/checklist/SEOChecklist.java ===
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

// === ARCHIVO: src/main/java/com/pragma/seoqa/validators/SEOValidator.java ===
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

// === ARCHIVO: src/main/java/com/pragma/seoqa/tasks/AnalyzeSEO.java ===
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

// === ARCHIVO: src/main/java/com/pragma/seoqa/questions/SEOCheckResults.java ===
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

// === ARCHIVO: src/test/java/com/pragma/seoqa/runners/RunSEOChecks.java ===
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

// === ARCHIVO: src/test/resources/features/seo_checks.feature ===
Feature: Validacion de metricas SEO-QA en paginas web

  @seo
  Scenario: Validar titulo y descripcion meta en pagina de inicio
    Given que el usuario tiene acceso a la pagina "https://example.com"
    When realiza el analisis SEO de la pagina
    Then el sistema debe verificar que el titulo no este vacio
    And debe verificar que la descripcion meta tenga entre 50 y 160 caracteres
    And debe reportar el resultado con el estado PASS

  @seo
  Scenario: Validar estructura de encabezados H1
    Given que el usuario tiene acceso a la pagina "https://example.com/blog"
    When realiza el analisis SEO de la pagina
    Then el sistema debe verificar que existe exactamente un H1
    And debe verificar que el H1 contiene palabras clave relevantes
    And debe reportar el resultado con el estado PASS o FAIL

  @seo
  Scenario: Validar velocidad de carga
    Given que el usuario tiene acceso a la pagina "https://example.com"
    When realiza el analisis SEO de la pagina
    Then el sistema debe medir el tiempo de carga
    And debe verificar que sea menor a 3 segundos
    And debe reportar el resultado con el estado PASS o WARNING

  @seo
  Scenario: Validar presencia de atributos alt en imagenes
    Given que el usuario tiene acceso a la pagina "https://example.com/productos"
    When realiza el analisis SEO de la pagina
    Then el sistema debe identificar todas las imagenes
    And debe verificar que todas tengan atributo alt
    And debe reportar el porcentaje de cumplimiento

  @seo
  Scenario: Validar compatibilidad con dispositivos moviles
    Given que el usuario tiene acceso a la pagina "https://example.com"
    When realiza el analisis SEO de la pagina
    Then el sistema debe verificar el viewport meta
    And debe verificar que los tactiles targets tengan tamano adequado
    And debe reportar el resultado con el estado PASS o FAIL

// === ARCHIVO: src/test/java/com/pragma/seoqa/steps/SEOSteps.java ===
package com.pragma.seoqa.steps;

import com.pragma.seoqa.questions.SEOCheckResults;
import com.pragma.seoqa.tasks.AnalyzeSEO;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.TheAnswer;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.*;

public class SEOSteps {

    @Managed(driver = "chrome")
    private WebDriver driver;

    private Actor tester = Actor.named("Tester de SEO");

    @Given("que el usuario tiene acceso a la pagina {string}")
    public void queElUsuarioTieneAccesoALaPagina(String url) {
        tester.can(BrowseTheWeb.with(driver));
        tester.attemptsTo(
            AnalyzeSEO.onPage(url)
        );
    }

    @When("realiza el analisis SEO de la pagina")
    public void realizaElAnalisisSEODelaPagina() {
        tester.attemptsTo(
            AnalyzeSEO.forCurrentPage()
        );
    }

    @Then("el sistema debe verificar que el titulo no este vacio")
    public void elSistemaDebeVerificarQueElTituloNoEsteVacio() {
        tester.should(
            seeThat("Resultado de verificacion de titulo",
                TheAnswer.valueOf(SEOCheckResults.titlePresent()),
                is(true))
        );
    }

    @And("debe verificar que la descripcion meta tenga entre {int} y {int} caracteres")
    public void debeVerificarQueLaDescripcionMetaTengaEntreYCaracteres(int min, int max) {
        tester.should(
            seeThat("Longitud de descripcion meta",
                TheAnswer.valueOf(SEOCheckResults.metaDescriptionLength()),
                allOf(greaterThanOrEqualTo(min), lessThanOrEqualTo(max))))
        );
    }

    @And("debe reportar el resultado con el estado PASS")
    public void debeReportarElResultadoConElEstadoPASS() {
        tester.should(
            seeThat("Estado del resultado",
                TheAnswer.valueOf(SEOCheckResults.lastStatus()),
                equalTo("PASS"))
        );
    }

    @And("debe verificar que existe exactamente un H1")
    public void debeVerificarQueExisteExactamenteUnH1() {
        tester.should(
            seeThat("Cantidad de H1",
                TheAnswer.valueOf(SEOCheckResults.h1Count()),
                equalTo(1))
        );
    }

    @And("debe verificar que el H1 contiene palabras clave relevantes")
    public void debeVerificarQueElH1ContienePalabrasClaveRelevantes() {
        tester.should(
            seeThat("H1 contiene keywords",
                TheAnswer.valueOf(SEOCheckResults.h1ContainsKeywords()),
                is(true))
        );
    }

    @And("debe reportar el resultado con el estado PASS o FAIL")
    public void debeReportarElResultadoConElEstadoPASSOFAIL() {
        tester.should(
            seeThat("Estado del resultado",
                TheAnswer.valueOf(SEOCheckResults.lastStatus()),
                anyOf(equalTo("PASS"), equalTo("FAIL")))
        );
    }

    @Then("el sistema debe medir el tiempo de carga")
    public void elSistemaDebeMedirElTiempoDeCarga() {
        tester.attemptsTo(
            AnalyzeSEO.measureLoadTime()
        );
    }

    @And("debe verificar que sea menor a {int} segundos")
    public void debeVerificarQueSeaMenorASegundos(int seconds) {
        tester.should(
            seeThat("Tiempo de carga",
                TheAnswer.valueOf(SEOCheckResults.loadTime()),
                lessThan(seconds))
        );
    }

    @And("debe reportar el resultado con el estado PASS o WARNING")
    public void debeReportarElResultadoConElEstadoPASSOWARNING() {
        tester.should(
            seeThat("Estado del resultado",
                TheAnswer.valueOf(SEOCheckResults.lastStatus()),
                anyOf(equalTo("PASS"), equalTo("WARNING")))
        );
    }

    @Then("el sistema debe identificar todas las imagenes")
    public void elSistemaDebeIdentificarTodasLasImagenes() {
        tester.attemptsTo(
            AnalyzeSEO.findAllImages()
        );
    }

    @And("debe verificar que todas tengan atributo alt")
    public void debeVerificarQueTodasTenganAtributoAlt() {
        tester.should(
            seeThat("Porcentaje de imagenes con alt",
                TheAnswer.valueOf(SEOCheckResults.imagesWithAltPercentage()),
                equalTo(100))
        );
    }

    @And("debe reportar el porcentaje de cumplimiento")
    public void debeReportarElPorcentajeDeCumplimiento() {
        Serenity.reportThat("Cumplimiento de atributos alt",
            () -> System.out.println("Reporte: " + SEOCheckResults.imagesWithAltPercentage())
        );
    }

    @Then("el sistema debe verificar el viewport meta")
    public void elSistemaDebeVerificarElViewportMeta() {
        tester.should(
            seeThat("Viewport presente",
                TheAnswer.valueOf(SEOCheckResults.viewportPresent()),
                is(true))
        );
    }

    @And("debe verificar que los tactiles targets tengan tamano adequado")
    public void debeVerificarQueLosTactilesTargetsTenganTamanoAdecuado() {
        tester.should(
            seeThat("Tactile targets adecuados",
                TheAnswer.valueOf(SEOCheckResults.tactileTargetsAdequate()),
                is(true))
        );
    }

// === ARCHIVO: src/test/resources/data/test_urls.csv ===
url,expected_title,expected_description
https://example.com,Example Domain,This domain is for use in illustrative examples in documents.
https://www.google.com,Google,Search the world's information, including webpages, images and more.
https://www.wikipedia.org,Wikipedia,Wikipedia is a free online encyclopedia, created and edited by volunteers around the world.
https://www.github.com,GitHub: Let’s build from here,GitHub is where over 100 million developers shape the future of software.
https://www.stackoverflow.com,Stack Overflow - Where Developers Learn,Share & Build Careers

// === ARCHIVO: docs/checklist_seo_qa.md ===
# Checklist de SEO-QA: Validaciones Básicas

## 1. Metadatos y Etiquetas de Página

### 1.1 Title Tag
- **Criterio de aceptación**: El title tag debe existir, tener entre 50-60 caracteres y ser único por página.
- **Validación**: Verificar que el elemento `<title>` esté presente en el `<head>` del HTML.
- **Severidad**: Crítica
- **Impacto SEO**: El title es el factor de ranking más importante para Google.

### 1.2 Meta Description
- **Criterio de aceptación**: La meta description debe existir, tener entre 150-160 caracteres y resumir el contenido de la página.
- **Validación**: Verificar que la etiqueta `<meta name="description">` esté presente.
- **Severidad**: Alta
- **Impacto SEO**: Influye directamente en el CTR desde los resultados de búsqueda.

### 1.3 Canonical URL
- **Criterio de aceptación**: Si existe contenido duplicado, debe haber una etiqueta canonical pointing a la versión preferida.
- **Validación**: Verificar `<link rel="canonical" href="...">` en el `<head>`.
- **Severidad**: Media
- **Impacto SEO**: Previene problemas de contenido duplicado.

---

## 2. Estructura de Encabezados (Heading Tags)

### 2.1 H1 Único
- **Criterio de aceptación**: Cada página debe tener exactamente un `<h1>` que describa el contenido principal.
- **Validación**: Contar elementos `<h1>` en el DOM (debe ser exactamente 1).
- **Severidad**: Crítica
- **Impacto SEO**: El H1 transmite la thématique principal a los motores de búsqueda.

### 2.2 Jerarquía de Encabezados
- **Criterio de aceptación**: Los encabezados deben seguir una jerarquía lógica (H1 → H2 → H3) sin saltos.
- **Validación**: Verificar que no existan saltos de nivel (ej. H1 → H3 sin H2).
- **Severidad**: Media
- **Impacto SEO**: Facilita la comprensión de la estructura del contenido.

### 2.3 Contenido en Encabezados
- **Criterio de aceptación**: Los encabezados deben contener palabras clave relevantes.
- **Validación**: Analizar el texto de cada encabezado para verificar presencia de keywords del dominio.
- **Severidad**: Baja
- **Impacto SEO**: Refuerza la relevancia temática.

---

## 3. Rendimiento y Velocidad de Carga

### 3.1 Time to First Byte (TTFB)
- **Criterio de aceptación**: TTFB menor a 600ms.
- **Validación**: Medir mediante herramientas de developer tools o Lighthouse.
- **Severidad**: Alta
- **Impacto UX/SEO**: Los sitios lentos tienen mayor tasa de rebote.

### 3.2 Largest Contentful Paint (LCP)
- **Criterio de aceptación**: LCP menor a 2.5 segundos.
- **Validación**: Métrica capturada mediante Core Web Vitals.
- **Severidad**: Alta
- **Impacto SEO**: Factor de ranking desde 2021.

### 3.3 Cumulative Layout Shift (CLS)
- **Criterio de aceptación**: CLS menor a 0.1.
- **Validación**: Métrica de estabilidad visual.
- **Severidad**: Alta
- **Impacto UX/SEO**: Afecta la experiencia del usuario y el ranking.

### 3.4 Total Blocking Time (TBT)
- **Criterio de aceptación**: TBT menor a 200ms.
- **Validación**: Medir interactividad durante la carga.
- **Severidad**: Media
- **Impacto SEO**: Indica problemas de JavaScript que bloquean el renderizado.

---

## 4. Imágenes y Recursos Multimedia

### 4.1 Atributos Alt en Imágenes
- **Criterio de aceptación**: Todas las imágenes deben tener atributo `alt` no vacío.
- **Validación**: Contar imágenes sin atributo `alt` o con `alt=""`.
- **Severidad**: Crítica
- **Impacto SEO/Accesibilidad**: Imprescindible para accesibilidad y indexing de imágenes.

### 4.2 Dimensiones en Imágenes
- **Criterio de aceptación**: Las imágenes deben especificar width y height para evitar CLS.
- **Validación**: Verificar presencia de atributos dimensionales en etiquetas `<img>`.
- **Severidad**: Media
- **Impacto UX/SEO**: Reduce el layout shift durante la carga.

### 4.3 Formato Moderno
- **Criterio de aceptación**: Preferir formatos WebP o AVIF sobre JPEG/PNG para imágenes grandes.
- **Validación**: Analizar las cabeceras de contenido de las imágenes.
- **Severidad**: Baja
- **Impacto SEO**: Optimiza el tiempo de carga.

---

## 5. Enlaces y Navegación

### 5.1 Enlaces con Texto Descriptivo
- **Criterio de aceptación**: Los hipervínculos deben tener texto descriptivo (no "clic aquí" o "leer más").
- **Validación**: Identificar anchor texts genéricos o vacíos.
- **Severidad**: Alta
- **Impacto SEO**: El texto del enlace transmite contexto sobre la página destino.

### 5.2 Enlaces Rotos
- **Criterio de aceptación**: No deben existir enlaces que devuelvan errores 4xx o 5xx.
- **Validación**: Verificar estado HTTP de todos los enlaces.
- **Severidad**: Crítica
- **Impacto UX/SEO**: Afecta la experiencia del usuario y el crawl budget.

### 5.3 Ratio de Enlaces Internos/Externos
- **Criterio de aceptación**: Balance adecuado entre enlaces internos y externos relevantes.
- **Validación**: Contar y clasificar los enlaces del sitio.
- **Severidad**: Baja
- **Impacto SEO**: Facilita la distribución de link equity.

---

## 6. Datos Estructurados (Schema Markup)

### 6.1 Presencia de Schema
- **Criterio de búsqueda**: La página debe incluir al menos un tipo de dato estructurado.
- **Validación**: Buscar etiquetas `<script type="application/ld+json">`.
- **Severidad**: Media
- **Impacto SEO**: Habilita rich snippets en resultados de búsqueda.

### 6.2 Validez del Schema
- **Criterio de aceptación**: Los datos estructurados deben ser válidos según Schema.org.
- **Validación**: Verificar con la herramienta de prueba de datos estructurados de Google.
- **Severidad**: Alta
- **Impacto SEO**: Schema inválido no produce beneficios.

---

## 7. Mobile y Responsive

### 7.1 Viewport Meta
- **Criterio de aceptación**: Presencia de `<meta name="viewport" content="width=device-width, initial-scale=1">`.
- **Validación**: Verificar en el `<head>` del documento.
- **Severidad**: Crítica
- **Impacto SEO**: Google usa mobile-first indexing.

### 7.2 Tamaño de Taps
- **Criterio de aceptación**: Los elementos táctiles deben tener al menos 48x48px.
- **Validación**: Analizar elementos interactivos en viewport móvil.
- **Severidad**: Media
- **Impacto UX**: Afecta la usabilidad en dispositivos móviles.

---

## 8. Accesibilidad (WCAG 2.1)

### 8.1 Contraste de Colores
- **Criterio de aceptación**: Ratio de contraste mínimo de 4.5:1 para texto normal.
- **Validación**: Medir contraste mediante herramientas automatizadas.
- **Severidad**: Alta
- **Impacto UX/SEO**: Afecta la experiencia de usuarios con discapacidad visual.

### 8.2 Navegación por Teclado
- **Criterio de aceptación**: Todos los elementos interactivos deben ser accesibles por teclado.
- **Validación**: Verificar presencia de atributos tabindex y manejo de focus.
- **Severidad**: Alta
- **Impacto SEO/UX**: Requisito legal y mejora la accesibilidad.

### 8.3 ARIA Labels
- **Criterio de aceptación**: Elementos interactivos sin texto visible deben tener atributos ARIA.
- **Validación**: Verificar labels en iconos y botones.
- **Severidad**: Media
- **Impacto SEO/Accesibilidad**: Proporciona contexto a tecnologías asistivas.

---

## 9. URL y Arquitectura del Sitio

### 9.1 URLs Amigables
- **Criterio de aceptación**: Las URLs deben ser legibles, descriptivas y usar guiones como separadores.
- **Validación**: Analizar estructura de URLs (evitar parámetros complejos, IDs largos).
- **Severidad**: Media
- **Impacto SEO**: Las URLs claras mejoran el CTR y la comprensión del contenido.

### 9.2 HTTPS
- **Criterio de aceptación**: El sitio debe servir todo el contenido por HTTPS.
- **Validación**: Verificar protocolo de todas las URLs.
- **Severidad**: Crítica
- **Impacto SEO**: Factor de ranking desde 2014.

### 9.3 Archivo robots.txt
- **Criterio de aceptación**: El archivo robots.txt debe existir y ser válido.
- **Validación**: Verificar presencia en la raíz del dominio.
- **Severidad**: Alta
- **Impacto SEO**: Controla el acceso de los crawlers.

### 9.4 Sitemap.xml
- **Criterio de aceptación**: El sitemap debe existir y estar actualizado.
- **Validación**: Verificar presencia y número de URLs indexadas.
- **Severidad**: Media
- **Impacto SEO**: Facilita el descubrimiento de páginas.

---

## 10. Contenido y Semántica

### 10.1 Longitud del Contenido
- **Criterio de aceptación**: Contenido sustancial (mínimo 300 palabras para páginas de contenido).
- **Validación**: Contar palabras en el cuerpo del contenido principal.
- **Severidad**: Media
- **Impacto SEO**: Contenido breve puede no ser suficientemente detallado.

### 10.2 HTML Semántico
- **Criterio de aceptación**: Uso correcto de elementos semánticos (`<main>`, `<article>`, `<section>`, `<nav>`).
- **Validación**: Verificar presencia de estructura semántica en el DOM.
- **Severidad**: Baja
- **Impacto SEO**: Mejora la interpretación del contenido por los crawlers.

---

## Matriz de Priorización

| Categoría | Métrica | Severidad | Tipo de Validación |
|-----------|---------|-----------|-------------------|
| Metadatos | Title | Crítica | Automática |
| Metadatos | Meta Description | Alta | Automática |
| Encabezados | H1 Único | Crítica | Automática |
| Rendimiento | LCP | Alta | Automática |
| Imágenes | Alt Text | Crítica | Automática |
| Enlaces | Enlaces Rotos | Crítica | Automática |
| Mobile | Viewport | Crítica | Automática |
| Seguridad | HTTPS | Crítica | Automática |
| Schema | Datos Estructurados | Media | Semiautomática |
| Accesibilidad | Contraste | Alta | Automática |

---

## Referencias

- [Google Search Central - SEO Starter Guide](https://developers.google.com/search/docs/fundamentals/seo-starter-guide)
- [Core Web Vitals](https://web.dev/vitals/)
- [Schema.org](https://schema.org/)
- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)

// === ARCHIVO: docs/reporte_resultados.md ===
# Reporte de Resultados: Validaciones SEO-QA

## Información del Proyecto

| Campo | Detalle |
|-------|---------|
| **Nombre del Proyecto** | E-commerce Pragma Store |
| **URL Evaluada** | https://tienda.pragma.com.co |
| **Fecha de Evaluación** | 2024-01-15 |
| **Auditor** | Equipo de Calidad de Software |
| **Versión del Reporte** | 1.0 |

---

## Resumen Ejecutivo

Se realizó una evaluación de SEO-QA sobre la página principal del proyecto E-commerce Pragma Store. El análisis automatizado cubrió 10 categorías con un total de 25 métricas. Se identificaron **3 hallazgos críticos**, **5 hallazgos de alta severidad** y **7 hallazgos de severidad media/baja**.

**Puntuación General SEO**: 68/100

**Estado**: Requiere atención inmediata en elementos críticos antes de lanzamiento a producción.

---

## Resultados por Categoría

### 1. Metadatos y Etiquetas de Página

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| Title Tag | ✅ PASS | 100% | Title presente con 54 caracteres: "Tienda Online - Pragma Store | Productos de Calidad" |
| Meta Description | ⚠️ WARNING | 60% | Description presente con 142 caracteres, ligeramente corta del rango óptimo (150-160). |
| Canonical URL | ❌ FAIL | 0% | No se detectó etiqueta canonical en la página principal. |

**Hallazgo Crítico #1**: Ausencia de etiqueta canonical en la página principal. Esto puede causar problemas de contenido duplicado si la página es accesible por múltiples URLs (con y sin www, con parámetros de sesión).

---

### 2. Estructura de Encabezados

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| H1 Único | ✅ PASS | 100% | Página contiene exactamente 1 elemento H1. |
| Jerarquía | ✅ PASS | 100% | Secuencia correcta: H1 → H2 → H3 sin saltos. |
| Keywords en Headers | ✅ PASS | 100% | Encabezados contienen palabras clave relevantes del dominio. |

---

### 3. Rendimiento y Velocidad

| Métrica | Umbral | Valor Obtenido | Estado |
|---------|--------|----------------|--------|
| TTFB | < 600ms | 423ms | ✅ PASS |
| LCP | < 2.5s | 3.2s | ❌ FAIL |
| CLS | < 0.1 | 0.08 | ✅ PASS |
| TBT | < 200ms | 145ms | ✅ PASS |

**Hallazgo Crítico #2**: Largest Contentful Paint (LCP) excede el umbral recomendado. El elemento LCP es una imagen del hero banner que mide 1200x600px sin optimización. Tiempo de carga: 3.2s vs 2.5s permitidos.

**Recomendación**: Implementar lazy loading para imágenes below-the-fold, optimizar la imagen del hero a formato WebP y agregar preconnect a dominios de CDN.

---

### 4. Imágenes y Recursos

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| Alt Text | ⚠️ WARNING | 75% | 25 imágenes evaluadas: 18 con alt apropiado, 4 con alt vacío, 3 sin atributo alt. |
| Dimensiones | ✅ PASS | 100% | Todas las imágenes especifican width y height. |
| Formato | ⚠️ WARNING | 70% | 60% de imágenes en JPEG, 40% en WebP. Migrar a WebP/AVIF. |

**Hallazgo de Alta Severidad #1**: 7 imágenes sin texto alternativo apropiado. Afecta tanto a SEO como a accesibilidad WCAG.

**Imágenes afectadas**:
- `/images/banner-promo.jpg` - sin atributo alt
- `/images/icon-facebook.png` - alt="" (vacío)
- `/images/icon-instagram.png` - sin atributo alt
- `/images/producto-005.jpg` - alt="img"

---

### 5. Enlaces y Navegación

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| Texto Descriptivo | ⚠️ WARNING | 65% | 34 enlaces evaluados: 12 con texto genérico ("leer más", "clic aquí"). |
| Enlaces Rotos | ✅ PASS | 100% | Todos los enlaces devuelven 200 OK o son internos. |
| Ratio Internos/Externos | ✅ PASS | 100% | 28 internos, 6 externos - ratio adecuado. |

**Hallazgo de Alta Severidad #2**: 12 enlaces utilizan texto no descriptivo que no aporta contexto SEO.

---

### 6. Datos Estructurados

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| Presencia de Schema | ❌ FAIL | 0% | No se detectó ninguna etiqueta schema.org. |
| Validez | N/A | - | Sin datos estructurados que validar. |

**Hallazgo Crítico #3**: Página sin datos estructurados. No se implementó Schema.org para productos, lo cual impide la aparición de rich snippets en resultados de búsqueda.

---

### 7. Mobile y Responsive

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| Viewport Meta | ✅ PASS | 100% | Etiqueta viewport correctamente configurada. |
| Tamaño de Taps | ⚠️ WARNING | 80% | 2 botones con tamaño menor a 48px en vista móvil. |

**Hallazgo de Media Severidad #1**: Botones de navegación inferior en móvil miden 36x36px (por debajo del umbral de 48px).

---

### 8. Accesibilidad

| Métrica | Umbral | Valor | Estado |
|---------|--------|-------|--------|
| Contraste | > 4.5:1 | 3.8:1 | ❌ FAIL |
| Navegación por Teclado | 100% | 95% | ⚠️ WARNING |
| ARIA Labels | 100% | 70% | ⚠️ WARNING |

**Hallazgo de Alta Severidad #3**: El texto del pie de página y los enlaces de navegación secundaria tienen contraste insuficiente (3.8:1 vs 4.5:1 requeridos).

**Hallazgo de Alta Severidad #4**: Faltan atributos ARIA en 3 iconos interactivos (carrito de compras, búsqueda, menú móvil).

---

### 9. URL y Arquitectura

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| URLs Amigables | ✅ PASS | 100% | Estructura limpia: `/categoria/producto/nombre` |
| HTTPS | ✅ PASS | 100% | Sitio completamente servido por HTTPS. |
| robots.txt | ✅ PASS | 100% | Presente y correctamente configurado. |
| Sitemap | ⚠️ WARNING | 80% | Presente pero con 3 URLs devolviendo 404. |

**Hallazgo de Media Severidad #2**: El sitemap.xml contiene 3 URLs que devuelven error 404 (productos descontinuados sin redirección).

---

### 10. Contenido y Semántica

| Métrica | Estado | Puntuación | Observación |
|---------|--------|------------|-------------|
| Longitud Contenido | ✅ PASS | 100% | Página principal: 1,245 palabras. |
| HTML Semántico | ⚠️ WARNING | 85% | Uso correcto de main/article, pero falta section en algunas áreas. |

---

## Resumen de Hallazgos

### Por Severidad

| Severidad | Cantidad | Acciones Requeridas |
|-----------|----------|---------------------|
| Crítica | 3 | Inmediata (bloqueante para producción) |
| Alta | 5 | Antes de lanzamiento |
| Media | 4 | En siguiente sprint |
| Baja | 3 | Planeación futura |

### Por Categoría

| Categoría | Total Hallazgos |
|-----------|-----------------|
| Metadatos | 1 |
| Rendimiento | 1 |
| Imágenes | 1 |
| Enlaces | 1 |
| Schema | 1 |
| Mobile | 1 |
| Accesibilidad | 2 |
| Sitemap | 1 |

---

## Evidencias Recolectadas

### Capturas de Pantalla

1. **Reporte Lighthouse**: `evidencias/lighthouse-desktop.png` - Puntuación de rendimiento: 72/100
2. **Auditoría de Imágenes**: `evidencias/images-audit.xlsx` - Listado completo de 25 imágenes con estado
3. **Mapa de Enlaces**: `evidencias/links-map.csv` - 34 enlaces categorizados
4. **Reporte Accessibility**: `evidencias/accessibility-report.pdf` - Detalle de problemas WCAG

### Logs de Ejecución

```
[INFO] SEO Check started: https://tienda.pragma.com.co
[INFO] Checking metadata... PASS
[INFO] Checking headings... PASS
[INFO] Checking performance metrics... FAIL (LCP: 3.2s > 2.5s)
[INFO] Checking images... WARNING (7 images without proper alt)
[INFO] Checking links... WARNING (12 non-descriptive anchors)
[INFO] Checking schema.org... FAIL (no schema detected)
[INFO] Checking accessibility... FAIL (contrast ratio: 3.8:1)
[INFO] SEO Check completed. Score: 68/100
```

### Herramientas Utilizadas

- **Lighthouse** versión 11.5.0 - Métricas de rendimiento y accesibilidad
- **WAVE** - Evaluación de accesibilidad
- **Google Rich Results Test** - Validación de datos estructurados
- **Screaming Frog** - Auditoría técnica de SEO
- **Serenity BDD** - Automatización de verificaciones

---

## Recomendaciones

### Acciones Inmediatas (Bloqueantes)

1. **Agregar etiqueta canonical**
   - Implementar: `<link rel="canonical" href="https://tienda.pragma.com.co/" />`
   - Prioridad: Crítica
   - Estimación: 1 hora

2. **Optimizar LCP**
   - Convertir imagen hero a WebP
   - Implementar lazy loading
   - Agregar preconnect a CDN
   - Prioridad: Crítica
   - Estimación: 4 horas

3. **Implementar Schema.org**
   - Agregar JSON-LD para Organization y Product
   - Validar con Google Rich Results Test
   - Prioridad: Crítica
   - Estimación: 6 horas

### Acciones para Siguiente Sprint

4. **Corregir texto alternativo en imágenes**
   - Revisar las 7 imágenes afectadas
   - Prioridad: Alta
   - Estimación: 2 horas

5. **Mejorar contraste de colores**
   - Ajustar colores del pie de página
   - Prioridad: Alta
   - Estimación: 1 hora

6. **Agregar ARIA labels**
   - Implementar en iconos interactivos
   - Prioridad: Alta
   - Estimación: 2 horas

### Acciones Planeación Futura

7. Migrar todas las imágenes a formato WebP/AVIF
8. Revisar y actualizar sitemap.xml quarterly
9. Implementar monitoring de Core Web Vitals en producción

---

## Conclusión

El proyecto E-commerce Pragma Store presenta una base sólida de SEO técnico, pero requiere resolver **3 hallazgos críticos** antes de su lanzamiento a producción. La puntuación actual de 68/100 se puede elevar a 85+ resolviendo las acciones inmediatas recomendadas.

La implementación de datos estructurados y la optimización del LCP tendrán el mayor impacto en la visibilidad de búsqueda y la experiencia del usuario respectivamente.

---

## Aprobaciones

| Rol | Nombre | Fecha | Firma |
|-----|--------|-------|-------|
| Líder de QA | [Por definir] | DD/MM/AAAA | _____________ |
| Product Owner | [Por definir] | DD/MM/AAAA | _____________ |
| Tech Lead | [Por definir] | DD/MM/AAAA | _____________ |

---

*Documento generado automáticamente con Serenity BDD y herramientas de SEO-QA.*
*Para consultas: equipo.calidad@pragma.com.co*
```
