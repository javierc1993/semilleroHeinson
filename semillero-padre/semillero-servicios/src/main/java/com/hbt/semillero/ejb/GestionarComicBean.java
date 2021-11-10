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
import com.hbt.semillero.dto.ConsultaNombrePrecioComicDTO;
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
public class GestionarComicBean implements IGestionarComicLocal {
	
	
	@PersistenceContext
	public EntityManager em;
	
	    /**
	   * Método encargado de crear un comic
	   * basado en un objeto DTO, en este caso comicDTO
	   * @return objeto de tipo comicDTO 
	   * @param comicDTO
	   * @author SEMILLERO HEINSONH
	   */
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
	   * y devolver un objeto consultaNombrePrecioDTO
	   * de tipo ConsultaNombrePrecioComicDTO
	   * @return objeto de tipo consultaNombrePrecioDTO 
	   * @author SEMILLERO HEINSONH
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
	
	   /**
	   * Método encargado de consultar el listado
	   * total de comics en BD.
	   * @return lista de objetos de tipo ComicDTO 
	   * @author JAVIER CUCHUMBE
	   */
	
	@Override
	public List<ComicDTO> consultarComics() {
		String consulta = "SELECT (c)  "
				+ " FROM Comic c ";
		
		ComicDTO  comicDTO= new ComicDTO();
		List<ComicDTO> listadoComics= new ArrayList<ComicDTO>();
		try {
			Query consultaNativa = em.createQuery(consulta);
			@SuppressWarnings("unchecked")
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
	
	   /**
	   * Método encargado de actualizar un  comic
	   * basado en un objeto, en este caso comicDTO
	   * @return objeto de tipo ComicDTO
	   * @param objeto de tipo ComicDTO
	   * @author javier cuchumbe
	   */

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
		comicDTOResult.setMensajeEjecucion("El comic : "+ nombreComic + " ha sido actualizado exitosamente");
		return comicDTOResult;
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ComicDTO venderComic(ComicDTO comicDTO) throws Exception  {
		
		if(comicDTO.getNombre() == null) {
			throw new Exception("El campo nombre es requerido");
		}
		LocalDate ahora = LocalDate.now();
		String nombreComic = comicDTO.getNombre();
		ComicDTO comicDTOResult = null;
		Comic comic = this.convertirComicDTOToComic(comicDTO);
		comic.setFechaVenta(ahora);
		em.merge(comic);
		comicDTOResult = this.convertirComicToComicDTO(comic);
		comicDTOResult.setExitoso(true);
		comicDTOResult.setMensajeEjecucion("El comic : "+ nombreComic + " ha sido actualizado exitosamente");
		return comicDTOResult;
		
	}
	 
	    /**
	   * Método encargado de eliminar un  comic
	   * basado en un parametro, en este caso idComic
	   * @return objeto de tipo ResultadoDTO
	   * @param primitivo de tipo Long idCcmic
	   * @author javier cuchumbe
	   */
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


	/**
	   * Método encargado de consultar 
	   * y clasificar los nombres de los comics 
	   * en listas basado en un parametro
	   * de tipo primitivo Short lengthComic
	   * retornando un objeto de tipo ConsultaComicTamanioNombreDTO
	   * basado en un objeto, en este caso comicDTO
	   * @return objeto de tipo ConsultaComicTamanioNombreDTO
	   * @param primitivo de tipo Short lengthComic
	   * @author javier CUCHUMBE
	   */
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
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarParametroComic(String parametro) {
		String consulta = "SELECT c  "
				+ " FROM Comic c WHERE  c.nombre = :parametro OR c.editorial = :parametro" ;
			
		ComicDTO  comicDTO= new ComicDTO();
		List<ComicDTO> listadoComics= new ArrayList<ComicDTO>();
		try {
			Query consultaNativa = em.createQuery(consulta);
			consultaNativa.setParameter("parametro", parametro);
			@SuppressWarnings("unchecked")
			List<Comic> listaComics = consultaNativa.getResultList();
			if(listaComics == null || listaComics.size() == 0)
			{
				comicDTO.setExitoso(false);
		     	comicDTO.setMensajeEjecucion("No hay resultados a su busqueda");
		     	listadoComics.add(comicDTO);
			}
			else {
			for(int i =0; i<listaComics.size(); i++)
			{ 
				comicDTO = this.convertirComicToComicDTO(listaComics.get(i));
				comicDTO.setExitoso(true);
		     	comicDTO.setMensajeEjecucion("Se ejecuto exitosamente la consulta");
		     	listadoComics.add(comicDTO);
			}
			}
			
			} catch (Exception e) {
					comicDTO.setExitoso(false);
					comicDTO.setMensajeEjecucion("Se ha presentado un error tecnico al consultar el listado de comics: " + e);
			}
		return  listadoComics;
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
