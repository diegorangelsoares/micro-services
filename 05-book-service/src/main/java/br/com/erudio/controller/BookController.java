package br.com.erudio.controller;

import br.com.erudio.Model.Book;
import br.com.erudio.Repository.BookRepository;
import br.com.erudio.proxy.CambioProxy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Book endpoiny")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CambioProxy cambioProxy;

    @Operation(summary = "find a specific book by your ID")
    @GetMapping (value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ){
        var port = environment.getProperty("local.server.port");
        var book = bookRepository.getById(id);
        if (book == null) throw new RuntimeException("Book not found.");

        var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        book.setEnvironment(port + " FEIGN");
        book.setPrice(cambio.getConversionValue());
        return book;
    }


//    @GetMapping (value = "/{id}/{currency}")
//    public Book findBook(
//            @PathVariable("id") Long id,
//            @PathVariable("currency") String currency
//    ){
//        var port = environment.getProperty("local.server.port");
//        var book = bookRepository.getById(id);
//        if (book == null) throw new RuntimeException("Book not found.");
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("amount", book.getPrice().toString());
//        params.put("from","USD");
//        params.put("to", currency);
//
//        var reponse = new RestTemplate().
//                getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
//
//        var cambio = reponse.getBody();
//
//        book.setEnvironment(port);
//        book.setPrice(cambio.getConversionValue());
//        return book;
//    }

}
