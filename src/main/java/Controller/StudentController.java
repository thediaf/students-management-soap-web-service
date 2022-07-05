package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import Entity.Student;
import Model.StudentModel;

@WebService
public class StudentController 
{
    private StudentModel studentModel;

    public StudentController()
    {
        this.studentModel = new StudentModel();
    }

    @WebMethod
    public boolean insert(@WebParam(name = "lastname")String lastname, @WebParam(name = "firstname")String firstname, 
                            @WebParam(name = "code")String code, @WebParam(name = "classroom")String classroom
                        ) 
    {
        Student student = new Student(lastname, firstname, code, classroom);

        return studentModel.insert(student);
    }
    
    @WebMethod
    public List<Student> getStudents() throws SQLException 
    {
        ResultSet result =  studentModel.select();
        List<Student> students = new ArrayList<Student>();
        while (result.next())
        {
            int id = result.getInt("id");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String code = result.getString("code");
            String classroom = result.getString("classroom");
            
            students.add(new Student(id, firstname, lastname, code, classroom));
        }

        return students;
    }

    @WebMethod
    public Student getStudent(@WebParam(name = "id")int id) throws SQLException 
    {
        return studentModel.getStudent(id);
    }

    @WebMethod
    public int update(@WebParam(name = "id")int id, @WebParam(name = "attribute")String attribute, 
                        @WebParam(name = "value")String value
                    )  
    {
        String query = ""; 
        if (attribute == "nom")
            query = "update student set lastname = ? where id = ?";
        else if(attribute == "prenom")
            query = "update student set firstname = ? where id = ?";  
        else if(attribute == "code")
            query = "update student set code = ? where id = ?";
        else if(attribute == "classe")
            query = "update student set classroom = ? where id = ?";     
        else
            query = "";
   
        return studentModel.update(id, value, query);
    }

    @WebMethod
    public boolean delete(@WebParam(name = "id")int id)  
    {
        return studentModel.delete(id);
    }
}
