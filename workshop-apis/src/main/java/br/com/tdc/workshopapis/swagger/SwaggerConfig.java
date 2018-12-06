package br.com.tdc.workshopapis.swagger;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicates;
import br.com.tdc.workshopapis.annotations.ApiTDC;
import br.com.tdc.workshopapis.filters.ApiFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("TDC Porto Alegre 2018", "http://thedevelopersconference.com.br", "guilherme.farto@gmail.com");

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(this.getApiInfo())
			.useDefaultResponseMessages(false)
	        .securitySchemes(securitySchemes())
	        .securityContexts(securityContext())
			.select()
			.apis(Predicates.and(RequestHandlerSelectors.basePackage("br.com.tdc.workshopapis.controllers"), RequestHandlerSelectors.withClassAnnotation(ApiTDC.class)))
			.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
			.title("TDC - Workshop APIs")
			.description("Workshop de APIs no TDC Porto Alegre 2018")
			.version("1.0.0")
			.contact(DEFAULT_CONTACT).build();
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder
			.builder()
			.filter(true)
			.docExpansion(DocExpansion.LIST)
			.defaultModelRendering(ModelRendering.EXAMPLE)
			.deepLinking(true)
			.defaultModelsExpandDepth(1)
			.defaultModelExpandDepth(1)
			.tagsSorter(TagsSorter.ALPHA)
			.operationsSorter(OperationsSorter.ALPHA)
			.displayRequestDuration(true)
			.showExtensions(true)
			.build();
	}

	private List<SecurityScheme> securitySchemes() {
		SecurityScheme securitySchemeApiKey = new ApiKey(ApiFilter.X_API_KEY, ApiFilter.X_API_KEY, "header");
		SecurityScheme securitySchemeBasic = new BasicAuth(ApiFilter.AUTHORIZATION);

		return Arrays.asList(securitySchemeApiKey, securitySchemeBasic);
	}

	private List<SecurityContext> securityContext() {
		return Arrays.asList(SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build());
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];

		SecurityReference securityReferenceApiKey = SecurityReference.builder().reference(ApiFilter.X_API_KEY).scopes(authorizationScopes).build();
		SecurityReference securityReferenceBasic = SecurityReference.builder().reference(ApiFilter.AUTHORIZATION).scopes(authorizationScopes).build();

		return Arrays.asList(securityReferenceApiKey, securityReferenceBasic);
	}

}
