import java.util.ArrayList;

public class Language implements LanguageInterface{
	private String language;
	private int numberOfSpeakers;
	private ArrayList<EmployeeInterface> speakers;
	
	public Language(String language) {
		this.language = language;
	}
	
	public void addSpeaker(EmployeeInterface employee) {
		speakers.add(employee);
		numberOfSpeakers++;
	}

	public int numberOfSpeakers() {
		return numberOfSpeakers;
	}

	public ArrayList<EmployeeInterface> getSpeakers() {
		return speakers;
	}

	public String getLangauge() {
		return language;
	}

	public int getNumberOfSpeakers() {
		return numberOfSpeakers;
	}
	
	public boolean equals(Language o) {
		return this.language.equals(o.getLangauge());
	}

	public String toString() {
		return "Language [language=" + language + 
				", numberOfSpeakers=" + numberOfSpeakers + 
				", speakers=" + speakers + "]";
	}
}
