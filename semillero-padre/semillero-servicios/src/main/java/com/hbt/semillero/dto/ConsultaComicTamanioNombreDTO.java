package com.hbt.semillero.dto;
import java.util.ArrayList;
import java.util.List;

/**
 *<b>Descripcion:<b> DTO utilizado para 
 *el taller 2 semillero heinsonh 
 *donde se crea el DTO para mostrar 
 *la información de dos listas con nombres
 *de comic
 *@author JAVIER CUCHUMBE. 
 */

public class ConsultaComicTamanioNombreDTO extends ResultadoDTO {

	/**
	 * En los DTO va lo que quiero mostrar o enviar por el servicio
	 */
	/**
	 * id único que identifica una clase cuando lo
	 * serializamos
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * atributo de tipo String 
	 * para el nombre del comic
	 */
	private String nombre;
	/**
	 * atributo de tipo lista
	 * que contendran los nombres 
	 * de los comics que superan el
	 * tamaño
	 */
	private List<String> nombreSuperanTamaño;
	
	/**
	 * atributo de tipo lista
	 * que contendran los nombres
	 * de los comics que no superan
	 * el tamaño
	 */
	private List<String> nombresNoSuperanTamaño;

	/**
	 *
	 * Constructor de la clase.
	 */
	public ConsultaComicTamanioNombreDTO() {
		this.nombre = nombre;
		this.nombreSuperanTamaño = new ArrayList<>();
		this.nombresNoSuperanTamaño= new ArrayList<>();
	}
	
	/**
	 * Metodo encargado de retornar el valor del atributo nombreSuperanTamaño
	 * @return El nombreSuperanTamaño asociado a la clase
	 */
	public List<String> getNombreSuperanTamaño() {
		return nombreSuperanTamaño;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo nombreSuperanTamaño
	 * @param nombreSuperanTamaño El nuevo nombreSuperanTamaño a modificar.
	 */
	public void setNombreSuperanTamaño(List<String> nombreSuperanTamaño) {
		this.nombreSuperanTamaño = nombreSuperanTamaño;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo nombresNoSuperanTamaño
	 * @return El nombresNoSuperanTamaño asociado a la clase
	 */
	public List<String> getNombresNoSuperanTamaño() {
		return nombresNoSuperanTamaño;
	}

	/**
	 * Metodo encargado de modificar el valor del atributo nombresNoSuperanTamaño
	 * @param nombresNoSuperanTamaño El nuevo nombresNoSuperanTamaño a modificar.
	 */
	public void setNombresNoSuperanTamaño(List<String> nombresNoSuperanTamaño) {
		this.nombresNoSuperanTamaño = nombresNoSuperanTamaño;
	}
	
	/**
	 * 
	 * Metodo encargado de 
	 * <b>retornar el valor del atributo nombre</b>
	 * @author FIET1
	 * 
	 * @return nombre
	 */
	
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * Metodo encargado de 
	 * <b>modificar el valor del atributo nombre</b>
	 * @author FIET1
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
