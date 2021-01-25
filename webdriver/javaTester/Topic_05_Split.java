package javaTester;

public class Topic_05_Split {
	public static void main(String[] args) {
		
		String likes = "1,938 like";
		String likeNumber = likes.split(" ")[0].replace(",", "");
		
		System.out.println(likeNumber);
		
		int likeNumbers = Integer.parseInt(likeNumber);
		System.out.println(likeNumbers);
	}

}
