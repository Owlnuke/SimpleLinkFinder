package main;

import java.net.MalformedURLException;
import java.net.URL;

public class App {

	public static void main(String[] args) {
		run();
	}

	private static void run() {
		URL in = null;
		try {
			in = new URL("http://shalladay-iis1.student.neumont.edu/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// LinkFinderTest tester = new LinkFinderTest();
		// tester.testLinks();
		WebCrawler test = new WebCrawler();
		test.Initialize(in);
	}

}
