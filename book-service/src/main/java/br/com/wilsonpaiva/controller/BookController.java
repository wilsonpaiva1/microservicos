package br.com.wilsonpaiva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wilsonpaiva.model.Book;
import br.com.wilsonpaiva.proxy.CambioProxy;
import br.com.wilsonpaiva.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book endpoint" ,description = "Endpoints for Managing People")
@RestController
@RequestMapping("book-service")
public class BookController {
	
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CambioProxy proxy;
	@Operation(summary =  " Find a specific book by uour ID")
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
		var book = repository.getById(id);
		if (book == null) throw new RuntimeException("Book not Foun");
		
		
		var cambio = proxy.getCambio(book.getPrice(), "USD", currency);
		var port = environment.getProperty("local.server.port");
		book.setEnvironment("Book port: "+ port + " Cambio Port: " + cambio.getEnviroment());
		book.setPrice(cambio.getConvertedValeu());
		
		return book;
	}

//	@GetMapping(value = "/{id}/{currency}")
//	public Book findBook(
//			@PathVariable("id") Long id,
//			@PathVariable("currency") String currency
//			) {
//		
//		var book = repository.getById(id);
//		if (book == null) throw new RuntimeException("Book not Foun");
//		
//		HashMap<String,String> params = new HashMap<>();
//		params.put("amount", book.getPrice().toString());
//		params.put("from", "USD");
//		params.put("to", currency);
//		
//		var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class,
//				params);
//		var cambio = response.getBody();
//		var port = environment.getProperty("local.server.port");
//		book.setEnvironment(port);
//		book.setPrice(cambio.getConvertedValeu());
//		
//		return book;
//	}

	
}
