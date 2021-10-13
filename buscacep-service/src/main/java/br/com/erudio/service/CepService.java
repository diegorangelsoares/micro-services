package br.com.erudio.service;

import br.com.erudio.response.EnderecoResponse;

import java.beans.ConstructorProperties;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CepService {

    static String webService = "http://viacep.com.br/ws/";
    static int codigoSucesso = 200;

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public CepService() {
    }

    public EnderecoResponse buscaEnderecoPeloCEP(String cep) throws Exception {
        String urlParaChamada = webService + cep + "/json";

        EnderecoResponse endereco = new EnderecoResponse();

        HttpRequest req = HttpRequest.newBuilder(URI.create(urlParaChamada))
                .GET().build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        if(response.get().statusCode() == 500)

            throw new RuntimeException("Endereco not found.");

        else
            {
                endereco = JSONUtils.covertFromJsonToObject(response.get().body(), EnderecoResponse.class);
            }

        endereco.setCep(endereco.getCep().replace("-",""));

        return endereco;
    }

}
