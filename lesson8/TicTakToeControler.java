package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTakToeControler extends JFrame {
    int SIZE;
    TicTacToe ttt;
    JButton[] jbs;
    final char DOT_HUMAN = 9587;
    final char DOT_AI = 9711;

    public TicTakToeControler(int SIZE) {
        this.SIZE = SIZE;

        ttt = new TicTacToe(SIZE);
        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);

        jbs = new JButton[SIZE * SIZE];
        setLayout(new GridLayout(SIZE, SIZE));

        System.out.println();

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                int index = Integer.parseInt(button.getName().substring(2,7).trim());


                if (click(button, index)) {
                    //перезапускаем игру
                    for (JButton b: jbs)
                        b.setText("");
                    ttt.initMap();
                    
                }
            }
        };
        float fSize = switch (SIZE) {
            case 3 -> 60f;
            case 4 -> 54f;
            case 5 -> 37f;
            case 6 -> 26f;
            case 7 -> 18f;
            case 8 -> 13f;
            default -> 2f;
        };
        for (int i = 0; i < jbs.length; i++) {
            jbs[i] = new JButton();
            jbs[i].setFont(jbs[i].getFont().deriveFont(Font.BOLD,fSize));
            jbs[i].setName(String.format("B_%5d",i));
            jbs[i].addActionListener(listener);
            add(jbs[i]);

        }
        setVisible(true);
    }

    boolean click(JButton button, int index){
        if (ttt.humanTurn(index / SIZE, index % SIZE)) {
            button.setText(String.valueOf(DOT_HUMAN));
            if (ttt.checkEnd(index / SIZE, index % SIZE, "Вы выйграли")) {
                showDialog("Вы выйграли");
                return true;
            } else {
                if (ttt.isMapFull()) {
                    showDialog("Ничья");
                    return true;
                }
                int aiIndex = ttt.aiTurn();
                jbs[aiIndex].setText(String.valueOf(DOT_AI));
                if (ttt.checkEnd(aiIndex / SIZE, aiIndex % SIZE,"К сожалению, Вы проиграли...")){
                    showDialog("К сожалению, Вы проиграли...");
                    return true;
                }
            }
        }
        if (ttt.isMapFull()) {
            showDialog("Ничья");
            return true;
        }
        return false;
    }

    void showDialog(String message){
        JOptionPane.showMessageDialog(TicTakToeControler.this, message);
    }
}

class MainClass {
    public static void main(String[] args) {
        new TicTakToeControler(3);
    }
}
