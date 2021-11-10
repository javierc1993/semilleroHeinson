import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { isEmpty } from 'rxjs-compat/operator/isEmpty';
import { ComicDTO } from '../../dto/comic-dto';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';

@Component({
  selector: 'app-comprar-comic',
  templateUrl: './comprar-comic.component.html',
  styleUrls: ['./comprar-comic.component.css']
})
export class ComprarComicComponent implements OnInit {
  public cantidad : number;
  public comicDTO : ComicDTO;
  public listaComics : Array<ComicDTO>;
  public mostrarTabla : Boolean;
  public mostrarAlert : Boolean;
  public mostrarDialogCompra : Boolean;
  public ventaComic : ComicDTO;
  public compraRealizada : Boolean;
  public mensaje : string;


  constructor(private router: Router, private gestionComicsService : GestionarComicService) { }

  ngOnInit() {
    this.comicDTO = new ComicDTO();
    this.ventaComic = new ComicDTO();
    this.listaComics = new Array<ComicDTO>();
    //this.consultarComics();
    this.mostrarTabla = false;
    this.mostrarAlert= false;
    this.mostrarDialogCompra = false;
    this.compraRealizada=false;
  }

  public onBlurEvent(event : any): void{
    this.gestionComicsService.consultarParametroComic(event.target.value).subscribe(data=>{
      
        if(data[0].exitoso){
          this.mostrarAlert =false;
          this.listaComics = data;
          this.mostrarTabla = true;
        }
        else{
          this.listaComics =data;
          this.mostrarAlert=true;
          console.log(data[0].mensajeEjecucion);
        }
    }, error =>{
      console.log(error);
    });
  }

  public confirmarCompraComic(comic : ComicDTO): void{
    this.mostrarDialogCompra=true;
    this.ventaComic = comic;
  }

  public cancelarAction(): void{
    this.mostrarDialogCompra=false;
  }

  public generarCompraComic(ventaComic : ComicDTO): void{
    let cantidadActual = ventaComic.cantidad;
    this.ventaComic.cantidad = cantidadActual - this.cantidad;
    console.log( Date.now());
    console.log(cantidadActual-this.cantidad);
    this.gestionComicsService.venderComic(ventaComic).subscribe(data=>{
      console.log(data);
     if(data.exitoso){
       this.mostrarDialogCompra=false;
       this.compraRealizada=true;
       this.mensaje= "compra realizada con exito"
     }
     else{
      this.compraRealizada=true;
      this.mensaje = data.mensajeEjecucion;
     }

    });
  }
  public consultarComics(){
    
    this.gestionComicsService.consultarComics().subscribe(data=>{
      
      if(data[0].exitoso){
        this.listaComics = data;
      
      }
      else{
        console.log(data[0].mensajeEjecucion);
      }  
    }, error =>{
      console.log(error);
    });
  }




}
