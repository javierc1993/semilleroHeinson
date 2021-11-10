package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;
/**
 *<b>Descripcion:<b> Clase interface donde se declaran los métodos
 *abstractos.
 *@author JAVIER CUCHUMBE
 */
@Local
public interface IGestionarCompraComic {
	
	
	   /**
	   * Método abstracto encargado de actualizar un comic
	   * basado en un objeto DTO, en este caso comicDTO
	   * @return objeto de tipo comicDTO 
	   * @param comicDTO
	   */

	public ComicDTO venderComic(ComicDTO comicDTO) throws Exception;
}
