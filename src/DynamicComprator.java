import java.util.Comparator;

//https://stackoverflow.com/questions/31199035/how-to-send-multiple-arguments-to-compareto-to-filter-sorting-type-and-sort-by-i
public class DynamicComprator implements Comparator<EmployeeInterface>{
	private static ShiftInterface shift;

	public DynamicComprator(ShiftInterface shift) {
		this.shift = shift;
	}

	public DynamicComprator compareBy(ShiftInterface compareBy) {
		this.shift = compareBy;
		return this;
	}

	//sort in descending order. If b comes first, return 1. If a comes first, return -1.
	public int compare(EmployeeInterface a, EmployeeInterface b) {

		//check if exactly one of them have worked previous continuous shift
		if(shift.getPreviousShift() != null) {
			if(shift.getPreviousShift().getSelectedEmployees().contains(a)) {
				if(!shift.getPreviousShift().getSelectedEmployees().contains(b)) {
					return 1;
				}
			} else {
				if(shift.getPreviousShift().getSelectedEmployees().contains(b)) {
					return -1;
				}
			}
		}

		//check if exactly one of them are able to work on this shift
		if(!a.isAvailableOn(shift, true)) {
			if(b.isAvailableOn(shift, true))			
				return 1;
		} else {
			if(!b.isAvailableOn(shift, true))
				return -1;
		}

		//check if they have already worked 'enough' (legally or as deemed by Adyen)
		if(a.getNumberOfAssignedShifts() == a.getAllowedNumberOfShifts()) {
			if(b.getNumberOfAssignedShifts() < b.getAllowedNumberOfShifts())
				return 1;
		} else if(a.getNumberOfAssignedShifts() < a.getAllowedNumberOfShifts()) {
			if(b.getNumberOfAssignedShifts() == b.getAllowedNumberOfShifts())
				return -1;
		}

		//check if exactly one of them is a sentinel
		if(a.isSentinel()) {
			return -1;
		} else if(b.isSentinel()) {
			return 1;
		}

		//check who speaks more languages (weight: 25%)
		int aNumLanguages = 0;
		int bNumLanguages = 0;
		for(int i=0; i<shift.getRequiredLanguages().size(); i++) {
			if(a.getLanguages().contains(shift.getRequiredLanguages().get(i)))
				aNumLanguages++;
			if(b.getLanguages().contains(shift.getRequiredLanguages().get(i)))
				bNumLanguages++;
		}

		//check if this is preferred shift of exactly one of them (weight: 30%)
		int aPreferred = 0;
		int bPreferred = 0;
		if(a.isAvailableOn(shift, false)) {
			if(!b.isAvailableOn(shift, false)) {
				aPreferred = 5;
			}
		} else {
			if(b.isAvailableOn(shift, false)) {
				bPreferred = 5;
			}
		}

		//check who has worked more based on their preference availability (satisfaction) (weight: 20%)
		int aDiffPreferredShifts = a.getMinShifts() - a.getNumberOfAssignedShifts();
		int bDiffPreferredShifts = b.getMinShifts() - b.getNumberOfAssignedShifts();

		//check who has worked more based on their overall availability (satisfaction) (weight: 10%)
		int aDiffAvailableShifts = a.getMaxShifts() - a.getNumberOfAssignedShifts();
		int bDiffAvailableShifts = b.getMaxShifts() - b.getNumberOfAssignedShifts();

		//check who has more priority (weight: 15)

		//calculate points based on the above metrics
		double aPoints = 0.25 * aNumLanguages
				+ 0.3 * aPreferred
				+ 0.2 * aDiffPreferredShifts
				+ 0.1 * aDiffAvailableShifts
				+ 0.15 * a.getPriority();
		double bPoints = 0.25 * bNumLanguages
				+ 0.3 * bPreferred
				+ 0.2 * bDiffPreferredShifts
				+ 0.1 * bDiffAvailableShifts
				+ 0.15 * b.getPriority();

		//award shift to the person having most points, else randomize
		if(aPoints != bPoints) {
			return (aPoints > bPoints)? -1 : 1;
		} else {
			double x = Math.random();
			return (x > 0.5)? 1 : -1;
		}
	}
}
