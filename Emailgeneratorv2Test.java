package repocheck;

import static org.junit.Assert.*;

import org.junit.Test;

public class Emailgeneratorv2Test {

	@Test
	public void test() {
		//checking the range of the emails is correct (set to 1-10 length)
		//@mailinator.com takes up 15
		String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		int length = 0;
		int[] alphabet = new int[26];
		String email = "";
		char[] chararray = new char[1];
		boolean done = false;
		int ptr = 0;
		Emailgeneratorv2 test = new Emailgeneratorv2();
		//this first checks to make sure the lengths of the email are in the correct range.
		for(int i = 0; i < 1000; i++){
			ptr = 0;
			done = false;
			email = test.emailgen();
			length = email.length() - 15;
			if(length > 10){
				fail("Length greater than default(10).");
			}
			if(length < 1){
				fail("Length shorter than default (0).");
			}
		//this next part I'm going to test to make sure all 26 letters are being used for name generation
			chararray = email.toCharArray();
			while(done == false && ptr < 26){
				if(chararray[0] == letters[ptr].charAt(0)){
					alphabet[ptr] = alphabet[ptr] + 1;
					done = true;
				}
				ptr++;
			}
			
		}
		for(int x = 0; x < 26; x++){
			if(alphabet[x] <= 0){
				fail("Did not generate all the letters.");	
			}
		}
		
		
		//fail("Not yet implemented");
	}

}
