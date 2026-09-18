import { Workation } from '../models/workation.model';
import { sortWorkations } from './sort-workations';

const rows: Workation[] = [
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
    workationId: 'w3',
    employee: 'Henry Duchamp',
    origin: 'Belgium',
    destination: 'Spain',
    start: '2022-09-01',
    end: '2023-03-01',
    workingDays: 131,
    risk: 'HIGH'
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

describe('sortWorkations', () => {
  it('sorts employees alphabetically', () => {
    const sorted = sortWorkations(rows, 'employee', 'asc');
    expect(sorted.map((row) => row.employee)).toEqual([
      'Ayushi Singh',
      'Henry Duchamp',
      'Steffen Jacobs'
    ]);
  });

  it('sorts working days descending', () => {
    const sorted = sortWorkations(rows, 'workingDays', 'desc');
    expect(sorted.map((row) => row.workingDays)).toEqual([131, 65, 35]);
  });

  it('sorts start dates chronologically', () => {
    const sorted = sortWorkations(rows, 'start', 'asc');
    expect(sorted.map((row) => row.start)).toEqual([
      '2022-09-01',
      '2023-03-13',
      '2024-01-02'
    ]);
  });
});
