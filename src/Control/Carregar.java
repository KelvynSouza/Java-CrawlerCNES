package Control;

import Model.Estabelecimento;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.commons.logging.LogFactory;

public class Carregar {

    private final ArrayList<Estabelecimento> estab = new ArrayList<Estabelecimento>();

    public void addEstab(Estabelecimento ct) {
        estab.add(ct);
    }

    public ArrayList<Estabelecimento> getEstab() {
        return (estab);
    }

    public void tratarErros(WebClient client) {
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.host.ActiveXObject").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.html.HtmlScript").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.host.WindowProxy").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache").setLevel(Level.OFF);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.getOptions().setThrowExceptionOnScriptError(false);
    }

    public void setarEstMun(HtmlPage page, String estado, String municipio) throws InterruptedException {
        //setar estado
        HtmlSelect estados = page.getFirstByXPath("/html/body//form//select[@ng-change='carregarMunicipios()' and @ng-model='Estado']");
        estados.getOptionByValue(estado).setSelected(true);

        Thread.sleep(3_000);

        //setar municipio
        HtmlSelect municipios = page.getFirstByXPath("/html/body//form//select[@ng-model='Municipio']");
        municipios.getOptionByValue(municipio).setSelected(true);

    }

    public void addInf(HtmlPage pagina) throws IOException {
        HtmlInput nome = pagina.getFirstByXPath("//input[@id='nome']");
        HtmlElement nomeemp = pagina.getFirstByXPath("//input[@id='cnpj' and @ng-value='estabelecimento.noEmpresarial']");
        HtmlElement cnes = pagina.getFirstByXPath("//input[@id='cnes']");
        HtmlInput cnpj = pagina.getFirstByXPath("//div[1]/div[3]/div[@class='form-group' and 1]/input[@id='cnpj' and @class='form-control input-sm' and 1]");
        HtmlInput logra = pagina.getFirstByXPath("//input[@id='cnpj' and @ng-value='estabelecimento.noLogradouro']");
        HtmlInput nume = pagina.getFirstByXPath("//input[@id='cnpj' and @ng-value='estabelecimento.nuEndereco']");
        HtmlInput comple = pagina.getFirstByXPath("//input[@id='cnpj' and @ng-value='estabelecimento.noComplemento']");
        HtmlInput bairro = pagina.getFirstByXPath("//input[@ng-value='estabelecimento.bairro']");
        HtmlInput muni = pagina.getFirstByXPath("//div[@class='col-md-5']/div[@class='form-group' and 1]/input[@id='cnpj' and @class='form-control input-sm' and 1 and not(contains(@ng-value,'estabelecimento.bairro'))]");
        HtmlInput uf = pagina.getFirstByXPath("//input[@ng-value='estabelecimento.uf']");
        HtmlInput cep = pagina.getFirstByXPath("//input[@ui-mask='99999-999']");
        HtmlInput tel = pagina.getFirstByXPath("//input[@id='tel']");
        //Guardando Dados
        addEstab(new Estabelecimento(nome.asText(), nomeemp.asText(), cnes.asText(), cnpj.asText(), logra.asText(), nume.asText(), comple.asText(), bairro.asText(), muni.asText(), uf.asText(), cep.asText(), tel.asText()) {
        });
        HtmlButton btnFechar = pagina.getFirstByXPath("//button[@class='close']");
        btnFechar.click();
    }

    public void criarCSV() throws IOException {
        FileWriter pw = new FileWriter(new File("Dados.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Nome;Nome Empresarial;Cnes;CNPJ;Logradouro;Numero;Complemento;Bairro;Municipio;UF;CEP;Telefone\n");
        for (Estabelecimento o : getEstab()) {
            sb.append(o.getNome());
            sb.append(';');
            sb.append(o.getNomeEmp());
            sb.append(';');
            sb.append(o.getCnes());
            sb.append(';');
            sb.append(o.getCnpj());
            sb.append(';');
            sb.append(o.getLogra());
            sb.append(';');
            sb.append(o.getNume());
            sb.append(';');
            sb.append(o.getComple());
            sb.append(';');
            sb.append(o.getBairro());
            sb.append(';');
            sb.append(o.getMuni());
            sb.append(';');
            sb.append(o.getUF());
            sb.append(';');
            sb.append(o.getCep());
            sb.append(';');
            sb.append(o.getTel());
            sb.append('\n');
        }
        pw.write(sb.toString());
        pw.close();
        System.out.println("Tarefa finalizada com sucesso.");
    }

}
