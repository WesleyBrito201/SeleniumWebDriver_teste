import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

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
	public void testeTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

	}

	@Test
	public void deveInteragirComTextArea() {

		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("sugestões\nNovo\nNovo\nNovo");
		Assert.assertEquals("sugestões\nNovo\nNovo\nNovo",
				driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

	}

	@Test
	public void deveInteragirComRadiobuton() {

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

	}

	@Test
	public void deveInteragirComCheckbox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

	}

	@Test
	public void deveInteragirComCombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// combo.selectByIndex(2);
		// combo.selectByValue("2grauincomp");
		combo.selectByVisibleText("2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", combo.getFirstSelectedOption().getText());

	}

	@Test
	public void deveVerificarValoresCombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;

			}
		}
		Assert.assertTrue(encontrou);

	}

	@Test
	public void deveVerificarValoresComboMultiplo() {

		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());

	}

	@Test
	public void deveinteragirCombotoes() {

		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void deveinteragirComLinks() {

		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());

	}

	@Test
	public void deveBuscarTextosNaPagina() {

		driver.findElement(By.tagName("body"));
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo
		// de Treinamento"));

		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				driver.findElement(By.className("facilAchar")).getText());

	}
}
