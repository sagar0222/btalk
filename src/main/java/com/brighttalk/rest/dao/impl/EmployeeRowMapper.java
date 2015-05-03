/*
 * Copyright 2015 (C) <your company>
 * 
 * Created on : APR-30-2015
 * Author     : Sagar Sarkar
 * File		  : EmployeeRowMapper.java
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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.brighttalk.rest.entity.Employee;

/**
 * @author sagar
 *
 */
public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee emp = new Employee();
		
		emp.setId(rs.getInt("ID"));
		emp.setFirstName(rs.getString("FORSTNAME"));
		emp.setLastName(rs.getString("LASTNAME"));
		emp.setEmail(rs.getString("EMAIL"));
		emp.setPhone(rs.getString("PHONE"));
		
		
		return emp;
	}

}
