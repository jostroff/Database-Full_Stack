package application.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hphc.utils.crypto.DesTwoWay;
import org.hphc.utils.exceptions.HphcException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "fmsEntityManagerFactory",
transactionManagerRef = "fmsTransactionManager", basePackages = {"application.repositories"})
																//Repository class. MUST BE HERE.
public class DataSourceConfig {
    
	@Value("${fms.datasource.driver-class-name}") 
	  String driverClass;
	  
	  @Value("${fms.datasource.jdbcUrl}") 
	  String url;
	  
	  @Value("${fms.datasource.username}") 
	  String username;
	 
	  @Value("${fms.datasource.password}") 
	  String password;
	
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver"); //org.h2.Driver
////        dataSourceBuilder.driverClassName("org.h2.Driver");
//        dataSourceBuilder.url("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=tcp)(HOST= devdb01.hphc.org)(PORT=1551))(ADDRESS=(PROTOCOL=tcp)(HOST= devdb02.hphc.org)(PORT=1551))(ADDRESS=(PROTOCOL=tcp)(HOST= devdb03.hphc.org)(PORT=1551))(ADDRESS=(PROTOCOL=tcp)(HOST= devdb04.hphc.org)(PORT=1551)))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=fmsdev1svc.hphc.org)))"); //jdbc:h2:mem:test
//        dataSourceBuilder.username("pdm_usr"); //SA
//        dataSourceBuilder.password("mYB1gdr35mS"); //""
//        return dataSourceBuilder.build();
//    }
	  
	  
	@Primary
    @Bean(name = "fmsDataSource")
	public DataSource fmsDataSource() throws HphcException {
    	System.out.println("JOSHUA: " + DesTwoWay.decrypt(password)); //We have arrived.
		return DataSourceBuilder.create()
				.driverClassName(driverClass)
				.url(url)
				.username(username)
				.password(DesTwoWay.decrypt(password))
				.build();
	}

    @Primary
	@Bean(name = "fmsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean fmsEntityManagerFactory(
			EntityManagerFactoryBuilder builder, @Qualifier("fmsDataSource") DataSource fmsDataSource) {
		return builder.dataSource(fmsDataSource).packages("application.entities").persistenceUnit("fms")
				.build();									//Users class. MUST BE HERE.
	}

	@Primary
	@Bean(name = "fmsTransactionManager")
	public PlatformTransactionManager fmsTransactionManager(
			@Qualifier("fmsEntityManagerFactory") EntityManagerFactory fmsEntityManagerFactory) {
		return new JpaTransactionManager(fmsEntityManagerFactory);
	}
}
