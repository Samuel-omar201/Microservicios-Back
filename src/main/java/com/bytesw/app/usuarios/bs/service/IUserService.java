package com.bytesw.app.usuarios.bs.service;

import java.util.List;

import com.bytesw.app.usuarios.eis.bo.User;

public interface IUserService {
	//crud operations
	public List<User> findAll();
	public User findById(Long Id);
	public Boolean save(User user);
	public void delet(Long Id);
	public User editUser(User user);
}
