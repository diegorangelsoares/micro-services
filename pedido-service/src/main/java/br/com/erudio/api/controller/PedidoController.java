package br.com.erudio.api.controller;

import br.com.erudio.api.request.PedidoRequest;
import br.com.erudio.model.BookPedido;
import br.com.erudio.model.Cliente;
import br.com.erudio.model.Pedido;
import br.com.erudio.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pedido endpoint")
@RestController
@RequestMapping("pedido-service")
@Slf4j
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "find a specific Pedido by your ID")
    @GetMapping(value = "/pedido/{id}/")
    public ResponseEntity<Pedido> findPedido(
            @PathVariable("id") Long id
    ){
        var pedido = pedidoService.finById(id);
        if (pedido == null) throw new RuntimeException("Pedido not found.");

        return ResponseEntity.status(HttpStatus.OK).body(pedido);
    }

    @Operation(summary = "find all Pedidos")
    @GetMapping(value = "/pedido/")
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> clientes = pedidoService.listAll();
        if (clientes == null) throw new RuntimeException("Pedido not found.");

        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @Operation(summary = "Save new Pedido")
    @PostMapping(value = "/pedido/")
    public ResponseEntity<Pedido> save(@Validated @RequestBody Pedido pedidoRequest) {

        log.info("Salvando Pedido...");


        Pedido pedido = pedidoService.salvarNovo(pedidoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @Operation(summary = "find Books by ID pedido")
    @GetMapping(value = "/books/{idPedido}/")
    public ResponseEntity<List<BookPedido>> findBooksPedido(
            @PathVariable("idPedido") Long idPedido
    ){
        var pedido = pedidoService.finById(idPedido);
        if (pedido == null) throw new RuntimeException("Pedido not found.");
        List<BookPedido> books = pedido.getBooks();

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

}
