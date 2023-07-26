package com.insy2s.KeyCloakAuth;

import com.insy2s.KeyCloakAuth.model.Role;
import com.insy2s.KeyCloakAuth.model.User;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import com.insy2s.KeyCloakAuth.repository.UserRepository;
import com.insy2s.KeyCloakAuth.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class KeyCloakAuthServiceApplication {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(KeyCloakAuthServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(){
		return args-> {

	/*		Role admin = new Role();
		admin.setName("ADMIN");
		saveRole(admin)		;
=======
<<<<<<< HEAD
		/*	Role admin = new Role();
			admin.setName("ADMIN");
			saveRole(admin)		;

			Role consultant = new Role();
			consultant.setName("CONSULTANT");
			saveRole(consultant);
=======
//			Role admin = new Role();
//			admin.setName("ADMIN");
//			saveRole(admin)		;
//
//			Role consultant = new Role();
//			consultant.setName("CONSULTANT");
//			saveRole(consultant);


			Role consultant = new Role();
		consultant.setName("CONSULTANT");
			saveRole(consultant);

	 */

	/*		User user = new User();
			user.setUsername("iheb");
			user.setEmail("iheb@gmail.com");
			user.setPassword("iheb");
			user.setRoles(roleRepository.findAll());
			saveUser(user);
*/

		};}



	private void saveRole(Role role)
	{
		Optional<Role> roleSearched=roleRepository.findByName(role.getName());
		if(roleSearched.isEmpty()){
			roleRepository.save(role);
			System.out.println("The role with name "+role.getName() +" saved ");

		}
		else{
			System.out.println("The role with name "+role.getName() +" found ");
		}
	}

	public void saveUser(User user){
		userRepository.findByUsername("iheb");
		userRepository.save(user);
		System.out.println(user+"user");

	}
}


