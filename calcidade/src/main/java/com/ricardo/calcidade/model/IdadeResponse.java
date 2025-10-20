package com.ricardo.calcidade.model;

public class IdadeResponse {

    private String nomeCompleto;
    private int anos;
    private int meses;
    private int dias;
    private String mensagem;

    public IdadeResponse(String nomeCompleto, int anos, int meses, int dias) {
        this.nomeCompleto = nomeCompleto;
        this.anos = anos;
        this.meses = meses;
        this.dias = dias;
        this.mensagem = String.format("%s possui exatamente %d anos, %d meses e %d dias.",
                                        nomeCompleto, anos, meses, dias);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public int getAnos() {
        return anos;
    }

    public int getMeses() {
        return meses;
    }

    public int getDias() {
        return dias;
    }

    public String getMensagem() {
        return mensagem;
    }
}