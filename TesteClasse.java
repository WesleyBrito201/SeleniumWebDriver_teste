import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteClasse {
	
	private WebDriver driver;

	@Before
	public void inicializa() {

		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 950));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

	}

	@After

	public void finaliza() {
		driver.quit();
	}


	@Test
	public void deveInteragirComAlertSimples() {
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();

		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);

		

	}

	@Test
	public void deveInteragirComAlertConfirm() {
		

		driver.findElement(By.id("confirm")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();

		driver.findElement(By.id("confirm")).click();
		alerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.dismiss();

		

	}

	@Test
	public void deveInteragirComAlertaPrompt() {
		
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		//Assert.assertEquals(":D", alerta.getText());
		alerta.accept();

		

	}

}

/*
 * @Test public void deveInteragirComAlertPromptDismiss() { WebDriver driver =
 * new FirefoxDriver(); driver.manage().window().setSize(new Dimension(900,
 * 950)); driver.get("file:///" + System.getProperty("user.dir") +
 * "/src/main/resources/componentes.html"); // Rota cancelando os alerta no
 * botal prompt// driver.findElement(By.id("prompt")).click(); Alert alerta =
 * driver.switchTo().alert(); Assert.assertEquals("Digite um numero",
 * alerta.getText()); alerta.dismiss(); Assert.assertEquals("Era null?",
 * alerta.getText()); alerta.dismiss(); Assert.assertEquals(":(",
 * alerta.getText()); alerta.accept();
 * 
 * driver.quit();
 * 
 * }
 */
