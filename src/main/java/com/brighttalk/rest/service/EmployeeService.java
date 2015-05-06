/*
 * Copyright 2015 (C) <your company>
 * 
 * Created on : APR-30-2015
 * Author     : Sagar Sarkar
 * File		  : EmployeeService.java
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/DATE      	DESCRIPTION OF CHANGE
 *-----------------------------------------------------------------------------
 * 0.1     | Sagar Sarkar        | Initial Create.
 *         | APR-30-2015         |
 *---------|---------------------|---------------------------------------------
 *         |                     | 
 *         |                     | 
 *---------|---------------------|---------------------------------------------
 */
package com.brighttalk.rest.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.brighttalk.rest.dao.EmployeeDao;
import com.brighttalk.rest.entity.Employee;

/**
 * @author sagar
 *
 */
@Component
@Path("/employee")
public class EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;
	
	
	
	/**
	 * Adds a new employee resource from the given json format (email should be unique)
	 * 
	 * @param employee Employee
	 * @return Response
	 */
	@POST 
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_HTML})	
	@Transactional
	public Response createEmployee(Employee employee) {
				
		employeeDao.createEmployee(employee);
			return Response.status(201).entity("Employee created successfully").build(); 		
	}
	
	
	/**
	 * Adds a new employee resource from web page (email should be unique)
	 * 
	 * @param id int
	 * @param firstName String
	 * @param lastName String
	 * @param email String
	 * @param phone String
	 * @return Response
	 */
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.TEXT_HTML})	
	@Transactional
	public Response createEmployeFromWebPage(
						@FormParam("firstName") String firstName,
						@FormParam("lastName") String lastName,
						@FormParam("email") String email,
						@FormParam("phone") String phone
						) {
		Employee employee = new Employee(firstName, lastName, email, phone);
		
		employeeDao.createEmployee(employee);
			return Response.status(201).entity("New employee created successfully").build(); 		
	}
	
	
	/**
	 * Returns all resources (employees) from the database
	 * @return List<Employee>
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Employee> getEmployees() {
		return employeeDao.retrieveEmployees();
	}
	
	/**. Retrieves an employee from DB.
	 * 
	 * @param id int
	 * @return Response
	 */
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response findById(@PathParam("id") int id) {		
		Employee employee = employeeDao.retrieveEmployee(id);
		if(employee != null) {
			return Response.status(200).entity(employee).build(); 
		} else {
			return Response.status(404).entity("The employee with the id " + id + " does not exist").build();
		}
	}
	
	
	/**
	 * Updates the attributes of employee received via JSON for the given @param id
	 * 
	 * If the employee does not exist yet in the database (verified by <strong>id</strong>) then
	 * the application throw error message. 
	 * 
	 * @param id
	 * @param employee Employee
	 * @return
	 */	
	@PUT @Path("{id}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_HTML})	
	@Transactional
	public Response updateEmployeeById(@PathParam("id") int id, Employee employee) {
		String message; 
		int status; 
		if(employeeDao.updateEmployee(employee)){
			status = 200; //OK
			message = "Employee has been updated";
		} else {
			status = 406; //Not acceptable
			message = "Update failed !  <br/> "
						+ " If you want to UPDATE please make sure you provide an existent <strong>id</strong> <br/>" ;
					
		}

		return Response.status(status).entity(message).build();		
	}
	
			
	
	/**.
	 * Deletes an employee from DB.
	 * @param id int
	 * @return Response
	 */
	@DELETE @Path("{id}")
	@Produces({MediaType.TEXT_HTML})
	@Transactional
	public Response deletePodcastById(@PathParam("id") int id) {
		if(employeeDao.deleteEmployee(id)){
			return Response.status(200).entity("Employee with the id " + id + " deleted successfully.").build();
		} else {
			return Response.status(404).entity("Employee with the id " + id + " is not present in the database").build();
		}
	}
	
	
	
	
	
	
}
