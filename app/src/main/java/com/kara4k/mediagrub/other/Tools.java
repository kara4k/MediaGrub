package com.kara4k.mediagrub.other;


import java.util.Locale;

public class Tools {

    private static final String ZERO = "00";

    public static String formatDuration(final long seconds) {
        final long oneHour = 60 * 60;
        final long oneMin = 60;
        final int hour;
        final int min;
        final int sec;

        hour = (int) (seconds / oneHour);
        min = (int) ((seconds - hour * oneHour) / oneMin);
        sec = (int) (seconds - hour * oneHour - min * oneMin);

        final String hours = formatTimeUnit(hour);
        final String mins = formatTimeUnit(min);
        final String secs = formatTimeUnit(sec);

        if (hours.equals(ZERO)) {
            return String.format(Locale.ENGLISH, "%s:%s", mins, secs);
        }
        return String.format(Locale.ENGLISH, "%s:%s:%s", hours, mins, secs);
    }

    private static String formatTimeUnit(final int value) {
        if (value == 0) return ZERO;

        if (value < 10) return String.format(Locale.ENGLISH, "0%d", value);

        return String.valueOf(value);
    }
}
