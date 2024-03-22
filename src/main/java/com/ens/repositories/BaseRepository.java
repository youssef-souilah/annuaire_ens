package com.ens.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<MODEL,ID> {
	Optional<MODEL> find(ID id)throws SQLException;
	List<MODEL> findAll() throws SQLException;
	boolean save(MODEL model) throws SQLException;
	boolean update(MODEL model) throws SQLException;
	boolean delete(MODEL model) throws SQLException;
}
