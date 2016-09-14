import java.util.Random;

public class Emailgenerator {
	public static void main(String[] args){
		System.out.println(emailgen());
	}
	public static String emailgen(){
		Random randgen = new Random();
		String numconv = Integer.toString(randgen.nextInt(999999));
		String email = numconv + "@mailinator.com";
		return email;
		
	}
	

}
