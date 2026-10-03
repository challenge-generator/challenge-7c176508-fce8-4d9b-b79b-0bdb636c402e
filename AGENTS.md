# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Validaciones Básicas de SEO-QA**.

| | |
|---|---|
| Tema | SEO Testing Básico |
| Nivel | senior-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD 4.1.12 |
| Patron arquitectonico | Page Object Model con Screenplay para mantenibilidad y reutilización |
| Tiempo estimado | 2 semanas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- net.serenity-bdd:serenity-core 4.1.12
- net.serenity-bdd:serenity-junit5 4.1.12
- net.serenity-bdd:serenity-screenplay 4.1.12
- net.serenity-bdd:serenity-screenplay-webdriver 4.1.12
- io.cucumber:cucumber-java 7.15.0
- io.cucumber:cucumber-junit-platform-engine 7.15.0
- org.seleniumhq.selenium:selenium-java 4.18.1
- org.junit.jupiter:junit-jupiter 5.10.0
- org.apache.maven.plugins:maven-surefire-plugin 3.2.5
- net.serenity-bdd:serenity-maven-plugin 4.1.12

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración y Checklist de SEO-QA**: Checklist de SEO-QA con métricas y criterios de aceptación.
- **Fase 2 — Implementación de Validaciones en Proyecto**: Reporte de resultados de las validaciones de SEO-QA en el proyecto seleccionado.
- **Fase 3 — Socialización de Resultados y Mejoras**: Presentación y documentación de los resultados de las validaciones de SEO-QA y las mejoras implementadas.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/seoqa/runners/RunSEOChecks.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/features/seo_checks.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/seoqa/steps/SEOSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/data/test_urls.csv` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/seoqa/SEOCheckTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (2 de 14)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `RunCucumberTest.java`
- [ ] `src/test/java/com/pragma/seoqa/SEOCheckTest.java`

### 2. Referencias colgando (3)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/test/java/com/pragma/seoqa/steps/SEOSteps.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/seoqa/models/SEOCheckResult.java` — `CheckStatus.getDisplayName`
      Se invoca `getDisplayName` sobre `CheckStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.12`
      net.serenity-bdd:serenity-bom declara la version 4.1.12, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (13)

- `pom.xml`
- `src/main/java/com/pragma/seoqa/models/SEOCheckResult.java`
- `src/test/java/com/pragma/seoqa/runners/RunCucumberTest.java`
- `src/main/java/com/pragma/seoqa/checklist/SEOChecklist.java`
- `src/main/java/com/pragma/seoqa/validators/SEOValidator.java`
- `src/main/java/com/pragma/seoqa/tasks/AnalyzeSEO.java`
- `src/main/java/com/pragma/seoqa/questions/SEOCheckResults.java`
- `src/test/java/com/pragma/seoqa/runners/RunSEOChecks.java`
- `src/test/resources/features/seo_checks.feature`
- `src/test/java/com/pragma/seoqa/steps/SEOSteps.java`
- `src/test/resources/data/test_urls.csv`
- `docs/checklist_seo_qa.md`
- `docs/reporte_resultados.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/seoqa`
- `src/main/java/com/pragma/seoqa/checklist`
- `src/main/java/com/pragma/seoqa/tasks`
- `src/main/java/com/pragma/seoqa/questions`
- `src/main/java/com/pragma/seoqa/models`
- `src/main/java/com/pragma/seoqa/validators`
- `src/test/java/com/pragma/seoqa/runners`
- `src/test/java/com/pragma/seoqa/steps`
- `src/test/resources/features`
- `src/test/resources/data`
- `reports`
- `docs`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **Page Object Model con Screenplay para mantenibilidad y reutilización**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Seniority Senior
- Brecha que el reto ataca: Realiza validaciones mínimas de SEO-QA y socializa los resultados en los proyectos, apoyado en el checklist diseñado para este propósito
- Mision: Candidato con experiencia senior en automatización de pruebas.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
