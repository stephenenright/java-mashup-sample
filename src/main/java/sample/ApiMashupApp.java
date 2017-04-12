package sample;

import java.util.List;

import sample.configuration.Configer;
import sample.model.MashupResult;
import sample.service.GitHubHttpClientApiServiceImpl;
import sample.service.MashupApiService;
import sample.service.MashupApiServiceImpl;
import sample.service.MashupPrintConsoleServiceImpl;
import sample.service.MashupPrintService;
import sample.service.Twitter4jApiServiceImpl;

public class ApiMashupApp {

	public ApiMashupApp() {

	}

	public void run() {
		Configer configer = new Configer();
		configer.configure();

		MashupApiService mashupApiService = new MashupApiServiceImpl(new GitHubHttpClientApiServiceImpl(),
				new Twitter4jApiServiceImpl(configer.getTwitter4jFactory()));

		List<MashupResult> resultsLists = mashupApiService.searchForReactiveProjects();
		MashupPrintService printService = new MashupPrintConsoleServiceImpl();

		for (MashupResult result : resultsLists) {
			printService.print(result);
		}

	}

	public static void main(String[] args) {
		new ApiMashupApp().run();
	}

}
