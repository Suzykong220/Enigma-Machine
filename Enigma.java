///////////////////////////////////////////////////////////////////////////////
// Title:            Enigma
// Files:            Enigma.java
// Semester:         Summer 2016
// Author:     	     Qingxu Kong
// Email:            qkong5@wisc.edu
//////////////////////////// 80 columns wide //////////////////////////////////

/** 
 * In this application, we ask the user if they want to quit the program,
 * encrypt a message, or decrypt a message. We validate user input by checking
 * if the option entered by the user is valid, rotor configurations are valid,
 * and their rotation entry is valid. Finally, we encrypt and decrypt messages
 * that can include special characters and digits.
 */

import java.util.Scanner;

public class Enigma {
	/** 
	 * Program execution begins here.
	 * @param args UNUSED
	 */

	/**
	 * Start users off with a welcome message, such as Willkommen auf der
	 * Enigma-Maschine (that's "Welcome to the Enigma Machine" in German.)
	 */

	private static final String WELCOME_MSG = "Willkommen auf der "
			+ "Enigma-Maschine!";

	/**
	 * Message printed before exit.
	 */
	private static final String EXIT_MESSAGE = "Auf Wiedersehen!";

	/**
	 * Prompt the user for the initial options.
	 */
	private static final String MENU_OPTIONS = "\nOPTIONS\n"
			+ "1 : encrypt a message\n" + "2 : decrypt a message\n"
			+ "0 : exit the Enigma Machine\n" + "\nENTER YOUR OPTION: ";

	/**
	 * Error message for invalid option.
	 */
	private static final String WRONG_OPTION = "Option is not valid. "
			+ "Try again.";

	/**
	 * Message about start of encryption.
	 */
	private static final String ENCRYPTION_MESSAGE = "\nENCRYPTION WITH "
			+ "ENIGMA!\n"
			+ "=======================";

	/**
	 * Message about start of decryption.
	 */
	private static final String DECRYPTION_MESSAGE = "\nDECRYPTION WITH "
			+ "ENIGMA!";

	/**
	 * Message to prompt the user for the number of rotations.
	 */
	private static final String NUMROTATIONS_MSG = "How many rotations you "
			+ "want to perform on the rotors?: ";

	/**
	 * Message about invalid input for the number of rotations.
	 */
	private static final String INVALID_NUMROTATIONS = "Invalid number of "
			+ "rotations. Try again.";

	/**
	 * Message to prompt the user to enter the list of rotors.
	 */
	private static final String ROTOR_PROMPT = "\nROTOR CONFIGURATION\n"
			+ "-------------------\n"
			+ "This must be a list of numbers in the range "
			+ "from 0 to 8, separated by spaces.\n"
			+ "Maximum number of rotors you can use is 3. "
			+ "Note that rotor 0 is the identity rotor.\n"
			+ "\nENTER YOUR ROTOR CONFIGURATION: ";

	/**
	 * Message that rotor configurations are going to be printed before
	 * rotations are completed.
	 */
	private static final String ROTOR_BEFORE_ROTATION = "\nYOUR ROTOR "
			+ "CONFIGURATION BEFORE ROTATION\n";

	/**
	 * Message that rotor configurations are going to be printed after
	 * completing rotation on rotors.
	 */
	private static final String ROTOR_AFTER_ROTATION = "\nYOUR ROTOR "
			+ "CONFIGURATION AFTER ROTATION\n";

	/**
	 * Message about invalid input for the rotor indices due to duplicate rotor
	 * entries.
	 */
	private static final String DUP_ROTORS_MSG = "You cannot use the same "
			+ "rotor twice. Try again.";

	/**
	 * Message about invalid input for the rotor indices if something other than
	 * an integer is entered.
	 */
	private static final String INVALID_ROTOR_MSG = "Invalid rotor "
			+ "configuration. Try again.";

	/**
	 * Message about invalid input for the rotor indices when more than 3 rotor
	 * indices are entered.
	 */
	private static final String MORE_THAN_ALLOWED = "You cannot use more than "
			+ "3 rotors. Try again.";

