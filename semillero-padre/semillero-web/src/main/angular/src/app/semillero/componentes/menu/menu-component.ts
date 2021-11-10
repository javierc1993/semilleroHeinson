import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

/**
 * @description Componente menu, el cual contiene la logica para direccionar a los modulos
 * desarrollados
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
  selector: 'home-page',
  templateUrl: './menu-component.html',
})
export class MenuComponent implements OnInit {

  /**
   * Constructor de la clase
   * @param router permite direccionar a otros componentes
   */
  constructor(private router: Router) {

  }

  /**
   * Evento angular que se ejecuta al iniciar el componente
   */
  ngOnInit(): void {

  }

  /**
   * @description Metodo encargado de direccionar al componente de gestionar comic
   * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
   */
  public navegarGestionarComic(): void {
    this.router.navigate(['gestionarComic']);
  }

  /**
   * @description Metodo encargado de direccionar al componente de gestionar comic
   * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
   */
  public navegarHome(): void {
    this.router.navigate(['bienvenida']);
  }

  /**
   * @description Metodo encargado de direccionar al componente de comprar comic
   * @author Yehison Javier Cuchumbe Pencua <javierc@unicauca.edu.co>
   */
  public navegarGestionarCompra(): void {
    this.router.navigate(['comprarComic']);
  }
}