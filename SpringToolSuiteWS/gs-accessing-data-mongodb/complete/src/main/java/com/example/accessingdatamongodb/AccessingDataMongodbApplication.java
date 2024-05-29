package com.example.accessingdatamongodb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class AccessingDataMongodbApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		repository.save(new Customer("John", "Doe"));
		repository.save(new Customer("Jane", "Doe"));
		repository.save(new Customer("James", "Webber"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("\nCustomer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("\nCustomers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

		System.out.println("\nCustomers found with customized query:");
		System.out.println("--------------------------------");
//		Query query = new Query(Criteria.where("lastName").in(List.of("Smith", "Doe")).andOperator(
//				Criteria.where("firstName").is("Bob").orOperator(
//						Criteria.where("firstName").is("John"))));
		// see https://stackoverflow.com/questions/16478101/spring-data-mongo-use-or-in-query
		Query query = new Query(new Criteria().andOperator(Criteria.where("lastName").in(List.of("Smith", "Doe")),
				new Criteria().orOperator(Criteria.where("firstName").is("Bob"),
						Criteria.where("firstName").is("John"))));
		for( Customer customer : mongoTemplate.find(query, Customer.class)){
			System.out.println(customer);
		}

	}

}
