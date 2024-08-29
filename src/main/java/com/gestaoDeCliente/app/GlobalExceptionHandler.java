package com.gestaoDeCliente.app;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gestaoDeCliente.app.exceptions.AtividadeNotFoundException;
import com.gestaoDeCliente.app.exceptions.ErrorResponse;

public class GlobalExceptionHandler {

	 	@ExceptionHandler(AtividadeNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    @ResponseBody
	    public ErrorResponse handleResponseStatusException(AtividadeNotFoundException ex) {
	 		
	 		return new ErrorResponse("Not Found", ex.getMessage());
	    }
}
