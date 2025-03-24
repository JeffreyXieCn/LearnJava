package com.example.accessingdatamongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;


public class Customer {

	@Id
	public String id;

	public String firstName;
	public String lastName;
	public Map<String, String> programmingLanguages;

	public Customer() {}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		programmingLanguages = new HashMap<>();
	}

	public void addProgrammingLanguage(String name, String level) {
		programmingLanguages.put(name, level);
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[id=%s, firstName='%s', lastName='%s', programmingLanguages='%s']",
				id, firstName, lastName, programmingLanguages);
	}

}

