package view;

import dao.flowDao;
import dao.roomDao;
import entity.flow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class customerPayView extends JFrame {
    private JTextField idtxt;
    private JPanel contentPane;
    private JTable table;
    private roomDao hdao = new roomDao();
    private flowDao fDao=new flowDao();
    public  customerPayView(String name){

        setTitle("希尔顿国际酒店管理系统");
        setBounds(1000, 1000, 960, 540);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(new Color(32,178,170));
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 45, 900, 400);
        contentPane.add(scrollPane);
        Object[] columns = { "身份证号","姓名", "房间号" ,"手机号", "消费金额","消费项目" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(name);
        scrollPane.setViewportView(table);
    }
    public void load(String name){
        List<flow> list = fDao.queryList5(name);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (flow item : list) {
            String[] arr = new String[6];
            arr[0] = item.getFid() + "";
            arr[1] = item.getName();
            arr[2] = item.getRid();if (arr[2].equals(""))arr[2]="（非房间服务）";
            arr[3] = item.getcType();
            arr[4]=String.valueOf(item.getcMoney());
            arr[5] = String.valueOf(item.getcTime());
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }
}
