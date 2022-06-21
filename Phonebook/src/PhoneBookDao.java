import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class PhoneBookDao {

    Connection con = null;

    public void connect(){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/kimuldan", "kimuldan", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Contact getContact(String name){
        try {
            String query = "SELECT * FROM phonebook WHERE name= '" + name + "'";
            Contact c = new Contact();
            c.name = name;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String contactName = rs.getString(1);
            c.setName(contactName);
            c.setNumber(rs.getString(2));
            return c;
        } catch (Exception e) {
            System.out.println(e + " errorrrr");
        }
        return null;
    }


    public void view(){
        try {
            String query = "SELECT * FROM phonebook";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e + " errorrrr");
        }
    }

    public void addContact(Contact c){
        String query = "INSERT INTO phonebook VALUES (?,?)";
        PreparedStatement pst;
        try{
            pst = con.prepareStatement(query);
            pst.setString(1,c.getName());
            pst.setString(2,c.getNumber());
            pst.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void removeStudent(String name) {
        String query = "DELETE FROM phonebook WHERE name='" + name + "'";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}

/*
    public class JdbcDaoDemo {
        public static void main(String[] args) {
            StudentDAO dao = new StudentDAO();
            dao.connect();
            Student s1 = dao.getStudent(5);
            System.out.println(s1.sname);

            Student s2 = new Student();
            s2.rollno = 15;
            s2.sname = "Arc";
            dao.connect();
            dao.addStudent(s2);

//        dao.removeStudent(s2);
        }
    }

    class StudentDAO {
        Connection con = null;

        public void connect(){
            try {
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "kimuldan", "");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public Student getStudent(int rollno){
            try {
                String query = "SELECT username FROM student WHERE userid=" + rollno;
                Student s = new Student();
                s.rollno = rollno;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                rs.next();
                String name = rs.getString(1);
                s.sname = name;
                return s;
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }

        public void addStudent(Student s){
            String query = "INSERT INTO student VALUES (?,?)";
            PreparedStatement pst;
            try{
                pst = con.prepareStatement(query);
                pst.setInt(1,s.rollno);
                pst.setString(2,s.sname);
                pst.executeUpdate();
            } catch (Exception e){
                System.out.println(e);
            }
        }

        public void removeStudent(Student s2) {
            String query = "DELETE FROM student WHERE userid=" + s2.rollno;
            PreparedStatement pst;
            try {
                pst = con.prepareStatement(query);
                pst.executeUpdate();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    class Student {
        int rollno;
        String sname;
    }

 */