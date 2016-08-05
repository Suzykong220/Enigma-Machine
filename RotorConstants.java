/**
 * Predefined rotors for Enigma encryption and decryption.
 */
public class RotorConstants {   

	/** The number of letters printed on each rotor. */
	public static final int ROTOR_LENGTH = 26;

	/**
	 * The cipher strings for each rotor. They store the letter-order
	 * for each rotor. The rotor at index 0 is the identity rotor.
	 * The identity rotor will be useful for debugging.
	 *
	 * The remaining cipher strings correspond to:
	 * Rotor 1, Rotor 2, etc.
	 */
	public static String[] ROTORS = {

		// Identity Rotor (index 0 - and useful for testing):
		"ABCDEFGHIJKLMNOPQRSTUVWXYZ",

		// Standard Rotors 1 through 8:
		"EKMFLGDQVZNTOWYHXUSPAIBRCJ",
		"AJDKSIRUXBLHWTMCQGZNPYFVOE",
		"BDFHJLCPRTXVZNYEIWGAKMUSQO",
		"ESOVPZJAYQUIRHXLNFTGKDCMWB",
		"VZBRGITYUPSDNHLXAWMJQOFECK",
		"JPGVOUMFYQBENHZRDKASXLICTW",
		"NZJHGRCXMYSWBOUFAIVLPEKQDT",
		"FKQHTLXOCBJSPDZRAMEWNIUYGV",
	};   
}