package com.multicampus.teamProj4.bank.utils;

public class RandomStringGenerator {
	static private String seedStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	static public String getRandomString(int requestLength) {
		StringBuilder str = new StringBuilder();
		int length = seedStr.length();
		for (int i = 0; i < requestLength; ++i) {
			str.append(seedStr.charAt((int)Math.random() *length));
		}
		
		return str.toString();
	}
}
