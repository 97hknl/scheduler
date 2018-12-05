import java.util.ArrayList;

public class Employee implements EmployeeInterface{
	private String name;
	private ArrayList<LanguageInterface> languages;
	private ArrayList<ShiftInterface> preferredShifts;
	private ArrayList<ShiftInterface> availableShifts;
	private ArrayList<ShiftInterface> assignedShifts;
	private int priority;
	private boolean sentinel;
	private int maxShifts;
	private int minShifts;
	private int numberOfAssignedShifts;
	private int allowedNumberOfShifts;
	
	public Employee(String name, 
			ArrayList<LanguageInterface> languages, int allowedNumberOfShifts) {
		this.name = name;
		this.languages = languages;
		this.allowedNumberOfShifts = allowedNumberOfShifts;
	}
	
	public void addPreferredShift(ShiftInterface shift) {
		preferredShifts.add(shift);
		minShifts++;
		maxShifts++;
	}
	
	public void addAvailableShift(ShiftInterface shift) {
		availableShifts.add(shift);
		maxShifts++;
	}

	public boolean isSentinel() {
		return sentinel;
	}
	
	public void assignShift(ShiftInterface shift) {
		assignedShifts.add(shift);
		numberOfAssignedShifts++;
	}
	
	public boolean isAvailableOn(ShiftInterface shift, boolean checkAvailableShifts) {
		for(ShiftInterface child : preferredShifts) {
			if(child.equals(shift))
				return true;
		}
		if(checkAvailableShifts)
			for(ShiftInterface child : availableShifts) {
				if(child.equals(shift))
					return true;
			}
		
		return false;
	}
	
	//getters:
	public int getMaxShifts() {
		return maxShifts;
	}

	public int getMinShifts() {
		return minShifts;
	}
	
	public int getAllowedNumberOfShifts() {
		return allowedNumberOfShifts;
	}
	
	public int getNumberOfAssignedShifts() {
		return numberOfAssignedShifts;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<LanguageInterface> getLanguages() {
		return languages;
	}

	public ArrayList<ShiftInterface> getPreferredShifts() {
		return preferredShifts;
	}

	public ArrayList<ShiftInterface> getAvailableShifts() {
		return availableShifts;
	}
	
	//setters:
	public void setSentinel(boolean sentinel) {
		this.sentinel = sentinel;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/*
	public int compareTo(EmployeeInterface o, ShiftInterface shift) {
		//check if exactly one of them are able to work on this shift
		if(!this.isAvailableOn(shift, true)) {
			if(o.isAvailableOn(shift, true))			
				return -1;
		} else {
			if(!o.isAvailableOn(shift, true))
				return 1;
		}
		
		//check if they have already worked 'enough' (legally or as deemed by Adyen)
		if(this.numberOfAssignedShifts == this.allowedNumberOfShifts) {
			if(o.getNumberOfAssignedShifts() == o.getAllowedNumberOfShifts())
				return 0;
			else if(o.getNumberOfAssignedShifts() < o.getAllowedNumberOfShifts())
				return -1;
		} else if(this.numberOfAssignedShifts < this.allowedNumberOfShifts) {
			if(o.getNumberOfAssignedShifts() == o.getAllowedNumberOfShifts())
				return 1;
		}
		
		//check if one of them is a sentinel
		if(this.isSentinel()) {
			return 1;
		} else if(o.isSentinel()) {
			return -1;
		}
		
		//check who speaks more languages (weight: 25%)
		int thisNumLanguages = 0;
		int oNumLanguages = 0;
		for(int i=0; i<shift.requiredLanguages.size(); i++) {
			if(this.languages.contains(shift.requiredLanguages.get(i)))
				thisNumLanguages++;
			if(o.getLanguages().contains(shift.requiredLanguages.get(i)))
				oNumLanguages++;
		}
		
		//check if this is preferred shift of exactly one of them (weight: 30%)
		int thisPreferred = 0;
		int oPreferred = 0;
		if(this.isAvailableOn(shift, false)) {
			if(!o.isAvailableOn(shift, false)) {
				thisPreferred = 5;
			}
		} else {
			if(o.isAvailableOn(shift, false)) {
				oPreferred = 5;
			}
		}
		
		//check who has worked more based on their preference availability (satisfaction) (weight: 20%)
		int thisDiffPreferredShifts = this.minShifts - this.numberOfAssignedShifts;
		int oDiffPreferredShifts = o.getMinShifts() - o.getNumberOfAssignedShifts();
		
		//check who has worked more based on their overall availability (satisfaction) (weight: 10%)
		int thisDiffAvailableShifts = this.maxShifts - this.numberOfAssignedShifts;
		int oDiffAvailableShifts = o.getMaxShifts() - o.getNumberOfAssignedShifts();
		
		//check who has more priority (weight: 15)
		
		//calculate points based on the above metrics
		double thisPoints = 0.25 * thisNumLanguages
						+ 0.3 * thisPreferred
						+ 0.2 * thisDiffPreferredShifts
						+ 0.1 * thisDiffAvailableShifts
						+ 0.15 * priority;
		double oPoints = 0.25 * oNumLanguages
				+ 0.3 * oPreferred
				+ 0.2 * oDiffPreferredShifts
				+ 0.1 * oDiffAvailableShifts
				+ 0.15 * o.getPriority();
		
		//award shift to the person having most points, else randomize
		if(thisPoints != oPoints) {
			return (thisPoints > oPoints)? 1 : -1;
		} else {
			double x = Math.random();
			if(x > 0.5) {
				o.setPriority(o.getPriority() + 1); //exponential increase?
				return 1;
			} else {
				this.setPriority(this.getPriority() + 1);
				return -1;
			}
		}
	} */
}
