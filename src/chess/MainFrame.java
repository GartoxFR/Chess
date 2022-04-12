package chess;

import chess.player.Team;
import chess.test.TestFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    final int width = 800;
    final int height = 786;
    private JPanel mainBloc;
    private Board plateau;
    private BoardPanel game;
    private JPanel topBloc;
    private JPanel leftBloc;
    private JPanel rightBloc;
    private String playerW;
    private String playerB;
    private String timeDisplayW;
    private String timeDisplayB;
    private Timer t;
    private int minW; private int secW;
    private int minB; private int secB;
    private int timeMax;

    private ImageIcon background;
    private JLabel backgroundLabel;

    private ImageIcon sides;
    private JLabel leftLabel;
    private JLabel rightLabel;

    public MainFrame (Board plateau ,int timeMax) {

        this.timeMax = timeMax;
        this.plateau = plateau;
        minW=timeMax; minB=timeMax; secW=0; secB = 0;
        //Renseigne le nom des joueurs
        playerB = plateau.getPlayers()[0].getName();
        playerW = plateau.getPlayers()[1].getName();


        setLocation(290, 10);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //init min et sec (selon init de la fenetre de depart)

        game = new BoardPanel(plateau);
        this.addKeyListener(game);
        game.setBounds(128,176,530,530);

        topBloc = new JPanel();
        topBloc.setLayout(null);
        topBloc.setBounds(0, 0,this.width,96);
        this.background = new ImageIcon("res/images/background_war.png");
        backgroundLabel = new JLabel(this.background,JLabel.CENTER);
        backgroundLabel.setBounds(0,0,this.width,96);
        topBloc.add(backgroundLabel);

        leftBloc = new CapturedPiecePanel(Team.WHITE, plateau);
        leftBloc.setLayout(null);
        leftBloc.setBounds(0, 216, 128, 490);

        rightBloc = new CapturedPiecePanel(Team.BLACK, plateau);
        rightBloc.setLayout(null);
        rightBloc.setBounds(658, 216, 128, 490);

        Timer repaintTimer = new Timer(10, e -> {
            if (plateau.isShouldRender()) {
                this.repaint();
            }
        });
        repaintTimer.start();


        mainBloc = new JPanel();
        mainBloc.setLayout(null);
        mainBloc.setBounds(0,0,this.width, this.height);
        mainBloc.setBackground(Color.CYAN);

        mainBloc.add(game);
        mainBloc.add(topBloc);
        mainBloc.add(leftBloc);
        mainBloc.add(rightBloc);

        this.add(mainBloc);

        t = new Timer(1000,this);
        t.start();

        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(plateau.getToPlay() == Team.WHITE){
            if(secW>0){
                secW--;
            }else{
                secW=59;
                minW--;
            }
        }else if(plateau.getToPlay() == Team.BLACK){
            if(secB>0){
                secB--;
            }else{
                secB=59;
                minB--;
            }
        }
        timeDisplayW = String.format("temps restant %02d : %02d",minW, secW);
        timeDisplayB = String.format("temps restant %02d : %02d",minB, secB);
    }

    public String getPlayerW() {
        return this.playerW;
    }

    public String getPlayerB() {
        return this.playerB;
    }

    public String getTimeDisplayW() {
        return this.timeDisplayW;
    }

    public String getTimeDisplayB() {
        return this.timeDisplayB;
    }
}
