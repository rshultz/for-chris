package piazza.services.search.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiParam;

import piazza.services.search.model.DateParser;
import piazza.services.search.model.DateTimeParseException;
import piazza.services.search.model.Metadata;
import piazza.services.search.repository.MetadataRepository;

@RestController
public class Controller {
	
	@Autowired
	MetadataRepository repository;

	//Creating a deserializer to handle Key Values currently
	// add "/?userId=csmith" at the end of url to receive all metadata with that userId
	@RequestMapping(value="/metadata_v1/getRequest", method=RequestMethod.GET)
	public List<Metadata> getMetadataByUserId(@ApiParam(value="name of user")
			@RequestParam(value = "userId", required = true, defaultValue = "anonymous") String userId){
		return repository.findByUserId(userId);
	}
	
	//I changed the date format of Metadata while I was editing things, so I don't know that it will
	//let you search by date right now. Perhaps it still will as long as you write in the correct way.
	@RequestMapping(value="/metadata_v1/postRequest", method=RequestMethod.POST, consumes="application/json")
	public List<Metadata> getMetadataUsingJson(@ApiParam(value="Metadata Json to search using")
			@RequestBody(required = true) Metadata searchFor){
		String userId = searchFor.getUserId();
		Date date = searchFor.getDate();
		String classification = searchFor.getClassification();
		return repository.findByUserIdAndDateAndClassification(userId, date, classification);
	}
	
	protected Date convertTimeStringToDate(String timeString, String name) throws DateTimeParseException {
		Date date = null;
			date = DateParser.newInstance().parse(timeString).toDate();
		return date;
	}
	
	//This method should accept a start date and an end date and return all data entered between the two
	//I took all of the relevant gears directly from Barry's code for this per his recommendation
	@RequestMapping(value="/metadata_v1/dateBetweenSearch", method=RequestMethod.GET)
	public List<Metadata> dateBetweenSearch(@ApiParam(value="start date time of search")
			@RequestParam(value = "startTime", required = true) String startTime,
			@ApiParam(value = "stop date time of search")
			@RequestParam(value = "stopTime", required = true) String stopTime) throws DateTimeParseException{
		Date startDate = convertTimeStringToDate(startTime, "startTime");
		Date stopDate = convertTimeStringToDate(stopTime, "stopTime");
		return repository.findByDateBetween(startDate, stopDate);
	}
	
}
