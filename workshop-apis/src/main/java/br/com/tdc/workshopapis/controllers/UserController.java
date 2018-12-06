package br.com.tdc.workshopapis.controllers;

import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.tdc.workshopapis.annotations.ApiTDC;
import br.com.tdc.workshopapis.business.UserBO;
import br.com.tdc.workshopapis.dtos.RequestDTO;
import br.com.tdc.workshopapis.enums.UserTypeEnum;
import br.com.tdc.workshopapis.enums.UserTypeEnum.UserTypeEnumConverter;
import br.com.tdc.workshopapis.exceptions.EntityExistsException;
import br.com.tdc.workshopapis.exceptions.NotFoundException;
import br.com.tdc.workshopapis.models.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "users" })
@ApiTDC
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserBO bo;

	@ApiOperation("Recupera listagem de usuários")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuários recuperados com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserVO> getUsers(RequestDTO requestDTO) {
		return bo.getAll();
	}

	@ApiOperation("Filtra listagem de usuários")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuários filtrados com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<UserVO> filterUsers(@ApiParam(value = "Tipo de usuário", required = false) @RequestParam(name = "userType", required = false) UserTypeEnum userType) {
		return bo.filterUsers(userType);
	}

	@ApiOperation("Recupera usuário pelo {login}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário recuperado com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserVO getUser(@ApiParam(value = "Login do usuário", required = true) @PathVariable(value = "login", required = true) String login) {
		UserVO user = bo.get(login);

		if (user == null) {
			throw new NotFoundException(MessageFormat.format("User {0} not found", login));
		}

		return user;
	}

	@ApiOperation("Insere usuário")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário inserido com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserVO insertUser(@RequestBody UserVO user) {
		String login = user.getLogin();

		user = bo.insert(user);

		if (user == null) {
			throw new EntityExistsException(MessageFormat.format("User {0} already exists", login));
		}

		return user;
	}

	@ApiOperation("Atualiza usuário pelo {login}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário atualizado com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(value = "/{login}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserVO updateUser(@ApiParam(value = "Login do usuário", required = true) @PathVariable(value = "login", required = true) String login, @RequestBody UserVO user) {
		user.setLogin(login);
		user = bo.update(user);

		if (user == null) {
			throw new NotFoundException(MessageFormat.format("User {0} not found", login));
		}

		return user;
	}

	@ApiOperation("Remove usuário pelo {login}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário removido com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(value = "/{login}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deleteUser(@ApiParam(value = "Login do usuário", required = true) @PathVariable(value = "login", required = true) String login) {
		Long result = bo.delete(login);

		if (result == null) {
			throw new NotFoundException(MessageFormat.format("User {0} not found", login));
		}
	}

	@ApiOperation("Remove todos os usuários")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuários removidos com sucesso"), @ApiResponse(code = 401, message = "Falha na autenticação") })
	@RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deleteAll() {
		bo.deleteAll();
	}

	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(UserTypeEnum.class, new UserTypeEnumConverter());
	}
}
