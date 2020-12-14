package javaTester;

import java.util.List;

public class Topic_01_dataType {
	
	public static void main(String[] args) {
		//Kiểu dữ liệu: data type
		//1- Kiểu dữ liệu nguyên thủy
		   
		   //kiểu kí tự: đại diện cho 1 kí tự
		   //chard  , vd: 
		char key = 'A';
		  
		
		//số nguyên không có phần thập phân
		   //byte
		byte numberByte = 127;
		   //short
		short numberShort = 6300;
		    //int
		int numberInt = 65000;
		   //long
		long numberLong = 2345456;
		
		//số thực: có phần thập phân
		     //fload
		float nubmerFload = 14.44F;
		     //double
		double numberDouble = 44.33D;
		
		//Boolean giá trị true/false
		boolean status = true;
		
		//2- Kiễu dữ liệu tham chiếu (reference)s
		  //String/Array/Class/Object/collection
		String address = "!@#%abc111"; //chứa chữ+số+ kí tự đặc biệt
		String addreses[] = {"ho chi minh", "ha noi", "nam dinh"};
		int number[] = {12, 4, 5, 6};
		//Topic_01_Data_type topic01 = new Topic_01_Data_Type();
		
		List<String> address = new ArrayList<String>(); //collection: chứa trung
				address.add("ha noi");
		address.add("Ho Chi Minh");
		address.add("ha noi");
		address.add("ha noi");
		
		Set<String> add = new HashSet<String>(); //collection: không chứa trùng
		address.add("ha noi");
		address.add("Ho Chi Minh");
		address.add("ha noi");
		address.add("ha noi");
	}
}
