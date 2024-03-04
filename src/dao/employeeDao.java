package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.customer;
import entity.room;
import entity.employee;
import entity.flow;
import util.JDBCUtils;
public class employeeDao {

    //员工管理
    public List<employee> queryList3(String status,String employeeType){

        List<employee> list = new ArrayList<employee>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from employee where 1=1 ");
            if(status != null && !"".equals(status)){
                sb.append("and work_time not like ? and etype like ?");
                params.add(status);
                params.add(employeeType);
            }
            sb.append("order by id desc");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                employee person = new employee();
                person.setEid(rs.getString("eid"));
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setRid(rs.getString("rid"));
                person.setWorktime(rs.getString("work_time"));
                person.setEtype((rs.getString("etype")));
                list.add(person);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return list;


    }

    //按工号找员工
    public employee getByEid(String eid){

        List<employee> list = new ArrayList<employee>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        employee person = new employee();
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from employee where 1=1 ");
            if(eid != null && !"".equals(eid)){
                sb.append("and eid like ?");
                params.add(eid);
            }
            sb.append("order by id desc");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();

            while(rs.next()){

                person.setEid(rs.getString("eid"));
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setRid(rs.getString("rid"));
                person.setWorktime(rs.getString("work_time"));
                person.setEtype((rs.getString("etype")));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return person;


    }

    /**
     * 保存员工信息
     * @param person
     * @return
     */
    public boolean addemployee(employee person){

        Connection con = null;
        String sql = "insert into employee(eid,id,name,etype,rid,work_time) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, person.getEid());
            pstmt.setString(2, person.getId());
            pstmt.setString(3, person.getName());
            pstmt.setString(5,person.getRid());
            pstmt.setString(6, person.getWorktime());
            pstmt.setString(4, person.getEtype());
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }
    //删除员工
    public boolean dele(String eid){

        Connection con = null;
        String sql = "delete from employee where eid=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, eid);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }
    /**
     * @return
     */
    public boolean owner(){

        Connection con = null;
        String sql = "insert into employee(eid,id,name,etype,rid,work_time) values('202100300342','441402200309020012','许悦','董事长',null,'休息')";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            employee boss=getByEid("202100300342");
            if (boss!=null)return true;
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }
    //签到
    public boolean signin(String eid){
        Connection con = null;
        String sql = "update employee set work_time =? where eid =?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, eid);
            Date date = new Date();
            String inTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            pstmt.setString(1, inTime);

            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }
    //签离
    public boolean signout(String eid){
        Connection con = null;
        String sql = "update employee set work_time =? where eid =?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, eid);
            pstmt.setString(1, "休息");
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return false;

    }
    //退房之后的清理
    public employee clean1(String rid){
        Connection con = null;
        String sql = "select * from employee where rid like '空' and etype ='清洁工' limit 1";
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        employee person=new employee();
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String name="";
            while (rs.next()){
                person.setName(rs.getString("name"));
                name=rs.getString("name");
            }
            String sql2="update employee set rid= ? where name = ?";
            pstmt=con.prepareStatement(sql2);
            pstmt.setString(1,rid);
            pstmt.setString(2,name);
            pstmt.executeUpdate();
        } catch (SQLException  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }
        return person;

    }
    //日常保洁
    public void clean2(String rid,String eid){
        Connection con = null;
        String sql = "update employee set rid = ? where eid like ?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            con = JDBCUtils.getConnection();
            pstmt.setString(1,rid);
            pstmt.setString(2,eid);

            int row = pstmt.executeUpdate();

        } catch (SQLException  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(con, pstmt, null);
        }

    }
}
