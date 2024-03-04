//Created by Xu
package view;

import entity.room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.roomDao;

public class MainFrame extends JFrame {
    JTextField userId=new JTextField();
    JTextField password=new JTextField();
    private JTable table;
     roomDao hdao=new roomDao();
    JPanel root=new JPanel();
    JLabel r1num;
    JLabel r2num;
    JLabel r3num;
    JLabel r4num;
    JLabel r5num;
    public MainFrame(String title){
        super(title);
        setLocationRelativeTo(null);
        setBounds(0,0,1920,1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane =new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(70,130,180));
//        contentPane.setForeground(new Color(135,206,250));

        //添加按钮
        JButton addBtn = new JButton("办理入住");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkInView view = new checkInView();
                view.setVisible(true);

            }
        });
        addBtn.setBounds(0, 88, 123, 100);

        contentPane.add(addBtn);


        //修改按钮
        JButton updateBtn = new JButton("续住");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateView2 view = new updateView2();
                view.setVisible(true);

            }
        });
        updateBtn.setBounds(0, 200, 123, 100);
        contentPane.add(updateBtn);


        //查找指定房间
        JButton findRoomBtn = new JButton("查找房间");
        findRoomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                roomView view = new roomView();
                view.setVisible(true);
            }
        });
        findRoomBtn.setBounds(0, 312, 123, 100);
        contentPane.add(findRoomBtn);

        //房间送餐
        JButton consumeBtn = new JButton("房间送餐");
        consumeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consumeView view = new consumeView();
                view.setVisible(true);
            }
        });
        consumeBtn.setBounds(0, 424, 123, 100);
        contentPane.add(consumeBtn);

        //删除按钮
        JButton deleteBtn = new JButton("办理退房");
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkoutView view=new checkoutView();
               view.setVisible(true);
                return;
            }
        });
        deleteBtn.setBounds(0, 536, 123, 100);
        contentPane.add(deleteBtn);

        //预约按钮
        JButton reserveBtn = new JButton("自助餐厅预约");
        reserveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reserveView view=new reserveView();
                view.setVisible(true);
                return;
            }
        });
        reserveBtn.setBounds(0, 648, 123, 100);
        contentPane.add(reserveBtn);

        //员工按钮
        JButton employeeBtn1 = new JButton("员工管理");
        employeeBtn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                employee_manageView view=new employee_manageView();
                view.setVisible(true);
                return;
            }
        });
        employeeBtn1.setBounds(877, 736, 123, 50);
        contentPane.add(employeeBtn1);

        //员工按钮
        JButton employeeBtn = new JButton("员工值班");
        employeeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emloyeeView view=new emloyeeView();
                view.setVisible(true);
                return;
            }
        });
        employeeBtn.setBounds(1000, 736, 123, 50);
        contentPane.add(employeeBtn);

        //清洁服务
        JButton cleanBtn = new JButton("安排清洁");
        cleanBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_cleanView view=new do_cleanView();
                view.setVisible(true);
                return;
            }
        });
        cleanBtn.setBounds(1123, 736, 123, 50);
        contentPane.add(cleanBtn);

        //维修服务
        JButton maintenceBtn = new JButton("安排维修");
        maintenceBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_maintenanceView view=new do_maintenanceView();
                view.setVisible(true);
                return;
            }
        });
        maintenceBtn.setBounds(1246, 736, 123, 50);
        contentPane.add(maintenceBtn);

        //跑腿服务
        JButton deliveryBtn = new JButton("安排跑腿");
        deliveryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_deliveryView view=new do_deliveryView();
                view.setVisible(true);
                return;
            }
        });
        deliveryBtn.setBounds(1369, 736, 123, 50);
        contentPane.add(deliveryBtn);


        //交易记录
        JButton flowBtn = new JButton("交\n易\n记\n录");
        flowBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flowView view=new flowView();
                view.setVisible(true);
                return;
            }
        });
        flowBtn.setBounds(1269, 0, 123, 30);


        //结账记录
        JButton payBtn = new JButton("客户结账");
        payBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payView view=new payView();
                view.setVisible(true);
                return;
            }
        });
        payBtn.setBounds(1138, 0, 123, 30);



        //退出登录
        JButton logoutBtn = new JButton("退出登录");
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginView view = new LoginView();
                view.setVisible(true);
                setVisible(false);
            }
        });
        logoutBtn.setBounds(1400, 0, 123, 30);


        TimeUtlis time=new TimeUtlis();
        time.setBounds(0,0,1920,50);
        time.setBackground(new Color(32,178,170));
        time.setLayout(null);
        time.add(logoutBtn);
        time.add(flowBtn);
        time.add(payBtn);
        contentPane.add(time);

