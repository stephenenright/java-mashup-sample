package sample.utils;

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class UrlUtils {

	public static String createUrl(String scheme, String host, String requestPath, String[] parameterNames,
			String[] parameterValues) {
		if (parameterNames.length != parameterValues.length) {
			throw new IllegalArgumentException("Parameter names and parameter values length must be equal");
		}

		URIBuilder builder = new URIBuilder();
		builder.setScheme(scheme).setHost(host).setPath(requestPath);

		for (int i = 0; i < parameterNames.length; i++) {
			builder.setParameter(parameterNames[i], parameterValues[i]);
		}

		try {
			return builder.build().toString();
		} catch (URISyntaxException use) {
			throw new RuntimeException(use);
		}
	}

}
