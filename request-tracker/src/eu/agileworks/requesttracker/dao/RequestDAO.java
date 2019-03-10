package eu.agileworks.requesttracker.dao;

import java.util.List;

import eu.agileworks.requesttracker.entity.Request;

public interface RequestDAO {
	
	public List<Request> getRequests();

	public void saveRequest(Request theRequest);

	public Request getRequest(int theId);
	
	public Request getRequestByDescription(String description);

	public void deleteRequest(int theId);

}
