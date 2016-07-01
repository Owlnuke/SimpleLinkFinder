package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkFinder {
	public static Pattern p = Pattern.compile("<[Aa]\\s+[hH][rR][eE][fF]\\s*=\\s*\"([^\"]+)\".*>.*");
	ArrayList<String> Links = new ArrayList<String>();
	ArrayList<String> results = new ArrayList<String>();

	private void ProcessResultPage(InputStream out) {
		InputStreamReader reader = new InputStreamReader(out);
		String line;
		BufferedReader buffReader = new BufferedReader(reader);
		try {
			while ((line = buffReader.readLine()) != null) {
				TestPattern(p, line, false);
				// System.out.println(line);
			}
		} catch (java.io.IOException e) {
		}
		System.out.println("VVVVVV");
	}

	private void ProcessPage(InputStream in) {
		InputStreamReader reader = new InputStreamReader(in);
		String line;
		BufferedReader buffReader = new BufferedReader(reader);
		try {
			while ((line = buffReader.readLine()) != null) {
				TestPattern(p, line, true);
				// System.out.println(line);
			}
		} catch (java.io.IOException e) {
		}
		System.out.println("------");
	}

	public Iterator<String> getLinks(ArrayList<String> array) {
		return array.iterator();
	}

	public boolean TestPattern(Pattern pattern, String line, boolean condition) {
		Matcher m = pattern.matcher(line);
		if (condition) {
			if (m.find()) {
				Links.add(m.group(1));
				return true;
			} else {
				return false;
			}
		} else {
			if (m.find()) {
				return false;
			} else {

				results.add(line);
				return true;
			}
		}

	}

	public void Vomit(int j) {
		if (j == 1) {
			int i = 1;
			for (String s : Links) {
				System.out.println(i++ + " " + s);
			}
		} else {
			int i = -89;
			for (String s : results) {
				System.out.println(i++ + " " + s);
			}
		}
	}

	public void Initialize(InputStream in, InputStream out) {
		try {
			ProcessPage(in);
			ProcessResultPage(out);
		} catch (java.lang.NullPointerException c) {
		}
	}

}
