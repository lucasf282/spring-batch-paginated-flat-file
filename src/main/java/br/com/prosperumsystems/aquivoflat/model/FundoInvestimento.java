package br.com.prosperumsystems.aquivoflat.model;

import java.math.BigDecimal;

public class FundoInvestimento {
    private  String codigoFundo;
    private String nomeFundo;
    private String cnpjFundo;
    private String tipoFundo;
    private BigDecimal SaldoAtual;

    public FundoInvestimento(String codigoFundo, String nomeFundo, String cnpjFundo, String tipoFundo, BigDecimal saldoAtual) {
        this.codigoFundo = codigoFundo;
        this.nomeFundo = nomeFundo;
        this.cnpjFundo = cnpjFundo;
        this.tipoFundo = tipoFundo;
        this.SaldoAtual = saldoAtual;
    }

    public String getCodigoFundo() {
        return codigoFundo;
    }

    public String getNomeFundo() {
        return nomeFundo;
    }

    public String getCnpjFundo() {
        return cnpjFundo;
    }

    public String getTipoFundo() {
        return tipoFundo;
    }

    public BigDecimal getSaldoAtual() {
        return SaldoAtual;
    }
}
