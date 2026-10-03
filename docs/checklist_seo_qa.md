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