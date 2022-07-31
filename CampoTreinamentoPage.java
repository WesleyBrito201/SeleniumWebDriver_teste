import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

	private DSL dsl;

	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}

	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}

	public void setSexoMasculino() {
		dsl.clicaRadio("elementosForm:sexo:0");

	}

	public void setComidaPizza() {
		dsl.clicaRadio("elementosForm:comidaFavorita:2");

	}

	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:escolaridade", valor);

	}

	public void setEsporte(String valor) {
		dsl.selecionarCombo("elementosForm:esportes", valor);

	}

	public void setCadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	public String obterResultadoCadastro() {
		return dsl.obterTexto("resultado");
	}
	
	public String obeterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}
	public String obeterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}
	public String obeterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}
	public String obeterComidaCadastro() {
		return dsl.obterTexto("descComida") ;
	}
	public String obeterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}
	public String obeterEsporteCadastro() {
		return dsl.obterTexto("descEsportes") ;
	}
	
	

}
