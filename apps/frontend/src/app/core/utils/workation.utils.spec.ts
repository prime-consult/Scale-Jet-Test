import { flagUrl, formatIsoDate, riskLabel, riskModifier } from './workation.utils';

describe('workation utils', () => {
  it('formats ISO dates as dd/MM/yyyy', () => {
    expect(formatIsoDate('2024-01-02')).toBe('02/01/2024');
    expect(formatIsoDate('2023-12-31')).toBe('31/12/2023');
  });

  it('labels both LOW and NO as No risk', () => {
    expect(riskLabel('HIGH')).toBe('High risk');
    expect(riskLabel('LOW')).toBe('No risk');
    expect(riskLabel('NO')).toBe('No risk');
  });

  it('keeps distinct modifiers for the two no-risk levels', () => {
    expect(riskModifier('HIGH')).toBe('high');
    expect(riskModifier('LOW')).toBe('low');
    expect(riskModifier('NO')).toBe('none');
  });

  it('resolves known country flags', () => {
    expect(flagUrl('Germany')).toBe('/flags/de.png');
    expect(flagUrl('United States')).toBe('/flags/us.png');
    expect(flagUrl('Ukraine')).toBe('/flags/ua.png');
  });

  it('does not invent a flag path for unknown countries', () => {
    expect(flagUrl('Narnia')).toBeNull();
  });
});
