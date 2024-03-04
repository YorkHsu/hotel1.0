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

public class do_cleanView extends JFrame{
    JPanel contentPane=new JPanel();
    JTable table;
    JTextField ridText=new JTextField();
    JLabel ridtxt=new JLabel();
    roomDao hdao=new roomDao();
    employeeDao eDao =new employeeDao();
    public do_cleanView(){
        setTitle("沙袋大酒店管理系统");
        setBounds(1000, 1000, 920, 624);
        setLocationRelativeTo(null);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(244,164,96));
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 884, 300);
        contentPane.add(scrollPane);

        Object[] columns = { "工号", "身份证号", "姓名", "工种","服务房间号","工作起始时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load("休息","清洁工");
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(5);
        table.getColumnModel().getColumn(3).setPreferredWidth(5);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        scrollPane.setViewportView(table);

        JLabel ridtxt=new JLabel("房间号:");
        ridtxt.setFont(new Font("微软雅黑",Font.PLAIN,16));
        ridtxt.setBounds(349,377,80,21);
        ridText.setBounds(419,377,100,21);

        JButton askbutton=new JButton("清洁");
        askbutton.setBounds(289, 477, 100, 21);
        askbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                if (row<0){
                    JOptionPane.showMessageDialog(contentPane,"请选择一个清洁工","系统提示",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String eid=String.valueOf(table.getValueAt(row,0).toString());
                String name=String.valueOf(table.getValueAt(row,2).toString());
                String ridtext=ridText.getText();
                int rid=Integer.parseInt(ridtext);
                int floor=rid/100;
                int room=rid%100;
                if (floor<=5){
                    ridtext="特价舒适大床房"+floor+"楼"+room+"号房";
                }else if (floor<=10&&floor>5){
                    ridtext="轻奢优品商务房"+floor+"楼"+room+"号房";
                }else if (floor<=15&&floor>10){
                    ridtext="休闲放松棋牌房"+floor+"楼"+room+"号房";
                }else if (floor==21){
                    ridtext="奢华尊贵总统房"+floor+"楼"+room+"号房";
                }else if (floor<=20&&floor>15){
                    ridtext="近水楼台观景房"+floor+"楼"+room+"号房";
                }
                String finalRidtext = ridtext;
                int result=JOptionPane.showConfirmDialog(contentPane,"是否派遣"+name+"去清洁？","系统提示",JOptionPane.YES_NO_OPTION);
                if (result==0)JOptionPane.showMessageDialog(contentPane,name+"即将去清洁","系统提示",JOptionPane.INFORMATION_MESSAGE);
                eDao.clean2(ridtext,eid);
                load("休息","清洁工");
            }
        });

        JButton donebutton=new JButton("完成清洁");
//        donebutton.setBackground(Color.RED);
        donebutton.setBounds(489, 477, 100, 21);
        donebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                String eid=String.valueOf(table.getValueAt(row,0).toString());
                eDao.clean2("空",eid);
                load("休息","清洁工");
            }
        });


        contentPane.add(ridText);
        contentPane.add(ridtxt);
        contentPane.add(askbutton);
        contentPane.add(donebutton);

    }
    public void load(String stasus,String employeeType){
        List<employee> list = eDao.queryList3(stasus,employeeType);
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
