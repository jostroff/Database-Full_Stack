package application;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import application.entities.User;
import application.repositories.UserRepository;

@SpringBootApplication
//@PropertySource("application-${REGION}.properties")
//@Slf4j
public class Application {
	
	static UserRepository userRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        System.out.println("Hello ENDING.");
        
//        System.out.println(userRepository.findById(1L));
    }
    
    @Bean
    public CommandLineRunner run(UserRepository userRepository) throws Exception {
        return (String[] args) -> {   
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        	LocalDateTime now = LocalDateTime.now();
        	System.out.println("We have at " + dtf.format(now) + " the following from jostroffOHI: ");
            userRepository.findAll().forEach(user -> System.out.println(user));
            Application.userRepository = userRepository;
        };
    }
    /**Notes - In order to run this application, you need the following:
     * 		npm installation
     * 		nodejs 10.13 or higher - https://nodejs.org/dist/latest-v10.x/		(node-v10.22.1-win-x64.msi)
     * 		npm install @angular/cli
     * 		Make sure Control Panel\System and Security\System -> Advanced system settings ->
     * 			PATH = C:\Users\jostroff\AppData\Roaming\npm
     * 			JAVA_HOME = C:\Program Files\Java\jdk1.8.0_241
     * 			M2_HOME = C:\opt\apache\apache-maven-3.3.9
     * 			DOCKER_TOOLBOX_INSTALL_PATH = C:\Program Files\Docker Toolbox
     *
    */
    //ng serve --open
    

    //If we get Error message of:
    //  		Note: Identify and stop the process that's listening on port 8080 or 
    //  		configure this application to listen on another port.
    //Then we need to go to Terminal/Command Line: 
    //  	>netstat -ao |find /i "listening"		->		Gives port number 8080 ... LISTENING 69904
    //  	>Taskkill /F /IM 69904					->		Kills port number 8080 so can restart.
    
    
//Resources for R2DBC that we decided to FOREGO:
    //https://github.com/eugenp/tutorials/tree/master/spring-5-data-reactive/src/main/java/com/baeldung/r2dbc
    //https://github.com/eugenp/tutorials/blob/master/persistence-modules/r2dbc/src/main/resources/application.yml
    //https://groups.google.com/g/r2dbc
    //https://docs.spring.io/spring-data/r2dbc/docs/1.0.x/reference/html/#r2dbc.repositories
    //https://www.youtube.com/watch?v=fIMdlE5Hvzk&ab_channel=SpringDeveloper
    //https://github.com/joshlong/reactive-mysql-with-jasync-and-r2dbc/blob/master/src/main/java/com/example/rx/BootifulReactiveMySqlApplication.java
    
}
