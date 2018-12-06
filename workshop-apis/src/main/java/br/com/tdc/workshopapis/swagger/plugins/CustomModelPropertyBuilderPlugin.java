package br.com.tdc.workshopapis.swagger.plugins;

import java.util.Collections;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.google.common.base.Optional;
import br.com.tdc.workshopapis.annotations.DatabaseMetadata;
import springfox.documentation.service.ObjectVendorExtension;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1002)
public class CustomModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {

	@Override
	public void apply(ModelPropertyContext context) {
		context.getBuilder().allowEmptyValue(null);

		if (context != null && context.getBeanPropertyDefinition().isPresent()) {
			Optional<DatabaseMetadata> annotation = Optional.fromNullable(context.getBeanPropertyDefinition().get().getField().getAnnotation(DatabaseMetadata.class));

			if (annotation.isPresent()) {
				ObjectVendorExtension rootExtension = new ObjectVendorExtension("x-database-metadata");
				rootExtension.addProperty(new StringVendorExtension("column", annotation.get().column()));

				context.getBuilder().extensions(Collections.singletonList(rootExtension));
			}
		}
	}

	@Override
	public boolean supports(DocumentationType documentationType) {
		return true;
	}
}
