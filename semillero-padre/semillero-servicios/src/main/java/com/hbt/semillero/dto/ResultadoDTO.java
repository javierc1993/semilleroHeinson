package com.hbt.semillero.dto;

import java.io.Serializable;

/**
 * DTO que indica el resultado de una 
 * ejecución.
 * 
 * @author Javier cuchumbe
 *
 */
public class ResultadoDTO implements Serializable {

	/**
	 * Serial id único que identifica una clase cuando lo
	 * serializamos
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Indicador de resultado.
	 */
	private boolean exitoso = false;
	/**
	 * Mensaje de ejecución.
	 */
	private String mensajeEjecucion;

	public ResultadoDTO() {
		
	}
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param exitoso
	 * @param mensajeEjecucion
	 */
	public ResultadoDTO(boolean exitoso, String mensajeEjecucion) {
		super();
		this.exitoso = exitoso;
		this.mensajeEjecucion = mensajeEjecucion;
	}

	/**
	 * Método que obtiene el valor de la propiedad exitoso
	 * 
	 * @return the exitoso
	 */
	public boolean getExitoso() {
		return exitoso;
	}

	/**
	 * Método que asigna el valor de la propiedad exitoso
	 * 
	 * @param exitoso the exitoso to set
	 */
	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}

	/**
	 * Método que obtiene el valor de la propiedad mensajeEjecucion
	 * 
	 * @return the mensajeEjecucion
	 */
	public String getMensajeEjecucion() {
		return mensajeEjecucion;
	}

	/**
	 * Método que asigna el valor de la propiedad mensajeEjecucion
	 * 
	 * @param mensajeEjecucion the mensajeEjecucion to set
	 */
	public void setMensajeEjecucion(String mensajeEjecucion) {
		this.mensajeEjecucion = mensajeEjecucion;
	}

}
