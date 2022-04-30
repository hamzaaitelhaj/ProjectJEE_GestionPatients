package com.emsi.patientsmvc;

import com.emsi.patientsmvc.entities.Patient;
import com.emsi.patientsmvc.repositories.PatientRepository;
import com.emsi.patientsmvc.security.services.SecurityService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
CommandLineRunner commandLineRunner(PatientRepository PR){
        return args -> {
PR.save(new Patient(null,"hassan",new Date(),false,132));
            PR.save(
                    new Patient(  null, "Mohammed", new Date(),  true, 321));
            PR.save(
                    new Patient( null,  "Yasmine", new Date(),  true,  665));
            PR.save(
                    new Patient(  null,  "hamza", new Date(), false,  332));

            PR.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });
        };
}
//@Bean
CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("admin","admin","admin");
            securityService.saveNewUser("hamza","hamza","hamza");
            securityService.saveNewUser("slimaniya","casa","casa");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("admin","USER");
            securityService.addRoleToUser("admin","ADMIN");


            securityService.addRoleToUser("slimaniya","USER");
            securityService.addRoleToUser("hamza","USER");
        };
}
}
