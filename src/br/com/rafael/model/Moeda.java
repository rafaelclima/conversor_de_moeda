package br.com.rafael.model;

import br.com.rafael.services.CurrencyApi;

public class Moeda {
  private String nome;
  private String simbolo;
  private String codigo;
  private double valor;

  public Moeda(String nome, String simbolo, String codigo, CurrencyApi currencyApi) {
    this.nome = nome;
    this.simbolo = simbolo;
    this.codigo = codigo;
    this.valor = 10;
    currencyApi.addCurrencyKeys(this);
  }

  public String getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public String getSimbolo() {
    return simbolo;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

}
