package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datatype.Address;
import datatype.CPF;
import datatype.Date;
import datatype.Phone;
import datatype.RG;
import model.Course;
import model.Teacher;
import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;
import exception.TeacherException;

public class TeacherDAO extends DAO{
	
	private static final String TEACHER_TABLE_NAME = "Teacher";
	private static final String NAME_COLUMN = "teacher_name";
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
	private static final String STATUS_COLUMN	= "status";
	private static final String QUALIFICATION_COLUMN = "qualification";
	
	private static final String CANT_SAVE_TEACHER = "Não foi possível salvar os dados do professor informado.";
	private static final String CPF_ALREADY_EXISTS = "O CPF informado já está cadastrado.";
	private static final String COULDNT_CHECK_TEACHER = "Não foi possível checar se o professor está cadastrado. Tente novamente.";
	private static final String CANT_FOUND_TEACHERS = "Não foi possível encontrar os professores cadastrados.";
	private static final String CANT_FOUND_TEACHER = "Não foi possível encontrar os dados do professor selecionado.";;
	private static final String CANT_UPDATE_TEACHER = "Não foi possível alterar os dados do professor informado.";
	
	/**
	 * Save on database the received teacher 
	 * @param teacher - the data of the teacher to be save
	 * @throws TeacherException
	 */
	public void save(Teacher teacher) throws TeacherException{
		try{
			Teacher previousTeacher = get(teacher.getCpf());
			
			if(previousTeacher == null){
				try{
					String secondaryPhone;
					if(teacher.getSecondaryPhone() != null){
						secondaryPhone = teacher.getSecondaryPhone().getWholePhone();
					}
					else{
						secondaryPhone = "";
					}
					
					String teacherCpf = teacher.getCpf().getCpf();
					String teacherName = teacher.getName();
					String birthdate = teacher.getBirthdate().getHyphenFormattedDate();
					String email = teacher.getEmail();
					String motherName = teacher.getMotherName();
					String fatherName = teacher.getFatherName();
					String rgUf = teacher.getRg().getUf();
					String rgIssuing = teacher.getRg().getIssuingInstitution();
					String rgNumber = teacher.getRg().getRgNumber();
					String principalPhone = teacher.getPrincipalPhone().getWholePhone();
					String complement = teacher.getAddress().getComplement();
					String addressNumber = teacher.getAddress().getNumber();
					String city = teacher.getAddress().getCity();
					String cep = teacher.getAddress().getCep();
					String addressInfo = teacher.getAddress().getAddressInfo();
					String qualification = teacher.getQualification();
					
					String query = "INSERT INTO "+ TEACHER_TABLE_NAME +" ("+ CPF_COLUMN +", "+ NAME_COLUMN +", "
							   + BIRTHDATE_COLUMN +", "+ EMAIL_COLUMN +", "+ MOTHER_COLUMN +", "
							   + FATHER_COLUMN +", "+ UF_COLUMN +", "+ ISSUING_INSTITUTION_COLUMN +", "
							   + RG_NUMBER_COLUMN +", "+ PRINCIPAL_PHONE_COLUMN +", "+ SECONDARY_PHONE_COLUMN +", "
							   + COMPLEMENT_COLUMN +", "+ NUMBER_COLUMN +", "+ CITY_COLUMN +", "+ CEP_COLUMN +", "
							   + ADDRESS_COLUMN + "," + QUALIFICATION_COLUMN + ")"
							   + " VALUES ('"+ teacherCpf +"', '"+ teacherName +"', '"
							   + birthdate +"', '"+ email +"', '" + motherName +"', '"+ fatherName +"', '"
							   + rgUf +"', '" + rgIssuing +"', '"+ rgNumber +"', '"
							   + principalPhone +"', '"  + secondaryPhone +"', '"+ complement +"','"
							   + addressNumber +"', '"+ city +"', '" + cep +"', '"+ addressInfo +"',' " + qualification + "')";
					this.execute(query);
				}
				catch(SQLException e){
					throw new TeacherException(CANT_SAVE_TEACHER);
				}
			}
			else{
				throw new TeacherException(CPF_ALREADY_EXISTS);
			}
		}
		catch(PhoneException | CPFException | DateException |
			  AddressException | RGException e1){
			
			throw new TeacherException(COULDNT_CHECK_TEACHER);
		}
		
	}
	
