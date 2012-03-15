package br.com.yanaga.green.webflow.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yanaga.green.webflow.app.Pessoa;
import br.com.yanaga.green.webflow.app.repository.querydsl.JpaQueryDslPredicateExecutor;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaQueryDslPredicateExecutor<Pessoa> {

}