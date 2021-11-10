package com.hbt.semillero.ejb;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.result.NoMoreReturnsException;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
import com.hbt.semillero.dto.ConsultaComicTamanioNombreDTO;
import com.hbt.semillero.dto.ConsultaListadoTotalComicsDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidad.Comic;

/**
 *<b>Descripcion:<b> Clase Bean donde se determina la lógica 
 *de negocio. 
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal {

	@PersistenceContext
	public EntityManager em;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ComicDTO crearComic(ComicDTO comicDTO) throws Exception {
		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		
		ComicDTO comicDTOResult = new ComicDTO();
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.persist(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic ha sido creado exitosamente");
		return comicDTOResult;
	}
	
	   /**
	   * Método encargado de buscar comic
	   * basado en un parametro, en este caso el id del comic
	   * @return objeto de tipo consultaNombrePrecioDTO 
	   */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public ConsultaNombrePrecioComicDTO consultarNombrePrecioComic(Long idComic) {
		String consulta = "SELECT new com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO(c.nombre, c.precio)  "
						+ " FROM Comic c WHERE c.id = :idComic";
		ConsultaNombrePrecioComicDTO consultaNombrePrecioDTO = new ConsultaNombrePrecioComicDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNombrePrecioDTO = (ConsultaNombrePrecioComicDTO) consultaNativa.getSingleResult();
			consultaNombrePrecioDTO.setExitoso(true);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");	
		} catch (Exception e) {
			consultaNombrePrecioDTO.setExitoso(false);
			consultaNombrePrecioDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el comic: " + e);
		}

		return consultaNombrePrecioDTO;
	}

	
	@Override
	public List<ComicDTO> consultarComics() {
		String consulta = "SELECT (c)  "
				+ " FROM Comic c ";
		
		ComicDTO  comicDTO= new ComicDTO();
		List<ComicDTO> listadoComics= new ArrayList<ComicDTO>();
		try {
			Query consultaNativa = em.createQuery(consulta);
			List<Comic> listaComics = consultaNativa.getResultList();
			for(int i =0; i<listaComics.size(); i++)
			{ 
				comicDTO = this.convertirComicToComicDTO(listaComics.get(i));
				comicDTO.setExitoso(true);
		     	comicDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");
		     	listadoComics.add(comicDTO);
			}
			
			} catch (Exception e) {
					comicDTO.setExitoso(false);
					comicDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el listado de comics: " + e);
			}
		return  listadoComics;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO actualizarComic(ComicDTO comicDTO) throws Exception  {
		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		String nombreComic = comicDTO.getNombre();
		ComicDTO comicDTOResult = null;
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		em.merge(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic : "+ nombreComic + "ha sido actualizado exitosamente");
		return comicDTOResult;
		
	}

	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO eliminarComic(Long idComic) {
		String consulta = "DELETE " + "FROM Comic c WHERE c.id = :idComic";
		ResultadoDTO resultado = new ResultadoDTO();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("idComic", idComic);
			consultaNativa.executeUpdate();
			resultado.setExitoso(true);
			resultado.setMensajeEjecucion("el comic fue ELIMINADO");
		}
		catch(Exception e){
			resultado.setExitoso(false);
			resultado.setMensajeEjecucion("Ocurrio un error técnico: "+ e);
		}
		
		return resultado;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaComicTamanioNombreDTO consultarComicTamanioNombre(Short lengthComic) throws Exception {
		String consulta = "SELECT c.nombre "
				+ " FROM Comic c";
		ConsultaComicTamanioNombreDTO tamanioNombreDTO = new ConsultaComicTamanioNombreDTO();
		
		try {
			
			if(lengthComic == null || lengthComic >100) {
				throw new Exception ("El limite del tamaño del nombre NO puede ser vacio o MAYOR a 100");
			}
			
			Query consultaNativa = em.createQuery(consulta);
			List<String> listaNombres =  consultaNativa.getResultList();
			
			for (String nombreComic : listaNombres) {
			      
			     if(nombreComic.length() > lengthComic ) {
			    	 tamanioNombreDTO.getNombreSuperanTamaño().add(nombreComic);
			     }
			     else {
			    	tamanioNombreDTO.getNombresNoSuperanTamaño().add(nombreComic);
			     }
			     
			  }
				
			} catch (Exception e) {
					tamanioNombreDTO.setExitoso(false);
					tamanioNombreDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el listado de comics: " + e);
			}
		return  tamanioNombreDTO;
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
