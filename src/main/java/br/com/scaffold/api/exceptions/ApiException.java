package br.com.scaffold.api.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private List<MensagemExceptionDTO> messages = new ArrayList<>();
	
	public ApiException() {
		super();
		this.status = HttpStatus.BAD_REQUEST;
		this.messages.add(new MensagemExceptionDTO(this.getMessage(), ""));
	}
	
	public ApiException(String mensagem) {
		super(mensagem);
		this.status = HttpStatus.BAD_REQUEST;
		this.messages.add(new MensagemExceptionDTO(mensagem, ""));
	}

	public ApiException(MensagemExceptionDTO message, HttpStatus status) {
		super("");
		List<MensagemExceptionDTO> messages = new ArrayList<>();
		messages.add(message);
		this.status = status;
		this.messages = messages;
	}
	
	public ApiException(List<MensagemExceptionDTO> messages, HttpStatus status) {
		super("");
		this.status = status;
		this.messages = messages;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public List<MensagemExceptionDTO> getMessages() {
		return messages;
	}
	
	
}
