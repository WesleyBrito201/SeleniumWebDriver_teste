import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFrameEJanelas {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {

		// driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(900, 950));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After

	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeDeFrame() {

		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);

		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", msg);

	}

	@Test
	public void deveinteragirComJanelas() {

		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escreve(By.tagName("textarea"), "deu certo?");

	}

	@Test
	public void deveinteragirComJanelasSemTitulo() {

		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"), "deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"),"deu certo??");

	}
}
