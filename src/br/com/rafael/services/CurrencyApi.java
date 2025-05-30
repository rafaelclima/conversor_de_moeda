package br.com.rafael.services;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.rafael.model.Moeda;

public class CurrencyApi {
  private String url_str = "https://v6.exchangerate-api.com/v6/8b52168fd0c4e0fc3076840e/latest/";
  private ArrayList<Moeda> currencyKeys;

  public CurrencyApi() {
    this.currencyKeys = new ArrayList<Moeda>();
  }

  public ArrayList<Moeda> getCurrencyKeys() {
    return currencyKeys;
  }

  public void addCurrencyKeys(Moeda moeda) {
    this.currencyKeys.add(moeda);
  }

  public String converter(Moeda moeda) throws IOException, InterruptedException {

    var enderecoApi = url_str + URLEncoder.encode(moeda.getCodigo(), StandardCharsets.UTF_8);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(enderecoApi))
        .build();

    HttpResponse<String> response = client
        .send(request, HttpResponse.BodyHandlers.ofString());

    String json = response.body();
    ApiRecord jsonFormatado = gson.fromJson(json, ApiRecord.class);

    for (var codigoValorMoedaApi : jsonFormatado.conversion_rates().entrySet()) {

      for (var objMoeda : currencyKeys) {
        if (objMoeda.getCodigo().equals(codigoValorMoedaApi.getKey())) {
          var calculoConversao = Double.parseDouble(moeda.getValor()) * codigoValorMoedaApi.getValue();
          objMoeda.setValor(String.valueOf(calculoConversao));
        }
      }
    }

    return json;
  }
}
