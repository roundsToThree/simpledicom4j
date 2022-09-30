package com.roundsToThree.Representations;

import com.roundsToThree.DataProcessing.ByteUtils;
import com.roundsToThree.Structures.DateTime;
import com.roundsToThree.Structures.ValueRepresentation;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DateTimeRepresentation extends Representation {
    /*
        Data is stored like this
        YYYYMMDDHHMMSS.FFFFFF&ZZXX
        YYYY = Year
        (Everything else is optional - Stop reading on NULL)
        MM = Month
        DD = Day
        HH = Hour
        MM = Minute
        SS = Seconds
        FFFFFF = Fraction of second (down to 1 millionth)

        // Note:
        while you terminate reading on the first NULL, do keep in mind that
        UTC offset wont necessarily terminate there as well:
        202209+1100 is valid even though every other parameter is missing

        &  = +/- For UTC Offset
        ZZ = UTC Offset Hours
        XX = UTC Offset Minutes
    */

    // Class specific variables
    public DateTime[] value;

    // The Value Representation of this class
    public static final ValueRepresentation valueRepresentation = ValueRepresentation.VALUE_REPRESENTATION_DT;

    @Override
    public ValueRepresentation getValueRepresentation() {
        return valueRepresentation;
    }

    // Converts raw DateTime value into a easier to use format
    public DateTimeRepresentation(byte[] data) {
        // Basic error checking
        if (data == null || data.length == 0)
            return;

        // Split on delimiter
        String[] dateTimes = new String(data, StandardCharsets.UTF_8).split("\\\\");
        value = new DateTime[dateTimes.length];

        for (int i = 0; i < dateTimes.length; i++) {

            // Trim and skip any entries less than 4 long
            String dateTime = dateTimes[i].trim();

            int year = 0;
            int month = 0;
            int day = 0;
            int hour = 0;
            int minutes = 0;
            int seconds = 0;
            int microseconds = 0;
            int hourOffset = 0;
            int minuteOffset = 0;

            String dateData;
            String offsetData = null;

            // Start by looking for UTC offset
            // Check positive offset
            String[] tmp = dateTime.split("\\+");
            if (tmp.length == 1)
                // Try negative offset
                tmp = dateTime.split("-");

            dateData = tmp[0].trim();
            // If tmp is still only 1 long, then there is no offset data
            if (tmp.length == 2)
                offsetData = tmp[1].trim();

            int dateDataLength = dateData.length();

            if (dateDataLength < 4)
                continue;

            // Read the year
            year = Integer.parseInt(dateData.substring(0, 4));

            // Read month
            if (dateDataLength >= 6)
                month = Integer.parseInt(dateData.substring(4, 6));
            // Read day
            if (dateDataLength >= 8)
                day = Integer.parseInt(dateData.substring(6, 8));
            // Read hour
            if (dateDataLength >= 10)
                hour = Integer.parseInt(dateData.substring(8, 10));
            // Read minutes
            if (dateDataLength >= 12)
                minutes = Integer.parseInt(dateData.substring(10, 12));
            // Read seconds
            if (dateDataLength >= 14)
                seconds = Integer.parseInt(dateData.substring(12, 14));
            // Read microseconds
            if (dateDataLength >= 21)
                // Exclude decimal point
                microseconds = (int) Integer.parseInt(dateData.substring(15, 21));

            // Now find utc offset
            int offsetDatalength = -1;
            if (offsetData != null)
                offsetDatalength = offsetData.length();

            if (offsetDatalength >= 3)
                hourOffset = Integer.parseInt(offsetData.substring(0, 3));

            if (offsetDatalength >= 5)
                minuteOffset = Integer.parseInt(offsetData.substring(3, 5));

            value[i] = new DateTime(ZonedDateTime.of(
                    year,
                    Math.max(month, 1),
                    Math.max(day, 1),
                    hour,
                    minutes,
                    seconds,
                    microseconds * 1000,
                    ZoneId.ofOffset("UTC", ZoneOffset.ofHoursMinutes(hourOffset, minuteOffset))
            ));
        }
    }


}
