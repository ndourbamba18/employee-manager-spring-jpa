package com.saraya;

import com.saraya.entities.Employee;
import com.saraya.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
		return args -> {
			employeeRepository.save(new Employee("Jone Doe", "jonedoe@saraya.edu.io", "+33452014587", "Department 1", "", "Paris French"));
			employeeRepository.save(new Employee("Fatima Diallo", "diallofatime@saraya.edu.io", "+221778451204", "Department 1", "", "Dakar, Senegal"));
			employeeRepository.save(new Employee("Coumba Ndiaye", "ndiayecoumba@saraya.edu.io", "+221774512014", "Department 1", "", "Point E, Dakar, Senegal"));
			employeeRepository.save(new Employee("Moussa Sane", "sanemoussa@saraya.edu.io", "+221774510214", "Department 3", "", "Ouakam, Dakar, Senegal"));
			employeeRepository.save(new Employee("Iboulaye Kane", "kaneibou10@saraya.edu.io", "+22177410021", "Department 3", "", "Dakar, Senegal"));
			employeeRepository.save(new Employee("Salla Mbaye", "mbayesalla01@saraya.edu.io", "+22179541287", "Department 1", "", "Point E, Senegal"));
			employeeRepository.save(new Employee("Amina Sow", "sowamina@saraya.edu.io", "+221775420145", "Department 2", "", "Yoff, Dakar, Senegal"));
			employeeRepository.save(new Employee("Binta Diop", "diopbinta@saraya.edu.io", "+221778214571", "Department 1", "", "Dakar, Senegal"));
			employeeRepository.save(new Employee("Moussa Cisse", "cissemoussa@saraya.edu.io", "+221774012457", "Department 2", "", "Pikine, Dakar, Senegal"));
			employeeRepository.save(new Employee("Alice jane", "janealice02@saraya.edu.io", "+221775214570", "Department 3", "", "Dakar, Senegal"));
			employeeRepository.findAll().forEach(employee -> {System.out.println(employee.getName());});
		};
	}

}
