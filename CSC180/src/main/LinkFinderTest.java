package main;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import org.junit.Test;

public class LinkFinderTest {
	LinkFinder testfinder = new LinkFinder();
	InputStream in;
	InputStream out;

	@Test
	public void test() {
		assertTrue(testfinder.TestPattern(testfinder.p, "<a href=\"banana.html\">", true));
		assertFalse(testfinder.TestPattern(testfinder.p, "banana.html", true));
		assertTrue(testfinder.TestPattern(testfinder.p, "banana.html", false));
		assertFalse(testfinder.TestPattern(testfinder.p, "<a href=\"banana.html\">", false));
		testLinks();
	}

	public void testLinks() {
		try {
			in = new FileInputStream("neumont.txt");
			out = new FileInputStream("results.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		testfinder.Initialize(in, out);
		Iterator<String> in = testfinder.getLinks(testfinder.Links);
		Iterator<String> out = testfinder.getLinks(testfinder.results);
		int i = 1;
		while (in.hasNext() && out.hasNext()) {
			String on = in.next(), off = out.next();
			assertEquals(on, off);
			boolean result = on.equalsIgnoreCase(off);
			if (result) {
				System.out.println(i++ + " " + on + " |||| matches |||| " + off);
			} else {
				System.out.println(i++ + " " + " ^V^V does not match ^V^V " + off);
			}
		}
	}
}
