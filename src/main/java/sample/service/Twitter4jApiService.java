package sample.service;

import java.util.List;

import twitter4j.Status;

public interface Twitter4jApiService {
	public List<Status> searchTweets(String term);
}
