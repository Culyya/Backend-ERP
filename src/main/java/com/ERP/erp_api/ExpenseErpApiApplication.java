package com.ERP.erp_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ERP.erp_api.filters.AuthFilter;

@SpringBootApplication
public class ExpenseErpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseErpApiApplication.class, args);
	}

    @Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/item/*");
        registrationBean.addUrlPatterns("/api/departement/*");
        registrationBean.addUrlPatterns("/api/role/*");
        registrationBean.addUrlPatterns("/api/materialrequest/*");
        registrationBean.addUrlPatterns("/api/materialrequestitem/*");
        registrationBean.addUrlPatterns("/api/materialrequestnumber/*");
		return registrationBean;

	}
}
