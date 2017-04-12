package sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubRepository {

	private String name;
	
	@JsonProperty("full_name")
	private String fullName;

	private String description;
	
	@JsonProperty("private")
	private boolean privateRepository;
	
	@JsonProperty("html_url")
	private String htmlUrl;
	
	
	public String getName() {
		return name;
	}


	public String getFullName() {
		return fullName;
	}


	public String getDescription() {
		return description;
	}


	public boolean isPrivateRepository() {
		return privateRepository;
	}


	public String getHtmlUrl() {
		return htmlUrl;
	}
	
	


}
