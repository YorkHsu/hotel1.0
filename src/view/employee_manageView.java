package view;

import dao.employeeDao;
import dao.roomDao;
import entity.employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class employee_manageView extends JFrame {
    JPanel contentPane=new JPanel();
    JTable table;
    roomDao hdao=new roomDao();
    employeeDao eDao =new employeeDao();
    public employee_manageView(){
        setTitle("沙袋大酒店管理系统");
        setBounds(1000, 1000, 920, 624);
        setLocationRelativeTo(null);

        
        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBounds(100,100,920,624);
        contentPane.setBackground(new Color(244,164,96));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 764, 460);
        contentPane.add(scrollPane);

        Object[] columns = { "工号", "身份证号", "姓名", "工种","工作起始时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(null);
        scrollPane.setViewportView(table);

        JButton adde=new JButton("添加员工");
        adde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeView view =new addEmployeeView();
                view.setVisible(true);

            }
        });

        adde.setBounds(800,400,100,50); contentPane.add(adde);

        JButton dele=new JButton("删除员工");
        dele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int row=table.getSelectedRow();

                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一个员工", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }String eid= (String) table.getValueAt(row,0);
                int result = JOptionPane.showConfirmDialog(contentPane, "确定解聘"+table.getValueAt(row,2)+"吗？", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result==0){
                boolean flag=eDao.dele(eid);
                if(flag){
                    JOptionPane.showMessageDialog(contentPane, "解聘成功", "系统提示", JOptionPane.WARNING_MESSAGE);
                    load(null);
                }else{
                    JOptionPane.showMessageDialog(contentPane, "操作失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }}
                else{
                    return;
                }
            }
        });
        contentPane.add(dele);
        dele.setBounds(800,500,100,50);

        JButton refresh=new JButton("刷新");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(null);
            }
        });
        refresh.setBounds(800,20,100,30);
        contentPane.add(refresh);
    }
    public void load(String stasus){
        List<employee> list = eDao.queryList3(stasus,"%");
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (employee item : list) {
            String[] arr = new String[5];
            arr[0] = item.getEid() + "";
            arr[1] = item.getId();
            arr[2] = item.getName();
            arr[3]=item.getEtype();
            arr[4] = item.getWorktime();
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }
}
