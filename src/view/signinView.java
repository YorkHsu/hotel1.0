package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.employeeDao;
import dao.roomDao;
import entity.employee;

public class signinView extends JFrame{
    JPanel contentPane=new JPanel();
    JTable table;
    roomDao hdao=new roomDao();
    employeeDao eDao =new employeeDao();
    public signinView(){
        setTitle("希尔顿国际酒店管理系统");
        setBounds(100, 100, 960, 540);
        setLocationRelativeTo(null);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(244,164,96));
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 900, 400);
        contentPane.add(scrollPane);

        Object[] columns = { "工号", "身份证号", "姓名", "工种","服务房间号","工作起始时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(null);
        scrollPane.setViewportView(table);

        JButton sign=new JButton("打卡签到");
        sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一个员工", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String eid = String.valueOf(table.getValueAt(row, 0).toString());
                eDao.signin(eid);
                JOptionPane.showMessageDialog(contentPane,"签到成功！","系统提示",JOptionPane.INFORMATION_MESSAGE);
                load(null);
            }
        });
        sign.setBounds(450,450,100,50);
        contentPane.add(sign);
    }
    public void load(String stasus){
        List<employee> list = eDao.queryList3(stasus,"%");
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (employee item : list) {
            String[] arr = new String[6];
            arr[0] = item.getEid() + "";
            arr[1] = item.getId();
            arr[2] = item.getName();
            arr[3]=item.getEtype();
            arr[4] = item.getRid();
            arr[5] = item.getWorktime();
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }
}
