package umt.cs_346.elevatorsimulation.constants;

import java.util.Calendar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

/**
 * Class containing static values that are referenced throughout the application.
 * @author Chris Hanshew
 */
public class Constants {
	
	//Elevator Tab
	public static final int LAYOUT_HORIZONAL_GAP = 5;
	public static final int LAYOUT_VERITCAL_GAP = 5;
	public static final int LAYOUT_ROWS = 2;
	public static final int LAYOUT_COLUMNS = 6;
	public static final int TAB_WIDTH = 175;
	public static final int TAB_HEIGHT = 250;
	
	//Elevator and associated classes
    public static final int FLOOR_HEIGHT = 15;
    public static final int CAR_HEIGHT = 15;
    public static final int YSTART = 200;
    public static final int XSTART = 55;
    public static final int ELEVATOR_DELAY = 10;
    public static final int BUTTON_VALUE_OFFSET = 1;
    public static final Dimension FLOOR_BUTTON_DIMENSION = new Dimension(20, 15);
    public static final Color FLOOR_BUTTON_COLOR = new Color(Color.lightGray.getRGB());
    
    //Controller
    public static final String COMMAND_URL = "http://www.chrishanshew.com/elevatorsimulation/consolecommands.html";
    public static final int UPDATE_DELAY = 50;
    
    //Elevator UI
    public static final GridLayout GUI_BUTTON_LAYOUT = new GridLayout(2, 6, 5, 5);
    public static final String TITLE = "Elevator Simulation";
    
    //Error Frame
    public static final Dimension ERROR_FRAME_DIMENSION = new Dimension(200, 300);
    
    //Console Time string
    public static final String DATE_FORMAT_NOW = "M-dd HH:mm:ss";
    public static String currentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());

      }
}
