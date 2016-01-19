package test.calculadora.online.stepdefs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import test.calculadora.online.pageObjects.CalculaOperacion;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CalculadoraSteps {

	private static final int TIEMPO_IMPLICITO = 20;
	private static final String URL_CALCULADORA = "http://web2.0calc.es/";
	private int operando1;
	private int operando2;
	private String operacion;

	private List<Integer> datos;

	WebDriver driver;

	@Before
	public void beforeCucumber() {
		configurarDriver();
	}

	@After
	public void afterCucumber() {
		System.out.println("Cerrando driver");
		cerrarDriver();
	}

	@Given("^Accedo a la calculadora online$")
	public void iniciarCalculadoraSimpl() throws Throwable {
		driver.get(URL_CALCULADORA);
	}

	@Given("^Elegimos el numero  \"(.*?)\" y \"(.*?)\"$")
	public void asignaValor(int arg1, int arg2) throws Throwable {
		operando1 = arg1;
		operando2 = arg2;
	}

	@Given("^Queremos operar con los siguientes numeros :$")
	public void queremos_operar_con_los_siguientes_numeros(List<Integer> datos) throws Throwable {

		this.datos = datos;
	}

	@When("^Realizo una \"(.*?)\"$")
	public void defineOperacion(String oper) throws Throwable {
		operacion = oper;
		CalculaOperacion calculadora = new CalculaOperacion(driver);
		calculadora.pulsaBotonNumero(operando1);
		calculadora.pulsaOperando(operacion);
		calculadora.pulsaBotonNumero(operando2);

	}

	@When("^La operacion realizada es \"(.*?)\"$")
	public void la_operacion_realizada_es(String operandoMult) throws Throwable {
		String opMult = operandoMult;
		CalculaOperacion calculadora = new CalculaOperacion(driver);
		for (int i = 0; i < datos.size(); i++) {
			calculadora.pulsaBotonNumero(datos.get(i));

			if (i < (datos.size() - 1)) {
				calculadora.pulsaOperando(opMult);
			}
		}
	}

	@Then("^El resultado es \"(\\d+)\"$")
	public void devuelveResultado(int resultadoEsperado) throws Throwable {
		CalculaOperacion calculadora = new CalculaOperacion(driver);
		calculadora.pulsarIgual();
		Assert.assertEquals(resultadoEsperado, calculadora.getResultado());
	}

	private void configurarDriver() {
		if (driver == null) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(TIEMPO_IMPLICITO, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
	}

	private void cerrarDriver() {
		driver.close();
		driver.quit();
	}
}