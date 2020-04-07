/**
 * 
 */
package com.musala.demo.model;

/**
 * Class Object will be returned in case of
 * (1) Exception 	error
 * (3) Validation 	error
 * (4) Backend 		error
 * (5) DataBase 	error
 * 
 * 
 * @author aeltayary
 *
 */
public class ErrorResponseModel {

	private String errorCode;;
	private String errorMessage;
	
/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
public String getErrorMessage() {
	return errorMessage;
}
public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}
@Override
public String toString() {
	return "ErrorResponseModel [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
}



}
