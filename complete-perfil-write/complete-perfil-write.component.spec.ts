import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletePerfilWriteComponent } from './complete-perfil-write.component';

describe('CompletePerfilWriteComponent', () => {
  let component: CompletePerfilWriteComponent;
  let fixture: ComponentFixture<CompletePerfilWriteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompletePerfilWriteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletePerfilWriteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
