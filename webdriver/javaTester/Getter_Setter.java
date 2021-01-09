package javaTester;

public class Getter_Setter {
	String carName;

	public static void main(String[] args) {
		Getter_Setter exp = new Getter_Setter();
		
		exp.setCarName("Mazda");
		System.out.println(exp.getCarName());

	}
	//Lấy ra dữ liệu của 1 biến: getter
	public String getCarName() {
		return this.carName;
	}
	
	//Gán dữ liệu mới vào 1 biến: setter
	public void setCarName(String car) {
		carName=car;
	}

}
