package test1;

public class Student implements Cloneable{
private String name;
private int age;
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
     public String toString() {
         return "Student [name=" + name + ", age=" + age + "";
     }

@Override
	protected Object clone() throws CloneNotSupportedException {
	Student s2=(Student)super.clone();
		return s2;
	}

public static void main(String[] args) throws CloneNotSupportedException {
	Student s1=new Student();
	s1.setAge(1);
	s1.setName("wk");
	Student s2=(Student) s1.clone();
	s2.setName("na");
	System.out.println(s1.name);
	System.out.println(s2.name);
	System.out.println(s1.name==s2.name);
}
}
