package br.com.scaffold.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class MensagemExceptionDTO {

	private String campo;
	private String mensagemUsuario;
	private String mensagemDesenvolvedor;

	public MensagemExceptionDTO(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.campo = "";
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public MensagemExceptionDTO(String campo, String mensagemUsuario, String mensagemDesenvolvedor) {
		this.campo = campo;
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

}
