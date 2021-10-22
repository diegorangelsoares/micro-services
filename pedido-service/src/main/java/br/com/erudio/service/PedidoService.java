package br.com.erudio.service;

import br.com.erudio.exception.PedidoNotFoundException;
import br.com.erudio.model.BookPedido;
import br.com.erudio.model.Cliente;
import br.com.erudio.model.Endereco;
import br.com.erudio.model.Pedido;
import br.com.erudio.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private BookPedidoService bookPedidoService;

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

    public Pedido salvarNovo(Pedido pedido){

        Cliente cliente = pedido.getCliente();
        cliente.setEndereco(enderecoService.SalvarNovo(cliente.getEndereco()));;
        pedido.setCliente(clienteService.salvarCliente(pedido.getCliente()));
        pedido.setBooks(bookPedidoService.saveAll(pedido.getBooks()));

        return pedidoRepository.save(pedido);
    }

}
