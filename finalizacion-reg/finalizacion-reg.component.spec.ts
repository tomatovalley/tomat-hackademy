import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FinalizacionRegComponent } from './finalizacion-reg.component';

describe('FinalizacionRegComponent', () => {
  let component: FinalizacionRegComponent;
  let fixture: ComponentFixture<FinalizacionRegComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FinalizacionRegComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FinalizacionRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
