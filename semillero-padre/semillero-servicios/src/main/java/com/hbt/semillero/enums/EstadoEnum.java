package com.hbt.semillero.enums;
/**
 * <b>Descripción:<b> Clase que determina la enumeracion para representar los
 * tipos de estados que puede presentar un comic
 * 
 * @author yehison javier cuchumbe pencua
 * @version
 */
public enum EstadoEnum {
	ENABLED("enum.estado.stock"), 
	UNABLED("enum.estado.notstock"),
	ACTIVO("enum.estado.activo"),
	INACTIVO("enum.estado.inactivo"),
	;
	/**
	 * Atributo que contiene la clave del mensaje.
	 */
	private String codeMessage;
	
	/**
	 * Constructor que recibe como párametro el código del mensaje
	 * 
	 * @param codeMessage, Clave del mensaje.
	 */
	EstadoEnum(String codeMessage) {
		this.setCodeMessage(codeMessage);
	}
	
	/**
	 * Metodo que retorna el valor del atributo
	 * 
	 * @return cadena con el código del mensaje
	 */
	public String getCodeMessage() {
		return codeMessage;
	}
	
	/**
	 * Método que establece el valor del atributo
	 *
	 * @param codeMessage
	 */
	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}
}
