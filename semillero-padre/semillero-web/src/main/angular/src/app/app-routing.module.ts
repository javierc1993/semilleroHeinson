import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import {GestionarComicComponent} from './semillero/componentes/gestionarComic/gestionarComic.component';
import { EditarComicComponent } from './semillero/componentes/editar-comic/editar-comic.component';
import { CrearComicComponent } from './semillero/componentes/crear-comic/crear-comic.component';
import { ComprarComicComponent } from './semillero/componentes/comprar-comic/comprar-comic.component';
const routes: Routes = [
  { path: 'bienvenida', component: BienvenidaComponent, data : null },
  {path:'gestionarComic', component: GestionarComicComponent},
  {path:'editarComic', component: EditarComicComponent},
  {path:'crearComic', component: CrearComicComponent},
  {path:'comprarComic',component:ComprarComicComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
