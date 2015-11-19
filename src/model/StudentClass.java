package model;

import exception.StudentClassException;

public class StudentClass extends Model{
	
	private static final String STUDENT_CANT_BE_NULL = "O estudante de uma turma não pode ser nulo.";
	private static final String CLASS_CANT_BE_NULL = "A turma não pode ser nula.";
	private static final String INVALID_SITUATION = "A situação do aluno na turma só pode ser 'Aprovado' ou 'Reprovado'.";
	
	private static final String APPROVED_SITUATION = "Aprovado";
	private static final String DISAPPROVED_SITUATION = "Reprovado";
	
	private Student student;
	private Class enrolledClass;
	private Integer absences;
	private Integer grade;
	private String studentSituation;
	
	// To enroll students in class
	public StudentClass(Student student, Class enrolledClass) throws StudentClassException{
		setStudent(student);
		setEnrolledClass(enrolledClass);
	}
	
	// To close the class
	public StudentClass(Student student, Class enrolledClass, Integer absences, Integer grade) throws StudentClassException{
		setStudent(student);
		setEnrolledClass(enrolledClass);
		setAbsences(absences);
		setGrade(grade);
		assessSituation();
	}
	
	// To set data of the database
	public StudentClass(Student student, Class enrolledClass, Integer absences, Integer grade, String situation) throws StudentClassException{
		setStudent(student);
		setEnrolledClass(enrolledClass);
		setAbsences(absences);
		setGrade(grade);
		setSituation(situation);
	}

	private void assessSituation(){
		
	}

	private void setSituation(String situation) throws StudentClassException{
		
		switch(situation){
		
			case APPROVED_SITUATION: 
				this.studentSituation = situation;
				break;
				
			case DISAPPROVED_SITUATION: 
				this.studentSituation = situation;
				break;
				
			default:
				throw new StudentClassException(INVALID_SITUATION);
		}
	}
	
	private void setStudent(Student student) throws StudentClassException{
		
		if(student != null){
			this.student = student;
		}
		else{
			throw new StudentClassException(STUDENT_CANT_BE_NULL);
		}
	}
	
	private void setEnrolledClass(Class enrolledClass) throws StudentClassException{
		
		if(enrolledClass != null){
			this.enrolledClass = enrolledClass;
		}
		else{
			throw new StudentClassException(CLASS_CANT_BE_NULL);
		}
	}
	
	public void setAbsences(Integer absences){
		
		this.absences = absences;
	}
	
	public void setGrade(Integer grade){
		this.grade = grade;
	}	
	
	public Student getStudent(){
		return this.student;
	}
	
	public Class getEnrolledClass(){
		return this.enrolledClass;
	}
	
	public Integer getAbsences(){
		return this.absences;
	}
	
	public Integer getGrade(){
		return this.grade;
	}
}
