package br.com.erudio.service;

import br.com.erudio.exception.PedidoNotFoundException;
import br.com.erudio.model.Pedido;
import br.com.erudio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido finById (long id){
        return pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotFoundException(String.format(
                "Pedido not found by ID [%s]!", id)));
    }

    public List<Pedido> listAll(){
        return pedidoRepository.findAll();
    }



}