	/**
	 * Gets all teachers from database
	 * @return an array with all teachers 
	 * @throws PhoneException
	 * @throws CPFException
	 * @throws DateException
	 * @throws AddressException
	 * @throws RGException
	 * @throws StudentException
	 */
	public ArrayList<Teacher> get() throws PhoneException, CPFException, DateException, AddressException,
												RGException, TeacherException {
		
		ResultSet resultOfTheSearch = null;
		
		String query = ("SELECT * FROM "+ TEACHER_TABLE_NAME + " WHERE " + STATUS_COLUMN + "=" + 1);
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		Teacher teacher = null;
		CPF teacherCpf = null;
		
		try{
			resultOfTheSearch = this.search(query);
			
			while(resultOfTheSearch.next()){
				String teacherName = (resultOfTheSearch.getString(NAME_COLUMN));
				String cpf = (resultOfTheSearch.getString(CPF_COLUMN));
				teacherCpf = new CPF(cpf);
				teacher = new Teacher(teacherName, teacherCpf);
				teachers.add(teacher);
			}
			
		}
		catch(SQLException e){
			throw new TeacherException(CANT_FOUND_TEACHERS);
		} 
		catch (PersonException e) {
			throw new TeacherException(CANT_FOUND_TEACHERS);
		}

		return teachers;
	}

	/**
	 * Gets the teacher that has the specific 'cpf'
	 * @param	cpf - the 'cpf' of the searched teacher 
	 * @return an object with the data of the teacher 
	 * @throws PhoneException
	 * @throws CPFException
	 * @throws DateException
	 * @throws AddressException
	 * @throws RGException
	 * @throws StudentException
	 */
	public Teacher get(CPF cpf) throws PhoneException, CPFException, DateException, AddressException,
												RGException, TeacherException {
		
		ResultSet resultOfTheSearch = null;
		String receivedCPF = cpf.getCpf();
		
		String query = "SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + CPF_COLUMN + "= \"" + receivedCPF + "\""; 
		Teacher teacher = null;

		try{
			resultOfTheSearch = this.search(query);
			while(resultOfTheSearch.next()){
				teacher = getFoundTeacher(resultOfTheSearch);
			}
		}
		catch(SQLException e){
			
		} 
		catch (PersonException e) {
			
		}
		return teacher;
	}

	/**
	 * Gets the data of the teacher
	 * @param resultOfTheSearch the row from database that contains the data of the teacher
	 * @return an object with the data of the teacher
	 * @throws PhoneException
	 * @throws SQLException
	 * @throws CPFException
	 * @throws DateException
	 * @throws AddressException
	 * @throws RGException
	 * @throws PersonException 
	 */
	private Teacher getFoundTeacher(ResultSet resultOfTheSearch) throws PhoneException, SQLException, 
	CPFException, DateException, AddressException, RGException, TeacherException, PersonException {

		// Get the data from database
		String teacherName = resultOfTheSearch.getString(NAME_COLUMN);

		String email = resultOfTheSearch.getString(EMAIL_COLUMN);
		String motherName = resultOfTheSearch.getString(MOTHER_COLUMN);
		String fatherName = resultOfTheSearch.getString(FATHER_COLUMN);

		//CPF
		String cpf = resultOfTheSearch.getString(CPF_COLUMN);
		CPF teacherCpf = new CPF(cpf);

		//RG
		String rg = resultOfTheSearch.getString(RG_NUMBER_COLUMN);
		String uf = resultOfTheSearch.getString(UF_COLUMN);
		String issuing_institution = resultOfTheSearch.getString(ISSUING_INSTITUTION_COLUMN);
		RG teacherRg = new RG(rg,issuing_institution,uf);

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
		
		String DDDSecondaryPhone;
		String numberSecondaryPhone;
		Phone principalPhone;
		Phone secondaryPhone;
		
		principalPhone = new Phone(DDDPrincipalPhone,numberPrincipalPhone);
		
		if(!residencePhone.isEmpty() && residencePhone != "null"){
			DDDSecondaryPhone = residencePhone.substring(0,2);
			numberSecondaryPhone = residencePhone.substring(2,10);
			secondaryPhone = new Phone(DDDSecondaryPhone,numberSecondaryPhone);
		}
		else{
			secondaryPhone = null;
		}

		// Birthdate
		String date = resultOfTheSearch.getString(BIRTHDATE_COLUMN);
		Date birthdate = new Date(date);
		
		String qualification = resultOfTheSearch.getString(QUALIFICATION_COLUMN);
		
		int status = resultOfTheSearch.getInt(STATUS_COLUMN);
		
		Teacher teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
									 principalPhone, secondaryPhone, motherName, fatherName, qualification, status);
	
