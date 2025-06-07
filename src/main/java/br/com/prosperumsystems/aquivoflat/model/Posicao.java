package br.com.prosperumsystems.aquivoflat.model;

import org.springframework.data.domain.Page;

public class Posicao {
    private String dataMovimento;
    private String documento;
    private String nome;
    private String nuProduto;
    private String nomeFundoInvestimento;
    private String cnpjFundo;
    private String operacao;
    private String dataEfetivacao;
    private String dataConversao;
    private String qtdeCotas;
    private String valorInicial;
    private String saldoBruto;
    private String saldoLiquido;
    private String cotaInicial;
    private String cotaAtual;
    private String iof;
    private String ir;
    private String formaLiquidacao;
    private String agencia;
    private String operacaoProduto;
    private String contaDv;
    private FlatFilePaginationInfo paginationInfo;


    // Getters and setters for all fields
    public String getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(String dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNuProduto() {
        return nuProduto;
    }

    public void setNuProduto(String nuProduto) {
        this.nuProduto = nuProduto;
    }

    public String getNomeFundoInvestimento() {
        return nomeFundoInvestimento;
    }

    public void setNomeFundoInvestimento(String nomeFundoInvestimento) {
        this.nomeFundoInvestimento = nomeFundoInvestimento;
    }

    public String getCnpjFundo() {
        return cnpjFundo;
    }

    public void setCnpjFundo(String cnpjFundo) {
        this.cnpjFundo = cnpjFundo;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getDataEfetivacao() {
        return dataEfetivacao;
    }

    public void setDataEfetivacao(String dataEfetivacao) {
        this.dataEfetivacao = dataEfetivacao;
    }

    public String getDataConversao() {
        return dataConversao;
    }

    public void setDataConversao(String dataConversao) {
        this.dataConversao = dataConversao;
    }

    public String getQtdeCotas() {
        return qtdeCotas;
    }

    public void setQtdeCotas(String qtdeCotas) {
        this.qtdeCotas = qtdeCotas;
    }

    public String getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(String valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getSaldoBruto() {
        return saldoBruto;
    }

    public void setSaldoBruto(String saldoBruto) {
        this.saldoBruto = saldoBruto;
    }

    public String getSaldoLiquido() {
        return saldoLiquido;
    }

    public void setSaldoLiquido(String saldoLiquido) {
        this.saldoLiquido = saldoLiquido;
    }

    public String getCotaInicial() {
        return cotaInicial;
    }

    public void setCotaInicial(String cotaInicial) {
        this.cotaInicial = cotaInicial;
    }

    public String getCotaAtual() {
        return cotaAtual;
    }

    public void setCotaAtual(String cotaAtual) {
        this.cotaAtual = cotaAtual;
    }

    public String getIof() {
        return iof;
    }

    public void setIof(String iof) {
        this.iof = iof;
    }

    public String getIr() {
        return ir;
    }

    public void setIr(String ir) {
        this.ir = ir;
    }

    public String getFormaLiquidacao() {
        return formaLiquidacao;
    }

    public void setFormaLiquidacao(String formaLiquidacao) {
        this.formaLiquidacao = formaLiquidacao;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getOperacaoProduto() {
        return operacaoProduto;
    }

    public void setOperacaoProduto(String operacaoProduto) {
        this.operacaoProduto = operacaoProduto;
    }

    public String getContaDv() {
        return contaDv;
    }

    public void setContaDv(String contaDv) {
        this.contaDv = contaDv;
    }

    public FlatFilePaginationInfo getPaginationInfo() {
        return paginationInfo;
    }

    public void setPaginationInfo(FlatFilePaginationInfo paginationInfo) {
        this.paginationInfo = paginationInfo;
    }
}