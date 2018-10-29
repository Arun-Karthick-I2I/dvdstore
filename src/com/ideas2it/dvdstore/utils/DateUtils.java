package com.ideas2it.dvdstore.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ideas2it.dvdstore.common.Constants;

/**
 * <p>
 * The {@code DateUtils} class provides functions related to date
 * </p>
 *
 */
public class DateUtils {

    /**
     * <p>
     * Calculates the difference between the given date and today and returns
     * it as a String
     * </p>
     *
     * @param  date
     *         Date whose difference with the present day has to be calculated
     *
     * @return dateDifference
     *         Returns the difference between the given date and today in
     *         months and days as a String object
     */
    public static String calculateDaysDifference(LocalDate date) {
        long years = ChronoUnit.YEARS.between(date, LocalDate.now());
        long months = ChronoUnit.MONTHS.between(date.plusYears(years), 
            LocalDate.now());
        long days = ChronoUnit.DAYS.between(date.plusYears(years).plusMonths(
            months), LocalDate.now());

        StringBuilder dateDifference = new StringBuilder();
        dateDifference.append(years).append(Constants.LABEL_YEARS).
            append(months).append(Constants.LABEL_MONTHS).
            append(days).append(Constants.LABEL_DAYS);

        return dateDifference.toString();
    }

    /**
     * <p>
     * Calculates the difference between the given date and today and returns
     * it as number of days
     * </p>
     *
     * @param  date
     *         Date whose difference with the present day has to be calculated
     *
     * @return days
     *         Returns the difference between the given date and today as days.
     */
   public static long calculateDays(LocalDate date) {
        return ChronoUnit.DAYS.between(date, LocalDate.now());
   }
}
