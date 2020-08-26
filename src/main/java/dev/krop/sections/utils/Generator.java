package dev.krop.sections.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Random;

@UtilityClass
public class Generator {

    /*
        Костыльный метод генерации певдослучайного числа
     */
    public long generateJobId() {
        Random random = new Random();
        int randomInt = random.nextInt(8999);
        randomInt+=1000;
        LocalDateTime date = LocalDateTime.now();
        String data = "" + date.getYear()
                + date.getMonthValue()
                + date.getDayOfMonth()
                + date.getHour()
                + date.getMinute()
                + date.getSecond()
                + date.getNano();
        return Long.parseLong(data.substring(0, 16)) + randomInt;
    }
}
