package com.rm_open_api.framework.ux;

import java.text.NumberFormat;

import com.runemate.game.api.hybrid.util.Time;

/**
 * @author Mihael
 *
 *         21. avg. 2016
 */
public class Format {

	public String format(long i) {
		String formatted = "";
		String end = "";
		NumberFormat format = NumberFormat.getInstance();
		formatted = format.format(i).toString();
		if (i >= 1000) {
			end = "k";
			if (i >= 1000000) {
				end = "M";
				if (i >= 1000000000) {
					end = "B";
				}
			}
			if (formatted.contains(",")) {
				formatted = formatted.split("\\,")[0] + "." + formatted.split("\\,")[1].substring(0, 2) + end;
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
		return (max * done) / 100.0;
	}

}
