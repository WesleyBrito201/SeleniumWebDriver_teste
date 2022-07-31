import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {

		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(900, 950));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After

	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeDePreenchimentoeconfirmacao() {
		page.setNome("Wesley");
		page.setSobrenome("Ferreira Brito");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.setCadastrar();

		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obeterNomeCadastro().startsWith("Nome: Wesley"));
		Assert.assertEquals("Sobrenome: Ferreira Brito", page.obeterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obeterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obeterComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.obeterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obeterEsporteCadastro());

		// Assert.assertEquals("Nome: Wesley Carlos",
		// driver.findElement(By.id("descNome")).getText());
	}

	@Test
	public void deveValidarNomeObrigatorio() {

		// não é necessário cadastrar nome pois o primeiro cadastro requerido é o nome
		// proximo teste valida o preenchimento do Nome e a ausencia de sobrenome
		dsl.clicarBotao("elementosForm:cadastrar");

		dsl.confereAlert("Nome eh obrigatorio");
		// Alert alert = driver.switchTo().alert();
		// Assert.assertEquals("Nome eh obrigatorio", alert.getText());

	}

	@Test
	public void deveValidarSobreNomeObrigatorio() {

		// cadastra nome e verifica a ausencia de sobrenome
		dsl.escreve("elementosForm:nome", "Nome");
		dsl.clicarBotao("elementosForm:cadastrar");

		dsl.confereAlert("Sobrenome eh obrigatorio");
		// Alert alert = driver.switchTo().alert();
		// Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());

	}

	@Test
	public void deveValidarSexoObrigatorio() {

		dsl.escreve("elementosForm:nome", "Nome");
		dsl.escreve("elementosForm:sobrenome", "sobrenome");
		dsl.clicarBotao("elementosForm:cadastrar");

		dsl.confereAlert("Sexo eh obrigatorio");

	}

	@Test
	public void deveValidarSeVegetariano() {

		dsl.escreve("elementosForm:nome", "Nome");
		dsl.escreve("elementosForm:sobrenome", "sobrenome");
		dsl.clicaRadio("elementosForm:sexo:0");
		dsl.clicaRadio("elementosForm:comidaFavorita:1");
		dsl.clicaRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:cadastrar");
		dsl.confereAlert("Tem certeza que voce eh vegetariano?");

	}

	@Test
	public void deveValidarSeFazEsporte() {

		dsl.escreve("elementosForm:nome", "Nome");
		dsl.escreve("elementosForm:sobrenome", "sobrenome");
		dsl.clicaRadio("elementosForm:sexo:0");
		dsl.clicaRadio("elementosForm:comidaFavorita:1");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotao("elementosForm:cadastrar");
		dsl.confereAlert("Voce faz esporte ou nao?");

		// driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		// driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("sobrenome");
		// driver.findElement(By.id("elementosForm:sexo:0")).click();
		// comida favorita basta uma para validar
		// driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		// driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

		// instacia "new Select" será substituida por "Select Combo"
		// new
		// Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		// new
		// Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O
		// que eh esporte?");
		// Select combo = new
		// Select(driver.findElement(By.id("elementosForm:esportes")));
		// combo.selectByVisibleText("Natacao");
		// combo.selectByVisibleText("O que eh esporte?");

		// driver.findElement(By.id("elementosForm:cadastrar")).click();
		// Alert alert = driver.switchTo().alert();
		// Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		// alert.accept();

	}
}