	/**
	 * Message about invalid input for the rotor indices since the input is
	 * empty.
	 */
	private static final String NO_ROTORS_MSG = "You must specify at least one "
			+ "rotor. Try again.";

	/**
	 * Prompt the user to enter the message to be encrypted
	 */
	private static final String USER_PROMPT1 = "Enter the line(s) of text to "
			+ "be encrypted: ";
	/**
	 * Prompt the user to enter the message to be decrypted
	 */
	private static final String USER_PROMPT2 = "Enter the line(s) of text to "
			+ "be decrypted: ";

	private static final Object[] ROTORS = null;

	/**
	 * The one and only scanner that we'll use to read input from the keyboard.
	 * This should be the only scanner used whenever we read user input from the
	 * keyboard. This scanner can be accessed in ALL the methods.
	 */
	public static Scanner in = new Scanner(System.in);

	/**
	 * From this main method execution will start. 
	 * It should contain the main loop for the usage of the
	 * Enigma machine and should call other methods in 
	 * this class. There are some comments added to the 
	 * body of this method that might help you to 
	 * complete the project. 
	 * 
	 * @param args unused
	 */

	public static void main(String[] args) {
		// boolean that runs while the encryption is playing and only stops
		// when user enters option 0 to quit
		boolean played = false;

		// print out welcome message
		System.out.println(WELCOME_MSG);

		// print out menu options
		System.out.print(MENU_OPTIONS);

		// takes in user input for options
		String options = in.nextLine();
		while(!played) {
			// while loop for getting the correct option from user
			while (validateOptions(options) == -1) {
				// prints out invalid messages and prompts user to enter
				// new input while validating that new input
				System.out.println(WRONG_OPTION);
				System.out.print(MENU_OPTIONS);
				options = in.nextLine();
				validateOptions(options);	
			}
			// call validateOptions method
			validateOptions(options);

			// if the user input is 0, exit the program and print exit message
			if (validateOptions(options) == 0) {
				System.out.println(EXIT_MESSAGE);
				played = true;
				break;

			}
			// if option is 1 (encrypt), go through encryption process
			else if (validateOptions(options) == 1) {
				// prints out encryption messagea and rotor prompt
				System.out.println(ENCRYPTION_MESSAGE);
				System.out.print(ROTOR_PROMPT);

				// prompt the user for the rotor indices
				String rotorIndicesLine = in.nextLine();

				// if null, re-prompt user for input
				while (parseRotorIndices(rotorIndicesLine) == null) {
					System.out.print(ROTOR_PROMPT);
					rotorIndicesLine = in.nextLine();
				}
				// setting up an array of the rotor indices entered by user
				int[] rotorIndices = parseRotorIndices(rotorIndicesLine);

				// call setUpRotors method
				int[][] rotorConfig = setUpRotors(rotorIndices);
				int[][] rotorSet = rotorConfig;

				// prints out the rotation before actually rotated
				System.out.println(ROTOR_BEFORE_ROTATION);
				// call on rotor configuration method
				displayRotorConfiguration(rotorConfig);
				// asks user how many times they want to rotate rotor 
				System.out.print(NUMROTATIONS_MSG);
				String numRotationsInput = in.next();
				int numOfRotations = 0;
				// set this variable to the method that validates num rotation
				numOfRotations = validateNumRotations(numRotationsInput);
				while (numOfRotations == -1) {
					// if it isn't valid, prints invalid message and re-prompts
					// user for input and checks if that is valid
					System.out.println(INVALID_NUMROTATIONS);
					System.out.print(NUMROTATIONS_MSG);
					numRotationsInput = in.next();
					numOfRotations = validateNumRotations(numRotationsInput);
				}
				//int[][] copyArray = new int[rotorConfig.length][26];
				rotorSet = rotateRotors(numOfRotations, rotorSet);

				// prints out the rotor after it is rotated
				System.out.println(ROTOR_AFTER_ROTATION);
				// calls on method that displays the rotor
				displayRotorConfiguration(rotorSet);
				// asks user what message they want to encrypt
				System.out.print(USER_PROMPT1);
				in.nextLine();
				// takes message input as a string
				String userMessage = in.nextLine();

				// calls on the encrypt method to actually encrypt the message
				encrypt(userMessage, rotorSet);
				// after encryption, re-prompts user to enter a new option
				System.out.print(MENU_OPTIONS);
				//in.nextLine();
				options = in.nextLine();
				validateOptions(options);	
			}
			// if option is 2, decryption process will happen
			else if (validateOptions(options) == 2) {
				// prints out the decryption message and rotor prompt
				System.out.println(DECRYPTION_MESSAGE);
				System.out.print(ROTOR_PROMPT);

				// prompt the user for the rotor indices
				String rotorIndicesLine = in.nextLine();

				// if null, re-prompt user for input
				while (parseRotorIndices(rotorIndicesLine) == null) {
					// re-prompts user for input
					System.out.print(ROTOR_PROMPT);
					rotorIndicesLine = in.nextLine();
				}			
				// setting up an array of the rotor indices entered by user
				int[] rotorIndices = parseRotorIndices(rotorIndicesLine);
				// call setUpRotor method 
				int[][] rotorConfig = setUpRotors(rotorIndices);

				// call displayRotorConfiguration method
				int[][] rotorSet = rotorConfig;

				// prints out the rotation before actually rotated
				System.out.println(ROTOR_BEFORE_ROTATION);
				displayRotorConfiguration(rotorConfig);
				// prompt for the number of rotations
				System.out.print(NUMROTATIONS_MSG);
				String numRotationsInput = in.next();
				int numOfRotations = 0;

				// call validateNumRotations method
				numOfRotations = validateNumRotations(numRotationsInput);
				// if input is invalid, it re-prompts user for input and
				// checks if that input is valid
				while (numOfRotations == -1) {
					System.out.println(INVALID_NUMROTATIONS);
					System.out.print(NUMROTATIONS_MSG);
					numRotationsInput = in.next();
					numOfRotations = validateNumRotations(numRotationsInput);
				}

				// call rotateRotors method
				rotorSet = rotateRotors(numOfRotations, rotorSet);

				// prints out rotor after rotation 
				System.out.println(ROTOR_AFTER_ROTATION);

				// call displayRotorConfiguration method to print out rotor
				displayRotorConfiguration(rotorSet);

				// prompt the user to input message to decrypt
				System.out.print(USER_PROMPT2);
				in.nextLine();
				String userMessage = in.nextLine();

				// display the decrypted message
				userMessage = decrypt(userMessage, rotorSet);
				// prints out encrypted message
				System.out.println("DECRYPTED MESSAGE: " + userMessage);
				// re-prompts user to enter a new option in menu
				System.out.print(MENU_OPTIONS);
				options = in.nextLine();
				validateOptions(options);	
			}
		}
	}

