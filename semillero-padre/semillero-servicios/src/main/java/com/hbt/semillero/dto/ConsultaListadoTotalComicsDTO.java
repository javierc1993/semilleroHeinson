package com.hbt.semillero.dto;
import java.util.ArrayList;
import java.util.List;

import com.hbt.semillero.entidad.Comic;
/**
 *<b>Descripcion:<b> DTO usado para obtner 
 *el listado total de los comics
 *@see SEMILLERO HEINSONH
 *@author JAVIER CUCHUMBE. 
 */
public class ConsultaListadoTotalComicsDTO extends ResultadoDTO {
	/**
	 * id único que identifica una clase cuando lo
	 * serializamos
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * atributo lista
	 * que contiene objetos de tipo comic
	 * 
	 */
	private List<Comic> listadoComics;
	
	/**
	 * 
	 * Constructor de la clase.
	 */
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