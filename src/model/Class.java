package model;

import java.util.ArrayList;

import exception.ClassException;
import model.datatype.Date;

public class Class extends Model{
	
	public static final String MORNING_SHIFT = "MA";
	public static final String AFTERNOON_SHIFT = "VE";
	public static final String NIGHT_SHIFT = "NO";
	
	private static final String INVALID_SHIFT = "O turno informado é inválido.";
	private static final String START_DATE_CANT_BE_NULL = "A data de início da turma é obrigatória.";
	private static final String END_DATE_CANT_BE_NULL = "Ocorreu um erro ao calcular a data de término da turma. Data não pode ser nula.";
	private static final String CLASS_MUST_HAVE_A_TEACHER = "Um professor deve ser associado à turma.";
	private static final String CLASS_MUST_BELONGS_TO_A_COURSE = "A turma deve estar associada a um curso.";
	
	private String classId;
	private Date startDate;
	private Date endDate;
	private String shift;
	private Teacher teacher;
	private Course course;
	private ArrayList<Student> students = new ArrayList<Student>();
		
	// For new classes
	public Class(Date startDate, String shift, Teacher teacher, Course course) throws ClassException {
		
		setStartDate(startDate);
		setShift(shift);
		setTeacher(teacher);
		setCourse(course);
		generateEndDate();
		generateClassID();
	}
	
	// For updating classes
	public Class(String classId, Date startDate, String shift, Teacher teacher) throws ClassException {
		
		setClassId(classId);
		setStartDate(startDate);
		setShift(shift);
		setTeacher(teacher);
		generateEndDate();
	}
	
	private void generateClassID(){
		
		String classId = "";
		
		String courseFirstName = getCourseFirstName();
		
		Integer startDay = getStartDate().getDay();
		Integer startMonth = getStartDate().getMonth();
		
		classId += courseFirstName + " - " + getShift() + " " + startDay + "/" + startMonth;
		
		classId = classId.toUpperCase();
		
		setClassId(classId);
	}
	
	private String getCourseFirstName(){
		
		String courseName = getCourse().getName();
		
		String courseFirstName = "";
		
		for(int i = 0; i < courseName.length(); i++){
			
			char currentChar = courseName.charAt(i);
			
			if(currentChar != ' '){
				courseFirstName += currentChar;
			}
			else{
				break;
			}
		}
		
		return courseFirstName;
	}
	
	private void setClassId(String classId){
		this.classId = classId;
	}
	
	private void generateEndDate(){
			
	}
	
	private void setEndDate(Date endDate) throws ClassException{
		
		if(endDate != null){
			this.endDate = endDate;
		}
		else{
			throw new ClassException(END_DATE_CANT_BE_NULL);
		}
	}

	private void setTeacher(Teacher teacher) throws ClassException{
		
		if(teacher != null){
			this.teacher = teacher;
		}
		else{
			throw new ClassException(CLASS_MUST_HAVE_A_TEACHER);
		}
	}

	private void setCourse(Course course) throws ClassException{
		
		if(course != null){
			this.course = course;
		}
		else{
			throw new ClassException(CLASS_MUST_BELONGS_TO_A_COURSE);
		}
	}

	private void setShift(String shift) throws ClassException{
		
		if(containsOnlyLetters(shift)){
			
			switch(shift){
				
				case "MA":
					this.shift = shift;
					break;
					
				case "VE":
					this.shift = shift;
					break;
					
				case "NO":
					this.shift = shift;
					break;
					
				default:
					throw new ClassException(INVALID_SHIFT);
			}
		}
		else{
			throw new ClassException(INVALID_SHIFT);
		}
	}
	
	private void setStartDate(Date startDate) throws ClassException{
		
		if(startDate != null){
			this.startDate = startDate;
		}
		else{
			throw new ClassException(START_DATE_CANT_BE_NULL);
		}
	}
	
	public Date getStartDate(){
		return this.startDate;
	}
	
	public String getShift(){
		return this.shift;
	}

	public String getClassId(){
		return this.classId;
	}

	public Date getEndDate(){
		return this.endDate;
	}

	public Teacher getTeacher(){
		return this.teacher;
	}

	public Course getCourse(){
		return this.course;
	}

	public ArrayList<Student> getStudents(){
		return this.students;
	}
}
