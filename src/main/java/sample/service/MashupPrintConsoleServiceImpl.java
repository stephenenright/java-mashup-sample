package sample.service;

import java.util.List;

import sample.model.MashupResult;
import twitter4j.Status;

public class MashupPrintConsoleServiceImpl implements MashupPrintService {

	public void print(MashupResult result) {
		printHeaderFooterLine();
		System.out.println(String.format("Project: %s", result.getRepository().getFullName()));
		System.out.println(String.format("Short Name: %s", result.getRepository().getName()));
		System.out.println(String.format("Url: %s", result.getRepository().getHtmlUrl()));
		printTwitterStatusList(result);
		printHeaderFooterLine();
	}

	private void printTwitterStatusList(MashupResult result) {
		List<Status> twitterStatusList = result.getStatusList();

		if (twitterStatusList.size() == 0) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("Twitter Status not available for project");
			return;
		}

		for (Status status : twitterStatusList) {
			System.out.println("-----------------------------------------------------------");
			System.out.println(String.format("Twitter Status: %s", status.getText()));
		}
	}

	private void printHeaderFooterLine() {
		System.out.println("***************************************************************");
	}

}
