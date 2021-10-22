package semillero.pruebasUnitarias;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.enums.TematicaEnum;


public class ComicTest {
	public static void main(String[] args) {
		
		
		
	}
	
	private final static Logger log = Logger.getLogger(ComicTest.class);
	@BeforeTest
	public void inicializar() {
		BasicConfigurator.configure();  // inicializa el logger con una configuracion basica
		log.info(":::::::::::::::::::::::::::: INICIAN PRUEBAS UNITARIAS COMIC:::::::::::::::::::::::::::: ");
		Comic comic1 = new Comic();
		comic1.setNombre("comic1");
		comic1.setEditorial("editComic1");
		comic1.setTematicaEnum(TematicaEnum.HORROR);
		comic1.setColeccion("colección1");
		comic1.setNumeroPaginas(100);
		comic1.setPrecio(new BigDecimal(100000));
		comic1.setAutores("autor1");
		comic1.setColor(false);
		comic1.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic1.setEstadoEnum(EstadoEnum.ENABLED);
		comic1.setCantidad((long) 20);
		
		Comic comic2 = new Comic();
		comic2.setNombre("comic1");
		comic2.setEditorial("editComic1");
		comic2.setTematicaEnum(TematicaEnum.HORROR);
		comic2.setColeccion("colección1");
		comic2.setNumeroPaginas(100);
		comic2.setPrecio(new BigDecimal(100000));
		comic2.setAutores("autor1");
		comic2.setColor(false);
		comic2.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic2.setEstadoEnum(EstadoEnum.UNABLED);
		comic2.setCantidad((long)5);
		
		Comic comic3 = new Comic();
		comic3.setNombre("comic1");
		comic3.setEditorial("editComic1");
		comic3.setTematicaEnum(TematicaEnum.HORROR);
		comic3.setColeccion("colección1");
		comic3.setNumeroPaginas(100);
		comic3.setPrecio(new BigDecimal(100000));
		comic3.setAutores("autor1");
		comic3.setColor(false);
		comic3.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic3.setEstadoEnum(EstadoEnum.ENABLED);
		comic3.setCantidad((long)100);
		
		Comic comic4 = new Comic();
		comic4.setNombre("comic1");
		comic4.setEditorial("editComic1");
		comic4.setTematicaEnum(TematicaEnum.HORROR);
		comic4.setColeccion("colección1");
		comic4.setNumeroPaginas(100);
		comic4.setPrecio(new BigDecimal(100000));
		comic4.setAutores("autor1");
		comic4.setColor(false);
		comic4.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic4.setEstadoEnum(EstadoEnum.UNABLED);
		comic4.setCantidad((long)5);
		
		Comic comic5 = new Comic();
		comic5.setNombre("comic1");
		comic5.setEditorial("editComic1");
		comic5.setTematicaEnum(TematicaEnum.HORROR);
		comic5.setColeccion("colección1");
		comic5.setNumeroPaginas(100);
		comic5.setPrecio(new BigDecimal(100000));
		comic5.setAutores("autor1");
		comic5.setColor(false);
		comic5.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic5.setEstadoEnum(EstadoEnum.ENABLED);
		comic5.setCantidad((long)50);
		
		Comic comic6 = new Comic();
		comic6.setNombre("comic1");
		comic6.setEditorial("editComic1");
		comic6.setTematicaEnum(TematicaEnum.CIENCIA_FICCION);
		comic6.setColeccion("colección1");
		comic6.setNumeroPaginas(100);
		comic6.setPrecio(new BigDecimal(100000));
		comic6.setAutores("autor1");
		comic6.setColor(false);
		comic6.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic6.setEstadoEnum(EstadoEnum.UNABLED);
		comic6.setCantidad((long)10);
		
		Comic comic7 = new Comic();
		comic7.setNombre("comic1");
		comic7.setEditorial("editComic1");
		comic7.setTematicaEnum(TematicaEnum.CIENCIA_FICCION);
		comic7.setColeccion("colección1");
		comic7.setNumeroPaginas(100);
		comic7.setPrecio(new BigDecimal(100000));
		comic7.setAutores("autor1");
		comic7.setColor(false);
		comic7.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic7.setEstadoEnum(EstadoEnum.ENABLED);
		comic7.setCantidad((long)30);
		
		Comic comic8 = new Comic();
		comic8.setNombre("comic1");
		comic8.setEditorial("editComic1");
		comic8.setTematicaEnum(TematicaEnum.CIENCIA_FICCION);
		comic8.setColeccion("colección1");
		comic8.setNumeroPaginas(100);
		comic8.setPrecio(new BigDecimal(100000));
		comic8.setAutores("autor1");
		comic8.setColor(false);
		comic8.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic8.setEstadoEnum(EstadoEnum.UNABLED);
		comic8.setCantidad((long)25);
		
		Comic comic9 = new Comic();
		comic9.setNombre("comic1");
		comic9.setEditorial("editComic1");
		comic9.setTematicaEnum(TematicaEnum.CIENCIA_FICCION);
		comic9.setColeccion("colección1");
		comic9.setNumeroPaginas(100);
		comic9.setPrecio(new BigDecimal(100000));
		comic9.setAutores("autor1");
		comic9.setColor(false);
		comic9.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic9.setEstadoEnum(EstadoEnum.UNABLED);
		comic9.setCantidad((long)20);
		
		Comic comic10 = new Comic();
		comic10.setNombre("comic1");
		comic10.setEditorial("editComic1");
		comic10.setTematicaEnum(TematicaEnum.CIENCIA_FICCION);
		comic10.setColeccion("colección1");
		comic10.setNumeroPaginas(100);
		comic10.setPrecio(new BigDecimal(100000));
		comic10.setAutores("autor1");
		comic10.setColor(false);
		comic10.setFechaVenta(LocalDate.of(1999, Month.JANUARY,07));
		comic10.setEstadoEnum(EstadoEnum.ENABLED);
		comic10.setCantidad((long)94);
		
		ArrayList<Object> listaComics = new ArrayList<Object>();
		listaComics.add((Object) comic1);
		listaComics.add((Object) comic2);
		listaComics.add((Object) comic3);
		listaComics.add((Object) comic4);
		listaComics.add((Object) comic5);
		listaComics.add((Object) comic6);
		listaComics.add((Object) comic7);
		listaComics.add((Object) comic8);
		listaComics.add((Object) comic9);
		listaComics.add((Object) comic10);
	}
	 
	@Test (enabled=true)
	public void ComicsActivos(Object o) {
		
		log.info("Inicia ejecucion del metodo ComicsActivos()");
		if(o instanceof Comic) {
			    Comic comicObjeto = new Comic();
				if(comicObjeto.getEstadoEnum().equals(EstadoEnum.ENABLED))
				System.out.println(o.toString());
				
				try {
					if(!comicObjeto.getEstadoEnum().equals(EstadoEnum.ENABLED)) {
						throw new Exception("El Comic esta inactivo");
					}
					
				} catch (Exception e) {
					Assert.assertEquals(e.getMessage(), "Comic inactivo");
				}
				
			
		}
	}	
	
	@AfterTest
	public void finalizaPruebasUnitarias() {
		log.info(":::::::::::::::::::::::::::: FINALIZAN PRUEBAS UNITARIAS :::::::::::::::::::::::::::: ");
	}
}
		
		
		
		
		
	