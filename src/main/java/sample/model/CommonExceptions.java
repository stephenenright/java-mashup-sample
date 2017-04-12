package sample.model;

public class CommonExceptions {

	public static class ConfigurationException extends RuntimeException {

		private static final long serialVersionUID = -4700711228244127234L;

		public ConfigurationException(String message) {
			super(message);
		}

		public ConfigurationException(String message, Throwable t) {
			super(message, t);
		}

	}

	public static class ApiException extends RuntimeException {

		private static final long serialVersionUID = -4700711228244127234L;

		public ApiException(String message) {
			super(message);
		}

		public ApiException(String message, Throwable t) {
			super(message, t);
		}

	}
}
