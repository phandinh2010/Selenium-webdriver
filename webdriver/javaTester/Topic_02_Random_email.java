package javaTester;

import java.util.Random;

public class Topic_02_Random_email {
	public static void main(String[] args){
		Random rand = new Random();
		
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));
		System.out.println(rand.nextInt(999999));

		System.out.println("autotesting" + rand.nextInt(999999) + "@hotmail.com");
		System.out.println("autotesting" + rand.nextInt(999999) + "@gmail.com");
		System.out.println("autotesting" + rand.nextInt(999999) + "@live.com");
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return "testing" + rand.nextInt(9999) + "@gmail.com";
	}

}
