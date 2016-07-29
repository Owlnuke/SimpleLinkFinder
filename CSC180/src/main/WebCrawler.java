package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler implements Visitable {
	public static Pattern p = Pattern.compile("<[Aa]\\s+[hH][rR][eE][fF]\\s*=\\s*\"([^\"]+)\".*>.*");
	ArrayList<URL> VisitedLinks = new ArrayList<URL>();
	ArrayList<URL> ToVisitLinks = new ArrayList<URL>();
	private static final int maxPages = 50;
	private int i = 1;

	private void SprintTheWeb(URL in) {
		VisitedLinks.add(in);
		Visit(in);
		VisitAction(in);
		Iterator<URL> visitLinks = null;
		int count = 0;
		URL currentLink = null;
		while (!ToVisitLinks.isEmpty() && count < maxPages) {
			visitLinks = getLinks(ToVisitLinks);
			currentLink = visitLinks.next();
			ToVisitLinks.remove(currentLink);
			VisitedLinks.add(currentLink);
			Visit(currentLink);
			VisitAction(currentLink);
			count++;
		}
	}

	public void Visit(URL in) {
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(in.openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String line;
		BufferedReader buffReader = new BufferedReader(reader);
		try {
			while ((line = buffReader.readLine()) != null) {
				GrabLinks(p, line, in);
			}
		} catch (java.io.IOException e) {
		}
	}

	public Iterator<URL> getLinks(ArrayList<URL> array) {
		return array.iterator();
	}

	
	public void GrabLinks(Pattern pattern, String line, URL in) {
		Matcher m = pattern.matcher(line);
		URL url = null;
		if (m.find()) {
			try {
				url = new URL("http://" + in.getHost() + "/" + m.group(1));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			if (!VisitedLinks.contains(url) && !ToVisitLinks.contains(url)) {
				ToVisitLinks.add(url);
			}
		}
	}

	public void Initialize(URL in) {
		try {
			SprintTheWeb(in);
		} catch (java.lang.NullPointerException c) {
			c.printStackTrace();
		}
	}

	@Override
	public void VisitAction(URL url) {
		System.out.println("Now Visiting URL #" + i++ + ": " + url.toString());
	}

}
