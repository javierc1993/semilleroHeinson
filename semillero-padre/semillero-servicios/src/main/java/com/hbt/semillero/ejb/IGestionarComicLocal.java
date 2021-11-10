package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaListadoTotalComicsDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ResultadoDTO;

@Local
public interface IGestionarComicLocal {
	
	/*
	   * Método encargado de buscar comic
	   * basado en un parametro, en este caso el id del comic
	   * @return objeto de tipo consultaNombrePrecioDTO 
	   */
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) throws Exception;

	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception;
	
	public ResultadoDTO eliminarComic(Long idComic);
	
	public List<ComicDTO> consultarComics();

	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(Short lengthComic) throws Exception;

	public ComicDTO actualizarComic(ComicDTO comicDTO) throws Exception;
}
