package sample.model;

import java.util.List;

public class PageModels {

	public static class PagingContext {

		private int page;
		private int numResultsPerPage;

		public PagingContext(int pageNumber, int numResultsPerPage) {
			this.page = pageNumber;
			this.numResultsPerPage = numResultsPerPage;
		}

		public int getResultsPerPage() {
			return numResultsPerPage;
		}

		public int getPageNumber() {
			if (page == 0) {
				return 1;
			}

			return page;
		}

		public static PagingContext createForNextPage(PagingContext pagingContenxt) {
			return new PagingContext(pagingContenxt.getPageNumber() + 1, pagingContenxt.getResultsPerPage());
		}

	}

	public static class PagedResult<T> {
		private List<T> results;
		private long totalResults;

		public PagedResult(List<T> results, long totalResults) {
			this.results = results;
			this.totalResults = totalResults;

		}

		public List<T> getResults() {
			return results;
		}

		public long getTotalResults() {
			return totalResults;
		}
	}

}
