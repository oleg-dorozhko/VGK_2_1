package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class AppFrame extends JFrame implements AppEventer {

    private   JTextField jtf;
    private   JPanel p1;
    JButton[] buttons = new JButton[9];
    int selectedButton = -1;
    int indexPage = 9;

    String alphabet_str = null;
    AppFrame self = this;
    public AppFrame() throws Exception {

        super("Virtual keyboard with giant-sized characters and sound");

        setSize(new Dimension(800, 800));
        setLayout(new BorderLayout());

        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout());

         jtf = new JTextField("               ");

        jtf.setFont(new Font("Curier New", Font.BOLD, 80));
        jtf.setPreferredSize(new Dimension(440, 100));
        pan.add(jtf);
        //f.add(jtf,BorderLayout.NORTH);

      //  JButton insBtn = new JButton("ENTER");

      //  insBtn.setFont(new Font("Curier New", Font.BOLD, 80));

        JButton delBtn = new JButton(" << ");
        delBtn.setFont(new Font("Curier New", Font.BOLD, 80));

        pan.add(delBtn);
     //   pan.add(insBtn);
        add(pan, BorderLayout.NORTH);

        // f.add(new JLabel("NORTH"),BorderLayout.SOUTH);
        // f.add(new JLabel("NORTH"),BorderLayout.WEST);
        // f.add(new JLabel("NORTH"),BorderLayout.EAST);
        //   JPanel n = new JPanel(); n.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        //  n.setLayout(new FlowLayout());

        //  n.add();
        ArrayList<String> arr = new ArrayList<String>();
        String line = null;
        File f = new File(System.getProperty("user.dir")+ "/res/alphabets.txt");
        System.out.println(f);
        if(f.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(f));

            while(true) {
                line = in.readLine();
                if(line==null) break;
                arr.add(line.trim().split("=")[1].trim());
            }

        }
        else throw new Exception("File alphabets.txt not exist");
        if(arr.size()==0) throw new Exception("Cannot read file alphabets.txt. May be wrong format.");

        String delim_9_spaces = "         ";

        alphabet_str = new String (delim_9_spaces +  String.join(delim_9_spaces, arr)  + delim_9_spaces );

       // ukr = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

        //String s = new String("         abcdefghijklmnopqrstuvwxyz         " + ukr + "         ");
        //MyPicture mp = new MyPicture(this, jtf, alhpabet, 800,800, img);
        //mp.setSelectedAlpha( 9);
        // mp.setSelectedAlpha( main.RandomTool.getRandomInt(0,alhpabet.length));
        //f.add(mp,BorderLayout.CENTER);
        String[] alhpabet = alphabet_str.split("");

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = jtf.getText().trim();
                if(s.length()>=1) {
                    s = s.substring(0, s.length() - 1);// getAbjectById( list, this.selectedId ).value;
                    jtf.setText(s.trim());
                }

            }
        });
/*********
        insBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                //app.setMouseEventer(mp);

            }
        });
**********/
        JButton leftBtn = new JButton(" <<<< ");
        leftBtn.setFont(new Font("Curier New", Font.BOLD, 80));
        leftBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              setVisible(false);
                self.remove(p1);
                if(indexPage==9) indexPage=72;
                else indexPage-=9;
                p1 = createGridPan(3, 3, indexPage, alphabet_str);
                self.add(p1,BorderLayout.CENTER);
             setVisible(true);

            }
        });

        JButton getBtn = new JButton(" ins ");
        getBtn.setFont(new Font("Curier New", Font.BOLD, 80));
        getBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.setState(JFrame. ICONIFIED);



                GlassFrame gf = null;
                try {
                    gf = new GlassFrame(self);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        JButton rightBtn = new JButton(" >>>> ");
        rightBtn.setFont(new Font("Curier New", Font.BOLD, 80));
        rightBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                self.remove(p1);
                if(indexPage==72) indexPage=9;
                else indexPage+=9;
                p1 = createGridPan(3, 3, indexPage, alphabet_str);
                self.add(p1,BorderLayout.CENTER);
                setVisible(true);

            }
        });
        JPanel pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        pan2.add(leftBtn);
        pan2.add(getBtn);
        pan2.add(rightBtn);

        add(pan2, BorderLayout.SOUTH);
        setLocationRelativeTo(null);

        if(p1==null)
        p1 = createGridPan(3, 3, indexPage, alphabet_str);

        add(p1, BorderLayout.CENTER);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Cursor cursor = getCustomCursor();


        setCursor(cursor);
        setVisible(true);

    }

    public static Cursor getCustomCursor() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getBestCursorSize(48, 48);
        BufferedImage buffered = new BufferedImage(dim.width, dim.height,BufferedImage.TYPE_INT_ARGB);//GraphicsUtilities.createCompatibleTranslucentImage(dim.width, dim.height);
        Shape circle = new Ellipse2D.Float(0, 0, dim.width - 1, dim.height - 1);
        Graphics2D g = buffered.createGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.RED);
        g.draw(circle);
        g.setColor(Color.RED);
        int centerX = (dim.width - 1) /2;
        int centerY = (dim.height - 1) / 2;
        g.setStroke(new BasicStroke(10));
        g.drawLine(centerX, 0, centerX, dim.height - 1);
        g.drawLine(0, centerY, dim.height - 1, centerY);
        g.dispose();
        Cursor cursor = kit.createCustomCursor(buffered, new Point(centerX, centerY), "myCursor");
        return cursor;
    }

    public static boolean soundOn() throws  Exception {
        boolean soundOn = false;
        String line = null;
        File f = new File(System.getProperty("user.dir")+ "/res/app.settings");
        System.out.println(f);
        if(f.exists()) {
            BufferedReader in = new BufferedReader(new FileReader(f));

            while(true) {
                line = in.readLine();
                if(line==null) break;
                String s0 = (line.trim().split("=")[0].trim());
                String s1 = (line.trim().split("=")[1].trim());
                if(s0.equals("soundOn")) {
                     soundOn = Boolean.parseBoolean(s1);
                }
            }

        }
        return soundOn;
    }

    private JPanel createGridPan(int i, int j, int ind, String s) {

        String[] arr = s.substring(ind, ind+9).split("");
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(i, j));
        int n = 0;
        for (int j2 = 0; j2 < j; j2++) {
            for (int i2 = 0; i2 < i; i2++) {
                buttons[n] = new JButton(arr[n]);
                buttons[n].setFont(new Font("Curier New", Font.BOLD, 80));
                buttons[n].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s = jtf.getText().trim();
                        s +=  ((JButton) e.getSource()).getText().trim();
                        jtf.setText(s.trim());


                    }
                });
                pan.add(buttons[n]);
                n++;
            }
        }
        return pan;
    }

    @Override
    public void leftClickEvent(MouseEvent ev) throws Exception {
        int x = ev.getX();
        int y = ev.getY();
        System.out.println("x="+x+", y="+y);
        new Point(x,y) ;

        RobotActionWrapper.initRobot();
        RobotActionWrapper.mouseLeftClick(new Point(x,y));
        jtf.selectAll();
        jtf.copy();

        RobotActionWrapper.pressTwoKeys(RobotActionWrapper.getKeyCode("CONTROL"), RobotActionWrapper.getKeyCode("V"));
        RobotActionWrapper.pause(500);

    }

    @Override
    public boolean rightClickEvent(MouseEvent ev) throws Exception {
        return false;
    }
}



