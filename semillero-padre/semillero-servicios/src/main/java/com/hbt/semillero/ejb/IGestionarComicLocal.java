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
public interface IGestionarComicLocal {
	
	   /**
	   * Método abstracto encargado de buscar comic
	   * basado en un parametro, en este caso el id del comic
	   * @return objeto de tipo consultaNombrePrecioDTO 
	   */
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) throws Exception;
	
	
	   /**
	   * Método abstracto encargado de crear un comic
	   * basado en un objeto DTO, en este caso comicDTO
	   * @return objeto de tipo comicDTO 
	   * @param comicDTO
	   */
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	
	   /**
	   * Método abstracto encargado de eliminar un comic
	   * basado en el id del comic, en este caso idComic
	   * @return objeto de tipo ResultadoDTO 
	   * @param idComic (tipo Long)
	   */
	public ResultadoDTO eliminarComic(Long idComic);
	
	   /**
	   * Método abstracto encargado de consultar el total
	   * de los comics y retornar una lista 
	   * de objetos comicDTO
	   * @return lista de objetos de tipo comicDTO 
	   * 
	   */
	public List<ComicDTO> consultarComics();
	
	   /**
	   * Método abstracto encargado de consultar
	   * los comics en base a el tamaño de sus nombres
	   * basado primitivo Short lengthComic.
	   * @return objeto de tipo ConsultaComicTamanioNombreDTO 
	   * @param lengthComic
	   */

	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(Short lengthComic) throws Exception;
	
	   /**
	   * Método abstracto encargado de actualizar un comic
	   * basado en un objeto DTO, en este caso comicDTO
	   * @return objeto de tipo comicDTO 
	   * @param comicDTO
	   */

	public ComicDTO actualizarComic(ComicDTO comicDTO) throws Exception;


	public List<ComicDTO> consultarParametroComic(String parametro);
	
	   /**
	   * Método abstracto encargado de actualizar un comic
	   * basado en un objeto DTO, en este caso comicDTO
	   * @return objeto de tipo comicDTO 
	   * @param comicDTO
	   */

	public ComicDTO venderComic(ComicDTO comicDTO) throws Exception;
}
