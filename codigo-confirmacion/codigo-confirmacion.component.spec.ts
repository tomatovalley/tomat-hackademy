import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CodigoConfirmacionComponent } from './codigo-confirmacion.component';

describe('CodigoConfirmacionComponent', () => {
  let component: CodigoConfirmacionComponent;
  let fixture: ComponentFixture<CodigoConfirmacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CodigoConfirmacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CodigoConfirmacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
