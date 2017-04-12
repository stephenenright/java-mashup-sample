package sample;

import sample.configuration.Configer;
import sample.service.GitHubHttpClientApiServiceImpl;
import sample.service.MashupApiService;
import sample.service.MashupApiServiceImpl;
import sample.service.MashupPrintConsoleServiceImpl;
import sample.service.Twitter4jApiServiceImpl;

public class ApiMashupApp {

	public ApiMashupApp() {

	}
	
	public void run() {
		Configer configer = new Configer();
		configer.configure();

		MashupApiService mashupApiService = new MashupApiServiceImpl(new GitHubHttpClientApiServiceImpl(),
				new Twitter4jApiServiceImpl(configer.getTwitter4jFactory()), new MashupPrintConsoleServiceImpl());

		mashupApiService.searchForReactiveProjects();
		
	}

	public static void main(String[] args) {
		new ApiMashupApp().run();
	}

}
