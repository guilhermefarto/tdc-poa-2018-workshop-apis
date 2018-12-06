package br.com.tdc.workshopapis;

import java.util.Date;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.tdc.workshopapis.enums.UserTypeEnum;
import br.com.tdc.workshopapis.models.UserVO;
import br.com.tdc.workshopapis.repositories.UserRepository;

@SpringBootApplication
public class WorkshopApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopApisApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository repository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				repository.deleteAll();
				repository.save(UserVO.builder().login("guilherme.farto").mail("guilherme.farto@gmail.com").name("Guilherme Farto").createdAt(new Date()).status(true).userType(UserTypeEnum.ADMIN).build());
				repository.save(UserVO.builder().login("tamires.silva").mail("tamires.silva@gmail.com").name("Tamires Silva").createdAt(new Date()).status(false).userType(UserTypeEnum.USER).build());
				repository.save(UserVO.builder().login("joao.francisco").mail("joao.francisco@gmail.com").name("João Francisco").createdAt(new Date()).status(true).userType(UserTypeEnum.USER).build());
				repository.save(UserVO.builder().login("maria.fernanda").mail("maria.fernanda@gmail.com").name("Maria Fernanda").createdAt(new Date()).status(false).userType(UserTypeEnum.ADMIN).build());
			}
		};
	}
}
