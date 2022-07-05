package Model;

import java.sql.*;

import Entity.Student;

/**
 * StudentModel
 */
public class StudentModel 
{
    private final String url = "jdbc:mysql://localhost:3306/java_exercice";
    private final String user = "root";
    private final String password = "root";

    Connection connect; 


    public StudentModel()
    {
        try {
            connect = this.connexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection connexion() throws SQLException 
    {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean insert(Student student) 
    {
        boolean result = true;

        String query = "INSERT INTO student(lastname, firstname, code, classroom) "
                + "VALUES(?, ?, ?, ?)";

        try 
        {

            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setString(1, student.getLastname());
            preparedStmt.setString(2, student.getFirstname());
            preparedStmt.setString(3, student.getCode());
            preparedStmt.setString(4, student.getClassroom());

            result = preparedStmt.execute();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public ResultSet select() 
    {
        ResultSet result = null;

        String query = "SELECT * FROM student";

        try
        {

            Statement statement = connect.createStatement();
            
            result = statement.executeQuery(query);

        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }    

        return result;
        
    }

    public Student getStudent(int id) throws SQLException 
    {
        String query = "select * from student where id = ?";
        Student student = new Student();

        PreparedStatement preparedStmt = connect.prepareStatement(query);
        preparedStmt.setInt(1, id);

        ResultSet response = preparedStmt.executeQuery();  
        if (response.next()) {
            student.setId(response.getInt("id"));
            student.setLastname(response.getString("lastname"));
            student.setFirstname(response.getString("firstname"));
        } 
            
        return student;
    }

    public boolean delete(int id) 
    {
        boolean response = true;
        String query = "delete from student where id = ?";

        try
        {
            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setInt(1, id);

            response = preparedStmt.execute();   
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

        return response;
    }

    public int update(Student student) throws SQLException 
    {
        String query = "update student set lastname = ?, firstname = ? where id = ?";

        PreparedStatement preparedStmt = connect.prepareStatement(query);
        
        preparedStmt.setString(1, student.getLastname());
        preparedStmt.setString(2, student.getFirstname());
        preparedStmt.setInt(3, student.getId());
        // preparedStmt.setString(1, lastname);
        // preparedStmt.setString(2, firstname);
        // preparedStmt.setInt(3, id);

        return preparedStmt.executeUpdate();

    }
}