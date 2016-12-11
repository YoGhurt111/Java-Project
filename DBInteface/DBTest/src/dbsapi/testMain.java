package dbsapi;
import java.lang.*;
import java.sql.*;
public class testMain {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/school?useUnicode=true&characterEncoding=gbk&autoReconnect=true&failOverReadOnly=false&useSSL=false";
        DBSAPI test1 = new DBSAPI(url, "root", "123456");
        try{
            test1.showTable("院系表D");
        }
        catch (SQLException e){
            System.out.print(e);
        }
        test1.quit();

    }
}
