import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IntroduceCodConfirmacionComponent } from './introduce-cod-confirmacion.component';

describe('IntroduceCodConfirmacionComponent', () => {
  let component: IntroduceCodConfirmacionComponent;
  let fixture: ComponentFixture<IntroduceCodConfirmacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IntroduceCodConfirmacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IntroduceCodConfirmacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
