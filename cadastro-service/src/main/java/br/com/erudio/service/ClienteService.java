package br.com.erudio.service;

import br.com.erudio.exception.ClienteNotFoundException;
import br.com.erudio.model.Cliente;
import br.com.erudio.model.Endereco;
import br.com.erudio.repository.ClienteRepository;
import br.com.erudio.request.ClienteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClienteService {

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
                endereco.setId(cliente.getEndereco().getId());
                endereco.setBairro(cliente.getEndereco().getBairro());
                endereco.setCidade(cliente.getEndereco().getCidade());
                endereco.setLogradouro(cliente.getEndereco().getLogradouro());
                endereco.setEstado(cliente.getEndereco().getEstado());

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

}
