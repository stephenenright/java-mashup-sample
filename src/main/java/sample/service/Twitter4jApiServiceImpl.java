package sample.service;

import java.util.List;

import sample.model.CommonExceptions.ApiException;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Twitter4jApiServiceImpl implements Twitter4jApiService {

	private TwitterFactory twitterFactory;
	
	public Twitter4jApiServiceImpl(TwitterFactory twitterFactory) {
		this.twitterFactory = twitterFactory;
	}
	
	public List<Status> searchTweets(String term) {
		Twitter twitter = twitterFactory.getInstance();
		Query query = new Query(term);
		
		try {
			QueryResult result = twitter.search(query);
			return result.getTweets();
			
		}
		catch (TwitterException te) {
			throw new ApiException("Error calling twitter api", te);
		}
	}

	public TwitterFactory getTwitterFactory() {
		return twitterFactory;
	}

	public void setTwitterFactory(TwitterFactory twitterFactory) {
		this.twitterFactory = twitterFactory;
	}
	
	
}
