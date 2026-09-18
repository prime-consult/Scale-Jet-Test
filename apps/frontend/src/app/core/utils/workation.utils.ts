const COUNTRY_ISO: Record<string, string> = {
  Germany: 'de',
  'United States': 'us',
  Ukraine: 'ua',
  Belgium: 'be',
  Spain: 'es',
  Greece: 'gr',
  India: 'in'
};

export function flagUrl(country: string): string {
  const iso = COUNTRY_ISO[country] ?? country.slice(0, 2).toLowerCase();
  return `/flags/${iso}.png`;
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
