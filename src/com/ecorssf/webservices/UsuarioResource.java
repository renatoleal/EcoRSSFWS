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
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;

import util.HibernateUtil;
import DAO.UsuarioDAO;
import Entities.Usuario;

@Path("/usuario") // Will map the resource to the URL "usuario"
public class UsuarioResource {

	// Allows to insert contextual objects into the class, 
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("/getUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuarios() {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			UsuarioDAO uDAO = new UsuarioDAO(sf);
			List<Usuario> u = uDAO.listUsuarios();
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(u);
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
	@Path("/getLastId")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLastId() {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			UsuarioDAO uDAO = new UsuarioDAO(sf);
			int lastId = uDAO.getLastId() ;
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(lastId);
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
	@Path("/getLogin/{login}&{senha}")
	
	@Produces(MediaType.APPLICATION_JSON)
	public String getLogin(@PathParam("login") String login, @PathParam("senha") String senha) {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			UsuarioDAO uDAO = new UsuarioDAO(sf);
			
			
			
			int u = uDAO.getLogin(login, senha);
			
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(u);
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
	@Path("/get/byLogin/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserbyLogin(@PathParam("login") String login) {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			UsuarioDAO uDAO = new UsuarioDAO(sf);
			Usuario u = uDAO.getByLogin(login);
			
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(u);
		} finally {
			return retorno;
		}
	}
	
	@GET
	@Path("/get/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsuario(@PathParam("idUsuario") int id) {
		String retorno = null;
		try {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			UsuarioDAO uDAO = new UsuarioDAO(sf);
			Usuario u = uDAO.getById(id);
			
			ObjectMapper mapper = new ObjectMapper();
			retorno = mapper.writeValueAsString(u);
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
	@Path("/save/{id}&{nome}&{sobrenome}&{login}&{senha}&{email}&{ativo}&{admin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveUsuario(@PathParam("id") String id, @PathParam("nome") String nome,
			@PathParam("sobrenome") String sobrenome, @PathParam("login") String login,
			@PathParam("senha") String senha, @PathParam("email") String email,
			@PathParam("ativo") String ativo, @PathParam("admin") String admin) {
		
			nome = nome.replace("\"", "");
			sobrenome = sobrenome.replace("\"", "");
			
			SessionFactory sf = HibernateUtil.getSessionFactory();
			UsuarioDAO uDAO = new UsuarioDAO(sf);
			
			
			if(id.equals("novoID")){
				id = String.valueOf(uDAO.getLastId() + 1);
			}
			
			boolean ativoB = true;
			if (ativo.equals("Desativado"))
				ativoB = false;
			
			boolean adminB = true;
			if (admin.equals("false"))
				adminB = false;
			
			Usuario u = new Usuario(Integer.valueOf(id), nome, sobrenome, email, login, senha, ativoB, adminB);
			
			
			uDAO.save(u);
			
			return "Ok";
	}

}
