package sample.database;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sample.model.Food;
import sample.model.User;

import javax.xml.transform.Result;
import java.sql.*;

/*
table users :
username ||  password


*/
public class DB {


    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javatest","root","root");
        }catch(Exception e){System.out.println(e);}
        return con;
    }


    public static Boolean validateLogin(User user){
        //start the process as zero for failure..
        Boolean status = false;

        String ps = "Select * from users where username =? and password=?";
        Connection con=DB.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(ps);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                status = true;
            }


        }catch (Exception exc){
            System.out.println("error at stmt : " +exc);
        }


        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    } // end of ValidateLogin


    public static Boolean insertUserInDb(User user){
        Boolean status = false;

        String ps = "insert into users (username,password) values(?,?)";


        Connection con=DB.getConnection();

        try {
            PreparedStatement st = con.prepareStatement(ps);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());

            st.executeUpdate();

            status = true;


        }catch (Exception exc){
            System.out.println("error at stmt : " +exc);
        }


        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;

    } //end of insert user in db


    public static Boolean insertFoodInDb(Food food){
        Boolean status = false;


        String ps = "insert into foods (userid,food,grams) values(?,?,?)";

        Connection con=DB.getConnection();

        try {
            PreparedStatement st = con.prepareStatement(ps);

            st.setInt(1, food.getId());
            st.setString(2, food.getName());
            st.setDouble(3, food.getGrams());


            st.executeUpdate();

            status = true;


        }catch (Exception exc){
            System.out.println("error at stmt : " +exc);
        }


        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    } //end of insert food in db


    public static int getUserId(User user){
        int id = 0;

        String ps = "Select * from users where username =? and password=?";
        Connection con=DB.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(ps);

            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());

            ResultSet rs = st.executeQuery();
            if (rs.next()){
               id = rs.getInt("id");
            }


        }catch (Exception exc){
            System.out.println("error at stmt : " +exc);
        }


        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    } //end of getUserId


    public static ResultSet getFoodFromTable(int id){
        ResultSet rs = null;

        String ps = "select * from foods where userid = ?";

        Connection con=DB.getConnection();
        try {
            PreparedStatement st = con.prepareStatement(ps);

            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()){
                return rs;
            }

        }catch (Exception exc){
            System.out.println("problem at db: getFoodFromTable" +exc);;
        }



    return rs;
    } //end of getFood




}

