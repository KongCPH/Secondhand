package app.persistence;

import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User login (String name, String password, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1,name);
                ResultSet rs = ps.executeQuery();

                if (rs.next())
                {
                    if(password.equals(rs.getString("password"))) {
                        User user = new User(name, password, rs.getInt("user_id"));
                        return user;
                    }
                    else{
                        throw new DatabaseException("The password is incorrect");
                    }
                }
                else{
                    throw new DatabaseException("Username not found");
                }
            }
        }
        catch (SQLException ex)
        {
           // System.out.println(ex.getMessage());
            throw new DatabaseException("There was a problem with the system");
        }
    }
}
