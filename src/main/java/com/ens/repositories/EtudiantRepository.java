package com.ens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ens.models.Etudiant;
import com.ens.models.Filiere;
import com.ens.utils.DatabaseConnector;

public class EtudiantRepository implements BaseRepository<Etudiant, Long> {

	private static final String FIND_BY_ID_QUERY = "SELECT * FROM etudiants WHERE id = ?";
    private static final String FIND_BY_LONG_NAME_QUERY ="SELECT * FROM etudiants WHERE nom=? and prenom=? or nom=? and prenom=?";
    private static final String FIND_BY_NAME_QUERY ="SELECT * FROM etudiants WHERE nom=? or  prenom=?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM etudiants";
    private static final String SAVE_QUERY = "INSERT INTO etudiants (CNE, nom, prenom, filiere, departement, telephone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE etudiants SET CNE = ?, nom = ?, prenom = ?, filiere = ?, departement = ?, telephone = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM etudiants WHERE id = ?";
    
   
    
    public EtudiantRepository() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }
    @Override
    public Optional<Etudiant> find(Long id) throws SQLException {
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
    
    public Etudiant findById(Long id) throws SQLException {
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
    
    public Etudiant findByLongName(String firstName,String lastName) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LONG_NAME_QUERY)) {
        	statement.setString(1, lastName);
        	statement.setString(2, firstName);
        	statement.setString(3, firstName);
        	statement.setString(4, lastName);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return cast(result);
                }
            }
            connection.close();
        }
        return null;
    }
    public List<Etudiant> findByName(String name) throws SQLException {
    	List<Etudiant> etudiants = new ArrayList<>();
        Connection connection=null;
        try {
        	
        	connection = DatabaseConnector.getConnection();
        	if(connection!=null) {
        		PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_QUERY);
        		statement.setString(1, name);
        		statement.setString(2, name);
                ResultSet result = statement.executeQuery() ;
                while (result.next()) {
                	etudiants.add(cast(result));
                }
                connection.close();
        	}
        }finally {
        	connection.close();
        }
        
        return etudiants;    }

    @Override
    public List<Etudiant> findAll() throws SQLException {
        List<Etudiant> etudiants = new ArrayList<>();
        Connection connection=null;
        try {
        	
        	connection = DatabaseConnector.getConnection();
        	if(connection!=null) {
        		PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
                ResultSet result = statement.executeQuery() ;
                while (result.next()) {
                	etudiants.add(cast(result));
                }
                connection.close();
        	}
        }finally {
        	connection.close();
        }
        
        return etudiants;
    }

    @Override
    public boolean save(Etudiant etudiant) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
        	
        	
        	statement.setLong(1, etudiant.getCNE());
        	statement.setString(2, etudiant.getNom());
        	statement.setString(3, etudiant.getPrenom());
        	
        	statement.setString(4, etudiant.getFiliere());
        	statement.setString(5, etudiant.getDepartement());
        	statement.setString(6, etudiant.getTelephone());
            res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    @Override
    public boolean update(Etudiant etudiant) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
        	statement.setLong(1, etudiant.getCNE());
        	statement.setString(2, etudiant.getNom());
        	statement.setString(3, etudiant.getPrenom());
        	statement.setString(4, etudiant.getFiliere());
        	statement.setString(5, etudiant.getDepartement());
        	statement.setString(6, etudiant.getTelephone());
        	statement.setLong(7, etudiant.getId());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    @Override
    public boolean delete(Etudiant etudiant) throws SQLException {
    	boolean res;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
        	statement.setLong(1, etudiant.getId());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    private Etudiant cast(ResultSet result) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(result.getLong("id"));
        etudiant.setCNE(result.getLong("CNE"));
        etudiant.setNom(result.getString("nom"));
        etudiant.setPrenom(result.getString("prenom"));
        etudiant.setFiliere(result.getString("filiere"));
        etudiant.setDepartement(result.getString("departement"));
        etudiant.setTelephone(result.getString("telephone"));
        return etudiant;
    }
}

