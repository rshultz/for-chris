package piazza.services.search;

import java.util.ArrayList;

public class TaskService {
	
	private static ArrayList<Task> taskList = new ArrayList<Task>();
	private static Task task;
    
    public static void createTask (String description) {
    	task = new Task(description);
    	taskList.add(task);
    }
    
    public static String getList () {
		StringBuilder html = new StringBuilder();
    	
		if (taskList.isEmpty()) {
			html.append("<b>Try adding some things to your list</b>");
		} else {
			html.append("<ul>");
			for (Task item : taskList) {
                html.append("<li>").append(item.getDescription()).append("</p>");
            }
            html.append("</ul>");
		}
		return html.toString();
    }

}
