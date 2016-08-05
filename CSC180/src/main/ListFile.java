package main;

import java.io.File;
import java.io.RandomAccessFile;

public class ListFile {
	private static RandomAccessFile listFile;

	static void initialize(String listFileName) {
		try {
			listFile = new RandomAccessFile(listFileName, "rw");
			listFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(String listFileName) {
		File f = new File(listFileName);
		f.delete();
	}

	public ListFile(String listFileName) {
		try {
			listFile = new RandomAccessFile(listFileName, "rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			listFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long newEntry(Entry entry) {
		long x = 0;
		try {
			x = listFile.length();
			listFile.seek(x);
			listFile.writeInt(entry.getString().length());
			listFile.writeBytes(entry.getString());
			listFile.writeLong(entry.getLink());
			listFile.writeLong(entry.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public Entry getEntry(long offset) {
		Entry entry = null;
		try {
			listFile.seek(offset * 8);
			int x = listFile.readInt();
			byte[] b = new byte[x];
			for (int j = 0; j < x; j++) {
				b[j] = listFile.readByte();
			}
			long link = listFile.readLong();
			long value = listFile.readLong();
			entry = new Entry(new String(b), value, link);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entry;

	}

	public void putEntry(long offset, Entry entry) {
		try {
			listFile.seek(offset * 8);
			listFile.writeInt(entry.getString().length());
			listFile.writeBytes(entry.getString());
			listFile.writeLong(entry.getLink());
			listFile.writeLong(entry.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
