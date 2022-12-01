package br.com.wilsonpaiva.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wilsonpaiva.model.Cambio;
import br.com.wilsonpaiva.repository.CambioRepository;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
	
	private Logger logger = LoggerFactory.getLogger(CambioController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioRepository repository;
	
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable(value = "amount") BigDecimal amount,
			@PathVariable(value = "from") String from,
			@PathVariable(value = "to") String to
			) {
		
		logger.info("getCambio is called with -> {},{} and {}", amount,from,to);
		
		var cambio = repository.findByFromAndTo(from, to);
		if (cambio == null) throw new RuntimeException("Currency Unsupported");
		var port = environment.getProperty("local.server.port");
		BigDecimal conversionFactor = cambio.getConversionFactor();
		BigDecimal conversionValue =  conversionFactor.multiply(amount);
		cambio.setConvertedValeu(conversionValue.setScale(2,RoundingMode.CEILING));
		cambio.setEnviroment(port);
		return cambio;
	}

}
