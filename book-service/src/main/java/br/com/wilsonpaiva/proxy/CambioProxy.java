package br.com.wilsonpaiva.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.wilsonpaiva.response.Cambio;

@FeignClient(name = "cambio-service")
public interface CambioProxy {


@GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
public Cambio getCambio(
		@PathVariable(value = "amount") Double amount,
		@PathVariable(value = "from") String from,
		@PathVariable(value = "to") String to
		);
}
