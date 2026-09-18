import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Workation } from '../models/workation.model';
import { WorkationService } from './workation.service';

describe('WorkationService', () => {
  it('requests workations from the WorkFlex API', () => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()]
    });

    const service = TestBed.inject(WorkationService);
    const http = TestBed.inject(HttpTestingController);
    let result: Workation[] | undefined;

    service.findAll().subscribe((workations) => {
      result = workations;
    });

    const request = http.expectOne('/workflex/workation');
    expect(request.request.method).toBe('GET');
    request.flush([]);
    expect(result).toEqual([]);
    http.verify();
  });
});
