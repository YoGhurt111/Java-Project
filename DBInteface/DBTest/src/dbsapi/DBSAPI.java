package dbsapi;

import java.lang.*;
import java.sql.*;

public class DBSAPI {
    private Connection conn;
    private Statement sql;
    private ResultSet rs;

    //连接数据库
     DBSAPI(String url, String user, String pw){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("连接中...");
            conn = DriverManager.getConnection(url, user, pw);
            sql = conn.createStatement();
        }
        catch(Exception e){
            System.out.println(e);
            return;
        }
        System.out.println("已连接到数据库！");
    }

    //查询
    public void queryToDB(String tableName, String[] fields) throws SQLException {
        String fieldStr = stitch(fields);
        rs = sql.executeQuery("SELECT " + fieldStr + "FROM" + tableName);
    }

    //插入
    public void insertToDB(String tableName, String[] fields, String[] value) throws SQLException {
        String fieldStr = stitch(fields);
        String valueStr = stitch(value);
        String temp = "INSERT INTO ";
        temp += tableName;
        temp += "(";
        temp += fieldStr;
        temp += ")";
        temp += "VALUES";
        temp += valueStr;
        temp += ")";
        int num = sql.executeUpdate(temp);//返回受影响的行数
        System.out.println(num + "行数据已被插入");

    }

    //更新
    public void update(){

    }

    //创建表
    public void createTable(String tableName, String[] fields) throws SQLException {
        String fieldStr = stitch(fields);
        String temp = "CREATE TABLE ";
        temp += tableName;
        temp += "(";
        temp += fieldStr;
        temp += ")";
        sql.executeUpdate(temp);
    }

    //删除
    public void delete(){

    }

    //显示信息
    public void show(ResultSet tempRs) throws SQLException {
        int rsLen = rs.getMetaData().getColumnCount();
        while(rs.next()) {
            for (int i = 1; i <= rsLen; i++) {
                System.out.print(tempRs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    //显示某表字段信息
    public void showTable(String tableName)throws SQLException{
        rs = sql.executeQuery("SELECT * FROM " + tableName);
        show(rs);
    }

    //将String数组进行拼接,返回一个String值
    public String stitch(String[] str){
        int i = 0;
        String temp = "";
        while(i < str.length){
            temp += str[i];
            if(i < str.length - 1) {
                temp += ",";
            }
            i++;
        }
        return temp;
    }

    //断开与mySql的连接
    public void quit(){
        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("已退出数据库！");
    }
}
