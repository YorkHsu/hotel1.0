package view;
import java.awt.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimeUtlis extends JPanel
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 用于获得窗口的大小
    final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private JLabel label1 = new JLabel();
    private JLabel label = new JLabel();
    private JLabel label2 = new JLabel();
    Mypicture pl=new Mypicture();
    public TimeUtlis()
    {
        super();
        add(label);
        add(label1);
        add(label2);
        add(pl);
    }
    @Override
    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        Date data = new Date();

        // 获取星期几;
        String weeks[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

        Calendar weekStart = Calendar.getInstance();

        weekStart.setTime(new Date());

        int index = weekStart.get(Calendar.DAY_OF_WEEK);

        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// 设置显示格式

        @SuppressWarnings("unused")
        String nowTime = "";

        label1.setText(weeks[index - 1]);
        label1.setFont(new Font("SimSun", Font.BOLD, 20));
        label1.setBounds(350, 0, 300, 50);
        // label1.setBounds(110,0,300, 100);
        //label1.setForeground(ColorUtlis.goldenColor);

        label.setText(nowTime = df.format(data));
        label.setFont(new Font("SimSun", Font.BOLD, 20));
        // label.setBounds(200, 0, 300, 100);
        label.setBounds(50, 0, 1300, 50);

        label2.setText("希尔顿国际大酒店");
        label2.setForeground(new Color(255,140,0));
        label2.setFont(new Font("宋体",Font.BOLD,30));
        label2.setBounds(600,0,300,50);


        pl.setBounds(950,12,69,25);

        //label.setForeground(ColorUtlis.goldenColor);

    }}
