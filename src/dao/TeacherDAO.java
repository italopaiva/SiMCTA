package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import exception.AddressException;
import exception.CPFException;
import exception.DateException;
import exception.PersonException;
import exception.PhoneException;
import exception.RGException;
import exception.StudentException;
import exception.TeacherException;
import model.Teacher;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

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
		
		if(!residencePhone.isEmpty()){
			
			DDDSecondaryPhone = residencePhone.substring(0,2);
			numberSecondaryPhone = residencePhone.substring(2,10);
			principalPhone = new Phone(DDDPrincipalPhone,numberPrincipalPhone);
			secondaryPhone = new Phone(DDDSecondaryPhone,numberSecondaryPhone);
		}else{
			principalPhone = new Phone(DDDPrincipalPhone,numberPrincipalPhone);
			secondaryPhone = null;
		}

		// Birthdate
		String date = resultOfTheSearch.getString(BIRTHDATE_COLUMN);
		String year = date.substring(0,4);
		String month = date.substring(5,7);
		String day = date.substring(8,10);
		Date birthdate = new Date(new Integer(day),new Integer(month),new Integer(year));
		
		String qualification = resultOfTheSearch.getString(QUALIFICATION_COLUMN);
				
		Teacher teacher = new Teacher(teacherName, teacherCpf, teacherRg, birthdate, email, address,
									 principalPhone, secondaryPhone, motherName, fatherName, qualification);
	
		return teacher;
	}
}
