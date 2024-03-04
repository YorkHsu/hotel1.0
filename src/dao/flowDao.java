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

public class flowDao {


    //查消费记录
    public List<flow> queryList4(String time){

        List<flow> list = new ArrayList<flow>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from flow where 1=1 ");
            if(time != null && !"".equals(time)){
                sb.append("and cTime  like ?");
                params.add(time);
            }
            sb.append("order by cTime ");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                flow flowc=new flow();
                flowc.setFid(rs.getString("fid"));
                flowc.setRid(rs.getString("rid"));
                flowc.setName(rs.getString("name"));
                flowc.setcType(rs.getString("cType"));
                flowc.setcMoney(rs.getString("cMoney"));
                flowc.setcTime(rs.getString("cTime"));
                list.add(flowc);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return list;


    }

    //查消费记录
    public List<flow> queryList5(String name){

        List<flow> list = new ArrayList<flow>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            List<Object> params = new ArrayList<>();
            StringBuffer sb = new StringBuffer("select * from flow where 1=1 ");
            if(name != null && !"".equals(name)){
                sb.append("and name like ?");
                params.add(name);
            }
            sb.append("order by cTime ");
            pstmt = con.prepareStatement(sb.toString());
            if(params != null && params.size()>0){
                for(int i=0; i<params.size(); i++){
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            while(rs.next()){
                flow flowc=new flow();
                flowc.setFid(rs.getString("fid"));
                flowc.setRid(rs.getString("rid"));
                flowc.setName(rs.getString("name"));
                flowc.setcType(rs.getString("cType"));
                flowc.setcMoney(rs.getString("cMoney"));
                flowc.setcTime(rs.getString("cTime"));
                list.add(flowc);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            JDBCUtils.close(con, pstmt, rs);
        }
        return list;


    }




    //增加交易记录
    public boolean addflow(flow flowc){

        Connection con = null;
        String sql = "insert into flow(fid,rid,name,cType,cMoney,cTime) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtils.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,flowc.getFid());
            pstmt.setString(2, flowc.getRid());
            pstmt.setString(3, flowc.getName());
            pstmt.setString(4, flowc.getcType());
            pstmt.setString(5,flowc.getcMoney());
            pstmt.setString(6, flowc.getcTime());

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

}
