package Entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student implements Serializable
{
    private int id = 0;
    private String lastname;
    private String firstname;
    private String code;
    private String classroom;

    public Student()
    {
        super();
    }

    public Student(int id, String lastname, String firstname, String code, String classroom)
    {
        super();
        this.setId(id);
        this.setLastname(lastname);
        this.setFirstname(firstname);
        this.setCode(code);
        this.setClassroom(classroom);
    }
    
    public Student(String lastname, String firstname, String code, String classroom)
    {
        super();
        this.setLastname(lastname);
        this.setFirstname(firstname);
        this.setCode(code);
        this.setClassroom(classroom);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}
