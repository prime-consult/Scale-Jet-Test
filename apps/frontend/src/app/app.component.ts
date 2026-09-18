import { Component } from '@angular/core';
import { WorkationTableComponent } from './features/workation-table/workation-table.component';

@Component({
  selector: 'app-root',
  imports: [WorkationTableComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {}
