package br.com.erudio.service;

import br.com.erudio.exception.ClienteNotFoundException;
import br.com.erudio.model.Cliente;
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

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
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
        Cliente clienteNovo = new Cliente();
        clienteNovo.setNome(cliente.getNome());
        clienteNovo.setDataCriacao(new Date());
        return clienteRepository.save(clienteNovo);
    }

    public List<Cliente> buscarTodos (){
        return clienteRepository.findAll();
    }

}
