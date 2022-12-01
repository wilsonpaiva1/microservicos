package br.com.wilsonpaiva.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wilsonpaiva.model.Cambio;

public  interface CambioRepository extends JpaRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from,String to);

}
