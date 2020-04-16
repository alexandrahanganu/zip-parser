package com.zip.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

interface Variables {
	public static final String date = "<date>";
	public static final String time = "<time>";
	public static final String uid = "<uid>";
	
	static final String dateFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yy")).toString();
	static final String timeFormat = LocalTime.now().format(DateTimeFormatter.ofPattern("hh_mm")).toString();
}
