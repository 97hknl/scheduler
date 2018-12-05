import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Shift implements ShiftInterface {
	private int type;
	private int maxEmployees;
	private ArrayList<LanguageInterface> requiredLanguages;
	private ArrayList<EmployeeInterface> employees;
	private ArrayList<EmployeeInterface> selectedEmployees;
	private LocalDate date;
	private ShiftInterface previousShift;
	
	public Shift(int type, LocalDate date, int maxEmployees) {
		this.type = type;
		this.date = date;
		this.maxEmployees = maxEmployees;
	}
	
	public void selectEmployees() {
		DynamicComprator comparator = new DynamicComprator(this);
		Collections.sort(employees, comparator);
		
		for(int i=0; i<maxEmployees; i++) 
			selectedEmployees.add(employees.get(i));
		
		increasePriority();
	}
	
	public void increasePriority() {
		for(int i=maxEmployees; i<employees.size(); i++) {
			EmployeeInterface employee = employees.get(i);
			if(employee.isAvailableOn(this, true)) {
				if(employee.getNumberOfAssignedShifts() < employee.getAllowedNumberOfShifts()) {
					employee.setPriority(employee.getPriority() + 1); //exponential increase?
				}
			}
		}
	}
	
	public ShiftInterface getPreviousShift() {
		return previousShift;
	}

	public void setPreviousShift(ShiftInterface previousShift) {
		this.previousShift = previousShift;
	}

	public void addEmployee(EmployeeInterface employee) {
		employees.add(employee);
	}
	
	public void addrequiredLanguages(LanguageInterface language) {
		requiredLanguages.add(language);
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getMaxEmployees() {
		return maxEmployees;
	}
	
	public void setMaxEmployees(int maxEmployees) {
		this.maxEmployees = maxEmployees;
	}
	
	public ArrayList<LanguageInterface> getRequiredLanguages() {
		return requiredLanguages;
	}
	
	public ArrayList<EmployeeInterface> getEmployees() {
		return employees;
	}

	public ArrayList<EmployeeInterface> getSelectedEmployees() {
		return selectedEmployees;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
