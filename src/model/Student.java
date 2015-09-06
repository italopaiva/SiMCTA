package model;

public class Student {
	
	private String name;
	
	public Student(String name){
		this.setName(name);
	}
	
	private void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
