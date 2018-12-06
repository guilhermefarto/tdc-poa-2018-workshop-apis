package br.com.tdc.workshopapis.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.tdc.workshopapis.enums.UserTypeEnum;
import br.com.tdc.workshopapis.models.UserVO;
import br.com.tdc.workshopapis.repositories.UserRepository;

@Service
public class UserBO {

	@Autowired
	private UserRepository repository;

	public List<UserVO> getAll() {
		return repository.findAll();
	}

	public List<UserVO> filterUsers(UserTypeEnum userType) {
		if (userType == null) {
			return this.getAll();
		}

		return repository.filter(userType);
	}

	public UserVO get(String login) {
		UserVO user = repository.findByLogin(login);

		return user;
	}

	public boolean hasUser(String login) {
		return this.get(login) != null;
	}

	public UserVO insert(UserVO user) {
		if (this.hasUser(user.getLogin())) {
			return null;
		}

		return repository.insert(user);
	}

	public UserVO update(UserVO user) {
		if (!this.hasUser(user.getLogin())) {
			return null;
		}

		return repository.save(user);
	}

	public Long delete(String login) {
		if (!this.hasUser(login)) {
			return null;
		}

		return repository.deleteByLogin(login);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

}
