/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunnydays;


import java.awt.event.*;
import java.awt.*;
public class SketchPad extends Frame implements ActionListener, WindowListener, MouseListener, MouseMotionListener, ItemListener {

    String selectedShape = new String("Square");
    String selectedColor = new String("Blue");
    boolean Eraser = false;
    int upLX, upLY, W, H, selX1, selY1, selX2, selY2;
    String[] extrasList = {"Clear Canvas", "Eraser"};
    String[] colorList = {"Black", "Cyan", "Green", "Yellow", "Magenta", "Red", "Blue"};
    String[] shapeList = {"Line", "Rectangle", "Square", "Circle"};

	
	@Override //from WindowListener
    public void windowClosing(WindowEvent eve) {
        System.exit(0);
    }

	@Override //from WindowListener
    public void windowActivated(WindowEvent we) {
    }

	@Override //from WindowListener
    public void windowOpened(WindowEvent we) {
    }

	@Override //from WindowListener
    public void windowIconified(WindowEvent we) {
    }

	@Override //from WindowListener
    public void windowClosed(WindowEvent we) {
    }

	@Override //from WindowListener
    public void windowDeactivated(WindowEvent we) {
    }

	@Override //from WindowListener
    public void windowDeiconified(WindowEvent we) {
    }

	@Override //from MouseMotionListener
    public void mouseMoved(MouseEvent me) {
    }

	@Override //from MouseListener
    public void mouseClicked(MouseEvent me) {
    }

	@Override //from MouseListener
    public void mouseExited(MouseEvent me) {
    }

	@Override //from MouseListener
    public void mouseEntered(MouseEvent me) {
    }

	@Override //from ItemListener
    public void itemStateChanged(ItemEvent ie) {
    }

    public SketchPad(String title) {
        super(title);
        addMouseMotionListener(this);
        addWindowListener(this);
        addMouseListener(this);
        setLayout(null);
        setMenuItems();
        setBackground(Color.white);
    }

	@Override //from ActionListener
    public void actionPerformed(ActionEvent ape) {
        Graphics ga = getGraphics();
        Object s = ape.getActionCommand();
        for (int i = 0; i != colorList.length; i++) {
            if (s.equals(colorList[i])) {
                selectedColor = colorList[i];
                return;
            }
        }
        for (int i = 0; i != shapeList.length; i++) {
            if (s.equals(shapeList[i])) {
                selectedShape = shapeList[i];
                return;
            }
        }
        if (s.equals("Eraser")) {
            Eraser = true;
            return;
        } else if (s.equals("Clear Canvas")) {
            ga.clearRect(0, 0, 700, 700);
            return;
        }
    }

    void chooseColor(Graphics ga) {
        for (int i = 0; i != colorList.length; i++) {
            if (selectedColor.equals(colorList[i])) {
                switch (i) {
                    case 0:
                        ga.setColor(Color.black);
                        break;
                    case 1:
                        ga.setColor(Color.cyan);
                        break;
                    case 2:
                        ga.setColor(Color.green);
                        break;
                    case 3:
                        ga.setColor(Color.yellow);
                        break;
                    case 4:
                        ga.setColor(Color.magenta);
                        break;
                    case 5:
                        ga.setColor(Color.red);
                        break;
                    case 6:
                        ga.setColor(Color.blue);
                }
            }
        }
    }

	@Override //from MouseListener
    public void mouseReleased(MouseEvent me) {
        Graphics ga = getGraphics();
        if (Eraser) {
            Eraser = false;
            return;
        }
        chooseColor(ga);
        selX2 = me.getX();
        selY2 = me.getY();
        if (selectedShape.equals("Line")) {
            ga.drawLine(selX1, selY1, selX2, selY2);
        } else if (selectedShape.equals("Circle")) {
            drawSelectedShape(ga, "Circle");
        } else if (selectedShape.equals("Square")) {
            drawSelectedShape(ga, "Square");
        } else if (selectedShape.equals("Rectangle")) {
            drawSelectedShape(ga, "Rectangle");
        }
        ga.setColor(Color.yellow);
        ga.drawString(".", selX1, selY1);
        ga.setColor(Color.black);
    }

    void drawSelectedShape(Graphics ga, String sel_shape) {
        upLX = Math.min(selX1, selX2);
        upLY = Math.min(selY1, selY2);
        W = Math.abs(selX1 - selX2);
        H = Math.abs(selY1 - selY2);
        if (sel_shape.equals("Square")) {
            ga.fillRect(upLX, upLY, W, W);
        } else if (sel_shape.equals("Rectangle")) {
            ga.fillRect(upLX, upLY, W, H);
        } else if (sel_shape.equals("Circle")) {
            ga.fillOval(upLX, upLY, W, W);
        }
    }

	@Override //from MouseMotionListener
    public void mouseDragged(MouseEvent me) {
        Graphics ga = getGraphics();
        selX2 = me.getX();
        selY2 = me.getY();
        if (Eraser) {
            ga.setColor(Color.white);
            ga.fillRect(selX2, selY2, 10, 10);
        }
    }

	@Override //from MouseListener
    public void mousePressed(MouseEvent me) {
        if (Eraser) {
            return;
        }
        upLX = 0;
        upLY = 0;
        W = 0;
        H = 0;
        selX1 = me.getX();
        selY1 = me.getY();
        Graphics ga = getGraphics();
        ga.drawString(".", selX1, selY1);
    }

    void setMenuItems() {
        MenuBar mBar = new MenuBar();
        Menu menuShape = new Menu("Shape");
        for (int i = 0; i != shapeList.length; i++) {
            menuShape.add(shapeList[i]);
        }
        mBar.add(menuShape);
        menuShape.addActionListener(this);
        Menu menuColor = new Menu("Colors");
        for (int i = 0; i != colorList.length; i++) {
            menuColor.add(colorList[i]);
        }
        mBar.add(menuColor);
        menuColor.addActionListener(this);
        Menu menuExtras = new Menu("Extras");
        for (int i = 0; i != extrasList.length; i++) {
            menuExtras.add(extrasList[i]);
        }
        mBar.add(menuExtras);
        menuExtras.addActionListener(this);
        setMenuBar(mBar);
    }
	
	//main method menuExtrasecution starts here
	public static void main(String[] args) {
        SketchPad sp = new SketchPad("Sketchpad in Java");
        sp.setSize(700, 700);
        sp.setVisible(true);
    }
}
