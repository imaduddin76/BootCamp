package aim.helmi.bootcamp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {
    private Calendar c = Calendar.getInstance();
    private static String defFormat = "dd MMM yyyy HH:mm";
    private static SimpleDateFormat sdf = new SimpleDateFormat(defFormat, Locale.ROOT);

    public String getDateNow(){
        return sdf.format(c.getTime());
    }
}