		return teacher;
	}

	/**
	 * Gets the teachers with the searched name
	 * @param searchedTeacher - searched name by the user
	 * @return an arraylist with the found teachers
	 * @throws CPFException
	 * @throws PersonException 
	 * @throws TeacherException 
	 */
	public ArrayList<Teacher> get(String searchedTeacher) throws PersonException, TeacherException {
		
		ResultSet resultOfTheSearch = null;
		String query = "SELECT * FROM " + TEACHER_TABLE_NAME + " WHERE " + NAME_COLUMN + " LIKE \"%" + searchedTeacher + "%\""; 
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		Teacher teacher = null;
		CPF teacherCpf = null;
		
		try{
			resultOfTheSearch = this.search(query);
			
			while(resultOfTheSearch.next()){
				String teacherName = (resultOfTheSearch.getString(NAME_COLUMN));
				String cpf = (resultOfTheSearch.getString(CPF_COLUMN));
				teacherCpf = new CPF(cpf);
				teacher = new Teacher(teacherName, teacherCpf);
				teachers.add(teacher);
			}
			
		}
		catch(SQLException e){
			throw new TeacherException(CANT_FOUND_TEACHERS);
		} 
		catch (PersonException | CPFException e) {
			throw new PersonException(e.getMessage());
		}

		return teachers;
	}

	/**
	 * Update a given course on the database
	 * @param courseId - The course to be updated
	 * @param course - A Course object with the course new data
	 * @return TRUE if the course was updated on database or FALSE if it does not
	 * @throws TeacherException 
	 */
	public Teacher update(Teacher teacher) throws TeacherException{
				
		String teacherName = teacher.getName();		
		String email = teacher.getEmail();
		String motherName = teacher.getMotherName();
		String fatherName = teacher.getFatherName();

		//CPF
		CPF cpf = teacher.getCpf();
		String teacherCpf  = cpf.getCpf();

		// Birthdate
		Date date = teacher.getBirthdate();
		String birthdate = date.getHyphenFormattedDate();
		
		//Address
		Address address = teacher.getAddress();
		String city = address.getCity();
		String cep = address.getCep();
		String addressInfo = address.getAddressInfo();
		String complement = address.getComplement();
		String number = address.getNumber();
	
		//Phones
		Phone principalPhone = teacher.getPrincipalPhone();
		Phone secondaryPhone = teacher.getSecondaryPhone();
		
		String cellPhone = principalPhone.getWholePhone();
		String phone;
		if(secondaryPhone != null){
			phone = secondaryPhone.getWholePhone();
		}
		else{
			phone = "";
		}
		
		String qualification = teacher.getQualification();
				
		String query = "UPDATE "+ TEACHER_TABLE_NAME + " SET "
					   + NAME_COLUMN + "='" + teacherName + "', "
					   + EMAIL_COLUMN + "='" + email + "', "
					   + MOTHER_COLUMN + "='" + motherName + "', "
					   + FATHER_COLUMN + "='" + fatherName + "', "
					   + BIRTHDATE_COLUMN + "='" + birthdate + "', "
					   + ADDRESS_COLUMN + "='" + addressInfo + "', "
					   + COMPLEMENT_COLUMN + "='" + complement + "', "
					   + NUMBER_COLUMN + "='" + number + "', "
					   + CITY_COLUMN + "='" + city + "', "
					   + CEP_COLUMN + "='" + cep + "', "
					   + PRINCIPAL_PHONE_COLUMN + "='" + cellPhone + "', "
					   + SECONDARY_PHONE_COLUMN + "='" + phone + "', "
					   + QUALIFICATION_COLUMN + "='" + qualification + "' "
					   + "WHERE " + CPF_COLUMN + "='" + teacherCpf + "'";

		try{
			this.execute(query);
			teacher = get(cpf);
		}
		catch(SQLException | PhoneException | CPFException | DateException | AddressException | RGException | TeacherException caughtException){
			throw new TeacherException(CANT_UPDATE_TEACHER);
		}
		
		return teacher;
	}
	
	/**
	 * Updates the status of the given teacher on database
	 * @param teacher - Teacher to update the status
	 * @param newStatus - New status of the teacher
	 * @throws TeacherException
	 */
	public void updateStatus(Teacher teacher, int newStatus) throws TeacherException{
		
		CPF cpf = teacher.getCpf();
		String teacherCpf  = cpf.getCpf();
		
		String query = "UPDATE "+ TEACHER_TABLE_NAME + " SET "
				     + STATUS_COLUMN + "='" + newStatus + "'"
				     + " WHERE " + CPF_COLUMN + "='" + teacherCpf + "'";
		try{
			this.execute(query);
		}
		catch(SQLException e){
			throw new TeacherException(CANT_SAVE_TEACHER);
		}
	}
}
