package br.com.rafael.model;

public class Moeda {
  private String nome;
  private String simbolo;
  private String valor;

  public Moeda(String nome, String simbolo) {
    this.nome = nome;
    this.simbolo = simbolo;
    this.valor = "10";
  }

  public String getNome() {
    return nome;
  }

  public String getSimbolo() {
    return simbolo;
  }

  public String getValor() {
    return valor;
  }

  public void setValorEmReal(String valor) {
    this.valor = valor;
  }

}
