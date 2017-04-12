package sample.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import sample.model.CommonExceptions.ConfigurationException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Configer {

	private static Logger logger = Logger.getLogger(Configer.class.getName());
	private static final String CONFIGURATION_FILE_NAME = "application.properties";

	private Properties properties = new Properties();

	public void configure() {
		loadConfigProperties();
	}

	public String getTwitterConsumerKey() {
		return getConfigurationPropertyOrError("twitter.consumerKey");
	}

	public String getTwitterConsumerSecret() {
		return getConfigurationPropertyOrError("twitter.consumerSecret");
	}

	public String getTwitterAccessToken() {
		return getConfigurationPropertyOrError("twitter.accessToken");
	}

	public String getTwitterAccessSecret() {
		return getConfigurationPropertyOrError("twitter.accessTokenSecret");
	}

	public TwitterFactory getTwitter4jFactory() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(getTwitterConsumerKey())
				.setOAuthConsumerSecret(getTwitterConsumerSecret()).setOAuthAccessToken(getTwitterAccessToken())
				.setOAuthAccessTokenSecret(getTwitterAccessSecret());
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf;

	}

	private static final String FORMAT_CONFIGURATION_ERROR = "Configuration value: %s not set.  See application.properties";

	private String getConfigurationPropertyOrError(String key) {
		String value = properties.getProperty(key, null);

		if (value == null) {
			throw new ConfigurationException(String.format(FORMAT_CONFIGURATION_ERROR, key));
		}

		return value;
	}

	private void loadConfigProperties() {
		File configFile = new File(System.getProperty("user.dir"), CONFIGURATION_FILE_NAME);

		if (logger.isLoggable(Level.INFO)) {
			logger.info(String.format("Using configuration file %s", configFile.getAbsolutePath()));
		}

		if (!configFile.exists()) {
			throw new ConfigurationException(
					String.format("Unable to find  %s.  Please ensure this configuration file exists at %s",
							CONFIGURATION_FILE_NAME, configFile.getAbsolutePath()));
		}

		InputStream is = null;

		try {
			is = new FileInputStream(configFile);
			properties.load(is);
		} catch (IOException ioe) {
			throw new ConfigurationException(
					String.format("Unable to read  %s.  Please ensure this configuration file exists at %s",
							CONFIGURATION_FILE_NAME, configFile.getAbsolutePath()));
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ioe) {
					ioe.printStackTrace(System.err);
				}
			}
		}
	}

}
