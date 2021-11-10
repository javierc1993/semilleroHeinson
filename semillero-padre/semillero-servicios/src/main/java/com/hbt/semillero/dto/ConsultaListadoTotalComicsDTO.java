package com.hbt.semillero.dto;
import java.util.ArrayList;
import java.util.List;

import com.hbt.semillero.entidad.Comic;

public class ConsultaListadoTotalComicsDTO extends ResultadoDTO {
	
	private static final long serialVersionUID = 1L;
	private List<Comic> listadoComics;
	
	public ConsultaListadoTotalComicsDTO() {
		this.listadoComics=new ArrayList<>();
	}
	/**
	 * Metodo encargado de retornar el valor del atributo listadoComics
	 * @return El listadoComics asociado a la clase
	 */
	public List<Comic> getListadoComics() {
		return listadoComics;
	}
	/**
	 * Metodo encargado de modificar el valor del atributo listadoComics
	 * @param listadoComics El nuevo listadoComics a modificar.
	 */
	public void setListadoComics(List<Comic> listadoComics) {
		this.listadoComics = listadoComics;
	}
	
}