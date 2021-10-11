package br.com.erudio.controller;

import br.com.erudio.model.Cliente;
import br.com.erudio.request.ClienteRequest;
import br.com.erudio.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Cliente endpoiny")
@RestController
@RequestMapping("cadastro-service")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //@Operation(summary = "find a specific book by your ID")
    @GetMapping(value = "/{id}/{currency}")
    public ResponseEntity<Cliente> findCliente(
            @PathVariable("id") Long id//,
            //@PathVariable("currency") String currency
    ){
        //var port = environment.getProperty("local.server.port");
        var cliente = clienteService.buscarPorId(id);
        if (cliente == null) throw new RuntimeException("Cliente not found.");

        //var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

//        book.setEnvironment(port + " FEIGN");
//        book.setPrice(cambio.getConversionValue());
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    //@Operation(summary = "Save new book")
    @PostMapping(value = "/")
    public ResponseEntity<Cliente> save(@Validated @RequestBody ClienteRequest bookRequest) {

        log.info("Salvando cliente...");

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarNovo(bookRequest));
    }

}
