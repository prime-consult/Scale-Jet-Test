import { SortColumn, SortDirection, Workation } from '../models/workation.model';

export function sortWorkations(
  workations: Workation[],
  column: SortColumn,
  direction: SortDirection
): Workation[] {
  const factor = direction === 'asc' ? 1 : -1;
  return [...workations].sort((left, right) => factor * compare(left, right, column));
}

function compare(left: Workation, right: Workation, column: SortColumn): number {
  if (column === 'workingDays') {
    return left.workingDays - right.workingDays;
  }
  if (column === 'start' || column === 'end') {
    return left[column].localeCompare(right[column]);
  }
  return left[column].localeCompare(right[column], undefined, { sensitivity: 'base' });
}
