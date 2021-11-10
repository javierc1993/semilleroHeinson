package com.hbt.semillero.dto;
import java.util.ArrayList;
import java.util.List;

public class ConsultaComicTamanioNombreDTO extends ResultadoDTO {

	/**
	 * En los DTO va lo que quiero mostrar o enviar por el servicio
	 */
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<String> nombreSuperanTamaño;

	
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
	private List<String> nombresNoSuperanTamaño;
	
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
