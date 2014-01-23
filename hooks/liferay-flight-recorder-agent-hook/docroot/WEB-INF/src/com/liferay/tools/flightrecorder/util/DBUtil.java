/**
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.tools.flightrecorder.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.Map.Entry;
import java.util.Set;

import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * @author Vilmos Papp
 */
public class DBUtil {

	public static DBUtil getInstance() throws IOException {
		if (_instance == null) {
			_instance = new DBUtil();
		}

		return _instance;
	}

	public Set<Entry<Object, Object>> getRecordsToSend() {
		return _db.getHashMap(MAP_NAME).entrySet();
	}

	public void pushRecord(String key, Serializable value) {
		_db.getHashMap(MAP_NAME).put(key, value);
		_db.commit();
	}

	public void removeSentRecords(Set<Entry<Object, Object>> records) {
		for (Entry<Object, Object> entry : records) {
			_db.getHashMap(MAP_NAME).remove(entry.getKey());
		}
	}

	public void shutdown() {
		_db.close();
	}

	private DBUtil() throws IOException {
		_indexFile = File.createTempFile(FILE_NAME, EXTENSION);

		// ((EngineWrapper)_db.getEngine()).getClass().getName() -> StoreWAL

		_db = DBMaker.newFileDB(_indexFile).closeOnJvmShutdown().make();
	}

	private static final String EXTENSION = ".idx";

	private static final String FILE_NAME = "lfr";

	private static final String MAP_NAME = "liferay-flight-recorder";

	private static DBUtil _instance;

	private static DB _db;
	private static File _indexFile;

}