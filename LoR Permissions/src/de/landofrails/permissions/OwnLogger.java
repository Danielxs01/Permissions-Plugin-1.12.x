package de.landofrails.permissions;

import java.util.logging.Logger;

public class OwnLogger {

	private static Logger logger = null;

	private OwnLogger() {
	}

	public static Logger getLogger() {
		if (logger == null)
			logger = Logger.getLogger("LoR Permissions");
		return logger;
	}

}
