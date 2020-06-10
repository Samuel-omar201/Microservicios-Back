package com.bytesw.app.usuarios.bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesw.app.usuarios.bs.dao.UsuarioDao;
import com.bytesw.app.usuarios.eis.bo.User;

@Service
public class UserServiceImpl implements IUserService {

	private Boolean Response = false ;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Transactional(readOnly= true)
	public List<User> findAll() {
		return (List<User>) usuarioDao.findAll();
	}

	@Transactional(readOnly= true)
	public User findById(Long Id) {
		return usuarioDao.findById(Id).orElse(null);
	}

	public Boolean save(User user) {
		User userObj = null;
		String param = "";
		Boolean responseIt = false;
		param = user.getIdentificacion();
		
		List<User> listaDB = (List<User>) usuarioDao.findAll();
		for(User userIt : listaDB ) {
			if( param == userIt.getIdentificacion()) {
				System.out.println("Result True");
				responseIt = true;
				break;
			}else {
				System.out.println("Result False");
				responseIt = false;
				break;
			}
		}
		
		if(  responseIt == false  ) {
			userObj = usuarioDao.save(user);
			if( userObj != null ) {
				this.Response = true;
			}else {
				this.Response = false;
			}
		}else {
			this.Response = false;
		}
		
		
		return this.Response;
	}

	@Override
	public void delet(Long Id) {
		usuarioDao.deleteById(Id);
	}

	@Override
	public User editUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	


}