import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	/**** textField ***/ // Corrigido_ok/
	public void escreve(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escreve(String id_campo, String texto) {
		escreve(By.id(id_campo), texto);

	}

	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");

	}

	/**** radio e check ***/ // Corrigido_ok/

	public void clicaRadio(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();

	}

	public void clicarCheck(String id) {
		driver.findElement(By.id(id)).click();
	}

	public boolean isCheckMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	/**** combo ***/ // Corrigido_ok/
	public void selecionarCombo(String id, String valor) {

		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	public void deselecionarCombo(String id, String valor) {

		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obterValorCombo(String id) {

		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> obterValoresCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao : allSelectedOptions) {
			valores.add(opcao.getText());

		}
		return valores;
	}

	public int obterQuantidadeOpcoesCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificaropcaoCombo(String id, String opcao) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;

			}

		}
		return false;
	}

	/**** clicar botão ***/ // Corrigido_ok/

	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();

	}

	public String obterValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");

	}

	/**** interagir com link ***/ // Corrigido_ok/
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}

	/**** interagir com textos ***/ // Corrigido_ok/

	public String obterTexto(By by) {
		return driver.findElement(by).getText();
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}

	/**** interagir com alertas ***/ // Corrigido_ok/

	public String alertaObterTexto() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceita() {
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

	public String alertaObterTextoENega() {
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}

	public void alertaEscrever(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/**** interagir com frames e janelas ***/ // Corrigido_ok/

	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}

	public void sairFrame() {
		driver.switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}

	/**** Confere alerta "assert" *****/ // Criei com base na lógica
	public void confereAlert(String alerta) {
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alerta, alert.getText());
		alert.accept();

	}

	public void confereAlertNo(String alerta) {
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alerta, alert.getText());
		alert.dismiss();

	}

}
