import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoShortLinkComponent } from './no-short-link.component';

describe('NoShortLinkComponent', () => {
  let component: NoShortLinkComponent;
  let fixture: ComponentFixture<NoShortLinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoShortLinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoShortLinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
