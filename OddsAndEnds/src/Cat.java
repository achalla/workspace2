
public class Cat {
	
	
	String name;
	int age;
	boolean fat;
	
	public Cat(){ //constructor1
		name = "Fluffy";
		age = 10;		
	}
	
	@SuppressWarnings("unused")
	private Cat(String inputName, int inputAge){ //constructor2
		name = inputName;
		age = inputAge;
	}
	
	public int birthday(){
		return ++age;
	}
	
	public static int add(int a, int b){
		return a+b;
	}
	
	
	
	public static void main(String[] args) {
		
		Cat c = new Cat();
		System.out.println(c.age);
		System.out.println(c.name);
		System.out.println(c.fat);

	}

}
