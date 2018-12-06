package br.com.tdc.workshopapis.models;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import br.com.tdc.workshopapis.annotations.DatabaseMetadata;
import br.com.tdc.workshopapis.enums.UserTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "User", description = "Representa uma entidade de usuário")
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserVO {

	@Id
	@Field("login")
	@ApiModelProperty(value = "Login do usuário", example = "user.test")
	@DatabaseMetadata(column = "LOGIN")
	private String login;

	@ApiModelProperty(value = "Nome do usuário", example = "User Test")
	@DatabaseMetadata(column = "NAME")
	private String name;

	@ApiModelProperty(value = "E-mail do usuário", example = "user.test@gmail.com")
	@DatabaseMetadata(column = "MAIL")
	private String mail;

	@ApiModelProperty(value = "Data de criação do usuário")
	private Date createdAt;

	@ApiModelProperty(value = "Status do usuário", example = "true")
	private boolean status;

	@ApiModelProperty(value = "Tipo do usuário", example = "admin")
	private UserTypeEnum userType;

}
