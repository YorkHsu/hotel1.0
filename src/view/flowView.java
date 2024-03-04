package view;

import dao.*;
import entity.flow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class flowView extends JFrame{
    private JPanel contentPane;
    private JTable table;
    private roomDao hdao = new roomDao();
    private flowDao fDao=new flowDao();
    public  flowView(){
        setTitle("流水记录");
        setBounds(100,100,1400,600);

        contentPane=new JPanel();
        setContentPane(contentPane);
        contentPane.setBackground(new Color(32,178,170));
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBounds(100,100,900,600);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 1064, 500);
        contentPane.add(scrollPane);
        Object[] columns = { "消费单号", "姓名" ,"房间号", "消费类型", "消费金额","消费时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(null);
        scrollPane.setViewportView(table);

    }
    public void load(String time){
        List<flow> list = fDao.queryList4(time);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (flow item : list) {
            String[] arr = new String[6];
            arr[0] = item.getFid() + "";
            arr[1] = item.getName();
            arr[2] = item.getRid();
            if (arr[2].equals(""))arr[2]="（非房间服务）";
            arr[3] = item.getcType();
            arr[4]=item.getcMoney();
            arr[5] = item.getcTime();
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }
}
