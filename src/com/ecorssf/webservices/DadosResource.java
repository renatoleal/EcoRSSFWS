package com.ecorssf.webservices;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;

import util.HibernateUtil;
import DAO.DadosDAO;
import Entities.Dados;
import Entities.Usuario;

@Path("/dados")
// Will map the resource to the URL "dados"
public class DadosResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path("/last/{idSensor}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLast(@PathParam("idSensor") int id) {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			DadosDAO dDAO = new DadosDAO(sf);
			Dados d = dDAO.getLastDataFromSensor(id);
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(d);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return retorno;
		}
	}

	@GET
	@Path("/range/{idSensor}&{minValue}&{maxValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLast(@PathParam("idSensor") int id,
			@PathParam("minValue") String minValue,
			@PathParam("maxValue") String maxValue) {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			DadosDAO dDAO = new DadosDAO(sf);
			
			Long startDate = Long.valueOf(minValue);
			Long endDate = Long.valueOf(maxValue);
			
			List<Dados> d = dDAO.getRangeFromSensor(id, startDate, endDate);
			
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(d);

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return retorno;
		}
	}
}
