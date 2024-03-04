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
import entity.*;

class checkoutView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField idText;
    private JTextField nameText;
    private JTextField genderText;
    private JTextField ridText;

    private roomDao hdao = new roomDao();
    employeeDao eDao =new employeeDao();

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     * @param
     */
    public checkoutView() {
        setTitle("退房");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 490);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        Mypicture m2=new Mypicture();
        m2.setBounds(780,420,69,25);
        contentPane.add(m2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 870, 200);
        contentPane.add(scrollPane);

        Object[] columns = { "ID", "姓名", "性别", "房间号","入住时间","最晚退房时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        //加载学生数据
        load(null);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(5);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        scrollPane.setViewportView(table);

        JButton update=new JButton("退房");
        contentPane.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int result = JOptionPane.showConfirmDialog(contentPane, "该顾客确认退房吗？", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result>0){
                    JOptionPane.showMessageDialog(contentPane,"取消退房","系统提示",JOptionPane.INFORMATION_MESSAGE);
                    return;}

                String rid = String.valueOf(table.getValueAt(row, 3).toString());
                int amount=hdao.getByRid(rid).getAmount();
                boolean flag=hdao.CheckOut(rid);

                if(flag){
                    employee cleaner=new employee();
                    cleaner=eDao.clean1(rid);
                    JOptionPane.showMessageDialog(contentPane, "退房成功!\n该房间消费为:"+amount+"\n"+cleaner.getName()+"即将去清理房间");

                    load(null);
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        update.setBounds(438, 228, 63, 23);
        contentPane.add(update);


    }
    // 填充表格数据
    public void load(String name){
        List<room> list = hdao.queryList(name);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (room item : list) {
            String[] arr = new String[6];
            arr[0] = item.getId() + "";
            arr[1] = item.getName();
            arr[2] = item.getGender();
            arr[3]=item.getRid();
            arr[4] = item.getCheckInTime();
            arr[5] = item.getCheckOutTime();
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

}