package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;
import model.Student;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Phone;
import model.datatype.RG;
import model.datatype.Date;

public class StudentDAO extends DAO {

	private static final String TABLE_NAME = "Student";
	private static final String NAME_COLUMN = "name_student";
	private static final String CPF_COLUMN = "cpf";
	private static final String	BIRTHDATE_COLUMN = "birthdate";
	private static final String EMAIL_COLUMN	= "email";
	private static final String MOTHER_COLUMN	= "mother";
	private static final String FATHER_COLUMN	= "father";
	private static final String UF_COLUMN	= "uf";
	private static final String ISSUING_INSTITUTION_COLUMN	= "issuing_institution";
	private static final String RG_NUMBER_COLUMN	= "rg_number";
	private static final String SECONDARY_PHONE_COLUMN	= "secondary_phone";
	private static final String PRINCIPAL_PHONE_COLUMN	= "principal_phone";
	private static final String NUMBER_COLUMN	= "number";
	private static final String COMPLEMENT_COLUMN	= "complement";
	private static final String CITY_COLUMN	= "city";
	private static final String CEP_COLUMN	= "cep";
	private static final String ADDRESS_COLUMN	= "address_info";
	
	/**
	 * Gets the students with the searched name
	 * @param searchedStudentName - searched name by the user
	 * @return an arraylist with the found students
	 * @throws StudentException
	 * @throws CPFException
	 */
	public ArrayList<Student> get(String searchedStudentName) throws StudentException, CPFException {
		
		ResultSet resultOfTheSearch = null;
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COLUMN + " LIKE \"%" + searchedStudentName + "%\""; 
		ArrayList<Student> foundStudents = new ArrayList<Student>();
		Student student = null;
		CPF studentCpf = null;
		
		try{
			resultOfTheSearch = this.search(query);
			
			while(resultOfTheSearch.next()){
				String studentName = (resultOfTheSearch.getString("name_student"));
				String cpf = (resultOfTheSearch.getString("cpf"));
				studentCpf = new CPF(cpf);
				student = new Student(studentName, studentCpf);
				foundStudents.add(student);
			}
			
		}
		catch(SQLException e){
			
		}

		return foundStudents;
	}
	
	/**
	 * Gets the student that has the specific 'cpf'
	 * @param studentCpf - the 'cpf' of the searched student 
	 * @return an object with the data of the student 
	 * @throws PhoneException
	 * @throws CPFException
	 * @throws DateException
	 * @throws AddressException
	 * @throws RGException
	 * @throws StudentException
	 */
	public Student get(CPF studentCpf) throws PhoneException, CPFException, DateException, AddressException,
												RGException, StudentException {
		
		ResultSet resultOfTheSearch = null;
		String receivedCPF = studentCpf.getCpf();
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + CPF_COLUMN + "= \"" + receivedCPF + "\""; 
		Student student = null;

		try{
			resultOfTheSearch = this.search(query);
			while(resultOfTheSearch.next()){
				student = getFoundStudent(resultOfTheSearch);
			}
		}
		catch(SQLException e){
			
		}
		return student;
	}

	/**
	 * Gets the data of the student
	 * @param resultOfTheSearch the row from database that contains the data of the student
	 * @return an object with the data of the student
	 * @throws PhoneException
	 * @throws SQLException
	 * @throws CPFException
	 * @throws DateException
	 * @throws AddressException
	 * @throws RGException
	 * @throws StudentException
	 */
	private Student getFoundStudent(ResultSet resultOfTheSearch) throws PhoneException, SQLException, 
	CPFException, DateException, AddressException, RGException, StudentException {

		// Get the data from database
		String studentName = resultOfTheSearch.getString(NAME_COLUMN);

		String email = resultOfTheSearch.getString(EMAIL_COLUMN);
		String motherName = resultOfTheSearch.getString(MOTHER_COLUMN);
		String fatherName = resultOfTheSearch.getString(FATHER_COLUMN);

		//CPF
		String cpf = resultOfTheSearch.getString(CPF_COLUMN);
		CPF studentCpf = new CPF(cpf);

		//RG
		String rg = resultOfTheSearch.getString(RG_NUMBER_COLUMN);
		String uf = resultOfTheSearch.getString(UF_COLUMN);
		String issuing_institution = resultOfTheSearch.getString(ISSUING_INSTITUTION_COLUMN);
		RG studentRg = new RG(rg,issuing_institution,uf);

		//Address
		String city = resultOfTheSearch.getString(CITY_COLUMN);
		String addressInfo = resultOfTheSearch.getString(ADDRESS_COLUMN);
		String complement = resultOfTheSearch.getString(COMPLEMENT_COLUMN);
		String number = resultOfTheSearch.getString(NUMBER_COLUMN);
		String cep = resultOfTheSearch.getString(CEP_COLUMN);
		Address address = new Address(addressInfo, number, complement, cep,city);

		//Phones
		String cellPhone = resultOfTheSearch.getString(PRINCIPAL_PHONE_COLUMN);
		String residencePhone = resultOfTheSearch.getString(SECONDARY_PHONE_COLUMN);
		String DDDPrincipalPhone = cellPhone.substring(0,2);
		String numberPrincipalPhone = cellPhone.substring(2,10);
		String DDDSecondaryPhone = residencePhone.substring(0,2);
		String numberSecondaryPhone = residencePhone.substring(2,10);

		Phone principalPhone = new Phone(DDDPrincipalPhone,numberPrincipalPhone);
		Phone secondaryPhone = new Phone(DDDSecondaryPhone,numberSecondaryPhone);


		// Birthdate
		String date = resultOfTheSearch.getString(BIRTHDATE_COLUMN);
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		Date birthdate = new Date(new Integer(day),new Integer(month),new Integer(year));
		
		Student student = new Student(studentName, studentCpf, studentRg, birthdate, email, address,
									 principalPhone, secondaryPhone, motherName, fatherName);
	
		return student;
	}

}
