package br.com.erudio.service;

import br.com.erudio.model.Cliente;
import br.com.erudio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService (ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorCPF(long cpf){
        return clienteRepository.findByCpf(cpf).get(0);
    }

}
