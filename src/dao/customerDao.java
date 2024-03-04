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

/**
 * 酒店数据库操作
 * @author Lenovo
 *
 */
public class customerDao {


    //结账
    /**
     * 结账
     * @param id
     * @return
     */
    public boolean pay(String id){

        Connection con = null;
        String sql = "update customer set amount=0 where id=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, id);
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

    //vip
    /**
     * vip
     * @param id
     * @return
     */
    public boolean vip(String id,String vip){

        Connection con = null;
        String sql = "update customer set vip=? where id=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, id);
            pstmt.setString(1,vip);
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
     * 添加客户信息
     * @param person
     * @return
     */
    public boolean addCustomer(customer person){

        Connection con = null;
        String sql = "insert into customer(id,name,gender,phonenum,amount) values(?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, person.getId());
            pstmt.setString(2, person.getName());
            pstmt.setString(3, person.getGender());
            pstmt.setString(4, person.getPhoneNumber());
            pstmt.setInt(5,person.getAccount());
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
     * 个人消费信息
     * @param id,amount
     * @return
     */
    public boolean consume2(String id, int amount){

        Connection con = null;
        String sql = "update customer set amount=? where id=?";
        PreparedStatement pstmt = null;
        customer proom=getById(id);
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, id);
            if(proom!=null)amount=amount+proom.getAccount();
            pstmt.setInt(1, amount);

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
     * 个人消费情况
     * @param id
     * @return
     */
    public List<customer> payCustomer(String id){
        List<customer> list=new ArrayList<>();
        customer person = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from customer c,(select name,count(*) counts from flow group by name)d where c.name=d.name and id like ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, id);
            rs = pstmt.executeQuery();//result set
            while(rs.next()){
                person = new customer();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setPhoneNumber(rs.getString("phonenum"));
                person.setVip(rs.getString("vip"));
                person.setAccount(rs.getInt("amount"));
                person.setTimes(rs.getInt("counts"));
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
    /**
     * 根据ID查询顾客
     * @param id 顾客ID
     * @return
     */
    public customer getById(String id){


        customer person = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from customer where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                person = new customer();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setVip(rs.getString("vip"));
                person.setAccount(rs.getInt("amount"));
                person.setPhoneNumber(rs.getString("phonenum"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return person;

    }
}