package sample.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;

import sample.model.CommonExceptions.ApiException;
import sample.model.GitHubRepository;
import sample.model.PageModels.PagedResult;
import sample.model.PageModels.PagingContext;
import sample.utils.JsonUtils;
import sample.utils.UrlUtils;

public class GitHubHttpClientApiServiceImpl implements GitHubApiService {

	private static final Logger logger = Logger.getLogger(GitHubHttpClientApiServiceImpl.class.getName());

	public PagedResult<GitHubRepository> search(String keyword, PagingContext context) {
		
		JsonNode node = JsonUtils.parse(executeGetRequest(UrlUtils.createUrl("https", "api.github.com",
				"search/repositories", 
				new String[] { "q", "page", "per_page" }, 
				new String[] { keyword, String.valueOf(context.getPageNumber()), String.valueOf(context.getResultsPerPage()) })));


		return new PagedResult<GitHubRepository>(JsonUtils.parseToList(node.get("items"), GitHubRepository.class),
				Long.valueOf(node.get("total_count").asText().trim()));

	}

	private String executeGetRequest(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		try {
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ApiException("Unable to perform git hub get request, response status: " + status);
					}
				}
			};
			String response = httpclient.execute(httpget, responseHandler);
			return response;
		} catch (IOException ioe) {
			throw new ApiException("Unable to perform git hub get request for url:" + url, ioe);
		} finally {
			try {
				httpclient.close();
			} catch (IOException ioe) {
				if (logger.isLoggable(Level.WARNING)) {
					logger.log(Level.WARNING, "Unable to close http client", ioe);
				}
			}

		}

	}

}
