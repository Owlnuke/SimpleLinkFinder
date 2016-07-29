package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PersistentArray {
	private static RandomAccessFile file;
	private static final int offset = 8;

	public PersistentArray(String arrayFileName) {
		try {
			file = new RandomAccessFile(arrayFileName, "rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initialize(String arrayFileName, int arraySize, long initialValue) {
		try {
			file = new RandomAccessFile(arrayFileName, "rw");
			file.setLength(arraySize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int x = 0; x < arraySize; x++) {
			try {
				file.seek(x * offset);
				file.writeLong(initialValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void set(int index, long value) {
		try {
			file.seek(index * offset);
			file.writeLong(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long get(int index) {
		long x = -1;
		try {
			file.seek(index * offset);
			x = file.readLong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x;
	}

	public long getLength() {
		long x = -1;
		try {
			x = file.length();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return x;
	}

	public void close() {
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void delete(String arrayFileName) {
		File f = new File(arrayFileName);
		f.delete();
	}

}
