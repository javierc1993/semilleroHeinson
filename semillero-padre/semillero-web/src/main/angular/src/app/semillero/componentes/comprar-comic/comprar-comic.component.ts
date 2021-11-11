import { compileComponentFromRender2 } from '@angular/compiler/src/render3/view/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { isEmpty } from 'rxjs-compat/operator/isEmpty';
import { ComicDTO } from '../../dto/comic-dto';
import { ActivatedRoute } from '@angular/router';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';

@Component({
  selector: 'app-comprar-comic',
  templateUrl: './comprar-comic.component.html',
  styleUrls: ['./comprar-comic.component.css']
})
export class ComprarComicComponent implements OnInit {
  public gestionarComicForm: FormGroup;
  public cantidad : number;
  public comicDTO : ComicDTO;
  public listaComics : Array<ComicDTO>;
  public mostrarTabla : Boolean;
  public mostrarAlert : Boolean;
  public mostrarDialogCompra : Boolean;
  public ventaComic : ComicDTO;
  public compraRealizada : Boolean;
  public mensaje : string;
  public submitted : boolean;


  constructor(private fb: FormBuilder, private router: Router, private gestionComicsService : GestionarComicService, private activatedRoute: ActivatedRoute) {
    this.gestionarComicForm = this.fb.group({
     cantidad: [null, Validators.required],
     nombre:[null]
      });

   }

  ngOnInit() {
    this.submitted= false;
    let comic: any  = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
    this.ventaComic = new ComicDTO();
    this.listaComics = new Array<ComicDTO>();
    this.mostrarComic(comic);
    this.mostrarTabla = false;
    this.mostrarAlert= false;
    this.mostrarDialogCompra = true;
    this.compraRealizada=false;
  }

  get f() {
    return this.gestionarComicForm.controls;
}

  public mostrarComic(comicDTO: ComicDTO) : void{
    let comic = this.comicDTO;
    this.gestionarComicForm.setValue({nombre:comic.nombre,cantidad:1});
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
    this.router.navigate(['gestionarComic']);
  }

  public generarCompraComic(): void{
    let ventaComic= this.comicDTO;
    let cantidadActual: number  = +ventaComic.cantidad;
    let cantidadComprar: number = this.gestionarComicForm.controls.cantidad.value;
    ventaComic.cantidad = cantidadActual - cantidadComprar;
    this.gestionComicsService.venderTallerComic(ventaComic).subscribe(data=>{
      console.log(data);
     if(data.exitoso){
       this.mostrarDialogCompra=false;
       this.compraRealizada=true;
       this.mensaje= "compra realizada con exito"
       this.router.navigate(['gestionarComic',this.mensaje])
     }
     else{
      this.compraRealizada=true;
      this.mensaje = data.mensajeEjecucion;
     }

    });
  }





}
