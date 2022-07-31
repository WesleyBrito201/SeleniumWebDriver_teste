import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlerta {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {

		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 950));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);

	}

	@After

	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveInteragirComAlertSimples() {

		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		dsl.escreve("elementosForm:nome", texto);

	}

	@Test
	public void deveInteragirComAlertConfirm() {

		dsl.clicarBotao("confirm");
		dsl.confereAlert("Confirm Simples");
		dsl.confereAlert("Confirmado");

		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples",dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());

		

	}

	@Test
	public void deveInteragirComAlertaPromptAceita() {

		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
	}

	
}

