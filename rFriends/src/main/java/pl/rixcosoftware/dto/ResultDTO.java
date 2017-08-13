package pl.riscosoftware.dto;

import java.util.List;

public class ResultDTO {

	private List<PersonDTO> persons;
	private int total_count;
	private int start_index;
	private int range;
	
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public List<PersonDTO> getPersonDTO() {
		return persons;
	}
	public void setPersonDTO(List<PersonDTO> persons) {
		this.persons = persons;
	}
	public List<PersonDTO> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
	}
	public int getStart_index() {
		return start_index;
	}
	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
}
