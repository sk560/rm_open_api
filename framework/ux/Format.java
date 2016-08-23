package com.rm_open_api.framework.ux;

import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.Time;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;

import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author Mihael
 * @author Party - additions
 *         21. avg. 2016
 */
public class Format {

    public String format(long i) {
        String formatted = "";
        String end = "";
        NumberFormat format = NumberFormat.getInstance();
        formatted = format.format(i);
        if (i >= 1000) {
            end = "k";
            if (i >= 1000000) {
                end = "M";
                if (i >= 1000000000) {
                    end = "B";
                }
            }
            if (formatted.contains(",")) {
                formatted = formatted.split(",")[0] + "." + formatted.split(",")[1].substring(0, 2) + end;
            } else if (formatted.contains(".")) {
                formatted = formatted.split("\\.")[0] + "." + formatted.split("\\.")[1].substring(0, 2) + end;
            }
        }
        return formatted;
    }

    public static String getTimeTillEnd(final int left, final int perHour) {

        if (perHour < 1) {
            return Time.format(0L);
        }

        return Time.format((long) (left * (3600000D / perHour)));
    }

    public static double getProgress(int max, int done) {
        return (max * done) / 100D;
    }

    /**
     * Author: Party
     * Returns int value of exprate
     * @param expGained - Experience gained in particular skill
     * @param stopWatch - Bot's main stopwatch
     * @return int value of exprate
     */
    public static int formatExpRate(final int expGained,final StopWatch stopWatch) {
        return (int) CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), expGained);
    }

    /**
     * Author: Party
     * Returns the String value of the time left until next level
     * @param stopWatch - Bot's main stopwatch
     * @param expGain - Experience gained in particular skill
     * @param expToLevel - Experience remaining until next level in particular skill
     * @return String value of remaining time until level-up in particular skill
     */
    public static String getTimeToLevelAsString(StopWatch stopWatch, long expGain, long expToLevel){
        double xpRate = formatExpRateMillis(expGain, stopWatch);
        if(xpRate > 0D && expToLevel > 0) {
            return Time.format((long)(expToLevel / xpRate));
        }
        return "Unknown";
    }

    /**
     * Author: Party
     * Private method returning xp/ms for use in getTimeToLevelAsString
     * @param expGain - Experience gained in particular skill
     * @param stopWatch - Bot's main stopwatch
     * @return Double value of xp/ms
     */
    private static double formatExpRateMillis(long expGain, StopWatch stopWatch){
        return CommonMath.rate(TimeUnit.MILLISECONDS, stopWatch.getRuntime(), expGain);
    }

}
