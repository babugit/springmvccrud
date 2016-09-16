package example.spring.mvccrud.persistence.service.entity;

import java.io.Serializable;

import example.spring.mvccrud.config.Utils;

public class ApplicantInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String gender;
	private String name;
	private String position;
	private String email;
	private String skillsString;
	private String[] skills;

	public ApplicantInfo() {
		super();
	}

	public ApplicantInfo(Integer id, String name, String email, String gender, String position, String skillsString) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		this.gender = gender;
		this.skillsString = skillsString;
		this.skills = Utils.stringToArray(skillsString);
	}	

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the skills
	 */
	public String[] getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(String[] skills) {
		this.skills = skills;
		this.setSkillsString(Utils.arrayToString(skills));
	}

	/**
	 * @return the skillsString
	 */
	public String getSkillsString() {
		return skillsString;
	}

	/**
	 * @param skillsString the skillsString to set
	 */
	public void setSkillsString(String skillsString) {
		this.skillsString = skillsString;
	}
}
