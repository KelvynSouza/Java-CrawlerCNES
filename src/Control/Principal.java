package Control;

import Model.Estabelecimento;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Principal {

    private static ArrayList<Estabelecimento> estab = new ArrayList<Estabelecimento>();

    public static void addEstab(Estabelecimento ct) {
        estab.add(ct);
    }

    public static ArrayList<Estabelecimento> getEstab() {
        return (estab);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WebClient client = new WebClient(BrowserVersion.CHROME);
        Carregar carreg = new Carregar();
        JOptionPane.showMessageDialog(null, "O processo levara tempo, aguarde.");
        

        //Tratar mensagens de erro
        carreg.tratarErros(client);

        //URL do site 
        String connection_url = "http://cnes.datasus.gov.br/pages/estabelecimentos/consulta.jsp";

        //Configurando a pagina
        HtmlPage pagina = client.getPage(connection_url);

        //setando botao pesquisar
        final HtmlButton btnPesquisa = pagina.getFirstByXPath("/html/body//form//button[@class='btn btn-primary' and @ng-click='pesquisaEstabelecimentos()']");

        Thread.sleep(3_000);
        
        //Setar Estado e Municipio
        String estado = "26";
        String municipio="260960";
        carreg.setarEstMun(pagina, estado, municipio);
        
        pagina = btnPesquisa.click();
        Thread.sleep(3_000);

        //Abrir Fichas
        HtmlElement btnProxFich;
        int i = 2, cont = 0;
        do {
            btnProxFich = null;
            for (int fi = 1; fi <= 10; fi++) {
                HtmlButton btnFicha = pagina.getFirstByXPath("//tr[" + fi + "]/td[@class='text-center' and 8]/button[@class='btn btn-default' and 1]");
                if (btnFicha == null) {
                    break;
                }
                btnFicha.click();
                Thread.sleep(2_000);
                carreg.addInf(pagina);
                cont += 1;
            }
            btnProxFich = pagina.getFirstByXPath("//span[@class='ng-binding' and text()='" + i + "']");
            if (btnProxFich == null) {
                break;
            }
            btnProxFich.click();
            i++;
        } while (true);
        System.out.println("\nTotal de dados: " + cont);
        JOptionPane.showMessageDialog(null, "Processo Completo");
        carreg.criarCSV();
        client.close();
    }

}
