package dev.mrkevr.quizapp.api.service;

import java.util.Random;

public class AppUtil {

	public static String getRandomDocumentId() {
		Random random = new Random();
		String first = String.format("%04d", random.nextInt(10000));
		String last = String.format("%04d", random.nextInt(10000));
		return first + "-" + last;
	}

}
