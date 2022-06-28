import Helpers.DBUtils;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class UserRegistration {
    private  Connection con;
    public UserRegistration() {
        try {
            con = DBUtils.getDbConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ResultSet get(){
      try{
          String select = "SELECT * FROM users";
          PreparedStatement statement = con.prepareStatement(select);
          return statement.executeQuery();
      }     catch (SQLException e){
          e.printStackTrace();
      }
      return null;
    }

    void insert(String firstname, String lastname, String regnumber){
        try{
        String insert = "INSERT INTO users (first_name, last_name ,reg_number)" + "VALUES (?,?,?)";

        PreparedStatement statement = con.prepareStatement(insert);
        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setString(3, regnumber);

        statement.executeUpdate();
        statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void update( String firstname, String lastname, String regnumber){
        try{
            String update = "UPDATE users SET first_name = ?, last_name = ?, reg_number = ? WHERE reg_number = ?";

            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, regnumber);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void delete(int id){
        String delete = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1, id);
            statement.execute();
            statement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

