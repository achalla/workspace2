
public class Dog {

	String name;
	int age;
	
	public Dog(){
		name = "Fido";
		age = 7;
	}
	
	public static void main(String[] args) {

		Dog d = new Dog();
		Cat c = new Cat();
		
		System.out.println(d.age);
		System.out.println(d.name+'\n');
		
		System.out.println(c.age);
		System.out.println(c.name+'\n');
		
		System.out.println(Cat.add(1,2));

	}

}
