package test.calculadora.online.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculaOperacion {

	final private WebDriver driver;

	@FindBy(id = "Btn0")
	private WebElement btn0;

	@FindBy(id = "Btn1")
	private WebElement btn1;

	@FindBy(id = "Btn2")
	private WebElement btn2;

	@FindBy(id = "Btn3")
	private WebElement btn3;

	@FindBy(id = "Btn4")
	private WebElement btn4;

	@FindBy(id = "Btn5")
	private WebElement btn5;

	@FindBy(id = "Btn6")
	private WebElement btn6;

	@FindBy(id = "Btn7")
	private WebElement btn7;

	@FindBy(id = "Btn8")
	private WebElement btn8;

	@FindBy(id = "Btn9")
	private WebElement btn9;

	@FindBy(id = "BtnPlus")
	private WebElement bntSuma;

	@FindBy(id = "BtnMinus")
	private WebElement btnResta;

	@FindBy(id = "BtnMult")
	private WebElement btnMultiplicar;

	@FindBy(id = "BtnDiv")
	private WebElement btnDividir;

	@FindBy(id = "BtnCalc")
	private WebElement bntIgual;

	@FindBy(id = "input")
	private WebElement inputResultado;

	public CalculaOperacion(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void pulsaBotonNumero(Integer operando) {

		int posicion = String.valueOf(operando).length();
		int posicion2 = String.valueOf(operando).length();
		int[] arraydigitos = new int[posicion];
		while (operando > 0) {
			int digito = operando % 10;
			arraydigitos[(posicion - 1)] = digito;
			operando = operando / 10;
			posicion--;
		}

		for (int i = 0; i < posicion2; i++) {
			int digitoopera = arraydigitos[i];
			switch (digitoopera) {
			case 0:
				btn0.click();
				break;
			case 1:
				btn1.click();
				break;
			case 2:
				btn2.click();
				break;
			case 3:
				btn3.click();
				break;
			case 4:
				btn4.click();
				break;
			case 5:
				btn5.click();
				break;
			case 6:
				btn6.click();
				break;
			case 7:
				btn7.click();
				break;
			case 8:
				btn8.click();
				break;
			case 9:
				btn9.click();
				break;
			default:
				break;
			}
		}
	}

	public void pulsaOperando(String operando) {
		if (operando.equals("SUMA")) {
			bntSuma.click();
		} else if (operando.equals("RESTA")) {
			btnResta.click();
		} else if (operando.equals("MULTIPLICACION")) {
			btnMultiplicar.click();
		} else if (operando.equals("DIVISION")) {
			btnDividir.click();
		}
	}

	public int getResultado() {
		WebDriverWait waitDriver = new WebDriverWait(driver, 1);
		waitDriver.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return inputResultado.getAttribute("class").equalsIgnoreCase("loading") ? false : true;
			}
		});
		String valor = inputResultado.getAttribute("value");
		return Integer.parseInt(valor);
	}

	public void pulsarIgual() {
		bntIgual.click();

	}

}

  
