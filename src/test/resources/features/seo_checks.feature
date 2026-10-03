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