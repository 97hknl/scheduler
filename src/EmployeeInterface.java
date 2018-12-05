import java.util.ArrayList;

public interface EmployeeInterface {
	void addPreferredShift(ShiftInterface shift);
	void addAvailableShift(ShiftInterface shift);
	String getName();
	ArrayList<LanguageInterface> getLanguages();
	ArrayList<ShiftInterface> getPreferredShifts();
	ArrayList<ShiftInterface> getAvailableShifts();
	boolean isSentinel();
	void setSentinel(boolean sentinel);
	int getMaxShifts();
	int getMinShifts();
	int getPriority();
	void setPriority(int priority);
	int getNumberOfAssignedShifts();
	int getAllowedNumberOfShifts();
	boolean isAvailableOn(ShiftInterface shift, boolean checkAvailableShifts);
	void assignShift(ShiftInterface shift);
	//int compareTo(EmployeeInterface o);
	String toString();
}
