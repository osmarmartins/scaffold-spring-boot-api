package br.com.scaffold.api.exceptions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionMessages handleValidationErros(MethodArgumentNotValidException ex ){
        BindingResult bindingResult = ex.getBindingResult();
        return criarListaDeMensagemDeErro(bindingResult);
    }	
	
	private ApiExceptionMessages criarListaDeMensagemDeErro(BindingResult bindingResult){
		List<MensagemExceptionDTO> erros = new ArrayList<MensagemExceptionDTO>();	
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			String campo = fieldError.getField();
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			erros.add(new MensagemExceptionDTO(campo, mensagemUsuario, mensagemDesenvolvedor));
		}
		return new ApiExceptionMessages(erros);		
 	}
	
	@ExceptionHandler({ ApiException.class })
	public ResponseEntity<ApiExceptionMessages> handleAserpApplicationException(ApiException ex, WebRequest request) {
		List<MensagemExceptionDTO> erros =  ex.getMessages();
		return ResponseEntity.status(ex.getStatus()).body(new ApiExceptionMessages(erros));
	}

	private String pilhaDeErros(Exception ex) {
		StringBuilder detalhes = new StringBuilder("Detalhe: " + ex.getMessage() + " - Stack:");
		for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
			detalhes.append(stackTraceElement.toString());
		}
		String mensagemDesenvolvedor = detalhes.toString();
		return mensagemDesenvolvedor;
	}
	
	@ExceptionHandler({ NenhumResultadoEncontradoException.class, EmptyResultDataAccessException.class })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ApiExceptionMessages handleNenhumResultadoEncontradoException(NenhumResultadoEncontradoException ex, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("mensagem.nenhum.resultado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = pilhaDeErros(ex);
		List<MensagemExceptionDTO> erros = Arrays.asList(new MensagemExceptionDTO(mensagemUsuario, mensagemDesenvolvedor));
		return new ApiExceptionMessages(erros);
	}	
	
	@ExceptionHandler({ RecursoNaoLocalizadoException.class, RemoteException.class, JAXBException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiExceptionMessages handleRecursoNaoLocalizadoException(RecursoNaoLocalizadoException ex, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = pilhaDeErros(ex);
		List<MensagemExceptionDTO> erros = Arrays.asList(new MensagemExceptionDTO(mensagemUsuario, mensagemDesenvolvedor));
		return new ApiExceptionMessages(erros);
	}	
	
	@ExceptionHandler({ DataIntegrityViolationException.class, HttpMessageNotReadableException.class, ObjectOptimisticLockingFailureException.class } )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiExceptionMessages handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		String mensagemUsuario = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = pilhaDeErros(ex);
		List<MensagemExceptionDTO> erros = Arrays.asList(new MensagemExceptionDTO(mensagemUsuario, mensagemDesenvolvedor));
		return new ApiExceptionMessages(erros);
	}
	
	@ExceptionHandler({ InvalidDataAccessApiUsageException.class } )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiExceptionMessages handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex, WebRequest request){
		String mensagemUsuario = messageSource.getMessage("servidor.nao-precessou", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = pilhaDeErros(ex);
		List<MensagemExceptionDTO> erros = Arrays.asList(new MensagemExceptionDTO(mensagemUsuario, mensagemDesenvolvedor));
		return new ApiExceptionMessages(erros);
	}

}
