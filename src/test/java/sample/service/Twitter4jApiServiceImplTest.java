package sample.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Scopes;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.SymbolEntity;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

public class Twitter4jApiServiceImplTest {

	private Twitter4jApiService twitter4jApiService;
	private TwitterFactory twitterFactory;
	private Twitter twitter;

	@Before
	public void setup() {
		twitterFactory = mock(TwitterFactory.class);
		twitter = mock(Twitter.class);

		twitter4jApiService = new Twitter4jApiServiceImpl(twitterFactory);
	}

	@Test
	public void searchTweets() throws Exception {
		when(twitterFactory.getInstance()).thenReturn(twitter);
		QueryResult queryResult = mock(QueryResult.class);
		
		when(twitter.search(any(Query.class))).thenReturn(queryResult);
		
		List<Status> resultList = new LinkedList<Status>();
		resultList.add(new TwitterStatus(1l));
		when(queryResult.getTweets()).thenReturn(resultList);
		
		List<Status> statusList = twitter4jApiService.searchTweets("reactive");
		
		Assert.assertTrue(statusList.size()==1);
		Assert.assertEquals(statusList.get(0).getId(), 1l);
		
		verify(twitter).search(any(Query.class));
		verify(queryResult).getTweets();
		verify(twitterFactory);
		
	}

	@SuppressWarnings("serial")
	static class TwitterStatus implements Status {

		private Long id;

		public TwitterStatus(Long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}

		public HashtagEntity[] getHashtagEntities() {
			return null;
		}

		public MediaEntity[] getMediaEntities() {
			return null;
		}

		public SymbolEntity[] getSymbolEntities() {
			return null;
		}

		public URLEntity[] getURLEntities() {
			return null;
		}

		public UserMentionEntity[] getUserMentionEntities() {
			return null;
		}

		public int compareTo(Status o) {
			return 0;
		}

		public int getAccessLevel() {
			return 0;
		}

		public RateLimitStatus getRateLimitStatus() {
			return null;
		}

		public Date getCreatedAt() {
			return null;
		}

		public String getText() {
			return null;
		}

		public int getDisplayTextRangeStart() {
			return 0;
		}

		public int getDisplayTextRangeEnd() {
			return 0;
		}

		public String getSource() {
			return null;
		}

		public boolean isTruncated() {
			return false;
		}

		public long getInReplyToStatusId() {
			return 0;
		}

		public long getInReplyToUserId() {
			return 0;
		}

		public String getInReplyToScreenName() {
			return null;
		}

		public GeoLocation getGeoLocation() {
			return null;
		}

		public Place getPlace() {
			return null;
		}

		public boolean isFavorited() {
			return false;
		}

		public boolean isRetweeted() {
			return false;
		}

		public int getFavoriteCount() {
			return 0;
		}

		public User getUser() {
			return null;
		}

		public boolean isRetweet() {
			return false;
		}

		public Status getRetweetedStatus() {
			return null;
		}

		public long[] getContributors() {
			return null;
		}

		public int getRetweetCount() {
			return 0;
		}

		public boolean isRetweetedByMe() {
			return false;
		}

		public long getCurrentUserRetweetId() {
			return 0;
		}

		public boolean isPossiblySensitive() {
			return false;
		}

		public String getLang() {
			return null;
		}

		public Scopes getScopes() {
			return null;
		}

		public String[] getWithheldInCountries() {
			return null;
		}

		public long getQuotedStatusId() {
			return 0;
		}

		public Status getQuotedStatus() {
			return null;
		}

	}

}
