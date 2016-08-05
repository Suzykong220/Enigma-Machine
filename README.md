# Enigma-Machine
This program implements a simplified version of an encrypting and decrypting machine called Enigma.

Enigma Machine was invented by German engineer Arthur Scherbius and was used during World War II. This machine accepts the message that needs to be encrypted, uses some predefined rotors to encrypt the message and outputs the result. Also, it can accept encrypted messages and decrypt them. In this project I will implement both of these functionalities.

Rotors at positions 1 - 8 of the array ROTORS are strings with the letters of the alphabet appearing in various order. For example, the rotors 0 to 3 are shown below.

Rotor 0 - ABCDEFGHIJKLMNOPQRSTUVWXYZ
Rotor 1 - EKMFLGDQVZNTOWYHXUSPAIBRCJ
Rotor 2 - AJDKSIRUXBLHWTMCQGZNPYFVOE
Rotor 3 - BDFHJLCPRTXVZNYEIWGAKMUSQO

Let’s consider this example:
Message to be encrypted: JAVA
Rotor to use: 1
Number of rotations: 0
Encrypted message: ZEIE
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z  -> rotor 0
E K M F L G D Q V Z N T O W Y H X U S P A I B R C J  -> rotor 1
The position of J in  the original alphabet is 9. Therefore, Enigma finds the letter at position 9 on the rotor 1 which is Z. The position of A in the original alphabet is 0. Therefore, Enigma finds the letter at position 0 on the rotor 1 which is E, etc. This is how the message “JAVA” has been encrypted to “ZEIE” using the rotor 1 (without performing any rotations).

Message to be encrypted: JAVA
Rotors to use: 1 2 4
Number of rotations: 0
Encrypted message: PTMT


The rotors 1, 2, and 4 that are used for encryption are shown below:
====================================================
ROTORS 1, 2, 4
====================================================
E K M F L G D Q V Z N T O W Y H X U S P A I B R C J -> rotor 1
A J D K S I R U X B L H W T M C Q G Z N P Y F V O E -> rotor 2
E S O V P Z J A Y Q U I R H X L N F T G K D C M W B -> rotor 4
----------------------------------------------------

Round 1:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z  -> rotor 0
E K M F L G D Q V Z N T O W Y H X U S P A I B R C J  -> rotor 1 
(result is ZEIE after using rotor 1 for encryption)

Round 2:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z  -> rotor 0
A J D K S I R U X B L H W T M C Q G Z N P Y F V O E  -> rotor 2
(result is ESXS after using rotor 2 for encryption)

Round 3:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z  -> rotor 0
E S O V P Z J A Y Q U I R H X L N F T G K D C M W B  -> rotor 4
(result is PTMT after using rotor 4 for encryption)

Encryption using several rotors is done in the same way as encryption using one rotor, however there will be several rounds of encryption taking place. Once you get the message encrypted by rotor #1 which is ZEIE in our case, you pass it to the next rotor, rotor #2. Encrypted message of the rotor #2 is passed to the next rotor #4. Thus, the last encrypted message that comes out of rotor #4 is our final output PTMT.

Decryption in Enigma
In order to decrypt the message PTMT back to JAVA, the order of the rotors to be used should be reversed. Since we used rotors 1,  2 and 4 (in this same order) to encrypt our message JAVA, in order to decrypt it we should use the same rotors in the reverse order. i.e. 4, 2 and then 1. 

Message to be decrypted: PTMT
Rotors to use: 4 2 1
Number of rotations: 0
Decrypted message: JAVA

Rotation of the rotors:
While performing encryption or decryption of the message, we enter some number from 0 to 25 as a number of rotations. Number of rotations is simply the number of how many times you want to move the rotor values to the right (each row). 
