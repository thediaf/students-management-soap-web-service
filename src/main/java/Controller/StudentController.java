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

        // System.out.println(lastname);
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
    public int update(@WebParam(name = "id")int id, @WebParam(name = "lastname")String lastname, 
                        @WebParam(name = "firstname")String firstname
                    ) throws SQLException  
    {
        Student student = new Student(id, lastname, firstname);
        // System.out.println(lastname);
        // System.out.println(student.toString());
        
        return studentModel.update(student);
    }

    @WebMethod
    public boolean delete(@WebParam(name = "id")int id)  
    {
        return studentModel.delete(id);
    }
}
