import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprarComicComponent } from './comprar-comic.component';

describe('ComprarComicComponent', () => {
  let component: ComprarComicComponent;
  let fixture: ComponentFixture<ComprarComicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComprarComicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComprarComicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
