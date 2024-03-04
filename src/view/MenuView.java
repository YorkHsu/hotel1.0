package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MenuView extends JFrame {
    JPanel contentPane;
    JTable table;
    public MenuView(){
        setTitle("希尔顿国际酒店管理系统");
        setBounds(1000, 1000, 960, 540);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(70,130,180));
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 45, 900, 400);
        contentPane.add(scrollPane);
        Object[] columns = { "分类","名称", "价格" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行

        String []room=new String[3];
        room[0] = "房间";
        room[1] = "特价舒适大床房";
        room[2] = "288";
        tableModel.addRow(room);
        room[0] = "房间";
        room[1] = "轻奢优品商务房";
        room[2] = "388";
        tableModel.addRow(room);
        room[0] = "房间";
        room[1] = "休闲放松棋牌房";
        room[2] = "688";
        tableModel.addRow(room);
        room[0] = "房间";
        room[1] = "近水楼台观景房";
        room[2] = "888";
        tableModel.addRow(room);
        room[0] = "房间";
        room[1] = "奢华尊贵总统房";
        room[2] = "1888";
        tableModel.addRow(room);

        String []reservation=new String[3];
        reservation[0]="预约";
        reservation[1]="米其林三星餐厅";
        reservation[2]="98888";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="婚礼庆宴";
        reservation[2]="198888";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="豪华包厢";
        reservation[2]="8888";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="海鲜自助";
        reservation[2]="688";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="户外烤肉";
        reservation[2]="388";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="洗浴足浴";
        reservation[2]="2888";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="桑拿汗蒸";
        reservation[2]="2888";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="天然温泉";
        reservation[2]="3888";
        tableModel.addRow(reservation);
        reservation[0]="预约";
        reservation[1]="商务快餐";
        reservation[2]="88";
        tableModel.addRow(reservation);

        String []vip=new String[3];
        vip[0]="成为会员";
        vip[1]="希尔顿普通会员";
        vip[2]="1688";
        tableModel.addRow(vip);
        vip[0]="成为会员";
        vip[1]="希尔顿铂金会员";
        vip[2]="3688";
        tableModel.addRow(vip);
        vip[0]="成为会员";
        vip[1]="希尔顿钻石会员";
        vip[2]="6688";
        tableModel.addRow(vip);
        vip[0]="成为会员";
        vip[1]="希尔顿至臻黑钻会员";
        vip[2]="9688";
        tableModel.addRow(vip);



        scrollPane.setViewportView(table);
    }

}
