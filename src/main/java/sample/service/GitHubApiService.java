package sample.service;

import sample.model.GitHubRepository;
import sample.model.PageModels.PagedResult;
import sample.model.PageModels.PagingContext;

public interface GitHubApiService {

	public PagedResult<GitHubRepository> search(String keyword, PagingContext context);

}
