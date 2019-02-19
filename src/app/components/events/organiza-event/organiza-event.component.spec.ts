import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganizaEventComponent } from './organiza-event.component';

describe('OrganizaEventComponent', () => {
  let component: OrganizaEventComponent;
  let fixture: ComponentFixture<OrganizaEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrganizaEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrganizaEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
