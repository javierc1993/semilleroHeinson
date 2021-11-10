package com.hbt.semillero.ejb;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaVentaComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.enums.TematicaEnum;

/**
 *<b>Descripcion:<b> Clase Bean donde se determina la lógica 
 *de negocio. 
 *@author JAVIER CUCHUMBE
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarCompraComicBean implements IGestionarCompraComic {
	
	
	@PersistenceContext
	public EntityManager em;
	

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO venderComic(ComicDTO comicDTO) throws Exception  {
	     
		Long idComic = comicDTO.getId();
		Long cantidadComprar = comicDTO.getCantidad();
		
		String consultaEstado = "SELECT new com.hbt.semillero.dto.ConsultaVentaComicDTO(c.estadoEnum, c.cantidad)  "
		+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaVentaComicDTO consultaComicVenta = new ConsultaVentaComicDTO();
		EstadoEnum estadoComic;
		Long cantidadExistente;
		Long cero = (long) 0;
		ComicDTO comicDTOResult = new ComicDTO();
		try {
			Query consultaNativa = em.createQuery(consultaEstado);
			consultaNativa.setParameter("idComic", idComic);
			consultaComicVenta = (ConsultaVentaComicDTO) consultaNativa.getSingleResult();
			estadoComic = consultaComicVenta.getEstado();
			cantidadExistente = consultaComicVenta.getCantidad();
			if(estadoComic == EstadoEnum.INACTIVO ) {
				comicDTOResult.setExitoso(false);
				comicDTOResult.setMensajeEjecucion("El comic seleccionado no cuenta con STOCK en bodega ");
			}
			else {
				
				if(cantidadExistente<cantidadComprar)
				{
					throw new Exception("La cantidad existente del comic es: "+ cantidadExistente+" y supera la ingresada: " + cantidadComprar);
				}
				
				else {
					LocalDate ahora = LocalDate.now();
					String nombreComic = comicDTO.getNombre();
					Comic comic = this.convertirComicDTOToComic(comicDTO);
					
					if(cantidadComprar == cantidadExistente) {
				     comic.setEstadoEnum(EstadoEnum.INACTIVO);
				     comic.setCantidad(cero);
					}
					comic.setFechaVenta(ahora);
					em.merge(comic);
					comicDTOResult = this.convertirComicToComicDTO(comic);
					comicDTOResult.setExitoso(true);
					comicDTOResult.setMensajeEjecucion("La compra del comic : "+ nombreComic + " fue exitosa");
				}
			
			}
		} catch(Exception e){
			comicDTOResult.setExitoso(false);
			comicDTOResult.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el listado de comics: " + e);
		}
		
		return comicDTOResult;
		
	}
	 




	
	

	
	
	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		comicDTO.setId(comic.getId());
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		comic.setId(comicDTO.getId());
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}

}
