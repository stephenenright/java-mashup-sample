package sample.model;

import java.util.List;

import twitter4j.Status;

public class MashupResult {

	private GitHubRepository repository;
	private List<Status> statusList;
	
	public  MashupResult(GitHubRepository repository, List<Status> statusList) {
		this.repository = repository;
		this.statusList = statusList;
	}

	public GitHubRepository getRepository() {
		return repository;
	}
	public List<Status> getStatusList() {
		return statusList;
	}
}
