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