import java.time.LocalDate;
import java.util.ArrayList;

public interface ShiftInterface {
	int getType();
	void setType(int type);
	int getMaxEmployees();
	void setMaxEmployees(int maxEmployees);
	ArrayList<EmployeeInterface> getEmployees();
	ArrayList<LanguageInterface> getRequiredLanguages();
	ArrayList<EmployeeInterface> getSelectedEmployees();
	LocalDate getDate();
	void setDate(LocalDate date);
	void addrequiredLanguages(LanguageInterface language);
	void addEmployee(EmployeeInterface employee);
	void setPreviousShift(ShiftInterface previousShift);
	ShiftInterface getPreviousShift();
	void selectEmployees();
	void increasePriority();
}
