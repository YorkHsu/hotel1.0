package view;

import dao.*;
import entity.customer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class payView extends JFrame{
    private JTextField idtxt;
    private JPanel contentPane;
    private JTable table;
    private roomDao hdao = new roomDao();
    private customerDao cDao =new customerDao();


    public  payView(){
        setTitle("结账");
        setBounds(100,100,900,600);
        setLocationRelativeTo(null);

        contentPane=new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(32,178,170));
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBounds(100,100,900,600);

        idtxt =new JTextField();
        idtxt.setBounds(90,15,100,25);
        contentPane.add(idtxt);

        JLabel jLabel1=new JLabel("身份证号:");
        jLabel1.setBounds(10,15,80,25);
        contentPane.add(jLabel1);

        JButton searchBtn=new JButton("查询");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id= idtxt.getText();
                if(id.equals(""))id="%";
                load(id);
            }
        });
        searchBtn.setBounds(200,15,100,25);
        contentPane.add(searchBtn);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(32,178,170));

        scrollPane.setBounds(10, 45, 766, 500);
        contentPane.add(scrollPane);
        Object[] columns = { "身份证号","性别", "姓名" ,"手机号", "消费项目","消费金额","会员","会员价" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(25);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(80);

        load("%");
        scrollPane.setViewportView(table);

        JButton search=new JButton("具体消费");
        search.setBounds(780,280,88,68);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =table.getSelectedRow();
                if (row<0){
                    JOptionPane.showMessageDialog(contentPane, "请选择一个顾客", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String name =String.valueOf(table.getValueAt(row,2));
                customerPayView view =new customerPayView(name);
                view.setVisible(true);

            }
        });
        contentPane.add(search);

        JButton pay=new JButton("结账");
        pay.setBounds(780,120,88,68);
        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =table.getSelectedRow();
                if (row<0){
                    JOptionPane.showMessageDialog(contentPane, "请选择一个顾客", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String id =String.valueOf(table.getValueAt(row,0));
                boolean flag=cDao.pay(id);
                if (flag){
                    JOptionPane.showMessageDialog(contentPane,"结账成功","系统提示",JOptionPane.INFORMATION_MESSAGE);
                    load("%");
                }else {
                    JOptionPane.showMessageDialog(contentPane,"操作失败","系统提示",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
        contentPane.add(pay);

    }
    public void load(String id){
        List<customer> list = cDao.payCustomer(id);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (customer item : list) {
            String[] arr = new String[8];
            arr[0] = item.getId() + "";
            arr[1] = item.getGender();
            arr[2] = item.getName();
            arr[3] = item.getPhoneNumber();
            arr[4] = String.valueOf(item.getTimes());
            arr[5]=String.valueOf(item.getAccount());
            String vip=item.getVip();
            if (vip==null)vip="无";
            arr[6]=vip;
            double discount=1.0;
            switch (vip){
                case "希尔顿普通会员" : discount=0.98;break;
                case "希尔顿铂金会员" : discount=0.96;break;
                case "希尔顿钻石会员" : discount=0.92;break;
                case "希尔顿至臻黑钻会员" : discount=0.88;break;
            }
            double amount=item.getAccount()*discount;
            arr[7]=String.valueOf(amount);

            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }
}
