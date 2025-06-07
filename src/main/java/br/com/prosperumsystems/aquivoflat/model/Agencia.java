package br.com.prosperumsystems.aquivoflat.model;

import java.util.List;

public class Agencia {
    private String codigoAgencia;
    private String nomeAgencia;
    private List<FundoInvestimento> fundos;

    public Agencia(String codigoAgencia, String nomeAgencia, List<FundoInvestimento> fundos) {
        this.codigoAgencia = codigoAgencia;
        this.nomeAgencia = nomeAgencia;
        this.fundos = fundos;
    }

    public String getCodigoAgencia() {
        return codigoAgencia;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public List<FundoInvestimento> getFundos() {
        return fundos;
    }
}
