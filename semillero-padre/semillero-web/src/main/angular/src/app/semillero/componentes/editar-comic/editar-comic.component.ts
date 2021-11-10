import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { compileFactoryFunction } from '@angular/compiler/src/render3/r3_factory';
import { ThrowStmt } from '@angular/compiler';
import { ActivatedRoute, Router } from '@angular/router';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';
import { allowedNodeEnvironmentFlags } from 'process';

/**
 * @description Componente encargado de gestionar la logica para crear consultar actualizar y eliminar
 * un comic
 * @author dalvarez
 * @see SEMILLERO 2021
 */

@Component({
  selector: 'editar-comic',
  templateUrl: './editar-comic.component.html',
  styleUrls: ['./editar-comic.component.css']
})
export class EditarComicComponent implements OnInit {

  public gestionarComicForm: FormGroup;

  public comicDTO : ComicDTO;

  public comicDTOActualizar : ComicDTO;

  public comicDTOInfo : ComicDTO;

  public nombre : string;

  public listaComics : Array<ComicDTO>;
  
  public mostrarItem : boolean;

  public submitted : boolean;

  public mensajeEjecucion : string;

  constructor(private fb: FormBuilder, private router : Router, private gestionComicsService : GestionarComicService, private activatedRoute: ActivatedRoute) { 
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
    this.mostrarItem = false;
    this.listaComics = new Array<ComicDTO>();
    let comic: any  = this.activatedRoute.snapshot.params;
    this.comicDTO = comic;
    this.mostrarComic(this.comicDTO);
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

  public crearComic() : void {
    this.submitted = true;
    if(this.gestionarComicForm.invalid){
      return;
    }
    /*this.listaComics.push(this.comicDTO);
    this.comicDTO = new ComicDTO();*/
    //this.limpiarDatosComic(this.comicDTO);
    console.log(this.comicDTO);
    debugger;
    this.comicDTOActualizar = new ComicDTO();
    this.comicDTOActualizar.id = this.comicDTO.id;
    this.comicDTOActualizar.nombre= this.gestionarComicForm.controls.nombre.value;
    this.comicDTOActualizar.editorial= this.gestionarComicForm.controls.editorial.value;
    this.comicDTOActualizar.tematicaEnum= this.gestionarComicForm.controls.tematica.value;
    this.comicDTOActualizar.coleccion = this.gestionarComicForm.controls.coleccion.value;
    this.comicDTOActualizar.numeroPaginas= this.gestionarComicForm.controls.numeroPaginas.value;
    this.comicDTOActualizar.precio= this.gestionarComicForm.controls.precio.value;
    this.comicDTOActualizar.autores= this.gestionarComicForm.controls.autores.value;
    this.comicDTOActualizar.color= this.gestionarComicForm.controls.color.value;
    //this.comicDTO.fechaVenta= this.gestionarComicForm.controls.nombre.value;
    this.comicDTOActualizar.estadoEnum="ACTIVO";
    this.comicDTOActualizar.cantidad= this.gestionarComicForm.controls.cantidad.value;
    this.gestionComicsService.actualizarComic(this.comicDTOActualizar).subscribe(data=>{
      console.log(data);
      if (data.exitoso){
        this.mostrarItem = true;
        this.mensajeEjecucion = data.mensajeEjecucion;
        console.log(data.mensajeEjecucion);
        this.router.navigate(['gestionarComic']);
      } else{
        this.mostrarItem = true;
        this.mensajeEjecucion = data.mensajeEjecucion;
      }
     // this.limpiarDatosComic();
    }, error=>{console.log(error);
    });

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

  public mostrarComic(comicDTO: ComicDTO) : void{
    let comic = this.comicDTO;
    this.f.nombre.setValue(comic.nombre);
    this.f.editorial.setValue(comic.editorial);
    this.f.tematica.setValue(comic.tematicaEnum);
    this.f.coleccion.setValue(comic.coleccion);
    this.f.numeroPaginas.setValue(comic.numeroPaginas);
    this.f.precio.setValue(comic.precio);
    this.f.autores.setValue(comic.autores);
    this.f.color.setValue(comic.color);
    this.f.cantidad.setValue(comic.cantidad);
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
  }
  public editarComic(comic : ComicDTO) : void {
    this.cerrar();
    this.router.navigate(['editarComic', comic]);
  }

  get f() {
      return this.gestionarComicForm.controls;
  }

  public imprimirInfoComic(posicion : number) : void {
    this.mostrarItem = true;
    this.comicDTOInfo = this.listaComics[posicion];
  }

}



