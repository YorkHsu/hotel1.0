package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.roomDao;
import entity.room;

class updateView2 extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JTextField idText;
    private JTextField nameText;
    private JTextField genderText;
    private JTextField ridText;

    private roomDao hdao = new roomDao();

    /**
     * Launch the application.
     */
    /**
     * Create the frame.
     * @param
     */
    public updateView2() {
        setTitle("续住");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 960, 540);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        Mypicture m2=new Mypicture();
        m2.setBounds(800,450,138,50);
        contentPane.add(m2);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(8, 8, 900, 300);
        contentPane.add(scrollPane);



        Object[] columns = { "ID", "姓名", "性别", "房间号","入住时间","最晚退房时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(null);
        scrollPane.setViewportView(table);

        JButton update=new JButton("续住");
        contentPane.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String rid = String.valueOf(table.getValueAt(row, 3).toString());
                updateView view = new updateView(rid);
                view.setVisible(true);
            }
        });
        update.setBounds(438, 440, 73, 33);



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