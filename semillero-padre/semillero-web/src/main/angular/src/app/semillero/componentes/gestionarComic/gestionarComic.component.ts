import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { compileFactoryFunction } from '@angular/compiler/src/render3/r3_factory';
import { ThrowStmt } from '@angular/compiler';
import { Router, ActivatedRoute } from '@angular/router';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';
import { allowedNodeEnvironmentFlags } from 'process';

/**
 * @description Componente encargado de gestionar la logica para crear consultar actualizar y eliminar
 * un comic
 * @author dalvarez
 * @see SEMILLERO 2021
 */
 export interface DialogData {
    comicDTOInfo: ComicDTO;
  }

@Component({
  selector: 'gestionarComic',
  templateUrl: './gestionarComic.component.html'

})



export class GestionarComicComponent implements OnInit {
 
  public gestionarComicForm: FormGroup;

  public comicDTO : ComicDTO;

  public comicDTOInfo : ComicDTO;

  public nombre : string;

  public listaComics : Array<ComicDTO>;
  
  public mostrarItem : boolean;

  public mostrarToast : boolean;

  public mostrarAlert : boolean;

  public submitted : boolean;

  public mensajeEjecucion : string;

  public mensaje: string;
  


  constructor(private fb: FormBuilder, private router : Router, private gestionComicsService : GestionarComicService,private activatedRoute: ActivatedRoute) { 
    this.gestionarComicForm = this.fb.group({
    nombre : [null, Validators.required],
    editorial : [null, Validators.required],
    tematica : [null],
    coleccion : [null, Validators.required],
    numeroPaginas : [null, Validators.required],
    precio : [null, Validators.required],
    autores : [null],
    color : [null],
    fechaVenta : [null], 
    cantidad: [null, Validators.required]
    });
    
  }
  

  ngOnInit() {
    this.submitted= false;
    let mensajeExitoso: any  = this.activatedRoute.snapshot.params;
    this.mensaje= mensajeExitoso;
    this.mostrarItem = false;
    this.mostrarAlert = false;
    this.mostrarToast = true;
    this.comicDTO = new ComicDTO();
    this.listaComics = new Array<ComicDTO>();
    this.consultarComics();
  }



  public consultarComics(){
    
    this.gestionComicsService.consultarComics().subscribe(data=>{
      
      if(data[0].exitoso){
        this.listaComics = data;
        this.mostrarAlert = false;
      
      }
      else{
        console.log(data[0].mensajeEjecucion);
      }  
    }, error =>{
      console.log(error);
    });
  }

  public eliminarComic(idComic){
    
    this.gestionComicsService.eliminarComic(idComic).subscribe(data=>{
      
      if(data.exitoso){
       this.comicDTOInfo = data.mensajeEjecucion;
       this.mostrarToast = true;
       this.mostrarAlert = false;
      }
      else{
        console.log(data.mensajeEjecucion);
      }  
    }, error =>{
      console.log(error);
    });
  }

  public crearComic() : void {
    this.submitted = true;
    if(this.gestionarComicForm.invalid){
      return;
    }
    /*this.listaComics.push(this.comicDTO);
    this.comicDTO = new ComicDTO();*/
    //this.limpiarDatosComic(this.comicDTO);
    this.comicDTO = new ComicDTO();
    this.comicDTO.nombre= this.gestionarComicForm.controls.nombre.value;
    this.comicDTO.editorial= this.gestionarComicForm.controls.editorial.value;
    this.comicDTO.tematicaEnum= this.gestionarComicForm.controls.tematica.value;
    this.comicDTO.coleccion = this.gestionarComicForm.controls.coleccion.value;
    this.comicDTO.numeroPaginas= this.gestionarComicForm.controls.numeroPaginas.value;
    this.comicDTO.precio= this.gestionarComicForm.controls.precio.value;
    this.comicDTO.autores= this.gestionarComicForm.controls.autores.value;
    this.comicDTO.color= this.gestionarComicForm.controls.color.value;
    //this.comicDTO.fechaVenta= this.gestionarComicForm.controls.nombre.value;
    this.comicDTO.estadoEnum="ACTIVO";
    this.comicDTO.cantidad= this.gestionarComicForm.controls.cantidad.value;

    this.gestionComicsService.crearComic(this.comicDTO).subscribe(data=>{
      if (data.exitoso){
        this.mostrarItem = true;
        this.mensajeEjecucion = data.mensajeEjecucion;
        console.log(data.mensajeEjecucion);
        this.consultarComics();
      } else{
        this.mostrarItem = true;
        this.mensajeEjecucion = data.mensajeEjecucion;
      }
      this.limpiarDatosComic();
    }, error=>{console.log(error);
    });

  }

/*******
 * TALLER
 * 
 */
 public irAComprarComic(comic : ComicDTO) : void {
  this.cerrar();
  this.router.navigate(['comprarComic', comic]);
}
 


  private limpiarDatosComic( ) : void {
    this.submitted= false;
    
   this.gestionarComicForm.controls.nombre.setValue(null);
   this.gestionarComicForm.controls.editorial.setValue(null);
   this.gestionarComicForm.controls.tematica.setValue(null);
   this.gestionarComicForm.controls.coleccion.setValue(null);
   this.gestionarComicForm.controls.numeroPaginas.setValue(null);
   this.gestionarComicForm.controls.precio.setValue(null);
   this.gestionarComicForm.controls.autores.setValue(null);
   this.gestionarComicForm.controls.color.setValue(null);
    //this.comicDTO.fechaVenta= this.gestionarComicForm.controls.nombre.value;
   this.gestionarComicForm.controls.cantidad.setValue(null);
  }

  public consultarComic(posicion : number) : void{
    let comic = this.listaComics[posicion];
    this.f.nombre.setValue(comic.nombre);
    this.f.editorial.setValue(comic.editorial);
    this.f.tematica.setValue(comic.tematicaEnum);
    this.f.coleccion.setValue(comic.coleccion);
    this.f.numeroPaginas.setValue(comic.numeroPaginas);
    this.f.precio.setValue(comic.precio);
    this.f.autores.setValue(comic.autores);
    this.f.color.setValue(comic.color);


    this.f.nombre.disable();
    this.f.editorial.disable();
    this.f.tematica.disable();
    this.f.coleccion.disable();
    this.f.numeroPaginas.disable();
    this.f.precio.disable();
    this.f.autores.disable();
    this.f.color.disable();
  }

  public activarCampos() : void {
    this.f.nombre.enable();
    this.f.editorial.enable();
    this.f.tematica.enable();
    this.f.coleccion.enable();
    this.f.numeroPaginas.enable();
    this.f.precio.enable();
    this.f.autores.enable();
    this.f.color.enable();
    this.limpiarDatosComic();
  }

  public cerrar() : void {
    this.mostrarItem = false;
    this.mostrarToast = false;
    this.consultarComics();
  }

  public confirmar(comic): void{
    this.mostrarAlert = true;
    this.comicDTOInfo= comic;
  }
  public irAEditarComic(comic : ComicDTO) : void {
    this.cerrar();
    this.router.navigate(['editarComic', comic]);
  }
  public irACrearComic() : void {
    this.cerrar();
    this.router.navigate(['crearComic']);
  }
  get f() {
      return this.gestionarComicForm.controls;
  }

  public imprimirInfoComic(posicion : number) : void {
    this.mostrarItem = true;
    this.comicDTOInfo = this.listaComics[posicion];
  }
  
 
}
