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
import com.hbt.semillero.ejb.IGestionarComicLocal;
/**
 *<b>Descripcion:<b> Clase donde se declaran 
 *los puntos de acceso y las rutas de los métodos
 *declarados en el BEAN
 *@author JAVIER CUCHUMBE
 */
@Path("/gestionarComic")
public class GestionarComicRest {
	
	   /**
	   * Declaración de la interfaz
	   */
	@EJB
	private IGestionarComicLocal gestionarComicLocal;

	
	/**
	   * ruta:  /consultarNombrePrecioComic
	   * tipo: GET
	   * Método encargado de enviar el  parametro de tipo Long idComic,
	   * y retornar un DTO de tipo ConsultaNombrePrecioComicDTO
	   * @return objeto de tipo ConsultaNombrePrecioComicDTO
	   * @param primitivo de tipo Long idComic
	   * @author javier cuchumbe
	   */
	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) throws Exception {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}
	
	   /**
	   * ruta:  /consultarComics
	   * tipo: GET
	   * Método encargado de retornar todos los comics
	   * en una lista de ComicDTO 
	   * @return lista de objetos de tipo ComicDTO
	   * @author javier cuchumbe
	   */
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComics() {
		return  this.gestionarComicLocal.consultarComics();
	}
	
	   /**
	   * ruta:  /consultarComicTamanioNombre
	   * tipo: GET
	   * Método encargado de recibir un prmitivo Short ,
	   * y retorna un DTO de tipo ConsultaComicTamanioNombreDTO
	   * @return objeto de tipo ConsultaComicTamanioNombreDTO
	   * @param primitivo de tipo Short lengthComic
	   * @author javier cuchumbe
	   */
	
	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(@QueryParam("lengthComic") Short lengthComic) throws Exception {
		
		return  this.gestionarComicLocal.consultarComicTamanioNombre(lengthComic);
	}
	
	  /**
	   * ruta:  /eliminarComic
	   * tipo: GET
	   * Método encargado de recibir un prmitivo Long ,
	   * y retorna un DTO de tipo ResultadoDTO
	   * @return objeto de tipo ResultadoDTO
	   * @param primitivo de tipo Long idComic
	   * @author javier cuchumbe
	   */
	@GET
	@Path("/eliminarComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO eliminarComic( @QueryParam("idComic") Long idComic) throws Exception {
		ResultadoDTO comicDTOResult = new ResultadoDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.eliminarComic(idComic);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	} 
	
	 
	   /**
	   * ruta:  /crearComic
	   * tipo: POST
	   * Método encargado de recibir un DTO de tipo
	   * ComiCDTO y retorna un DTO de tipo ComicDTO
	   * @return objeto de tipo ComicDTO
	   * @param DTO de tipo ComicDTO
	   * @author javier cuchumbe
	   */
	@POST
	@Path("/crearComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO crearComic(ComicDTO comicDTO) {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.crearComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	} 
	
	/**
	   * ruta:  /actualizarComic
	   * tipo: POST
	   * Método encargado de recibir un DTO de tipo
	   * ComiCDTO y retorna un DTO de tipo ComicDTO
	   * @return objeto de tipo ComicDTO
	   * @param DTO de tipo ComicDTO
	   * @author javier cuchumbe
	   */
	@POST
	@Path("/actualizarComic")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComicDTO actualizarComic(ComicDTO comicDTO) throws Exception {
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			comicDTOResult = this.gestionarComicLocal.actualizarComic(comicDTO);
		}  catch (Exception e) {
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico, causa: " + e.getMessage());
		}
		return comicDTOResult;
	} 
	

	
}
