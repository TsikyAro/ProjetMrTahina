package connexionn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Connexion {
    String user;
        String password;
        String req;
        Connection con;
        public Connection getCon() {
            return con;
        }
        public void setCon(Connection con) {
            this.con = con;
        }
        public String getReq() {
            return req;
        }
        public void setReq(String req) {
            this.req = req;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = password;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public Connexion() {
            setUser(user);
            setPassword(password);
            setReq(req);
        }
     public Connection login() throws ClassNotFoundException{
        try
        {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stock", "stock", "stock");
        }
        catch(SQLException e){ 
            e.printStackTrace();
        }
        return con;
    }
    public Statement result() throws Exception{
        Connection conect=login();
        Statement stmt = conect.createStatement();
        return stmt;
    } 
    public ResultSet request(String requete)throws Exception{
        Statement stat=result();
        ResultSet res=stat.executeQuery(requete);
        getCon().setAutoCommit(false);
        return res;
    
    }
}