	/**
	 * This method takes as a parameter the String entered
	 * by the user for the option. It should check 
	 * whether the option entered by the user is an integer.
	 * If it is an integer, then it checks whether it is 0, 1 or 2. 
	 * If it is 0, 1 or 2, then return the corresponding number.
	 * If it is some other integer or string, 
	 * then return -1 to indicate an error.
	 * 
	 * For now it returns only -1. 
	 * 
	 * @param options (option entered by user)
	 * @return -1 for invalid input, otherwise 0, 1 or 2
	 */
	public static int validateOptions(String options){
		// if option as a string equals 0, it returns the string 0
		if (options.equals("0")) {
			return 0;
		}
		// if option as a string equals 1, it returns the string 1
		if (options.equals("1")) {
			return 1;
		}
		// if option as a string equals 2, it returns the string 2
		if (options.equals("2")) {
			return 2;
		}
		// if it is anything else, it returns -1
		else {
			return -1;
		}
	}
	/**
	 * This method accepts as a parameter the String entered 
	 * by the user for rotor indices. This method checks 
	 * several things:
	 * 1) whether the string contains only integers
	 * 2) whether there are no more than 3 rotor indices
	 * 3) whether there are no duplicate rotor indices
	 * 4) whether at least one rotor index is entered
	 * 5) whether the entered rotor indices are between 0 to 8
	 * 
	 * If the user input is correct, method creates a 1D array
	 * with integer indices in it and returns this 1D array.
	 * 
	 * For invalid input this method returns null.
	 * 
	 * For now it returns only null.
	 * 
	 * @param rotorIndicesLine (user input String for rotor indices)
	 * @return 1D array if user input is correct, null-if not
	 */

