package br.com.wilsonpaiva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wilsonpaiva.model.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

}
