package br.com.prosperumsystems.aquivoflat.model;

public class FlatFilePaginationInfo {
    private Long paginaAtual;
    private Long numeroItemPagina;
    private Long qtdPorPagina;
    private Long qtdTotalItens;

    public FlatFilePaginationInfo(Long paginaAtual, Long numeroItemPagina, Long qtdPorPagina, Long qtdTotalItens) {
        this.paginaAtual = paginaAtual;
        this.numeroItemPagina = numeroItemPagina;
        this.qtdPorPagina = qtdPorPagina;
        this.qtdTotalItens = qtdTotalItens;
    }

    public boolean temCabecalho() {
        return numeroItemPagina.equals(1L);
    }

    public boolean temRodape() {
        return numeroItemPagina.equals(qtdPorPagina);
    }

    public Long getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Long paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public Long getNumeroItemPagina() {
        return numeroItemPagina;
    }

    public void setNumeroItemPagina(Long numeroItemPagina) {
        this.numeroItemPagina = numeroItemPagina;
    }

    public Long getQtdPorPagina() {
        return qtdPorPagina;
    }

    public void setQtdPorPagina(Long qtdPorPagina) {
        this.qtdPorPagina = qtdPorPagina;
    }

    public Long getQtdTotalItens() {
        return qtdTotalItens;
    }

    public void setQtdTotalItens(Long qtdTotalItens) {
        this.qtdTotalItens = qtdTotalItens;
    }
}
