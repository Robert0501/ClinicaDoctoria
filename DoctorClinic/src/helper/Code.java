package helper;

import java.util.Random;

public class Code {
	public static int counter(int number) {
		int count = 0;
		while (number != 0) {
			number /= 10;
			count++;
		}

		return count;
	}

	public static String generateActivationCode() {
		int code = 0;

		Random rand = new Random();

		while (counter(code) != 6) {
			code = rand.nextInt();
		}

		if (code < 0) {
			code = -code;
		}

		return String.valueOf(code);
	}

}
