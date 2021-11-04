package com.hbt.semillero.dto;

public class ConsultaComicTamanioNombreDTO extends ResultadoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	
	public ConsultaComicTamanioNombreDTO() {
		//Constructor vacio
	}
	
	public ConsultaComicTamanioNombreDTO(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
