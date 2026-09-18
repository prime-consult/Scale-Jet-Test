import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Workation } from '../../core/models/workation.model';
import { WorkationTableComponent } from './workation-table.component';

const SAMPLE: Workation[] = [
  {
    workationId: 'w1',
    employee: 'Steffen Jacobs',
    origin: 'Germany',
    destination: 'United States',
    start: '2024-01-02',
    end: '2024-12-31',
    workingDays: 65,
    risk: 'HIGH'
  },
  {
    workationId: 'w4',
    employee: 'Andre Fischer',
    origin: 'Germany',
    destination: 'Greece',
    start: '2023-05-22',
    end: '2023-06-30',
    workingDays: 50,
    risk: 'LOW'
  },
  {
    workationId: 'w5',
    employee: 'Ayushi Singh',
    origin: 'Germany',
    destination: 'India',
    start: '2023-03-13',
    end: '2023-04-30',
    workingDays: 35,
    risk: 'NO'
  }
];

describe('WorkationTableComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkationTableComponent],
      providers: [provideHttpClient(), provideHttpClientTesting()]
    }).compileComponents();
  });

  function render() {
    const fixture = TestBed.createComponent(WorkationTableComponent);
    const http = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
    http.expectOne('/workflex/workation').flush(SAMPLE);
    fixture.detectChanges();
    return fixture;
  }

  it('renders formatted dates and both no-risk styles', () => {
    const fixture = render();
    const text = (fixture.nativeElement as HTMLElement).textContent ?? '';

    expect(text).toContain('02/01/2024');
    expect(text).toContain('31/12/2024');
    expect(text).toContain('High risk');
    expect((text.match(/No risk/g) ?? []).length).toBe(2);
    expect(fixture.nativeElement.querySelector('.risk-low')).toBeTruthy();
    expect(fixture.nativeElement.querySelector('.risk-none')).toBeTruthy();
  });

  it('sorts by employee when the column header is clicked', () => {
    const fixture = render();
    const buttons = fixture.nativeElement.querySelectorAll('thead button') as NodeListOf<HTMLButtonElement>;
    buttons[0].click();
    fixture.detectChanges();

    const names = Array.from(
      fixture.nativeElement.querySelectorAll('.col-employee') as NodeListOf<HTMLElement>
    )
      .filter((cell) => cell.tagName === 'TD')
      .map((cell) => cell.textContent?.trim());

    expect(names).toEqual(['Andre Fischer', 'Ayushi Singh', 'Steffen Jacobs']);
  });
});
