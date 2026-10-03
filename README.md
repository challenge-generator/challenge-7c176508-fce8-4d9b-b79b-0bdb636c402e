# Implementación de Validaciones Básicas de SEO-QA

Como automatizador de pruebas senior en un equipo de calidad de software, tu tarea es realizar validaciones mínimas de SEO-QA y socializar los resultados en los proyectos, apoyado en el checklist diseñado para este propósito. Debes asegurarte de que las páginas web cumplan con los estándares básicos de SEO y que los resultados sean claramente comunicados al equipo de desarrollo.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | SEO Testing Básico |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración y Checklist de SEO-QA

**Objetivo:** Identificar las métricas clave de SEO y crear un checklist para validaciones mínimas.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Investiga y enumera las métricas clave de SEO que deben ser validadas (ej. títulos, descripciones, etiquetas H1, URLs amigables).
- Crea un checklist de SEO-QA que incluya estas métricas y criterios de aceptación para cada una.

**Entregable:** Checklist de SEO-QA con métricas y criterios de aceptación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas de SEO y cómo afectan al rendimiento del sitio web.
- Piensa en cómo comunicar efectivamente los resultados al equipo de desarrollo.

</details>

### Fase 2: Implementación de Validaciones en Proyecto

**Objetivo:** Aplicar el checklist de SEO-QA en un proyecto real y documentar los resultados.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Selecciona un proyecto en el que aplicar las validaciones de SEO-QA.
- Utiliza el checklist creado en la fase anterior para realizar las validaciones.
- Documenta los resultados y cualquier hallazgo significativo.

**Entregable:** Reporte de resultados de las validaciones de SEO-QA en el proyecto seleccionado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo integrar las validaciones de SEO-QA en el proceso de desarrollo continuo.
- Piensa en cómo presentar los hallazgos de manera efectiva al equipo.

</details>

### Fase 3: Socialización de Resultados y Mejoras

**Objetivo:** Socializar los resultados con el equipo de desarrollo y proponer mejoras basadas en los hallazgos.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Presenta los resultados de las validaciones de SEO-QA al equipo de desarrollo.
- Propón mejoras y acciones correctivas basadas en los hallazgos.
- Documenta las acciones tomadas y los resultados de las mejoras implementadas.

**Entregable:** Presentación y documentación de los resultados de las validaciones de SEO-QA y las mejoras implementadas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo involucrar al equipo de desarrollo en el proceso de mejora continua.
- Piensa en cómo medir el impacto de las mejoras implementadas.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las validaciones de SEO-QA y por qué son importantes?
- **paraQueSirve**: ¿Cuál es el propósito de realizar validaciones de SEO-QA en un proyecto de software?
- **comoSeUsa**: ¿Cómo se aplican las validaciones de SEO-QA en un proyecto real?
- **erroresComunes**: ¿Cuáles son los errores comunes que se pueden encontrar al realizar validaciones de SEO-QA?
- **queDecisionesImplica**: ¿Qué decisiones implica la implementación de validaciones de SEO-QA en un proyecto?

## Criterios de Evaluacion

- Creación de un checklist de SEO-QA con métricas y criterios de aceptación.
- Aplicación del checklist en un proyecto real y documentación de los resultados.
- Presentación y propuesta de mejoras basadas en los hallazgos de las validaciones de SEO-QA.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
