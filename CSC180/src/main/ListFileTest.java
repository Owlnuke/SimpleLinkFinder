package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListFileTest {

	@Test
	public void test() {
		Entry testEntry = new Entry("testing!", 42, 12);
		ListFile.delete("testFile");
		ListFile.initialize("testFile");
		ListFile testFile = new ListFile("testFile");
		long temp = testFile.newEntry(testEntry);
		assertTrue(temp == 0);
		Entry storedEntry = testFile.getEntry(0);
		boolean testBool = storedEntry.getString().equals(testEntry.getString());
		assertTrue(testBool);
		testBool = storedEntry.getValue() == testEntry.getValue();
		assertTrue(testBool);
		testBool = storedEntry.getLink() == testEntry.getLink();
		assertTrue(testBool);
		ListFile.delete("testFile");
	}

}
