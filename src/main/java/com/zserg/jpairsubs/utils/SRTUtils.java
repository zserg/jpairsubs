package com.zserg.jpairsubs.utils;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SRTUtils {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public final static long MILLIS_IN_SECOND = 1000;
    public final static long MILLIS_IN_MINUTE = MILLIS_IN_SECOND * 60; // 60000
    public final static long MILLIS_IN_HOUR = MILLIS_IN_MINUTE * 60; // 3600000

    protected final static Pattern PATTERN_TIME = Pattern.compile("([\\d]{2}):([\\d]{2}):([\\d]{2}),([\\d]{3})");


    public static int textTimeToMillis(final String time) throws Exception {

        Matcher matcher = PATTERN_TIME.matcher(time);
        if (time == null || time.isEmpty() || !matcher.find())
            throw new Exception("Incorrect format...");

        int msTime = 0;
        short hours = Short.parseShort(matcher.group(1));
        byte min = Byte.parseByte(matcher.group(2));
        byte sec = Byte.parseByte(matcher.group(3));
        short millis = Short.parseShort(matcher.group(4));

        if (hours > 0)
            msTime += hours * MILLIS_IN_HOUR;
        if (min > 0)
            msTime += min * MILLIS_IN_MINUTE;
        if (sec > 0)
            msTime += sec * MILLIS_IN_SECOND;

        msTime += millis;

        return msTime;
    }

    public static String millisToText(final long millisToText) {

        int millisToSeconds = (int) millisToText / 1000;
        long hours = millisToSeconds / 3600;
        long minutes = (millisToSeconds % 3600) / 60;
        long seconds = millisToSeconds % 60;
        long millis = millisToText % 1000;

        if (hours < 0)
            hours = 0;

        if (minutes < 0)
            millis = 0;

        if (seconds < 0)
            seconds = 0;

        if (millis < 0)
            millis = 0;

        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, millis);
//		return (hours > 9 ? hours + ":" : "0" + hours + ":") + (minutes > 9 ?  minutes + ":" : "0" + minutes + ":") + (seconds > 9 ?  seconds : "0" + seconds) + "," + (millis > 99 ? millis : millis > 9 ? "0" + millis : millis >= 0 ? "00" + millis : "000");
    }


}
