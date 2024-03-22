package com.ens.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.ens.models.Etudiant;
import com.ens.utils.DatabaseConnector;

public class EtudiantRepository implements BaseRepository<Etudiant, Integer> {

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM etudiants WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM etudiants";
    private static final String SAVE_QUERY = "INSERT INTO etudiants (CNE, nom, prenom, filiere, departement, telephone) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE etudiants SET CNE = ?, nom = ?, prenom = ?, filiere = ?, departement = ?, telephone = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM etudiants WHERE id = ?";

    @Override
    public Optional<Etudiant> find(Integer id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
        	statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(cast(result));
                }
            }
            connection.close();
        }
        return Optional.empty();
    }

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
        	statement.setInt(1, etudiant.getCNE());
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
        	statement.setInt(1, etudiant.getCNE());
        	statement.setString(2, etudiant.getNom());
        	statement.setString(3, etudiant.getPrenom());
        	statement.setString(4, etudiant.getFiliere());
        	statement.setString(5, etudiant.getDepartement());
        	statement.setString(6, etudiant.getTelephone());
        	statement.setInt(7, etudiant.getId());
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
        	statement.setInt(1, etudiant.getId());
        	res= statement.executeUpdate() > 0;
            connection.close();
            return res;
        }
    }

    private Etudiant cast(ResultSet result) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(result.getInt("id"));
        etudiant.setCNE(result.getInt("CNE"));
        etudiant.setNom(result.getString("nom"));
        etudiant.setPrenom(result.getString("prenom"));
        etudiant.setFiliere(result.getString("filiere"));
        etudiant.setDepartement(result.getString("departement"));
        etudiant.setTelephone(result.getString("telephone"));
        return etudiant;
    }
}
