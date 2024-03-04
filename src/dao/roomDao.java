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
public class roomDao {



    /**
     * 查询房间列表
     * @param name 顾客姓名
     * @return
     */
    public List<room> queryList(String name){
        List<room> list = new ArrayList<room>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from room where 1=1 ");
            if(name != null && !"".equals(name)){
                sb.append("and name like ?");
                params.add(name);
            }
            sb.append("order by checkin_time desc");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                room person = new room();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setRid(rs.getString("rid"));
                person.setCheckInTime(rs.getString("checkin_time"));
                person.setCheckOutTime(rs.getString("checkout_time"));
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

    public List<room> queryList2(String rid){


        List<room> list = new ArrayList<room>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from room where 1=1 ");
            if(rid != null && !"".equals(rid)){
                sb.append("and rid like ? ");
                params.add(rid);
            }
            sb.append("order by checkin_time ");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                room person = new room();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setRid(rs.getString("rid"));
                person.setCheckInTime(rs.getString("checkin_time"));
                person.setCheckOutTime(rs.getString("checkout_time"));
                person.setAmount((rs.getInt("amount")));
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
     * 入住
     * @param person
     * @return
     */
    public boolean CheckIn(room person){

        Connection con = null;
        String sql = "insert into room(id,name,gender,rid,checkin_time,checkout_time) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, person.getId());
            pstmt.setString(2, person.getName());
            pstmt.setString(3, person.getGender());
            pstmt.setString(4,person.getRid());
            Date date = new Date();
            String inTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            pstmt.setString(5, inTime);
            String outTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date.getTime()+24*60*60*1000);
            pstmt.setString(6, outTime);
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
     * 续住
     * @param person
     * @return
     */
    public boolean update(room person){

        Connection con = null;
        String sql = "update room set name=?,checkout_time=? where rid=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(3, person.getRid());
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getCheckOutTime());

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
     * 房间消费信息
     * @param Rid,amount
     * @return
     */
    public boolean consume1(String Rid, int amount){

        Connection con = null;
        String sql = "update room set amount=? where Rid=?";
        PreparedStatement pstmt = null;
        room proom=getByRid(Rid);
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(2, Rid);
            if(proom!=null)amount=amount+proom.getAmount();
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





    //查找房间数量
    /**
     * 查找房间数量
     * @param rid
     * @return
     */

    public int roomNum(String rid){
        room person = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;int count=0;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from room where rid like ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, rid);
            rs = pstmt.executeQuery();

            while(rs.next()){
                count++;
                person = new room();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setRid(rs.getString("rid"));
                person.setCheckInTime(rs.getString("checkin_time"));
                person.setCheckOutTime(rs.getString("checkout_time"));
                person.setAmount(rs.getInt("amount"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return count;
    }

    /**
     * 退房
     * @param rid
     * @return
     */
    public boolean CheckOut(String rid){

        Connection con = null;
        String sql = "delete from room where rid=?";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, rid);
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
     * 根据名字查询顾客
     * @param name 顾客ID
     * @return
     */
    public customer getByName(String name){


        customer person = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from room where name = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, name);
            rs = pstmt.executeQuery();
            while(rs.next()){
                person = new customer();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setAccount(rs.getInt("amount"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return person;

    }

    public room getByRid(String rid){

        room person = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from room where rid = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, rid);
            rs = pstmt.executeQuery();
            while(rs.next()){
                person = new room();
                person.setId(rs.getString("id"));
                person.setName(rs.getString("name"));
                person.setGender(rs.getString("gender"));
                person.setRid(rs.getString("rid"));
                person.setCheckInTime(rs.getString("checkin_time"));
                person.setCheckOutTime(rs.getString("checkout_time"));
                person.setAmount(rs.getInt("amount"));
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