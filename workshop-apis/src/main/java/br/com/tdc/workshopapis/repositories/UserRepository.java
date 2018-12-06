package br.com.tdc.workshopapis.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.tdc.workshopapis.enums.UserTypeEnum;
import br.com.tdc.workshopapis.models.UserVO;

@Repository
public interface UserRepository extends MongoRepository<UserVO, String> {

	// https://docs.spring.io/spring-data/mongodb/docs/1.2.0.RELEASE/reference/html/mongo.repositories.html

	UserVO findByLogin(String login);

	@Query("{ 'userType' : ?0 }")
	List<UserVO> filter(UserTypeEnum userType);

	@Query(value = "{ 'login' : ?0 }", delete = true)
	Long deleteByLogin(String login);

}