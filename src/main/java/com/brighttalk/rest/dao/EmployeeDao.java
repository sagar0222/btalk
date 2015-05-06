/*
 * Copyright 2015 (C) <your company>
 * 
 * Created on : APR-30-2015
 * Author     : Sagar Sarkar
 * File		  : EmployeeDao.java
 *
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
package com.brighttalk.rest.dao;

import java.util.List;

import com.brighttalk.rest.entity.Employee;

public interface EmployeeDao {

	public void createEmployee (Employee employee);
	
	public Employee retrieveEmployee (int id);
	
	public List<Employee> retrieveEmployees ();
	
	public boolean updateEmployee (Employee emp);
	
	public boolean deleteEmployee (int id);
	
}
