package br.com.tdc.workshopapis;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.tdc.workshopapis.filters.ApiFilter;

@Configuration
public class FilterConfiguration {

	@Bean
	public FilterRegistrationBean<ApiFilter> apiFilter() {
		FilterRegistrationBean<ApiFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new ApiFilter());
		registrationBean.addUrlPatterns("/api/users/*");

		return registrationBean;
	}
}
