import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagenEventComponent } from './imagen-event.component';

describe('ImagenEventComponent', () => {
  let component: ImagenEventComponent;
  let fixture: ComponentFixture<ImagenEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImagenEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImagenEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
