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

public class emloyeeView extends JFrame {
    JPanel contentPane=new JPanel();
    JTable table;
    roomDao hdao=new roomDao();
    employeeDao eDao =new employeeDao();
    public emloyeeView(){
        setTitle("员工管理");
        setBounds(1000, 1000, 920, 624);
        setLocationRelativeTo(null);
        setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBounds(100,100,920,624);
        contentPane.setBackground(new Color(244,164,96));
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 30, 864, 400);
        contentPane.add(scrollPane);

        Object[] columns = { "工号", "身份证号", "姓名", "工种","服务房间号","工作起始时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load("休息");
        scrollPane.setViewportView(table);

        JButton signine=new JButton("员工签到");
        signine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signinView view =new signinView();
                view.setVisible(true);
            }
        });
        signine.setBounds(400,500,100,50);
        contentPane.add(signine);

        JButton signoute=new JButton("员工签离");
        signoute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一个员工", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String eid = String.valueOf(table.getValueAt(row, 0).toString());
                eDao.signout(eid);
                JOptionPane.showMessageDialog(contentPane,"签离成功！","系统提示",JOptionPane.INFORMATION_MESSAGE);
                load("休息");
            }
        });
        signoute.setBounds(200,500,100,50);
        contentPane.add(signoute);

        JButton refresh=new JButton("刷新");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              load("休息");
            }
        });
        refresh.setBounds(600,500,100,50);
        contentPane.add(refresh);

    }
    public void load(String stasus){
        List<employee> list = eDao.queryList3(stasus,"%");
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (employee item : list) {
            String[] arr = new String[6];
            arr[0] = item.getEid()+"";
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
