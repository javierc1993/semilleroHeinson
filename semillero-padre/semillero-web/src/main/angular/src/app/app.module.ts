import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule} from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { APP_BASE_HREF } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { AppComponent } from './app.component';
import { MenuComponent } from './semillero/componentes/menu/menu-component';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionarComic.component';
import { NgbModal, NgbModalConfig, NgbModule, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EditarComicComponent } from './semillero/componentes/editar-comic/editar-comic.component';
import { CrearComicComponent } from './semillero/componentes/crear-comic/crear-comic.component';
import { ComprarComicComponent } from './semillero/componentes/comprar-comic/comprar-comic.component';
//import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';

// DTOs
//export { ComicDTO } from './semillero/dto/comic.dto';
//export { ResultadoDTO } from './semillero/dto/resultado.dto';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    BienvenidaComponent,
    GestionarComicComponent,
    EditarComicComponent,
    CrearComicComponent,
    ComprarComicComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    BrowserAnimationsModule
  ],
  providers: [
  	{ provide: APP_BASE_HREF, useValue: '/SemilleroHBT' }, NgbModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
