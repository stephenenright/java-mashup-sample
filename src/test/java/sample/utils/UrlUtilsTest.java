package sample.utils;

import org.junit.Test;

import junit.framework.Assert;

public class UrlUtilsTest {

	@Test
	public void createUrl() {
		Assert.assertEquals(UrlUtils.createUrl("http", "test.com", "/test", 
				new String[]{"query"}, new String[]{"queryText"}), "http://test.com/test?query=queryText");
	}
	
	@Test
	public void createUrl_throwsException() {
		try {
			UrlUtils.createUrl(".dfd.", "", "", new String[]{"query"}, new String[]{"queryText"});
		}
		catch(RuntimeException re) {
			return;
		}
		
		Assert.fail("Expected runtime exception to be thrown");
		
	}
}
