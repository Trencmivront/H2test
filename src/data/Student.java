package data;

public record Student(int id, String name, int age) {
	
	@Override
	public String toString() {
		return id + "\t" + name + "\t" + age;
	}

}
