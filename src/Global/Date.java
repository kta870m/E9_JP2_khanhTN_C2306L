package Global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    public static LocalDateTime parseDate(String keyword){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(keyword, df);
    }
}
