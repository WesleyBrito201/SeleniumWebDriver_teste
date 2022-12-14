import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFrame {
	
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
	public void testeDeFrame() {
		
		driver.switchTo().frame("frame1"); // entra no frame na pagina
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();

		driver.switchTo().defaultContent();// sai do frame e volta a pagina
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);

		

	}

	@Test
	public void deveinteragirComJanelas() {
		

		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]); //entra no PopUp
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		driver.close();//fecha o PopUp
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]); //entra no pagina principal
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo??");
		

	}

	@Test
	public void deveinteragirComJanelasSemTitulo() {
		
		//exercicio teste buton PopUp do mal
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo?");
		driver.close();
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("deu certo??");
		
	}
}
