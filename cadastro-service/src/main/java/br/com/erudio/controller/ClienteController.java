package br.com.erudio.controller;

import br.com.erudio.model.Cliente;
import br.com.erudio.request.ClienteRequest;
import br.com.erudio.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cadastro endpoint")
@RestController
@RequestMapping("cadastro-service")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "find a specific Cliente by your ID")
    @GetMapping(value = "/{id}/")
    public ResponseEntity<Cliente> findCliente(
            @PathVariable("id") Long id
    ){
        var cliente = clienteService.buscarPorId(id);
        if (cliente == null) throw new RuntimeException("Cliente not found.");

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @Operation(summary = "find all Clientes")
    @GetMapping(value = "/")
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = clienteService.buscarTodos();
        if (clientes == null) throw new RuntimeException("Cliente not found.");

        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @Operation(summary = "Save new Cliente")
    @PostMapping(value = "/")
    public ResponseEntity<Cliente> save(@Validated @RequestBody ClienteRequest clienteRequest) {

        log.info("Salvando cliente...");

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarNovo(clienteRequest));
    }

}
