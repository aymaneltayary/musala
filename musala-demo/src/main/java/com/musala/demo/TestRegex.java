/**
 * 
 */
package com.musala.demo;

import java.util.regex.Pattern;

/**
 * @author aeltayary
 *
 */
public class TestRegex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Pattern PATTERN = Pattern.compile(
		        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

		System.out.println(PATTERN.matcher("192.186.10.1").matches());
	}

}
