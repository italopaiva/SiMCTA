package model;

import exception.AddressException;
import exception.CPFException;
import exception.CourseException;
import exception.DateException;
import exception.PackageException;
import exception.PhoneException;
import exception.RGException;
import exception.ServiceException;
import exception.StudentException;
import model.Course;
import model.Package;
import model.Service;
import model.ServiceItem;
import model.datatype.Address;
import model.datatype.CPF;
import model.datatype.Date;
import model.datatype.Phone;
import model.datatype.RG;

public class TestComposite {

	public static void main(String[] args) throws StudentException, CPFException, RGException, DateException, AddressException, PhoneException, CourseException, PackageException, ServiceException {
		
		Course course = new Course("Curso X", "Curso bom demais", 3, 150000);

		Course course2 = new Course("Curso Y", "Aulas chatas", 5, 50000);
		Course course3 = new Course("Curso Z", "Aulas massa", 2, 75049);
		
		Package pkg = new Package(1, "Pacote YZ", 145000);
		pkg.addServiceItem(course2);
		pkg.addServiceItem(course3);
		
		Student student = new Student(
			"Joao",
			new CPF("00240137140"),
			new RG("123423", "SSP", "DF"),
			new Date(12, 05, 1996),
			"joao@joao.com",
			new Address("Rua 5", "19", "", "72242111", "Cei"),
			new Phone("61", "82345678"),
			null,
			"Mariana",
			"Julio",
			1
		);
		
		Service service = new Service(student);
		service.addItem(course);
		service.addItem(pkg);
		
		for(ServiceItem item : service.getItens()){
			System.out.println(item.getName() + " - Duração: " + item.getDuration());
			System.out.println();
		}

	}

}
