package com.mountainwind.example.spring;

import com.querydsl.core.Tuple;
import com.querydsl.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class QuerydslsqlApplication  implements CommandLineRunner{

//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;




//	@Bean
//	@ConfigurationProperties()
//	public DataSource defaultDataSource() {
//
//		return DataSourceBuilder.create()
//				.driverClassName("org.apache.derby.jdbc.EmbeddedDriver")
//				.url("jdbc:derby:target/demoDB;create=true")
//				.build();
//	}

	@Autowired
	DataSource dataSource;

//	@Bean
//	public DataSource dataSource() {
//
//		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		EmbeddedDatabase db = builder
//				.setType(EmbeddedDatabaseType.DERBY) //.H2 or .HSQL
//				.build();
//		return db;
//	}



	public static void main(String[] args) {
		SpringApplication.run(QuerydslsqlApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {

//		QCustomer customer = QCustomer.customer;
//
//		SQLTemplates dialect = new DerbyTemplates();
//		Configuration configuration = new Configuration(dialect);
//
//
//		SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, dataSource);
//
//		List<String> lastNames = queryFactory
//				.select(customer.name).from(customer)
//				.where(customer.num.eq(1))
//				.fetch();
//
//		for(String name: lastNames) {
//			System.out.println(name);
//		}


		QCompany company = QCompany.company;
        QProductDetail product = QProductDetail.productDetail;

		SQLTemplates dialect = new MySQLTemplates();
		Configuration configuration = new Configuration(dialect);

		SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, dataSource);

        List<Tuple> products = queryFactory
                .select(product.productName, product.shortDescription, company.companyId, company.companyEmail)
                .from(product)
                .join(company)
                .where(company.companyId.eq("company1"))
                .fetch();

        for(Tuple name: products) {
            System.out.println(name);
        }
	}
}
