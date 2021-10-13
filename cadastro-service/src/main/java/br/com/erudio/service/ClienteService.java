package br.com.erudio.service;

import br.com.erudio.exception.ClienteNotFoundException;
import br.com.erudio.model.Cliente;
import br.com.erudio.model.Endereco;
import br.com.erudio.repository.ClienteRepository;
import br.com.erudio.request.ClienteRequest;
import br.com.erudio.response.EnderecoResponseApiCep;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ClienteService {

    private static String enderecoApiCambio = "http://localhost:8404/";

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepository,EnderecoService enderecoService){
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    public Cliente buscarPorId(long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(
                        "Cliente not found by ID [%s]!", id)));
    }

    public Cliente salvar (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente salvarNovo (ClienteRequest cliente){
        Endereco endereco = new Endereco();
        if (cliente.getEndereco() != null){

            if (cliente.getEndereco().getCep() > 0){
                //Fazer a busca por CEP no service-cep
                //Alterar as linhas abaixo
                EnderecoResponseApiCep enderecoApi = null;
                try {
                    enderecoApi = buscaEnderecoApi(cliente.getEndereco().getCep()+"");
                } catch (Exception e) {
                    e.printStackTrace();

                }
                if (enderecoApi != null){
                    endereco.setBairro(enderecoApi.getBairro());
                    endereco.setCidade(enderecoApi.getLocalidade());
                    endereco.setLogradouro(enderecoApi.getLogradouro());
                    endereco.setEstado(enderecoApi.getUf());
                }else {
                    endereco.setId(cliente.getEndereco().getId());
                    endereco.setBairro(cliente.getEndereco().getBairro());
                    endereco.setCidade(cliente.getEndereco().getCidade());
                    endereco.setLogradouro(cliente.getEndereco().getLogradouro());
                    endereco.setEstado(cliente.getEndereco().getEstado());
                }
            }else{
                endereco.setId(cliente.getEndereco().getId());
                endereco.setBairro(cliente.getEndereco().getBairro());
                endereco.setCidade(cliente.getEndereco().getCidade());
                endereco.setLogradouro(cliente.getEndereco().getLogradouro());
                endereco.setEstado(cliente.getEndereco().getEstado());
            }

            endereco.setCep(cliente.getEndereco().getCep());
            endereco.setNumero(cliente.getEndereco().getNumero());
            endereco.setComplemento(cliente.getEndereco().getComplemento());

            endereco.setDataCriacao(new Date());
            enderecoService.SalvarNovo(endereco);

        }

        Cliente clienteNovo = new Cliente();
        clienteNovo.setEndereco(endereco);
        clienteNovo.setNome(cliente.getNome());
        clienteNovo.setDataCriacao(new Date());
        return clienteRepository.save(clienteNovo);
    }

    public List<Cliente> buscarTodos (){
        return clienteRepository.findAll();
    }

//    public Endereco buscaEnderecoApi (String cep){
//        HashMap<String, String> params = new HashMap<>();
////        params.put("amount", book.getPrice().toString());
////        params.put("from","USD");
////        params.put("to", currency);
//        String url = "http://localhost:8404/buscacep-service/"+cep+"/";
//
//        System.out.println("Endereco api: "+url);
//
//        try {
//            var reponse = new RestTemplate().
//                    getForEntity(url, Endereco.class, params);
//
//            var cambio = reponse.getBody();
//
//            if (cambio != null){
//                return cambio;
//            }else{
//                return null;
//            }
//
//        }catch (Exception e){
//            throw new RuntimeException("Erro ao consumir apiCEP"+e.toString());
//        }
//
//
//    }

    public EnderecoResponseApiCep buscaEnderecoApi(String cep) throws Exception {
        //String urlParaChamada = webService + cep + "/json";
        String url = "http://localhost:8404/buscacep-service/"+cep+"/";

        EnderecoResponseApiCep endereco = new EnderecoResponseApiCep();

        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .GET().build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> System.out.println(res));

        if(response.get().statusCode() == 500)

            throw new RuntimeException("Erro 500 ao consumir BuscaCEP-service - "+response.get().body());

        else
        {
            endereco = JSONUtils.covertFromJsonToObject(response.get().body(), EnderecoResponseApiCep.class);
        }

        return endereco;
    }

}
