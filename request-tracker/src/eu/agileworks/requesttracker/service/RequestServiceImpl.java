package eu.agileworks.requesttracker.service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.agileworks.requesttracker.dao.RequestDAO;
import eu.agileworks.requesttracker.entity.Request;


@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestDAO requestDAO;

	@Override
	@Transactional
	public List<Request> getRequests() {
		List<Request> requests = requestDAO.getRequests();
		Date oneHourForward = getOneHourForward();
		
		for(Request request: requests) {
			
			setOverdue(oneHourForward, request);
		}

		requests.sort(new Comparator<Request>() {
			@Override
			public int compare(Request r1, Request r2) {
				return r1.getDeadline().compareTo(r2.getDeadline()); 
			}
		});
		return requests;
	}

	@Override
	@Transactional
	public void saveRequest(Request theRequest) {
		if(theRequest.getDeadline() == null) {
			theRequest.setDeadline(theRequest.getCreated());
		}
		requestDAO.saveRequest(theRequest);
		
	}

	@Override
	@Transactional
	public Request getRequest(int theId) {
		
		Request request = requestDAO.getRequest(theId);
		setOverdue(getOneHourForward(), request);
		return request;
	}


	@Override
	@Transactional
	public void deleteRequest(int theId) {
		requestDAO.deleteRequest(theId);
		
	}

	@Override
	@Transactional
	public Request getRequestByDescription(String description) {
		Request request = requestDAO.getRequestByDescription(description);
		setOverdue(getOneHourForward(), request);
		return request;
	}
	
	private Date getOneHourForward() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 1);
		Date oneHourForward= cal.getTime();
		return oneHourForward;
	}
	
	private void setOverdue(Date oneHourForward, Request request) {
		boolean isOverdue = request.getDeadline().before(oneHourForward);
		request.setOverdue(isOverdue);
	}

}
