package com.hbt.semillero.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarCompraComic;
/**
 *<b>Descripcion:<b> Clase donde se declaran 
 *los puntos de acceso y las rutas de los métodos
 *declarados en el BEAN
 *@author JAVIER CUCHUMBE
 */
@Path("/gestionarCompraComic")
public class GestionarCompraComicRest {
	
	   /**
	   * Declaración de la interfaz
	   */
	@EJB
	private IGestionarCompraComic gestionarCompraComic;

	



	/**
	   * ruta:  /venderComic
	   * tipo: POST
	   * Método encargado de recibir un DTO de tipo
	   * ComiCDTO y retorna un DTO de tipo ComicDTO
	   * @return objeto de tipo ComicDTO
	   * @param DTO de tipo ComicDTO
	   * @author javier cuchumbe
	   */
	@POST
	@Path("/venderComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO venderComic(ComicDTO comicDTO) throws Exception {
	
		
		
			return this.gestionarCompraComic.venderComic(comicDTO);

	} 

	
}
