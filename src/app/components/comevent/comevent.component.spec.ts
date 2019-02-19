import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComeventComponent } from './comevent.component';

describe('ComeventComponent', () => {
  let component: ComeventComponent;
  let fixture: ComponentFixture<ComeventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComeventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComeventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
