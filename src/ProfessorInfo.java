import java.util.Map;


public class ProfessorInfo {
	private String name;
	private Map<String, String> contactInfo;
	private String educationBackground;
	private String researchInterests;

	
	public ProfessorInfo(String name,Map<String, String> contactInfo,
			String educationBackground, String researchInterests) {
		super();
		this.name = name;
		this.contactInfo = contactInfo;
		this.educationBackground = educationBackground;
		this.researchInterests = researchInterests;
	}

	public ProfessorInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return contactInfo.get("email");
	}

	public String getPhone() {
		return contactInfo.get("phone");
	}

	public Map<String, String> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(Map<String, String> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}

	public String getResearchInterests() {
		return researchInterests;
	}

	public void setResearchInterests(String researchInterests) {
		this.researchInterests = researchInterests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
