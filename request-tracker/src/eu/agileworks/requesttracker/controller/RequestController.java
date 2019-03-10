package eu.agileworks.requesttracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.agileworks.requesttracker.entity.Request;
import eu.agileworks.requesttracker.service.RequestService;


@Controller
@RequestMapping("/request")
public class RequestController {
	
	@Autowired
	private RequestService requestService;
	
	@GetMapping("/list")
	public String listRequests(Model theModel) {
		
		List<Request> theRequests = requestService.getRequests();
		
		theModel.addAttribute("requests", theRequests);
		
		return "list-requests";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Request theRequest = new Request();
		theModel.addAttribute("request", theRequest);
		
		return "request-form";
	}
	
	@PostMapping ("/saveRequest")
	public String saveRequest(
			@Valid @ModelAttribute("request") Request theRequest,
			BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			return "request-form";
		}else {
			requestService.saveRequest(theRequest);
			
			return "redirect:/request/list";
		}		

	}
	
	
	@GetMapping ("/delete")
	public String deleteRequest(@RequestParam("requestId") int theId) {
		
		requestService.deleteRequest(theId);
		
		return "redirect:/request/list";
	}

}
