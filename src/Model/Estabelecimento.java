package Model;

public abstract class Estabelecimento {

    private String nome, nomeEmp, cnes, cnpj, logra, nume, comple, bairro, muni, UF, Cep, Tel;

    public Estabelecimento(String nome, String nomeEmp, String cnes, String cnpj, String logra, String nume, String comple, String bairro, String muni, String UF, String Cep, String Tel) {
        setNome(nome);
        setNomeEmp(nomeEmp);
        setCnes(cnes);
        setCnpj(cnpj);
        setLogra(logra);
        setNume(nume);
        setComple(comple);
        setBairro(bairro);
        setMuni(muni);
        setUF(UF);
        setCep(Cep);
        setTel(Tel);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeEmp() {
        return nomeEmp;
    }

    public void setNomeEmp(String nomeEmp) {
        this.nomeEmp = nomeEmp;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogra() {
        return logra;
    }

    public void setLogra(String logra) {
        this.logra = logra;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getComple() {
        return comple;
    }

    public void setComple(String comple) {
        this.comple = comple;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMuni() {
        return muni;
    }

    public void setMuni(String muni) {
        this.muni = muni;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

}
