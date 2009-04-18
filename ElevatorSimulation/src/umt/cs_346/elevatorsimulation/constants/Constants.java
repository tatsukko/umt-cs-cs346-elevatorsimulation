package umt.cs_346.elevatorsimulation.constants;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Class containing static values that are referenced throughout
 * the application.
 * @author chanshew
 *
 */
public class Constants {
	
	public static final int KEY_CODE_ENTER = 401;
	
	public static final int PANE_WIDTH = 900;
	public static final int PANE_HEIGHT = 600;

    public static final int FLOOR_HEIGHT = 15;
    
    public static final int YSTART = 200;
    
    public static final String TITLE = "Elevator Simulation";
    
    public static final String DATE_FORMAT_NOW = "M-dd HH:mm:ss";
    
    public static String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());

      }
}
