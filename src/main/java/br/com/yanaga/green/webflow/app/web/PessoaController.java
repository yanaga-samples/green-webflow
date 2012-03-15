package br.com.yanaga.green.webflow.app.web;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;

import br.com.yanaga.green.webflow.app.Pessoa;
import br.com.yanaga.green.webflow.app.QPessoa;
import br.com.yanaga.green.webflow.app.repository.PessoaRepository;

import com.mysema.query.types.expr.BooleanExpression;

@Controller
public class PessoaController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PessoaRepository pessoaRepository;

	public FiltroPessoa newFiltro() {
		return new FiltroPessoa();
	}

	public Pessoa newPessoa() {
		return new Pessoa();
	}

	public List<Pessoa> filtrar(FiltroPessoa filtro) {
		return pessoaRepository.findAll(filtro.toPredicate());
	}

	public void salvar(Pessoa pessoa, MessageContext messages) {
		try {
			pessoaRepository.save(pessoa);
			messages.addMessage(new MessageBuilder().info().defaultText("Pessoa salva com sucesso!").build());
		}
		catch (Exception ex) {
			logger.warn("Erro ao salvar pessoa.", ex);
			messages.addMessage(new MessageBuilder().error().defaultText("Erro ao salvar pessoa!").build());
		}
	}

	public static class FiltroPessoa implements Serializable {

		private static final long serialVersionUID = 1L;

		private String nome;

		public String getNome() {
			return nome;
		}

		public BooleanExpression toPredicate() {
			String nomeBusca = nome == null ? "" : nome;
			return QPessoa.pessoa.nome.containsIgnoreCase(nomeBusca);
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

	}

}