	public static int[] parseRotorIndices(String rotorIndicesLine) {
		// matches rotor indices with sequences of one or more whitespace chars
		rotorIndicesLine = rotorIndicesLine.replaceAll("\\s+", " ");
		// eliminates white space before and after string input
		rotorIndicesLine = rotorIndicesLine.trim();

		int num = rotorIndicesLine.length();

		// print the error message if user does not enter anything 
		if (rotorIndicesLine.isEmpty()) {
			System.out.println(NO_ROTORS_MSG);
			return null;
		}

		// if user just enter one character as a string 
		if (num == 1) {
			// checks ascii characters to see if input is between 0-8
			if ((int)rotorIndicesLine.charAt(0) >= 48 && 
					(int)rotorIndicesLine.charAt(0) <= 56) {
				int OneRotor = Integer.parseInt(rotorIndicesLine);
				// if it is, it puts input into an array and returns the array
				if (OneRotor <= 8 && OneRotor >= 0) {
					int[] rotorIndices = new int[1];
					rotorIndices[0] = OneRotor;
					return rotorIndices;
				}
				// if user enter a number that is more than 8 or less than 0
				else {
					System.out.println(INVALID_ROTOR_MSG);
					return null;
				}
			}
			// If user does not enter a number 
			else {
				System.out.println(INVALID_ROTOR_MSG);
				return null;
			}
		}
		// If user enters 2 characters (does not include space)
		else if (num == 3){
			// checks ascii characters to see if input is between 0-8
			if (((int)rotorIndicesLine.charAt(0) >= 48 
					&& (int)rotorIndicesLine.charAt(0) <= 56
					|| (int)rotorIndicesLine.charAt(2) >= 48 
					&& (int)rotorIndicesLine.charAt(2) <= 56)) {

				// checks the spaces in between each rotor and sets the first
				// rotor between index 0 and space to first rotor
				String firstRotor = rotorIndicesLine.substring
						(0,rotorIndicesLine.indexOf(' '));
				// sets second rotor between space and the length of the rotor
				// to get the second rotor
				String secondRotor = rotorIndicesLine.substring
						(rotorIndicesLine.indexOf(' ') + 1, 
								rotorIndicesLine.length());

				// parses both rotors to integers
				int firstNum = Integer.parseInt(firstRotor);
				int secondNum = Integer.parseInt(secondRotor);

				// if the number user enters is not in valid range 
				if (firstNum <= 8 && firstNum >= 0 && secondNum <= 8 
						&& secondNum >= 0) {

					// checks to see if the numbers equal to each other
					if (firstNum != secondNum) {
						int[] rotorIndices = new int[2];
						rotorIndices[0] = firstNum;
						rotorIndices[1] = secondNum;
						return rotorIndices;
					}
					// if user enters a duplicate number 
					else {
						System.out.println(DUP_ROTORS_MSG);
						return null;
					}
				}

				// If user enter a number greater than 8 or less than 0
				else {
					System.out.println(INVALID_ROTOR_MSG);
					return null;
				}
				// If user does not enter number 
			} else { 
				System.out.println(INVALID_ROTOR_MSG);
				return null;
			}
		}
		// checks when three rotors are entered
		else if (num == 5) {
			// check if user enters number between 0-8 using ascii
			if ((int)rotorIndicesLine.charAt(0) >= 48 
					&& (int)rotorIndicesLine.charAt(0) <= 56
					&& (int)rotorIndicesLine.charAt(2)>= 48 
					&& (int)rotorIndicesLine.charAt(2) <= 56
					&& (int)rotorIndicesLine.charAt(4)>= 48 
					&& (int)rotorIndicesLine.charAt(4) <= 56) {
				// three strings that are separated by checking the spaces
				// in between each entry and putting them into an array
				String firstNum = rotorIndicesLine.substring
						(0, rotorIndicesLine.indexOf(' '));
				String secondNum = rotorIndicesLine.substring
						(rotorIndicesLine.indexOf(' ') + 1, 
								rotorIndicesLine.lastIndexOf(' '));
				String thirdNum = rotorIndicesLine.substring
						(rotorIndicesLine.lastIndexOf(' ') + 1,
								rotorIndicesLine.length()); 
				// convert number character to integer 
				int firstRotor = Integer.parseInt(firstNum);
				int secondRotor = Integer.parseInt(secondNum);
				int thirdRotor = Integer.parseInt(thirdNum);
				// check if user enters the number that is in valid range 
				if (firstRotor <= 8 && firstRotor >= 0
						&& secondRotor <= 8 && secondRotor >= 0
						&& thirdRotor <= 8 && thirdRotor >= 0) {
					// check if the number has duplication 
					if (firstRotor != secondRotor && firstRotor != thirdRotor 
							&& secondRotor != thirdRotor) {
						int[] rotorIndices = new int[3];
						rotorIndices[0] = firstRotor;
						rotorIndices[1] = secondRotor;
						rotorIndices[2] = thirdRotor;
						return rotorIndices;
					}
					else {
						// if user enters same number twice - invalid message
						System.out.println(DUP_ROTORS_MSG);
						return null;
					}	
				} else {
					// prints invalid message if option is not valid
					System.out.println(INVALID_ROTOR_MSG);
					return null;
				}
			}
			// if user does not enter numbers, prints invalid rotor message
			else {
				System.out.println(INVALID_ROTOR_MSG); 
				return null;
			}
		}

		// if user enters more than 3 characters 
		else if (num > 5) {
			// array s that separates characters by spaces in between them
			// by using \\s+ to check for many spaces
			String[] s = rotorIndicesLine.split("\\s+");

			// boolean that determines what error is happening
			boolean invalid = false;

			// for each word in array
			for (int i = 0; i < s.length; i++) {

				// for each letter in word
				for (int j = 0; j < s[i].length(); j++) {

					// if it's a number, parse it and check if it's within
					// bounds
					if(((int)s[i].charAt(j) >= 47 && (int)s[i].charAt(j) <=
							56) || (int)s[i].charAt(j) == 32) {

						// parse and check number
						int number = Integer.parseInt(s[i]);

						// if a number is less than 0 or greater than 8
						// the boolean is true 
						if (number < 0 || number > 8) {
							invalid = true;
						} 
						// if anything isn't valid, boolean is true
					} else {
						invalid = true;
					}
				}
			}
			// prints error message according to boolean variable
			if (invalid) {
				System.out.println(INVALID_ROTOR_MSG);
			} else {
				System.out.println(MORE_THAN_ALLOWED);
			}
		}
		return null;
	}

