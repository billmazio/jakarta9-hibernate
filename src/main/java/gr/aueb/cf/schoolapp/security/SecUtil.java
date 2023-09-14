package gr.aueb.cf.schoolapp.security;

import org.mindrot.jbcrypt.BCrypt;




public class SecUtil {

	private SecUtil() {}





	public static boolean checkPassword(String inputPasswd, String storedHashedPasswd) {
		// Debug: Print or log inputPasswd and storedHashedPasswd to verify their values
		System.out.println("Input Password: " + inputPasswd);
		System.out.println("Stored Hashed Password: " + storedHashedPasswd);

		// Compare the input password with the stored hashed password
		return BCrypt.checkpw(inputPasswd, storedHashedPasswd);
	}

	public static String hashPassword(String inputPasswd) {
		int workload = 12;
		String salt = BCrypt.gensalt(workload);
		return BCrypt.hashpw(inputPasswd, salt);
	}
}
