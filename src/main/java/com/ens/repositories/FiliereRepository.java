package com.ens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ens.models.Filiere;
import com.ens.utils.DatabaseConnector;

public class FiliereRepository implements BaseRepository<Filiere, Long> {

	private static final String FIND_BY_ID_QUERY = "SELECT * FROM filieres WHERE id = ?";
	private static final String FIND_BY_DEPARTEMENT_ID_QUERY = "SELECT * FROM filieres WHERE departement_id = ?";
    private static final String FIND_BY_LONG_NAME_QUERY ="SELECT * FROM filieres WHERE nom=? ";
    private static final String FIND_ALL_QUERY = "SELECT * FROM filieres";
    private static final String SAVE_QUERY = "INSERT INTO filieres ( nom , departement_id) VALUES ( ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE filieres SET  nom = ? ,departement_id= ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM filieres WHERE id = ?";

    @Override
    public Optional<Filiere> find(Long id) throws SQLException {
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
    
    public Filiere findById(Long id) throws SQLException {
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
    public List<Filiere>findByDepartementId(Long departement_id) throws SQLException {
    	List<Filiere> filieres = new ArrayList<>();
        Connection connection=null;
    	try {
         	
         	connection = DatabaseConnector.getConnection();
         	if(connection!=null) {
         		PreparedStatement statement = connection.prepareStatement(FIND_BY_DEPARTEMENT_ID_QUERY);
         		statement.setLong(1, departement_id);
                 ResultSet result = statement.executeQuery() ;
                 while (result.next()) {
                 	filieres.add(cast(result));
                 }
                 connection.close();
         	}
         }finally {
         	connection.close();
         }
        return filieres;
    }
    
    public List<Filiere>findByLongName(String name) throws SQLException {
    	List<Filiere> filieres = new ArrayList<>();
        Connection connection=null;
    	try {
         	
         	connection = DatabaseConnector.getConnection();
         	if(connection!=null) {
         		PreparedStatement statement = connection.prepareStatement(FIND_BY_LONG_NAME_QUERY);
         		statement.setString(1, name);
                 ResultSet result = statement.executeQuery() ;
                 while (result.next()) {
                 	filieres.add(cast(result));
                 }
                 connection.close();
         	}
         }finally {
         	connection.close();
         }
        return filieres;
    }

    @Override
    public List<Filiere> findAll() throws SQLException {
        List<Filiere> filieres = new ArrayList<>();
        Connection connection=null;
        try {
        	connection = DatabaseConnector.getConnection();
        	if(connection!=null) {
        		PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
                ResultSet result = statement.executeQuery() ;
                while (result.next()) {
                	filieres.add(cast(result));
                }
                connection.close();
        	}
        }finally {
        	connection.close();
        }
        
        return filieres;
    }

    @Override
    public boolean save(Filiere filiere) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
        	statement.setString(1, filiere.getNom());
        	statement.setLong(2, filiere.getDepartementId());
            res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    @Override
    public boolean update(Filiere filiere) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
        	statement.setLong(3, filiere.getId());
        	statement.setString(1, filiere.getNom());
        	statement.setLong(2, filiere.getDepartementId());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    @Override
    public boolean delete(Filiere filiere) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
        	statement.setLong(1, filiere.getId());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    private Filiere cast(ResultSet result) throws SQLException {
    	Filiere filiere = new Filiere();
    	filiere.setId(result.getLong("id"));
    	filiere.setNom(result.getString("nom"));
    	filiere.setDepartementId(result.getLong("departement_id"));

        return filiere;
    }
}