//table
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(400, 200, 964, 500);
        add(scrollPane);

        Object[] columns = { "ID", "姓名", "性别", "房间号","入住时间","最晚退房时间" };// 字段
        Object[][] data = null;
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        load(null);
        scrollPane.setViewportView(table);

        JButton r1=new JButton("大床房");
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load("%大床房%");
            }
        });
        r1.setBounds(400,160,100,40);
        add(r1);

        JButton r2=new JButton("商务房");
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load("%商务房%");
            }
        });
        r2.setBounds(500,160,100,40);
        add(r2);

        JButton r3=new JButton("棋牌房");
        r3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load("%棋牌房%");
            }
        });
        r3.setBounds(600,160,100,40);
        add(r3);

        JButton r4=new JButton("观景房");
        r4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load("%观景房%");
            }
        });
        r4.setBounds(700,160,100,40);
        add(r4);

        JButton r5=new JButton("总统房");
        r5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load("%总统房%");
            }
        });
        r5.setBounds(800,160,100,40);
        add(r5);

        //房间数量
        r1num=new JLabel("大床房还有："+(100-hdao.roomNum("%大床房%")));
        r1num.setBounds(200,300,200,50);
        add(r1num);
        r2num=new JLabel("商务房还有："+(100-hdao.roomNum("%商务房%")));
        r2num.setBounds(200,380,200,50);
        add(r2num);
        r3num=new JLabel("棋牌房还有："+(100-hdao.roomNum("%棋牌房%")));
        r3num.setBounds(200,460,200,50);
        add(r3num);
        r4num=new JLabel("观景房还有："+(100-hdao.roomNum("%观景房%")));
        r4num.setBounds(200,540,200,50);
        add(r4num);
        r5num=new JLabel("总统房还有："+(100-hdao.roomNum("%总统房%")));
        r5num.setBounds(200,620,200,50);
        add(r5num);
        Font font=new Font("宋体",Font.BOLD,20);
        r1num.setForeground(Color.yellow);r1num.setFont(font);
        r2num.setForeground(Color.yellow);r2num.setFont(font);
        r3num.setForeground(Color.yellow);r3num.setFont(font);
        r4num.setForeground(Color.yellow);r4num.setFont(font);
        r5num.setForeground(Color.yellow);r5num.setFont(font);

        Mypicture m2=new Mypicture();
        m2.setBounds(1100,200,200,200);
        contentPane.add(m2);

        //刷新
        JButton rFresh=new JButton("刷新");
        rFresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(null);
                reset();
            }

        });
        rFresh.setBounds(1400,150,100,40);
        contentPane.add(rFresh);

        //价目表
        NewButton menu=new NewButton("价\n目\n表",140,80);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView=new MenuView();
                menuView.setVisible(true);
            }

        });
        menu.setBounds(1400,250,100,40);
        contentPane.add(menu);

        //加入会员
        NewButton vip=new NewButton("成为会员",140,80);

        vip.setForeground(Color.yellow);
        vip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vipView View=new vipView();
                View.setVisible(true);
            }

        });
        vip.setBounds(1400,350,100,40);
        contentPane.add(vip);

    }
    public void reset(){
        r1num.setText("大床房还有："+(100-hdao.roomNum("%大床房%")));
        r2num.setText("商务房还有："+(100-hdao.roomNum("%商务房%")));
        r3num.setText("棋牌房还有："+(100-hdao.roomNum("%棋牌房%")));
        r4num.setText("观景房还有："+(100-hdao.roomNum("%观景房%")));
        r5num.setText("总统房还有："+(100-hdao.roomNum("%总统房%")));
    }
    public void load(String rid){
        List<room> list = hdao.queryList2(rid);
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
