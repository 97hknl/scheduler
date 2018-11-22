import java.util.ArrayList;

public interface LanguageInterface {
	/*The kind of language, one of:
	 * English, Dutch, German, French, Italian, Spanish.
	 * 
	 * default constructor:
	 * Language(String language);
	 */

	String getLangauge();
	void addSpeaker(EmployeeInterface employee);
	int getNumberOfSpeakers();
	ArrayList<EmployeeInterface> getSpeakers();
	boolean equals(Language o);
	String toString();
}
