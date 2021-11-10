import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { compileFactoryFunction } from '@angular/compiler/src/render3/r3_factory';
import { ThrowStmt } from '@angular/compiler';
import { Router } from '@angular/router';
import { GestionarComicService } from '../../servicios/gestionar-comic.service';
import { allowedNodeEnvironmentFlags } from 'process';

/**
 * @description Componente encargado de gestionar la logica para crear consultar actualizar y eliminar
 * un comic
 * @author dalvarez
 * @see SEMILLERO 2021
 */

@Component({
  selector: 'app-crear-comic',
  templateUrl: './crear-comic.component.html'
})
export class CrearComicComponent implements OnInit {

  public gestionarComicForm: FormGroup;

  public comicDTO : ComicDTO;

  public comicDTOInfo : ComicDTO;

  public nombre : string;

  public listaComics : Array<ComicDTO>;
  
  public mostrarItem : boolean;

  public submitted : boolean;

  public mensajeEjecucion : string;

  constructor(private fb: FormBuilder, private router : Router, private gestionComicsService : GestionarComicService) { 
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
    this.comicDTO = new ComicDTO();
    this.listaComics = new Array<ComicDTO>();
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
       debugger;     
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
   
    }, error=>{console.log(error);
    });

  }

  public cerrar() : void {
    this.mostrarItem = false;
  }
  

  public imprimirInfoComic(posicion : number) : void {
    this.mostrarItem = true;
    this.comicDTOInfo = this.listaComics[posicion];
  }


  get f() {
    return this.gestionarComicForm.controls;
     }

  public irAEditarComic(comic : ComicDTO) : void {
      this.cerrar();
      this.router.navigate(['editarComic', comic]);
    }
 
  }



