package sample.service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import sample.model.CommonExceptions.ApiException;
import sample.model.GitHubRepository;
import sample.model.MashupResult;
import sample.model.PageModels.PagedResult;
import sample.model.PageModels.PagingContext;
import twitter4j.Status;

public class MashupApiServiceImpl implements MashupApiService {

	private GitHubApiService githubApiService;
	private Twitter4jApiService twitter4jApiService;

	public MashupApiServiceImpl(GitHubApiService githubApiService, Twitter4jApiService twitter4jApiService) {
		this.githubApiService = githubApiService;
		this.twitter4jApiService = twitter4jApiService;
	}

	public List<MashupResult> searchForReactiveProjects() {

		PagedResult<GitHubRepository> pagedResult = githubApiService.search("reactive", new PagingContext(1, 10));

		final List<Callable<MashupResult>> taskList = new LinkedList<Callable<MashupResult>>();

		for (final GitHubRepository repository : pagedResult.getResults()) {
			taskList.add(new Callable<MashupResult>() {

				public MashupResult call() throws Exception {
					List<Status> statusList = twitter4jApiService.searchTweets(repository.getFullName());
					return new MashupResult(repository, statusList);
				}
			});
		}

		final ExecutorService executorPool = Executors
				.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

		try {
			final List<Future<MashupResult>> mashupResultList = executorPool.invokeAll(taskList);

			List<MashupResult> results = new LinkedList<MashupResult>();

			for (final Future<MashupResult> result : mashupResultList) {
				results.add(result.get());
			}

			return results;
			
		} catch (InterruptedException ie) {
			throw new ApiException("Unable to perform mashup request task was Interrupted ", ie);
		} catch (ExecutionException ee) {
			throw new ApiException("Unable to perform mashup request", ee);
		} finally {
			executorPool.shutdown();
		}
	}
}
