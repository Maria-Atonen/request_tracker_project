package eu.agileworks.requesttracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.agileworks.requesttracker.entity.Request;


@Repository
public class RequestDAOImpl implements RequestDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Request> getRequests() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Request> theQuery = 
				currentSession.createQuery("from Request order by created",
						Request.class);
		
		List <Request> requests = theQuery.getResultList();
		
		return requests;		
		
	}

	@Override
	public void saveRequest(Request theRequest) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theRequest);
		
	}

	@Override
	public Request getRequest(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Request theRequest = currentSession.get(Request.class, theId);
		
		return theRequest;
	}

	@Override
	public void deleteRequest(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery =
				currentSession.createQuery("delete from Request where id=:requestId");
		theQuery.setParameter("requestId", theId);
		theQuery.executeUpdate();
		
	}

	@Override
	public Request getRequestByDescription(String description) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Request> theQuery = 
				currentSession.createQuery("from Request where description=:description",
						Request.class);
		theQuery.setParameter("description", description);
		
		Request theRequest = theQuery.getSingleResult();
		
		return theRequest;
	}



}
