const COUNTRY_ISO: Record<string, string> = {
  Germany: 'de',
  'United States': 'us',
  Ukraine: 'ua',
  Belgium: 'be',
  Spain: 'es',
  Greece: 'gr',
  India: 'in'
};

const RISK_RANK: Record<string, number> = {
  NO: 0,
  LOW: 1,
  HIGH: 2
};

export function flagUrl(country: string): string | null {
  const iso = COUNTRY_ISO[country];
  return iso ? `/flags/${iso}.png` : null;
}

export function formatIsoDate(isoDate: string): string {
  const [year, month, day] = isoDate.split('-');
  if (!year || !month || !day) {
    return isoDate;
  }
  return `${day}/${month}/${year}`;
}

export function riskLabel(risk: string): string {
  return risk === 'HIGH' ? 'High risk' : 'No risk';
}

export function riskModifier(risk: string): 'high' | 'low' | 'none' {
  if (risk === 'HIGH') {
    return 'high';
  }
  if (risk === 'LOW') {
    return 'low';
  }
  return 'none';
}

export function riskRank(risk: string): number {
  return RISK_RANK[risk] ?? Number.MAX_SAFE_INTEGER;
}
