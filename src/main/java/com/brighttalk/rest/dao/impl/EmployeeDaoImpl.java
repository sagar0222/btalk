/*
 * Copyright 2015 (C) <your company>
 * 
 * Created on : APR-30-2015
 * Author     : Sagar Sarkar
 * File		  : EmployeeDaoImpl.java
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
package com.brighttalk.rest.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.brighttalk.rest.dao.EmployeeDao;
import com.brighttalk.rest.entity.Employee;

/**
 * @author sagar
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {
	
	/** Datasource instance variable **/
	private DataSource dataSource;
	
	/** Spring JdbcTemplate instance **/
	private JdbcTemplate jdbcTemplate;
 
	/**.
	 * Setter method for datasource
	 * @param dataSource
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	/* (non-Javadoc)
	 * @see com.brighttalk.rest.dao.EmployeeDao#createEmployee(com.brighttalk.rest.entity.Employee)
	 */
	/**.
	 * Inserts an employee record into DB
	 * @param employee Employee
	 */
	@Override
	public void createEmployee(Employee employee) {

		String sql = "INSERT INTO EMPLOYEE "
				+ "(ID, FIRSTNAME, LASTNAME, EMAIL, PHONE) "
				+ "VALUES (EMPID_SEQ.NEXTVAL, ?, ?, ?, ?)";

		jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { employee.getId(), employee.getFirstName(),
						employee.getLastName(), employee.getEmail(),
						employee.getPhone() });

	}

	/* (non-Javadoc)
	 * @see com.brighttalk.rest.dao.EmployeeDao#retrieveEmployee(int)
	 */
	/**.
	 * Retrieves an employee record from DB matching the ID
	 * @param id int
	 * @return Employee
	 */
	@Override
	public Employee retrieveEmployee(int id) {
		
		String sql = "SELECT * FROM EMPLOYEE WHERE ID=?";		 
		
		Employee employees =  new JdbcTemplate(dataSource).queryForObject(
				sql,
				new Object[] {id},
				new EmployeeRowMapper());		
		
		return employees;
	}

	/* (non-Javadoc)
	 * @see com.brighttalk.rest.dao.EmployeeDao#retrieveEmployees()
	 */
	/**.
	 * Retrieves all employee records from DB
	 * @return List<Employee>
	 */
	@Override
	public List<Employee> retrieveEmployees() {
		
		String sql = "SELECT * FROM EMPLOYEE";
	 
		@SuppressWarnings("unchecked")
		List<Employee> employees = (List<Employee>) new JdbcTemplate(dataSource).queryForObject(
				sql, new EmployeeRowMapper());
		
		
		return employees;
	}

	/* (non-Javadoc)
	 * @see com.brighttalk.rest.dao.EmployeeDao#updateEmployee(com.brighttalk.rest.entity.Employee)
	 */
	/**.
	 * Updates employee records matching ID
	 * @param employee Employee
	 * @return boolean
	 */
	@Override
	public boolean updateEmployee(Employee employee) {

		String sql = "UPDATE EMPLOYEE "
				+ "SET FIRSTNAME =?, LASTNAME =?, EMAIL=?, PHONE=?) "
				+ "WHERE ID=?";

		jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(
				sql,
				new Object[] { employee.getFirstName(), employee.getLastName(),
						employee.getEmail(), employee.getPhone(),
						employee.getId() });

		return true;
	}

	/* (non-Javadoc)
	 * @see com.brighttalk.rest.dao.EmployeeDao#deleteEmployee(int)
	 */
	/**.
	 * Deletes employee record from DB matching the DB
	 * @param id int
	 * @return boolean
	 */
	@Override
	public boolean deleteEmployee(int id) {
		
		String sql = "DELETE FROM EMPLOYEE WHERE ID=?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		jdbcTemplate.execute(sql);
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.brighttalk.rest.dao.EmployeeDao#isEmployeeExist(com.brighttalk.rest.entity.Employee)
	 */
	/**.
	 * Checks if an employee record matching with the ID already exist in DB
	 * @param employee Employee
	 * @return boolean
	 */
	@Override
	public boolean isEmployeeExist(Employee employee) {
		String sql = "SELECT count(*) FROM EMPLOYEE WHERE ID=?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		int employeeCount = jdbcTemplate.queryForInt(sql);
		if(employeeCount ==0) 
			return true;
		
		return false;
	}

}
