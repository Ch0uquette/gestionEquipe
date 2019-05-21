package com.fr.adaming;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fr.adaming.entity.Equipe;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.IEquipeService;
import com.fr.adaming.service.IUserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootProjectApplication.class, args);

		Equipe e = new Equipe (1L,"nom",15);
		//context.getBean(IEquipeService.class).create(e);
		IEquipeService equipeService = (IEquipeService) context.getBean("equipeService");
		equipeService.create(e);

		User u = new User (5L,"nom","prenom","email","pwd",e);
		//context.getBean(IUserService.class).create(u);
		IUserService userService = (IUserService) context.getBean("userService");
		userService.create(u);
				
		Logger log = Logger.getLogger(SpringBootProjectApplication.class);
		log.trace("Message de log(TRACE)");
		log.debug("Message de log(DEBUG)");
		log.info("Message de log(INFO)");
		log.warn("Message de log(WARN)");
		log.error("Message de log(ERROR)");
		log.fatal("Message de log(FATAL)");

	}

}
