package model;

import exception.StudentClassException;

public class StudentClass extends Model{
	
	private static final String STUDENT_CANT_BE_NULL = "O estudante de uma turma não pode ser nulo.";
	private static final String CLASS_CANT_BE_NULL = "A turma não pode ser nula.";
	private static final String INVALID_SITUATION = "A situação do aluno na turma só pode ser 'Aprovado' ou 'Reprovado'.";
	
	private static final String APPROVED_SITUATION = "APROVADO";
	private static final String DISAPPROVED_SITUATION = "REPROVADO";
	
	// The minimum grade to approved student
	private static final Integer MINIMUM_GRADE = 5;
	
	// The maximum percent of permitted absences
	private static final Double MAXIMUM_PERCENT_ABSENCES = new Double(25);
	
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

	private void assessSituation() throws StudentClassException{

		Double percentOfAbsences = null;
	
		String grade = getGrade().toString();
		
		int lastDigit = grade.length();
		char decimalPart = grade.charAt(lastDigit - 1);
		
		grade = grade.substring(0, (lastDigit - 1));
		grade += "." + decimalPart;
		
		Double doubleGrade = new Double(grade);
		
		// Checking if the grade is greater than the minimum grade
		if(doubleGrade >= MINIMUM_GRADE){
			
			// Checking if the student has less than 25% of absences
			percentOfAbsences = calculatePercentOfAbsence();
			if(percentOfAbsences <= MAXIMUM_PERCENT_ABSENCES){
				setSituation(APPROVED_SITUATION);

			}
			else{
				setSituation(DISAPPROVED_SITUATION);
			}
		}
		else{
			setSituation(DISAPPROVED_SITUATION);
		}
		
		
	}

	private Double calculatePercentOfAbsence() {
		
		Integer duration = getEnrolledClass().getClassDuration();

		Integer absences = getAbsences();
		
		Double percentOfAbsences = (double) ((absences * 100)/duration);
		
		return percentOfAbsences;		
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

	public String getStudentSituation() {
		return this.studentSituation;
	}
}
