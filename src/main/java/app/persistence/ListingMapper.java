package app.persistence;

import app.entities.Listing;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListingMapper {


    public static List<Listing> getListingHeadlinesByUserID(int userId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "SELECT headline FROM listing WHERE owner = ?";
        List<Listing> listings = new ArrayList<>();
        try(Connection con = connectionPool.getConnection()){
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Listing l = new Listing( resultSet.getString("headline"));
                listings.add(l);
            }

            return listings;
        }
        catch(SQLException ex){
            throw new DatabaseException(ex.getMessage(), "There was a problem with the system");
        }

    }
}
