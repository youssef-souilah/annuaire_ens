package com.ens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ens.models.Departement;
import com.ens.utils.DatabaseConnector;

public class DepartementRepository implements BaseRepository<Departement, Long> {

	private static final String FIND_BY_ID_QUERY = "SELECT * FROM departements WHERE id = ?";
    private static final String FIND_BY_LONG_NAME_QUERY ="SELECT * FROM departements WHERE nom=? ";
    private static final String FIND_ALL_QUERY = "SELECT * FROM departements";
    private static final String SAVE_QUERY = "INSERT INTO departements ( nom) VALUES ( ?)";
    private static final String UPDATE_QUERY = "UPDATE departements SET  nom = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM departements WHERE id = ?";

    @Override
    public Optional<Departement> find(Long id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
        	statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(cast(result));
                }
            }
            connection.close();
        }
        return Optional.empty();
    }
    
    public Departement findById(Long id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
        	statement.setLong(1,(Long) id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                	 return cast(result);
                }
            }
            connection.close();
        }
        return null;
    }
    
    public List<Departement> findByLongName(String name) throws SQLException {
    	 List<Departement> departements = new ArrayList<>();
    	 Connection connection=null;
    	 try {
         	
         	connection = DatabaseConnector.getConnection();
         	if(connection!=null) {
         		PreparedStatement statement = connection.prepareStatement(FIND_BY_LONG_NAME_QUERY);
         		statement.setString(1, name);
                 ResultSet result = statement.executeQuery() ;
                 while (result.next()) {
                 	departements.add(cast(result));
                 }
                 connection.close();
         	}
         }finally {
         	connection.close();
         }
		return departements;
    }

    @Override
    public List<Departement> findAll() throws SQLException {
        List<Departement> departements = new ArrayList<>();
        Connection connection=null;
        try {
        	
        	connection = DatabaseConnector.getConnection();
        	if(connection!=null) {
        		PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
                ResultSet result = statement.executeQuery() ;
                while (result.next()) {
                	departements.add(cast(result));
                }
                connection.close();
        	}
        }finally {
        	connection.close();
        }
        
        return departements;
    }

    @Override
    public boolean save(Departement departement) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
        	statement.setString(1, departement.getNom());
            res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    @Override
    public boolean update(Departement departement) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
        	statement.setLong(2, departement.getId());
        	statement.setString(1, departement.getNom());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    @Override
    public boolean delete(Departement departement) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
        	statement.setLong(1, departement.getId());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    private Departement cast(ResultSet result) throws SQLException {
    	Departement departement = new Departement();
    	departement.setId(result.getLong("id"));
    	departement.setNom(result.getString("nom"));
        return departement;
    }
}