	/**
	 * This method takes as a parameter a 1D array of integers that represent
	 * the rotor indices entered by the user. Using these indices this method
	 * builds a 2D array which is the integer representation of the rotors to be
	 * used. The rotor configurations should be taken from the RotorConstants
	 * class.
	 * 
	 * If the rotorIndices is [1, 3, 4], then this method builds the 2D array of
	 * rotors 1, 3, 4 as shown below:
	 * 
	 * [4 10 12 5 11 6 3 16 21 25 13 19 14 22 24 7 23 20 18 15 0 8 1 17 2 9] [1
	 * 3 5 7 9 11 2 15 17 19 23 21 25 13 24 4 8 22 6 0 10 12 20 18 16 14] [4 18
	 * 14 21 15 25 9 0 24 16 20 8 17 7 23 11 13 5 19 6 10 3 2 12 22 1]
	 * 
	 * For now it returns null.
	 * 
	 * @param rotorIndices
	 *            (1D array with rotor indices)
	 * @return 2D array with rotor configurations
	 */
	public static int[][] setUpRotors(int[] rotorIndices) {
		// int row reads in the length of rotor indices
		int row = rotorIndices.length;

		// create a 2D array holding all characters of the alphabet
		char[][] LetterResult = new char [row][26];

		// for loop that calls on ROTORS class and sets up the proper rotor
		// using two for loops for row and column
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < 26; j++) {
				// sets the 2D array to indices of the rotor class
				LetterResult[i][j] = RotorConstants.ROTORS
						[rotorIndices[i]].charAt(j);	
			}
		}
		// 2D array that sets up the rotor in proper format using
		// ascii characters
		int[][] rotorConfig = new int [row][26];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < 26; j++) {
				// determines each chars value by subtracting 65 (A)
				// which evaluates which letter is at a certain index
				rotorConfig[i][j] = (int)(LetterResult[i][j] - 65);
			}
		}
		return rotorConfig;
	}

	/**
	 * This method accepts as a parameter the String input from the user
	 * numRotationsInput and checks whether the number is in the range from 0 to
	 * 25. If it is, it returns the number of rotations, else it returns -1.
	 * 
	 * For now it returns -1.
	 * 
	 * @param numRotationsInput
	 *            (number of rotations input String)
	 * @return The number of rotations if input is valid, -1 otherwise.
	 */
	public static int validateNumRotations(String numRotationsInput) {
		int num = 0;
		// if the rotation is empty, returns -1
		if(numRotationsInput.isEmpty()) {
			return -1;
		}
		// if there aren't special characters, returns -1
		else if (!numRotationsInput.matches("^-?\\d+$")) {
			return -1;
		}
		else {
			// otherwise, it is valid and parses String into an int
			num = Integer.parseInt(numRotationsInput);
			// checks if input is between 0 and 25
			if (num >= 0 && num <= 25) {
				return num;
			}
			else {
				// if not between 0 and 26, returns -1
				return -1;
			}
		}
	}

	/**
	 * This method accepts as parameters the number of rotations to be done on
	 * the 2D array of integers named rotorSet that represents the rotor
	 * configurations. It should perform those many rotations on the 2D array of
	 * rotors.
	 * 
	 * For now it returns the original 2D array passed to it.
	 * 
	 * @param numOfRotations
	 *            (number of rotations to be done)
	 * @param rotorSet
	 *            (rotor configurations)
	 * @return rotated 2D array of rotor configurations
	 */
	private static int[][] rotateRotors(int numOfRotations, int[][] rotorSet) {
		// set up a 2D array that holds the length of rotor set as row
		// and 26 as column which is the length of the alphabet
		int[][] copyArray = new int[rotorSet.length][26];
		for (int i = 0; i < rotorSet.length; i++) {

			// loops through the alphabet
			for (int j = 0; j < 26; j++) {
				// in case the number of rotations exceed the length of rotorset
				int j2 = j + numOfRotations;
				if (j2 > 25) {
					j2 = j2 % 26;
				}
				// sets the copy array to the rotor set
				// so the rotor isn't changed but the temporary rotation is made
				copyArray[i][j2] = rotorSet[i][j];
			}
		}
		// returns the 2D array - copy Array which is the copy of rotorSet
		return copyArray;
	}

	/**
	 * This method prints out the 2D array of rotor configurations that is
	 * passed to it as a parameter.
	 * 
	 * @param rotorConfig
	 */
	public static void displayRotorConfiguration(int[][] rotorConfig) {
		System.out.println(
				"====================================================");
		System.out
		.println("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
		System.out.println(
				"====================================================");

		// Print rotor configurations here
		for (int i = 0; i < rotorConfig.length; i++) {
			for (int j = 0; j < 26; j++) {
				if (j == 25) {
					// prints out the rotor configuration if j is 25 chars
					System.out.println((char)('A' + rotorConfig[i][j]));
				}
				else {
					// otherwise, it prints a space after each index
					System.out.print((char)('A' + rotorConfig[i][j]) + " ");
				}	
			}
		}	
		System.out.println(
				"----------------------------------------------------\n");
	}

	/**
	 * This method takes as parameters a String message to be encrypted and a 2D
	 * array of integers that represent the rotor configuration. It does the
	 * encryption of the message using rotor configurations and should return
	 * the encrypted message.
	 * 
	 * For now it returns the original message.
	 * 
	 * @param message
	 *            (message to be encrypted)
	 * @param rotorSet
	 *            (rotor configurations)
	 * @return encrypted message
	 */
	public static String encrypt(String message, int[][] rotorSet) {			
		// convert message to upper case
		message = message.toUpperCase();

		// convert to character array
		char[] charArray = message.toCharArray();  

		// create new int[] to store character positions
		int[] charPositions = new int[charArray.length];    

		// convert to positions
		for (int i = 0; i < charArray.length; i++) {
			charPositions[i] = charArray[i] - 65; 
		}
		// reads through the length of rotor set
		for (int i = 0; i < rotorSet.length; i++) {

			// reads through the positions and checks if each index is within
			// proper format using ascii - ranging from A to Z in alphabet
			for (int j = 0; j < charPositions.length; j++) {
				if (charPositions[j] + 65 >= 'A' && 
						charPositions[j] + 65 <= 'Z'){
					charPositions[j] = rotorSet[i][charPositions[j]];
				}
				else {
					// if the position isn't a char from A-Z, sets the
					// char position to index j
					charPositions[j] = charPositions[j];
				}		
			}
		}
		// rebuild string
		for (int i = 0; i < charPositions.length; i++) {
			// converts the position of the index to the proper letter
			// using ascii
			if (charPositions[i] + 65 >= 'A' && charPositions[i] + 65 <= 'Z'){
				charArray[i] = RotorConstants.ROTORS[0].charAt(charPositions[i]);
			}
			else {
				// if none of these letters, sets char array to index i
				charArray[i] = charArray[i];
			}
		}
		// convert character array back to string and return
		String send = new String(charArray);
		System.out.println("ENCRYPTED MESSAGE: " + send);
		return charArray.toString();
	}

	/**
	 * This method takes as parameters a String message to be decrypted and a 2D
	 * array of integers that represent the rotor configuration. It completes
	 * the decryption process of this message using the rotor configuration.
	 * This method should return the decrypted message as a String.
	 * 
	 * For now it returns the original message.
	 * 
	 * @param message
	 *            (message to be decrypted)
	 * @param rotorSet
	 *            (rotor configurations)
	 * @return decrypted message
	 */

	public static String decrypt(String message, int[][] rotorSet) {
		// convert the message to upper case
		message = message.toUpperCase();
		// set each character of the message to an array
		char[] messageArray = message.toCharArray();

		for (int k = 0; k < messageArray.length; k++) {
			// checks to see if the character is a letter
			if (Character.isLetter(messageArray[k])){	
				// reads through the length of the rotor set
				for (int row = 0; row < rotorSet.length; row++) {
					// reads through the rotorSet row to check each column
					for (int col = 0; col < rotorSet[row].length; col++) {
						// if the index of the message array is equal
						// to the char array's row and column, we add 65 to
						// that character to determine what letter it is
						// with ascii
						if (messageArray[k] == (char)(rotorSet[row][col] 
								+ 65)) {
							int letter = col + 65; 
							messageArray[k] = (char)letter;
							break;
						}
					}	
				}
			}
		}
		// returns the message as a string
		message = String.valueOf(messageArray);
		return message;
	}
}
