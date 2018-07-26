package com.flightnetworks.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class FileUtility {

	private static final String SRC_MAIN_RESOURCES = "src/main/resources";

	private FileUtility() {
	}

	public static List<String> getContents() {
		List<String> contents = new ArrayList<String>();

		List<String> fileNames = listFilesForFolder(new File(SRC_MAIN_RESOURCES));
		for (String fileName : fileNames) {
			LineIterator it = null;
			try {
				it = FileUtils.lineIterator(new File(SRC_MAIN_RESOURCES, fileName));
				while (it.hasNext()) {
					String line = it.nextLine();
					contents.add(line);
				}
			} catch (IOException ioe) {
				System.out.println("IO exception while reading files :" + ioe.getMessage());
			} finally {
				LineIterator.closeQuietly(it);
			}
		}

		return contents;
	}

	private static List<String> listFilesForFolder(final File folder) {
		List<String> fileNames = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				fileNames.add(fileEntry.getName());
			}
		}
		return fileNames;
	}

}
