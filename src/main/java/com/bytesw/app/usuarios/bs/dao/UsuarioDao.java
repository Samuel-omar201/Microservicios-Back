package com.bytesw.app.usuarios.bs.dao;

import org.springframework.data.repository.CrudRepository;

import com.bytesw.app.usuarios.eis.bo.User;

public interface UsuarioDao extends CrudRepository<User, Long> {

}

