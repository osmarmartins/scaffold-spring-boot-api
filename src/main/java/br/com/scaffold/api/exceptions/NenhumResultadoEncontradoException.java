package br.com.scaffold.api.exceptions;

public class NenhumResultadoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NenhumResultadoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public NenhumResultadoEncontradoException() {
		super();
	}

}
