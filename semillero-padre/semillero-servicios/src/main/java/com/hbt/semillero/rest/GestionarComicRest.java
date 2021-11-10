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
import com.hbt.semillero.dto.ConsultaListadoTotalComicsDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.ejb.IGestionarComicLocal;

@Path("/gestionarComic")
public class GestionarComicRest {
	
	@EJB
	private IGestionarComicLocal gestionarComicLocal;

	@GET
	@Path("/consultarNombrePrecioComic")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(@QueryParam("idComic") Long idComic) throws Exception {
		return this.gestionarComicLocal.consultarNombrePrecioComic(idComic);
	}
	
	@GET
	@Path("/consultarComics")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComicDTO> consultarComics() {
		return  this.gestionarComicLocal.consultarComics();
	}
	
	@GET
	@Path("/consultarComicTamanioNombre")
	@Produces(MediaType.APPLICATION_JSON)
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(@QueryParam("lengthComic") Short lengthComic) throws Exception {
		
		return  this.gestionarComicLocal.consultarComicTamanioNombre(lengthComic);
	}
	
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
	
}
