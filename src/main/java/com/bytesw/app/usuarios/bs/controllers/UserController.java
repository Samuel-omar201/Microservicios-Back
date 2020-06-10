package com.bytesw.app.usuarios.bs.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytesw.app.usuarios.bs.service.IUserService;
import com.bytesw.app.usuarios.eis.bo.User;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class UserController {
	
	@Autowired
	private IUserService usuarioService;
	private Map<String, Object> res = new HashMap();
	
	@GetMapping("/api/v1/user/list")
	public ResponseEntity<List<User>> listar(){
		List<User> usersList =  usuarioService.findAll();
		if( usersList.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/user/list/{Id}")
	public ResponseEntity<?> detalle(@PathVariable Long Id) {
		User user = null;		
		try {
			user = usuarioService.findById(Id);
		} catch (DataAccessException e) {
			this.res.put("message", "has error" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if ( user == null) {
			res.put("message", "el producto con el " + Id.toString() + " no existe");
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK); 
	}
	
	@PostMapping("/api/v1/user/add")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		Boolean responseCtrl = false;
		String messageResponse = "";
		try {
			responseCtrl = usuarioService.save(user);
		} catch (DataAccessException e) {
			this.res.put("body", user);
			this.res.put("message", "has error" + e.getMostSpecificCause());
			return new ResponseEntity<User>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		res.put("message", "usuario guardado exitosamente !");
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}
	
	@PutMapping("/api/v1/user/edit/{Id}")
	public ResponseEntity<?> editUser(@RequestBody User user, @PathVariable Long Id ) {
		User userDB =  usuarioService.findById(Id);
		Boolean responseEdited = false;
		
		if ( userDB  == null) {
			res.put("message", "el producto con el " + Id.toString() + " no existe");
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.NOT_FOUND);
		}
		
		try {
			userDB.setNombre( user.getNombre());
			userDB.setApellido(  user.getApellido());
			userDB.setIdentificacion( user.getIdentificacion());
			userDB.setFechaNacimiento( user.getFechaNacimiento());
			
			responseEdited  = usuarioService.save( userDB );
		}catch(DataAccessException e) {	
			this.res.put("message", "has error" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		res.put("message", "usuario con id "+ Id.toString() + " editado !! " );
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/api/v1/user/delete/{Id}")
	public ResponseEntity<Map<String, Object>> removeUserById(@PathVariable Long Id) {
		try {
			usuarioService.delet(Id);
		} catch (DataAccessException e) {
			this.res.put("message", "has error" + e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		this.res.put("message", "Usuario con id "+ Id + " eliminado !!" );
		return new ResponseEntity<Map<String, Object>>(this.res, HttpStatus.OK);
	}
	
	
}