package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersistentArrayTest {

	@Test
	public void test() {
		PersistentArray.initialize("testFile", 10, -1);
		PersistentArray file = new PersistentArray("testFile");
		assertTrue(file.get(0) == -1);
		file.set(3, 69);
		assertTrue(file.get(3) == 69);
		System.out.println(file.getLength());
		assertTrue(file.getLength() == 80);
		file.close();
		PersistentArray.delete("testFile");
	}

}
