package eu.agileworks.requesttracker.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.agileworks.requesttracker.entity.Request;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/request-tracker-servlet.xml")
public class RequestServiceImplTest {
	
	@Autowired
	private RequestService requestService;
	
	@Test
	public void saveRequestTest() {
		
		Request request = makeTestRequest();
		String description = request.getDescription();
		Date deadline = request.getDeadline();
		
		requestService.saveRequest(request);

		Calendar cal = Calendar.getInstance();
	    cal.setTime(request.getCreated());
	    cal.set(Calendar.MILLISECOND, 0);
		Date created = cal.getTime();
		
		Request requestDB = requestService.getRequestByDescription(description);
		
		assertEquals(created, requestDB.getCreated());
		assertEquals(description, requestDB.getDescription());
		assertEquals(deadline, requestDB.getDeadline());
		
	}
	
	@Test
	public void deleteRequestTest() {
		
		Request request = makeTestRequest();		
		requestService.saveRequest(request);
		int theId = request.getId();

		requestService.deleteRequest(theId);
		
		assertThrows(NullPointerException.class,
	            ()->{
	            	requestService.getRequest(theId);
	            });		
	}
	
	@Test
	public void testForOverdue() {
		Request requestWithOverdue = makeTestRequest(true);
		requestService.saveRequest(requestWithOverdue);
		int theId = requestWithOverdue.getId();
		Request requestWithOverdueDB = requestService.getRequest(theId);
		assertTrue(requestWithOverdueDB.isOverdue());	
		
	}
	
	@Test
	public void testForNoOverdue() {
		Request requestWithoutOverdue = makeTestRequest(false);
		requestService.saveRequest(requestWithoutOverdue);
		int theId = requestWithoutOverdue.getId();
		Request requestWithoutOverdueDB = requestService.getRequest(theId);
		assertFalse(requestWithoutOverdueDB.isOverdue());	
		
	}
	
	@Test
	public void testSorting() {
		Request request1 = makeTestRequest();
		requestService.saveRequest(request1);
		Request request2 = makeTestRequest();
		requestService.saveRequest(request2);
		List<Request> requests = requestService.getRequests();
		Date firstRequestDeadline = requests.get(0).getDeadline();
		Date secondRequestDeadline = requests.get(1).getDeadline();
		assertTrue(firstRequestDeadline.before(secondRequestDeadline));
		
	}
	
	private Request makeTestRequest(boolean isOverdue) {
		Request request = new Request();
		String description = "test. " + String.valueOf(Math.random());
		request.setDescription(description);
		Calendar cal = Calendar.getInstance();
		if(!isOverdue) cal.add(Calendar.HOUR, 2);
		cal.set(Calendar.MILLISECOND, 0);
		Date deadline = cal.getTime();
		request.setDeadline(deadline);
		return request;
	}
	
	private Request makeTestRequest() {
		return makeTestRequest(false);
	}
	
	

}
