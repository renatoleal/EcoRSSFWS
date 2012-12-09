package com.ecorssf.webservices;

import java.io.IOException;
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
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;

import util.HibernateUtil;
import DAO.SensoresDAO;
import Entities.Dados;
import Entities.Sensores;

@Path("/sensores") // Will map the resource to the URL "sensores"
public class SensoresResource {

	// Allows to insert contextual objects into the class, 
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensoresList() {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			SensoresDAO sDAO = new SensoresDAO(sf);
			List<Sensores> sList = sDAO.listSensores();
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(sList);
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
	@Path("/get/{idSensor}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSensor(@PathParam("idSensor") int id) {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			SensoresDAO sDAO = new SensoresDAO(sf);
			Sensores s = sDAO.getById(id);
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(s);
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
	@Path("/save/{id}&{minValue}&{maxValue}&{info}&{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveSensor(@PathParam("id") String id, @PathParam("minValue") String minValue,
			@PathParam("maxValue") String maxValue, @PathParam("info") String info,
			@PathParam("status") String status) {
		
			Integer minV = null;
			Integer maxV = null;
			
			if (!minValue.equals("null") && !minValue.isEmpty()) {
				minV = Integer.parseInt(minValue);
			}
			if (!maxValue.equals("null") && !maxValue.isEmpty()) {
				maxV = Integer.parseInt(maxValue);
			}
			
			String inf = null;
			System.out.println(info);
			if (!info.equals("null") && !info.isEmpty()) {
				inf = info;
			}
			
			boolean b = true;
			if (status.equals("Desativado"))
				b = false;
			

			SessionFactory sf = HibernateUtil.getSessionFactory();
			SensoresDAO sDAO = new SensoresDAO(sf);
			Sensores s = sDAO.getById(Integer.parseInt(id));
			s.setMinValue(minV);
			s.setMaxValue(maxV);
			s.setInfo(inf);
			s.setStatus(b);
			
			sDAO.save(s);
			
			return "Ok";
	}
}
