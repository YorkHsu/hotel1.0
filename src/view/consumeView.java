package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.customerDao;
import dao.flowDao;
import dao.roomDao;
import entity.*;
public class consumeView extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private roomDao hdao = new roomDao();
    private customerDao cDao=new customerDao();
    private flowDao fDao=new flowDao();
    private JTextField ridText;
    String rid=null;
    int count=0;

    public consumeView(){
        setTitle("房间送餐");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setLocationRelativeTo(null);
        setVisible(true);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(32,178,170));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 630, 200);
        contentPane.add(scrollPane);

        Mypicture m2=new Mypicture();
        m2.setBounds(1100,400,140,50);
        contentPane.add(m2);

        Object[] columns = { "房间号", "姓名", "性别", "消费" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(null);
        scrollPane.setViewportView(table);
        scrollPane.setBackground(Color.orange);
        table.setBackground(Color.CYAN);

        Font font=new Font("宋体",Font.BOLD,20);

        JLabel recipe1 =new JLabel("Foie Gras鹅肝----695rmb");
        recipe1.setBounds(300,310,400,21);
        JCheckBox c1=new JCheckBox();
        c1.setBounds(280,310,20,20);
        contentPane.add(recipe1);
        recipe1.setForeground(Color.yellow);
        contentPane.add(c1);
        JLabel recipe2 =new JLabel("惠林顿三星牛排----395rmb");
        recipe2.setBounds(300,340,400,21);
        JCheckBox c2=new JCheckBox();
        c2.setBounds(280,340,20,20);
        contentPane.add(recipe2);
        recipe2.setForeground(Color.yellow);
        contentPane.add(c2);
        JLabel recipe3 =new JLabel("m4干式熟成和牛----395rmb");
        recipe3.setBounds(300,370,400,21);
        JCheckBox c3=new JCheckBox();
        c3.setBounds(280,370,20,20);
        contentPane.add(recipe3);
        recipe3.setForeground(Color.yellow);
        contentPane.add(c3);
        recipe1.setFont(font);
        recipe2.setFont(font);
        recipe3.setFont(font);

        JLabel recipe4 =new JLabel("黑松露酥皮汤----295rmb");
        recipe4.setBounds(20,310,400,21);
        JCheckBox c4=new JCheckBox();
        c4.setBounds(0,310,20,20);
        contentPane.add(recipe4);
        recipe4.setForeground(Color.yellow);
        contentPane.add(c4);
        JLabel recipe5 =new JLabel("勃艮第蜗牛----595rmb");
        recipe5.setBounds(20,340,400,21);
        JCheckBox c5=new JCheckBox();
        c5.setBounds(0,340,20,20);
        contentPane.add(recipe5);
        recipe5.setForeground(Color.yellow);
        contentPane.add(c5);
        JLabel recipe6 =new JLabel("鳟鱼慕斯配鳌虾酱---495rmb");
        recipe6.setBounds(20,370,400,21);
        JCheckBox c6=new JCheckBox();
        c6.setBounds(0,370,20,20);
        contentPane.add(recipe6);
        recipe6.setForeground(Color.yellow);
        contentPane.add(c6);
        recipe4.setFont(font);
        recipe5.setFont(font);
        recipe6.setFont(font);

        JLabel recipe7 =new JLabel("经济商务快餐----68rmb");
        recipe7.setBounds(20,220,400,21);
        JCheckBox c7=new JCheckBox();
        c7.setBounds(0,220,20,20);
        contentPane.add(recipe7);
//        recipe7.setForeground(Color.yellow);
        contentPane.add(c7);
        JLabel recipe8 =new JLabel("浓汤豚骨面----98rmb");
        recipe8.setBounds(20,250,400,21);
        JCheckBox c8=new JCheckBox();
        c8.setBounds(0,250,20,20);
        contentPane.add(recipe8);
//        recipe8.setForeground(Color.yellow);
        contentPane.add(c8);
        JLabel recipe9 =new JLabel("干炒牛河---88rmb");
        recipe9.setBounds(20,280,400,21);
        JCheckBox c9=new JCheckBox();
        c9.setBounds(0,280,20,20);
        contentPane.add(recipe9);
//        recipe9.setForeground(Color.yellow);
        contentPane.add(c9);
        recipe7.setFont(font);
        recipe8.setFont(font);
        recipe9.setFont(font);

        JLabel recipe10 =new JLabel("香菜凤仁鸡----68rmb");
        recipe10.setBounds(300,220,400,21);
        JCheckBox c10=new JCheckBox();
        c10.setBounds(280,220,20,20);
        contentPane.add(recipe10);
//        recipe7.setForeground(Color.yellow);
        contentPane.add(c10);
        JLabel recipe11 =new JLabel("香翅捞饭----68rmb");
        recipe11.setBounds(300,250,400,21);
        JCheckBox c11=new JCheckBox();
        c11.setBounds(280,250,20,20);
        contentPane.add(recipe11);
//        recipe8.setForeground(Color.yellow);
        contentPane.add(c11);
        JLabel recipe12 =new JLabel("香精煎鱼---68rmb");
        recipe12.setBounds(300,280,400,21);
        JCheckBox c12=new JCheckBox();
        c12.setBounds(280,280,20,20);
        contentPane.add(recipe12);
//        recipe9.setForeground(Color.yellow);
        contentPane.add(c12);
        recipe10.setFont(font);
        recipe11.setFont(font);
        recipe12.setFont(font);

        JButton order=new JButton("配餐");
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(contentPane, "请选择一条记录", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int result = JOptionPane.showConfirmDialog(contentPane, "该顾客确认点餐吗？", "提示",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    rid = table.getValueAt(row, 0).toString();
                }
                int account=0;
                if (c1.isSelected())account+=695;
                if (c2.isSelected())account+=395;
                if (c3.isSelected())account+=395;
                if (c4.isSelected())account+=295;
                if (c5.isSelected())account+=595;
                if (c6.isSelected())account+=495;
                if (c7.isSelected())account+=68;
                if (c8.isSelected())account+=98;
                if (c9.isSelected())account+=88;
                if (c10.isSelected())account+=68;
                if (c11.isSelected())account+=68;
                if (c12.isSelected())account+=68;

                if(hdao.consume1(rid,account)){

                    flow flowc=new flow();
                    flowc.setRid(rid);
                    flowc.setName(table.getValueAt(row,1).toString());
                    flowc.setcMoney(String.valueOf(account));
                    flowc.setcType("点餐");
                    Date date = new Date();
                    String inTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    String inTime1 = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
                    flowc.setcTime(inTime);
                    flowc.setFid("101"+inTime1);
                    fDao.addflow(flowc);

                    cDao.consume2(hdao.getByRid(rid).getId(),account);
                    JOptionPane.showMessageDialog(contentPane, "下单成功", "系统提示", JOptionPane.WARNING_MESSAGE);
                    load(null);
                }else {
                    JOptionPane.showMessageDialog(contentPane, "下单失败", "系统提示", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        contentPane.add(order);
        order.setBounds(700,100,80,36);






    }
    public void load(String rid){
        List<room> list = hdao.queryList2(rid);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);// 清除原有行
        // 填充数据
        for (room item : list) {
            String[] arr = new String[4];
            arr[0] = item.getRid() + "";
            arr[1] = item.getName();
            arr[2] = item.getGender();
            arr[3]=item.getAmount()+"";
            // 添加数据到表格
            tableModel.addRow(arr);
        }
    }

}
