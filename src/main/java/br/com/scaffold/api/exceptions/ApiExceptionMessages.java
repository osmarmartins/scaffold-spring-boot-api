package br.com.scaffold.api.exceptions;

import java.util.Arrays;
import java.util.List;

public class ApiExceptionMessages {
	
	private List<MensagemExceptionDTO> errors;

	public ApiExceptionMessages(List<MensagemExceptionDTO> errors) {
		this.errors = errors;
	}
	
	public ApiExceptionMessages(MensagemExceptionDTO error) {
		this.errors = Arrays.asList(error);
	}
	
	public ApiExceptionMessages(String mensagemErro) {
		this.errors = Arrays.asList(new MensagemExceptionDTO(mensagemErro, ""));
	}

	public List<MensagemExceptionDTO> getErrors() {
		return errors;
	}

}
