/**
 * 
 */
package com.musala.demo.aop;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.musala.demo.common.ErrorCode;
import com.musala.demo.exception.DataNotFoundException;
import com.musala.demo.model.ErrorResponseModel;

/**
 * Catch all exception propagated to
 * GatewayController and deliver the 
 * Appropriate response
 * @author aeltayary
 *
 */
@ControllerAdvice
public class GatewayAdvice {


@Autowired
private MessageSource  msg;





/**
 * Data Base exception
 * @param daex
 * @return
 */
@ExceptionHandler(DataAccessException.class)
@ResponseBody
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseModel HandleDBException(DataAccessException daex) {
	ErrorResponseModel resp= new ErrorResponseModel();
	resp.setErrorCode(ErrorCode.DATABASE_ERROR);
	resp.setErrorMessage(daex.getMessage());
	return resp;
}


@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public ErrorResponseModel HandleMethodArgumentNotValidException(MethodArgumentNotValidException argex,Locale locale) {
	ErrorResponseModel resp= new ErrorResponseModel();
	resp.setErrorCode(ErrorCode.VALIDATION_ERROR);
	StringBuilder errorMsg=new StringBuilder();
	argex.getBindingResult().getAllErrors().stream().forEach((ObjectError)->{errorMsg.append(ObjectError.getDefaultMessage());});
	resp.setErrorMessage(errorMsg.toString());
	return resp;
}



/**
 * 
 * @param ex
 * @param locale
 * @return
 */

@ExceptionHandler(HttpMessageNotReadableException.class)
@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public ErrorResponseModel HandleHttpMessageNotReadableExceptionn(HttpMessageNotReadableException ex,Locale locale) {
	ErrorResponseModel resp= new ErrorResponseModel();
	resp.setErrorCode(ErrorCode.VALIDATION_ERROR);
	resp.setErrorMessage(msg.getMessage(ErrorCode.VALIDATION_ERROR,null,locale));
	return resp;
}





/**
 * Handle MissingPathVariableException
 * @param ex
 * @param locale
 * @return
 */
@ExceptionHandler(MissingPathVariableException.class)
@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public ErrorResponseModel HandleMissingPathVariableException(MissingPathVariableException ex,Locale locale) {
	ErrorResponseModel resp= new ErrorResponseModel();
	resp.setErrorCode(ErrorCode.SERVER_ERROR);
	String errorMsg=ex.getMessage()==null? msg.getMessage(ErrorCode.SERVER_ERROR,null,locale):ex.getMessage();
	resp.setErrorMessage(errorMsg);
	return resp;
}


/**
 * 
 * @param notFounex
 * @param locale
 * @return
 */


@ExceptionHandler(DataNotFoundException.class)
@ResponseBody
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public ErrorResponseModel HandleDataNotFoundException(DataNotFoundException notFounex,Locale locale) {
	ErrorResponseModel resp= new ErrorResponseModel();
	resp.setErrorCode(ErrorCode.SERVER_ERROR);
	String errorMsg=notFounex.getMessage()==null? msg.getMessage(ErrorCode.SERVER_ERROR,null,locale):notFounex.getMessage();
	resp.setErrorMessage(errorMsg);
	return resp;
}



/**
 * General Exception
 * @param ex
 * @return
 */
@ExceptionHandler(Exception.class)
@ResponseBody
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public ErrorResponseModel HandleException(Exception ex,Locale locale) {
	ErrorResponseModel resp= new ErrorResponseModel();
	resp.setErrorCode(ErrorCode.SERVER_ERROR);
	String errorMsg=ex.getMessage()==null? msg.getMessage(ErrorCode.SERVER_ERROR,null,locale):ex.getMessage();
	resp.setErrorMessage(errorMsg);
	return resp;
}




}
