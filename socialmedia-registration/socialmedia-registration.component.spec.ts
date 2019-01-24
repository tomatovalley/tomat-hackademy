import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SocialmediaRegistrationComponent } from './socialmedia-registration.component';

describe('SocialmediaRegistrationComponent', () => {
  let component: SocialmediaRegistrationComponent;
  let fixture: ComponentFixture<SocialmediaRegistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SocialmediaRegistrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SocialmediaRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
