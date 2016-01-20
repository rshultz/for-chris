package piazza.services.search.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import piazza.services.search.TaskService;


@RestController
public class Controller {
	

	@RequestMapping("/")
    public String index() {
        return "Hi.";
    }
	
	@RequestMapping(value="/add/{item}", method = RequestMethod.POST)
	public void add(@PathVariable String item) {
		TaskService.createTask(item);
        System.out.println(item);
    }

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList() {
		return TaskService.getList();
	}
}