package application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import application.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
//	@Query(value="SELECT max(run_start_date-.005) FROM job_runs WHERE JOB_STATUS = 'COMPLETED' and app_name='PBDR_INCREMENTAL_PROCESS'", nativeQuery = true)


//JpaRepository<User, Integer>{
	
//	public default User save(User user) { //Q: What is default keyword again? Only know error message requires it.
//		
//		return user;
//	}
	
	//NEWLY COMMENTED:
//	@Query("SELECT * FROM jostroffOHI u where u.id = :id") // where u.id = :id
//	public static User findById(int id) {
//		return new User();
//	}
}
