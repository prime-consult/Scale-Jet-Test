export type Risk = 'HIGH' | 'LOW' | 'NO';

export interface Workation {
  workationId: string;
  employee: string;
  origin: string;
  destination: string;
  start: string;
  end: string;
  workingDays: number;
  risk: Risk;
}

export type SortColumn =
  | 'employee'
  | 'origin'
  | 'destination'
  | 'start'
  | 'end'
  | 'workingDays'
  | 'risk';

export type SortDirection = 'asc' | 'desc';
