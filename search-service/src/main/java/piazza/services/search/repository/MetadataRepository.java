package piazza.services.search.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import piazza.services.search.model.Metadata;

public interface MetadataRepository extends ElasticsearchRepository<Metadata, String> {

	List<Metadata> findByUserId(String userId);
	
	List<Metadata> findByDateBetween(Date startDate, Date stopDate);
	
	List<Metadata> findByUserIdAndDateAndClassification(String userId, Date date, String classification);
	
//	Documentation for reference:
//	
//	  List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);
//
//	  // Enables the distinct flag for the query
//	  List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
//	  List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);
//
//	  // Enabling ignoring case for an individual property
//	  List<Person> findByLastnameIgnoreCase(String lastname);
//	  // Enabling ignoring case for all suitable properties
//	  List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);
//
//	  // Enabling static ORDER BY for a query
//	  List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
//	  List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
	
}
