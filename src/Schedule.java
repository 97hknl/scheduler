import java.time.LocalDate;
import java.util.ArrayList;

public class Schedule implements ScheduleInterface {
	final int MORNING_SHIFT = 0;
	final int EVENING_SHIFT = 0;
	final int NUM_EMPLOYEES_MORNING_SHIFT = 0;
	final int NUM_EMPLOYEES_EVENING_SHIFT = 0;
	
	private ArrayList<ShiftInterface> shifts;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public void init() {
		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)){
			Shift shift1 = new Shift(MORNING_SHIFT, date, NUM_EMPLOYEES_MORNING_SHIFT);
			Shift shift2 = new Shift(MORNING_SHIFT, date, NUM_EMPLOYEES_MORNING_SHIFT);
			shifts.add(shift1);
			shifts.add(shift2);
		}
		for(int i=0; i<)
	}
}
