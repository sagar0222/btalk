/**
 * 
 */
package com.brighttalk.rest.service;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Invocation.Builder;

import org.junit.Assert;
import org.junit.Test;
import org.codehaus.jackson.map.ObjectMapper;

import com.brighttalk.rest.entity.Employee;

/**
 * @author sagar
 *
 */
public class EmployeeServiceTest {

	
	
	@Test
	public void testGetEmployees() throws JsonGenerationException,
			JsonMappingException, IOException {
 
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);
 
		Client client = ClientBuilder.newClient(clientConfig);
 
		WebTarget webTarget = client
				.target("http://localhost:8082/btalk/rest/employees/2");
 
		Builder request = webTarget.request(MediaType.APPLICATION_JSON);
 
		Response response = request.get();
		Assert.assertTrue(response.getStatus() == 200);
 
		Employee employee = response.readEntity(Employee.class);
 
		ObjectMapper mapper = new ObjectMapper();
		System.out
				.print("Received podcast from database *************************** "
						+ mapper.writerWithDefaultPrettyPrinter()
								.writeValueAsString(employee));
 
	}
	
	
	
}
