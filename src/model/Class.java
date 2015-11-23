package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import datatype.Date;
import exception.ClassException;
import exception.DateException;

public class Class extends Model {

	public static final String MORNING_SHIFT = "MA";
	public static final String AFTERNOON_SHIFT = "VE";
	public static final String NIGHT_SHIFT = "NO";

	private static final String INVALID_SHIFT = "O turno informado é inválido.";
	private static final String START_DATE_CANT_BE_NULL = "A data de início da turma é obrigatória.";
	private static final String END_DATE_CANT_BE_NULL = "Ocorreu um erro ao calcular a data de término da turma. Data não pode ser nula.";
	private static final String CLASS_MUST_HAVE_A_TEACHER = "Um professor deve ser associado à turma.";
	private static final String CLASS_MUST_BELONGS_TO_A_COURSE = "A turma deve estar associada a um curso.";
	private static final String COULDNT_GENERATE_END_DATE = "Não foi possível gerar a data final da turma.";
	private static final String INVALID_STATUS = "Status da turma inválido";

	// Number of days in a week
	private static final Integer DAYS_IN_WEEK = 7;

	public static final int OPEN_CLASS = 1;
	public static final int CLOSED_CLASS = 0;

	private String classId;
	private Date startDate;
	private Date endDate;
	private String shift;
	private Teacher teacher;
	private Course course;
	private ArrayList<Student> students = new ArrayList<Student>();
	private Integer status;

	// For new classes
	public Class(Date startDate, String shift, Teacher teacher, Course course)
			throws ClassException {

		setStartDate(startDate);
		setShift(shift);
		setTeacher(teacher);
		setCourse(course);
		generateEndDate();
		generateClassID();
	}

	// For updating classes
	public Class(String classId, Date startDate, String shift, Teacher teacher)
			throws ClassException {

		setClassId(classId);
		setStartDate(startDate);
		setShift(shift);
		setTeacher(teacher);
		generateEndDate();
	}

	// For search classes
	public Class(String classId, Date startDate, Date endDate, String shift,
			Teacher teacher, Course course) throws ClassException {

		setClassId(classId);
		setStartDate(startDate);
		setEndDate(endDate);
		setShift(shift);
		setTeacher(teacher);
		setCourse(course);
	}

	// For get a class
	public Class(String classId, Date startDate, Date endDate, String shift,
			Teacher teacher, Course course, Integer status)
			throws ClassException {

		setClassId(classId);
		setStartDate(startDate);
		setEndDate(endDate);
		setShift(shift);
		setTeacher(teacher);
		setCourse(course);
		setStatus(status);
	}

	public Class(String classId) {
		setClassId(classId);
	}

	private void generateClassID() {

		String classId = "";

		String courseFirstName = getCourseFirstName();

		Integer startDay = getStartDate().getDay();
		Integer startMonth = getStartDate().getMonth();
		Integer wholeYear = getStartDate().getYear();

		// Only two last digits
		String twoDigitsOfyear = wholeYear.toString().substring(2, 4);
		Integer startYear = new Integer(twoDigitsOfyear);

		classId += courseFirstName + " - " + getShift() + " " + startDay + "/"
				+ startMonth + "/" + startYear;

		classId = classId.toUpperCase();

		setClassId(classId);
	}

	private String getCourseFirstName() {

		String courseName = getCourse().getName();

		String courseFirstName = "";

		for (int i = 0; i < courseName.length(); i++) {

			char currentChar = courseName.charAt(i);

			if (currentChar != ' ') {
				courseFirstName += currentChar;
			} else {
				break;
			}
		}

		return courseFirstName;
	}

	private void setClassId(String classId) {
		this.classId = classId;
	}

	private void generateEndDate() throws ClassException {

		// Setting the date to the java.util.Date format
		Integer year = getStartDate().getYear() - 1900;
		Integer month = getStartDate().getMonth() - 1;
		Integer day = getStartDate().getDay();
		java.util.Date date = new java.util.Date(year, month, day);

		// Get the course duration
		Integer courseDurationInDays = getClassDuration();

		// Adding to the start date the course duration
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)
				+ courseDurationInDays);

		// Get the formatted end date
		String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(c
				.getTime());

		Date endDate;
		try {
			endDate = new Date(formattedDate);
			setEndDate(endDate);
		} catch (DateException | ClassException e) {
			throw new ClassException(COULDNT_GENERATE_END_DATE);
		}
	}

	public Integer getClassDuration() {
		Integer courseDurationInWeeks = getCourse().getDuration();
		int courseDurationInDays = courseDurationInWeeks * (DAYS_IN_WEEK);

		return courseDurationInDays;
	}

	private void setEndDate(Date endDate) throws ClassException {

		if (endDate != null) {
			this.endDate = endDate;
		} else {
			throw new ClassException(END_DATE_CANT_BE_NULL);
		}
	}

	private void setTeacher(Teacher teacher) throws ClassException {

		if (teacher != null) {
			this.teacher = teacher;
		} else {
			throw new ClassException(CLASS_MUST_HAVE_A_TEACHER);
		}
	}

	private void setCourse(Course course) throws ClassException {

		if (course != null) {
			this.course = course;
		} else {
			throw new ClassException(CLASS_MUST_BELONGS_TO_A_COURSE);
		}
	}

	private void setShift(String shift) throws ClassException {

		if (containsOnlyLetters(shift)) {

			switch (shift) {

			case MORNING_SHIFT:
				this.shift = shift;
				break;

			case AFTERNOON_SHIFT:
				this.shift = shift;
				break;

			case NIGHT_SHIFT:
				this.shift = shift;
				break;

			default:
				throw new ClassException(INVALID_SHIFT);
			}
		} else {
			throw new ClassException(INVALID_SHIFT);
		}
	}

	private void setStartDate(Date startDate) throws ClassException {

		if (startDate != null) {
			this.startDate = startDate;
		} else {
			throw new ClassException(START_DATE_CANT_BE_NULL);
		}
	}

	private void setStatus(Integer status) throws ClassException {

		if (status == OPEN_CLASS || status == CLOSED_CLASS) {
			this.status = status;
		} else {
			throw new ClassException(INVALID_STATUS);
		}
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public String getShift() {
		return this.shift;
	}

	public String getClassId() {
		return this.classId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public Course getCourse() {
		return this.course;
	}

	public ArrayList<Student> getStudents() {
		return this.students;
	}

	public Integer getStatus() {
		return this.status;
	}
}
