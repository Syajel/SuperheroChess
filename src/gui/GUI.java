package GUI;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;

public final class GUI extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Piece p;
    JLabel playr1;
    JLabel playr2;
    JPanel board;
    JButton[][] Cell;

    JPanel payload1;
    JButton[] pay1;
    JPanel payload2;
    JButton[] pay2;
    Game game;

    private final JPanel deadc1;
    private final JPanel deadc2;
    JLabel qe;
    JPanel directionpad;
    JButton up;
    JButton upleft;
    JButton upright;
    JButton down;
    JButton downleft;
    JButton downright;
    JButton left;
    JButton right;
    JButton power;
    private JButton temp;
    private JButton temp2;
    private final String player1;
    private final String player2;
    private final JButton newgame;
    private Direction direction;
    private Piece target;
    private JButton targettemp;
    private Point newpos;
    private final JPanel techpower;
    private final JButton teleport;
    private final JButton cancel;
    private final JButton restorepower;
    private JButton deadcharacter1;
    private JButton deadcharacter2;
    private int panew;
    private int pa2new;

    public GUI() {

        temp = null;
        // getContentPane().setBackground(Color.LIGHT_GRAY);

        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setTitle("Super Heroes Chess");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        playr1 = new JLabel();
        playr2 = new JLabel();
        JLabel background = new JLabel(new ImageIcon("..\\assets\\background.png"));
        background.setBounds(0, 0, 1280, 800);

        player1 = JOptionPane.showInputDialog(null, "Enter Player 1 name");
        playr1.setText(player1);
        playr1.setFont(playr1.getFont().deriveFont(20f));
        playr1.setIconTextGap(-400);
        playr1.setForeground(Color.BLACK);

        player2 = JOptionPane.showInputDialog(null, "Enter Player 2 name");
        playr2.setText(player2);
        playr2.setFont(playr2.getFont().deriveFont(20f));
        playr2.setIconTextGap(-400);

        playr2.setForeground(Color.BLACK);

        pay1 = new JButton[7];
        payload1 = new JPanel();
        payload1.setLayout(new GridLayout(7, 1));

        pay2 = new JButton[7];
        payload2 = new JPanel();
        payload2.setLayout(new GridLayout(7, 1));
        techpower = new JPanel();
        techpower.setLayout(new BorderLayout());
        teleport = new JButton("teleport");
        cancel = new JButton("Cancel");
        restorepower = new JButton("restore");
        techpower.add(teleport, BorderLayout.WEST);
        techpower.add(restorepower, BorderLayout.EAST);
        techpower.add(cancel, BorderLayout.SOUTH);

        game = new Game(new Player(player1), new Player(player2));

        for (int k = 6; k >= 0; k--) {

            pay1[k] = new JButton(" ");
            pay1[k].setBackground(new Color(25 * k, 5 * k, 5 * k));
            pay1[k].setBorderPainted(false);
            payload1.add(pay1[k]);

        }

        for (int l = 0; l <= 6; l++) {
            pay2[l] = new JButton(" ");
            pay2[l].setBackground(new Color(5 * l, 10 * l, 25 * l));
            pay2[l].setBorderPainted(false);
            payload2.add(pay2[l]);
        }

        Cell = new JButton[7][6];
        board = new JPanel();
        board.setLayout(new GridLayout(7, 6));
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 5; j++) {
                Cell[i][j] = new JButton();

                // Cell[i][j].setBorderPainted(false);
                Cell[i][j].setBackground(new Color(30 * i, 0, 210 - 30 * i));
                board.add(Cell[i][j]);

            }

        }

        deadc1 = new JPanel();
        deadc2 = new JPanel();

        board.setBounds(360, 60, 500, 580);
        playr1.setBounds(360, 640, 500, 50);
        playr2.setBounds(360, 10, 500, 50);
        payload1.setBounds(0, -10, 50, 720);
        payload2.setBounds(1230, -10, 50, 720);
        deadc1.setBounds(300, 60, 60, 580);
        deadc2.setBounds(860, 60, 60, 580);
        techpower.setBounds(60, 300, 150, 80);
        qe = new JLabel();
        qe.setBounds(60, 80, 200, 40);

        qe.setBackground(Color.red);
        qe.setOpaque(true);

        up = new JButton("up");

        up.setIcon(new ImageIcon("..\\assets\\up.png"));
        up.setBorderPainted(false);
        up.addActionListener(this);
        up.setContentAreaFilled(false);
        up.setActionCommand("10");
        up.setBackground(Color.DARK_GRAY);

        right = new JButton("right");
        right.setBorderPainted(false);
        right.addActionListener(this);
        right.setIcon(new ImageIcon("..\\assets\\right.png"));
        right.setContentAreaFilled(false);
        right.setActionCommand("21");

        down = new JButton("down");
        down.setBorderPainted(false);
        down.addActionListener(this);
        down.setIcon(new ImageIcon("..\\assets\\down.png"));
        down.setContentAreaFilled(false);
        down.setActionCommand("12");

        upleft = new JButton("upleft");
        upleft.setBorderPainted(false);
        upleft.addActionListener(this);
        upleft.setIcon(new ImageIcon("..\\assets\\upleft.png"));
        upleft.setContentAreaFilled(false);
        upleft.setActionCommand("00");

        downright = new JButton("downright");
        downright.setBorderPainted(false);
        downright.addActionListener(this);
        downright.setIcon(new ImageIcon("..\\assets\\downright.png"));
        downright.setContentAreaFilled(false);
        downright.setActionCommand("22");

        downleft = new JButton("Downleft");
        downleft.setBorderPainted(false);
        downleft.addActionListener(this);
        downleft.setIcon(new ImageIcon("..\\assets\\downleft.png"));
        downleft.setContentAreaFilled(false);
        downleft.setActionCommand("02");

        upright = new JButton("upright");
        upright.addActionListener(this);
        upright.setBorderPainted(false);
        upright.setIcon(new ImageIcon("..\\assets\\upright.png"));
        upright.setContentAreaFilled(false);
        upright.setActionCommand("20");

        left = new JButton("left");
        left.setBorderPainted(false);
        left.addActionListener(this);
        left.setIcon(new ImageIcon("..\\assets\\left.png"));
        left.setContentAreaFilled(false);
        left.setActionCommand("01");

        power = new JButton("Use Power");
        power.setBorderPainted(false);
        power.addActionListener(this);
        power.setContentAreaFilled(false);
        power.setActionCommand("11");

        deadc1.setLayout(new GridLayout(9, 1));
        deadc2.setLayout(new GridLayout(9, 1));

        directionpad = new JPanel();
        directionpad.setLayout(new GridLayout(3, 3));
        directionpad.add(upleft);
        directionpad.add(up);
        directionpad.add(upright);
        directionpad.add(left);
        directionpad.add(power);
        directionpad.add(right);
        directionpad.add(downleft);
        directionpad.add(down);
        directionpad.add(downright);
        directionpad.setBounds(80, 400, 210, 210);

        setResizable(false);
        newgame = new JButton("new game");
        newgame.setBounds(60, 180, 200, 40);
        newgame.addActionListener(this);
        add(directionpad);
        add(board);
        add(payload1);
        add(payload2);
        add(playr2);
        add(playr1);

        add(qe);
        add(newgame);
        add(deadc1);
        add(deadc2);

        assembl();
        currentplayer();
        payloadcheck();
        checkdead();
    }

    public void payloadcheck() {
        int k = game.getPlayer1().getPayloadPos();
        for (int i = 0; i <= k; i++) {

            pay1[i].setBackground(Color.RED);
        }

        if (k >= 6) {
            JOptionPane.showMessageDialog(getContentPane(), ((player1 + "   WON!!")));
            System.exit(0);

        }

        int l = game.getPlayer2().getPayloadPos();

        for (int i = 0; i <= l; i++) {
            pay2[i].setBackground(Color.BLUE);
        }
        if (l >= 6) {
            JOptionPane.showMessageDialog(getContentPane(), ((player2 + "  WON!!")));
            System.exit(0);
        }
    }

    public Point getpoint(JButton e) {
        String loc = e.getActionCommand();
        int location = Integer.parseInt(loc);
        int x = location / 10;
        int y = location % 10;
        Point newpoint = new Point(x, y);
        return newpoint;
    }

    public static String tos(Piece a) {

        String s = "";

        if (a instanceof Armored) {
            s = "Armored";
        }
        if (a instanceof Super) {
            s = "super";
        }
        if (a instanceof Speedster) {
            s = "Speedster";
        }
        if (a instanceof Tech) {
            s = "Tech";
        }
        if (a instanceof SideKick) {
            s = "Sidekick";
        }
        if (a instanceof Medic) {
            s = "Medic";
        }

        return s;

    }

    public void assembl() {

        for (int l = 0; l < game.getBoardHeight(); l++) {
            for (int m = 0; m < game.getBoardWidth(); m++) {
                p = game.getCellAt(l, m).getPiece();
                if (p != null) {

                    if (p.getOwner() == game.getPlayer2() && p instanceof Armored) {

                        if (((Armored) p).isArmorUp()) {
                            Cell[l][m].setIcon(new ImageIcon("..\\assets\\captainamerica.png"));
                        } else {
                            Cell[l][m].setIcon(new ImageIcon("..\\assets\\captainamerica.png"));
                        }
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setText("Captain America");
                        Cell[l][m].setActionCommand(l + "" + m);

                    }
                    if (p.getOwner() == game.getPlayer1() && p instanceof Medic) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\greenlantern.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setText("Green Lantern");
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer2() && p instanceof Medic) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\vision.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setText("Vision");
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer1() && p instanceof Ranged) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\greenarrow.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setText("Green Arrow");
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer2() && p instanceof Ranged) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\hawkeye.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setText("Hawk Eye");
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer1() && p instanceof Speedster) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\flash.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer2() && p instanceof Speedster) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\vision.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer1() && p instanceof Super) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\superman.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer2() && p instanceof Super) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\hulk.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer1() && p instanceof Tech) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\batman.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer2() && p instanceof Tech) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\ironman.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }
                    if (p.getOwner() == game.getPlayer1() && p instanceof Armored) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\wonderwoman.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);
                    }

                    if (p instanceof SideKickP1) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\robin.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);

                    }
                    if (p instanceof SideKickP2) {
                        Cell[l][m].setIcon(new ImageIcon("..\\assets\\spiderman.png"));
                        Cell[l][m].setToolTipText(toString(p));
                        Cell[l][m].addActionListener(this);
                        Cell[l][m].setActionCommand(l + "" + m);

                    }
                } else {
                    Cell[l][m].setIcon(new ImageIcon("..\\assets\\aw.jpg"));
                    Cell[l][m].setToolTipText(null);
                    Cell[l][m].setText(null);

                }
            }
        }
    }

    public static String toString(Piece a) {
        String s = "<html> " + "Hero: " + a.getName() + "<br>" + "     Player: " + a.getOwner().getName();

        String as = "";
        if (a instanceof Armored) {
            as = "Armored";
        }
        if (a instanceof Ranged) {
            as = "Ranged";
        }
        if (a instanceof Tech) {
            as = "Tech";
        }
        if (a instanceof Super) {
            as = "Super";
        }
        if (a instanceof SideKick) {
            as = "Sidekick";
        }
        if (a instanceof Speedster) {
            as = "Speedster";
        }
        if (a instanceof Medic) {
            as = "Medic";
        }

        s += "<html><br>" + "Type: " + as + "<br>";

        switch (a) {
            case Armored armored -> {
                if (armored.isArmorUp()) {
                    s += " <html>Armor: Up  </html>  ";
                } else {
                    s += "<html> Armor: Down </html>   ";
                }
            }
            case ActivatablePowerHero activatablePowerHero -> {
                if (activatablePowerHero.isPowerUsed()) {
                    s += " <html> Power Used </html>   ";
                } else {
                    s += "<html>  Power Not Used </html> ";
                }
            }
            default -> {
            }
        }

        return s;
    }

    public void currentplayer() {
        if (game.getCurrentPlayer() == game.getPlayer1()) {
            playr1.setIcon(new ImageIcon("..\\assets\\p1curr.png"));
            playr2.setIcon(new ImageIcon("..\\assets\\p2.png"));
            playr2.setText(player2);
            playr1.setText("Current Player :  " + playr1.getText());

        }
        if (game.getCurrentPlayer() == game.getPlayer2()) {
            playr2.setIcon(new ImageIcon("..\\assets\\p2curr.png"));
            playr1.setIcon(new ImageIcon("..\\assets\\p1.png"));
            playr1.setText(player1);
            playr2.setText("Current Player :  " + playr2.getText());

        }
    }

    public Direction getdir(JButton e) {
        String loc = e.getActionCommand();
        int location = Integer.parseInt(loc);
        int x = location / 10;
        int y = location % 10;
        Direction a = null;

        if (x == 0 && y == 0) {
            a = Direction.UPLEFT;
        }
        if (x == 0 && y == 1) {
            a = Direction.LEFT;
        }
        if (x == 0 && y == 2) {
            a = Direction.DOWNLEFT;
        }
        if (x == 1 && y == 0) {
            a = Direction.UP;
        }
        if (x == 2 && y == 0) {
            a = Direction.UPRIGHT;
        }
        if (x == 1 && y == 1) {
            a = null;
        }
        if (x == 2 && y == 1) {
            a = Direction.RIGHT;
        }
        if (x == 2 && y == 2) {
            a = Direction.DOWNRIGHT;
        }
        if (x == 1 && y == 2) {
            a = Direction.DOWN;
        }
        return a;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((JButton) e.getSource() == newgame) {
            System.exit(0);
        }
        if (temp == null) {
            if ((JButton) e.getSource() == up || (JButton) e.getSource() == upleft || (JButton) e.getSource() == upright
                    || (JButton) e.getSource() == down || (JButton) e.getSource() == downleft
                    || (JButton) e.getSource() == downright || (JButton) e.getSource() == left
                    || (JButton) e.getSource() == right || (JButton) e.getSource() == power) {
                return;
            }

            temp = (JButton) e.getSource();
            String loc = temp.getActionCommand();
            int location = Integer.parseInt(loc);
            int i = location / 10;
            int j = location % 10;
            p = game.getCellAt(i, j).getPiece();
            if (p.getOwner() == game.getPlayer1() && p instanceof Tech) {
                temp.setIcon(new ImageIcon("..\\assets\\batmanselected.png"));
            }
            if (p.getOwner() == game.getPlayer1() && p instanceof Super) {
                temp.setIcon(new ImageIcon("..\\assets\\supermanselected.png"));
            }

            qe.setText(p.getName());

            if (p instanceof ActivatablePowerHero) {
                if ("Superman".equals(p.getName())) {
                    power.setIcon(new ImageIcon("..\\assets\\supermanpower.png"));
                } else {
                    power.setIcon(new ImageIcon("..\\assets\\supermanpoer.png"));
                }
            }
        }
        if (temp != null) {

            if ((JButton) e.getSource() == up && temp2 != power) {

                try {
                    p.move(Direction.UP);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;
                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;

                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;
                }
            }
            if ((JButton) e.getSource() == down && temp2 != power) {

                try {
                    p.move(Direction.DOWN);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;
                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;
                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;
                }
            }
            if ((JButton) e.getSource() == upleft && temp2 != power) {

                try {
                    p.move(Direction.UPLEFT);
                    assembl();
                    currentplayer();
                    checkdead();
                    payloadcheck();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;

                    return;
                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;
                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;

                }

            }
            if ((JButton) e.getSource() == upright && temp2 != power) {

                try {
                    p.move(Direction.UPRIGHT);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;

                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;

                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;

                }

            }
            if ((JButton) e.getSource() == left && temp2 != power) {

                try {
                    p.move(Direction.LEFT);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;

                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;

                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;

                }

            }
            if ((JButton) e.getSource() == right && temp2 != power) {

                try {
                    p.move(Direction.RIGHT);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;

                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;

                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;

                }

            }
            if ((JButton) e.getSource() == downright && temp2 != power) {

                try {
                    p.move(Direction.DOWNRIGHT);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;

                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;
                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;

                }

            }
            if ((JButton) e.getSource() == downleft && temp2 != power) {

                try {
                    p.move(Direction.DOWNLEFT);
                    assembl();
                    currentplayer();
                    payloadcheck();
                    checkdead();
                    temp = null;
                    return;
                } catch (UnallowedMovementException u) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot Move in this Direction");
                    temp = null;
                    return;

                } catch (WrongTurnException w) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This is not " + ((p.getOwner() == game.getPlayer1()) ? player1 : player2) + "'s turn");
                    temp = null;
                    return;

                } catch (OccupiedCellException o) {
                    JOptionPane.showMessageDialog(getContentPane(), "Cannot attack a friendly piece");
                    temp = null;
                    return;

                }
            }

            if ((JButton) e.getSource() == power) {
                temp2 = power;
                if (!(p instanceof ActivatablePowerHero)) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "This type of Heroes does not have an activatable power");
                    temp = null;
                    temp2 = null;
                    return;
                }
                return;
            }

            if (temp2 == power) {

                if (p instanceof Medic medic) {

                    targettemp = (JButton) e.getSource();
                    String loca = targettemp.getActionCommand();
                    System.out.println(loca);

                    if (p.getOwner() == game.getPlayer1()) {
                        int posa = Integer.parseInt(loca);
                        target = game.getPlayer1().getDeadCharacters().get(posa);
                        JOptionPane.showMessageDialog(getContentPane(), posa + "");

                    }
                    if (p.getOwner() == game.getPlayer2()) {
                        int posa = Integer.parseInt(loca);
                        target = game.getPlayer2().getDeadCharacters().get(posa);

                    }
                    if (targettemp != null) {
                        newpos = getpoint((JButton) e.getSource());
                    }
                    try {
                        medic.usePower(null, target, newpos);
                        assembl();
                        currentplayer();
                        payloadcheck();
                        checkdead();
                        temp = null;
                        temp2 = null;
                        p = null;
                        target = null;
                        targettemp = null;
                        return;
                    } catch (NullPointerException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                    } catch (InvalidPowerUseException | WrongTurnException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                    }
                }

                if (p instanceof Ranged || p instanceof Super) {
                    if ((JButton) e.getSource() == up || (JButton) e.getSource() == upleft
                            || (JButton) e.getSource() == upright || (JButton) e.getSource() == down
                            || (JButton) e.getSource() == downleft || (JButton) e.getSource() == downright
                            || (JButton) e.getSource() == left || (JButton) e.getSource() == right) {
                        direction = getdir((JButton) e.getSource());
                    }
                }
                if (p instanceof Tech tech) {

                    targettemp = (JButton) e.getSource();
                    String loc2 = targettemp.getActionCommand();
                    int location2 = Integer.parseInt(loc2);
                    int i2 = location2 / 10;
                    int j2 = location2 % 10;
                    target = game.getCellAt(i2, j2).getPiece();
                    if (target instanceof SideKick) {
                        JOptionPane.showMessageDialog(getContentPane(), "cannot hack a sidekick");

                    }

                    try {
                        tech.usePower(null, target, null);
                        assembl();
                        payloadcheck();
                        temp = null;
                        temp2 = null;
                        p = null;
                        target = null;
                        return;

                    } catch (NullPointerException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                        return;
                    } catch (InvalidPowerDirectionException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                        return;
                    } catch (WrongTurnException | PowerAlreadyUsedException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                        return;
                    } catch (InvalidPowerUseException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                        return;
                    }
                }

                if (p instanceof Ranged ranged) {
                    try {
                        ranged.usePower(direction, null, null);
                        assembl();
                        currentplayer();
                        payloadcheck();
                        checkdead();
                        temp = null;
                        temp2 = null;
                        return;
                    } catch (NullPointerException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                    } catch (InvalidPowerUseException | WrongTurnException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                    }
                }

                if (p instanceof Super aSuper) {
                    try {
                        aSuper.usePower(direction, null, null);
                        assembl();
                        currentplayer();
                        payloadcheck();
                        checkdead();
                        temp = null;
                        temp2 = null;
                    } catch (NullPointerException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), "fe eeeh");
                        temp = null;
                        temp2 = null;
                    } catch (InvalidPowerUseException | WrongTurnException e1) {

                        JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
                        temp = null;
                        temp2 = null;
                    }
                }

            }
        }
    }

    public void checkdead() {
        int pa = game.getPlayer1().getDeadCharacters().size() - 1;

        if (pa >= 0) {
            deadcharacter1 = new JButton(game.getPlayer1().getDeadCharacters().get(pa).getName());
            deadcharacter1.setActionCommand(pa + "");
            deadcharacter1.setBackground(new Color(100, 40, 40));
            deadcharacter1.setForeground(Color.WHITE);
            deadcharacter1.setFont(deadcharacter1.getFont().deriveFont(10f));
            deadcharacter1.addActionListener(this);
            if (panew < pa) {
                if (game.getPlayer1().getDeadCharacters().get(pa) != null) {
                    deadc1.add(deadcharacter1);
                }
            }
        }
        int pa2 = game.getPlayer2().getDeadCharacters().size() - 1;
        if (pa2 >= 0) {
            deadcharacter2 = new JButton(game.getPlayer2().getDeadCharacters().get(pa2).getName());
            deadcharacter2.setActionCommand(pa2 + "");
            deadcharacter2.setBackground(new Color(40, 40, 100));
            deadcharacter2.setForeground(Color.WHITE);
            deadcharacter2.setFont(deadcharacter2.getFont().deriveFont(10f));
            deadcharacter2.addActionListener(this);
            if (pa2new < pa2) {
                if (game.getPlayer2().getDeadCharacters().get(pa2) != null) {
                    deadc2.add(deadcharacter2);
                }
            }
        }
        panew = pa;
        pa2new = pa2;

    }

    public static void main(String[] args) {
        GUI a = new GUI();
        a.setVisible(true);
        a.game.toString();
    }
}
