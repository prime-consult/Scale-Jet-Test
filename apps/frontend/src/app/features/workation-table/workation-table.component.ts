import { Component, computed, inject, signal } from '@angular/core';
import { WorkationService } from '../../core/services/workation.service';
import { SortColumn, SortDirection, Workation } from '../../core/models/workation.model';
import { sortWorkations } from '../../core/utils/sort-workations';
import { flagUrl, formatIsoDate, riskLabel, riskModifier } from '../../core/utils/workation.utils';

@Component({
  selector: 'app-workation-table',
  imports: [],
  templateUrl: './workation-table.component.html',
  styleUrl: './workation-table.component.scss'
})
export class WorkationTableComponent {
  private readonly workationService = inject(WorkationService);

  protected readonly workations = signal<Workation[]>([]);
  protected readonly loading = signal(true);
  protected readonly error = signal<string | null>(null);
  protected readonly sortColumn = signal<SortColumn | null>(null);
  protected readonly sortDirection = signal<SortDirection>('asc');

  protected readonly rows = computed(() => {
    const column = this.sortColumn();
    const items = this.workations();
    if (!column) {
      return items;
    }
    return sortWorkations(items, column, this.sortDirection());
  });

  protected readonly columns: { key: SortColumn; label: string }[] = [
    { key: 'employee', label: 'Employee' },
    { key: 'origin', label: 'Origin' },
    { key: 'destination', label: 'Destination' },
    { key: 'start', label: 'Start' },
    { key: 'end', label: 'End' },
    { key: 'workingDays', label: 'Working days' },
    { key: 'risk', label: 'Risk' }
  ];

  constructor() {
    this.workationService.findAll().subscribe({
      next: (workations) => {
        this.workations.set(workations);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Unable to load workations.');
        this.loading.set(false);
      }
    });
  }

  protected sortBy(column: SortColumn): void {
    if (this.sortColumn() === column) {
      this.sortDirection.update((direction) => (direction === 'asc' ? 'desc' : 'asc'));
      return;
    }
    this.sortColumn.set(column);
    this.sortDirection.set('asc');
  }

  protected flag(country: string): string {
    return flagUrl(country);
  }

  protected date(isoDate: string): string {
    return formatIsoDate(isoDate);
  }

  protected label(risk: string): string {
    return riskLabel(risk);
  }

  protected modifier(risk: string): string {
    return riskModifier(risk);
  }

  protected icon(risk: string): string {
    const modifier = riskModifier(risk);
    return `risk/${modifier === 'none' ? 'no' : modifier}.svg`;
  }
}
