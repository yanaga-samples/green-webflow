package br.com.yanaga.green.webflow.app;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Version
	Integer version;

	@NotEmpty
	private String nome;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static enum Sexo {
		MASCULINO {
			@Override
			public void calcular() {
			}
		}, FEMININO {
			@Override
			public void calcular() {
			}
		};

		public abstract void calcular();
	}

}