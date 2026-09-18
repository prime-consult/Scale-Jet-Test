import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Workation } from '../models/workation.model';

@Injectable({ providedIn: 'root' })
export class WorkationService {
  private readonly http = inject(HttpClient);

  findAll(): Observable<Workation[]> {
    return this.http.get<Workation[]>('/workflex/workation');
  }
}
