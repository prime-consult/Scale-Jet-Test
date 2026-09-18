package com.workflex.workation.domain;

/**
 * Risk stored in the database. CSV values {@code HIGH}, {@code LOW} and {@code NO}
 * (and the challenge aliases {@code HIGH_RISK}, {@code LOW_RISK}, {@code NO_RISK})
 * all map here. The UI labels both {@link #LOW} and {@link #NO} as "No risk"
 * but uses different colours.
 */
public enum Risk {
    HIGH,
    LOW,
    NO;

    public static Risk fromCsv(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Risk value must not be blank");
        }

        String normalized = value.trim().toUpperCase().replace("-", "_");
        return switch (normalized) {
            case "HIGH", "HIGH_RISK" -> HIGH;
            case "LOW", "LOW_RISK" -> LOW;
            case "NO", "NO_RISK" -> NO;
            default -> throw new IllegalArgumentException("Unknown risk value: " + value);
        };
    }
}
