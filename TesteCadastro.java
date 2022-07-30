import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
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
	public void testeDePreenchimentoeconfirmacao() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Wesley Carlos");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Ferreira Brito");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertEquals("Nome: Wesley Carlos", driver.findElement(By.id("descNome")).getText());
		Assert.assertEquals("Sobrenome: Ferreira Brito", driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Frango", driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());

		

	}

	@Test
	public void deveValidarNomeObrigatorio() {
		

		// não é necessário cadastrar nome pois o primeiro cadastro requerido é o nome
		// proximo teste valida o preenchimento do Nome e a ausencia de sobrenome
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		

	}

	@Test
	public void deveValidarSobreNomeObrigatorio() {
		
		//cadastra nome e verifica a ausencia de sobrenome
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
	}
	@Test
	public void deveValidarSexoObrigatorio() {
		

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		driver.findElement(By.id("elementosForm:cadastrar")).click();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		

	}
	@Test
	public void deveValidarSeVegetariano() {
		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		
		
	}
	@Test
	public void deveValidarSeFazEsporte() {
		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		//comida favorita basta uma para validar 
		//driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		
		//instacia "new Select" será substituida por "Select Combo"
		//new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		//new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		//alert.accept();
		
		
	}
}
