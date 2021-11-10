package com.hbt.semillero.dto;

import java.math.BigDecimal;

import com.hbt.semillero.enums.EstadoEnum;

public class ConsultaVentaComicDTO extends ResultadoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EstadoEnum estado;
	private Long cantidad;
	
	public ConsultaVentaComicDTO() {
		//Constructor vacio
	}
	
	public ConsultaVentaComicDTO(EstadoEnum estado, Long cantidad) {
		this.estado = estado;
		this.cantidad = cantidad;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo estado
	 * @return El estado asociado a la clase
	 */
	public EstadoEnum getEstado() {
		return estado;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo estado
	 * @param estado El nuevo estado a modificar.
	 */
	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo cantidad
	 * @return El cantidad asociado a la clase
	 */
	public Long getCantidad() {
		return cantidad;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo cantidad
	 * @param cantidad El nuevo cantidad a modificar.
	 */
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	

}
