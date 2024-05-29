package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[13];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton, powerButton, sqrButton, invButton, sqrtButton;
    JPanel panel;

    Font myFont = new Font("Ink", Font.BOLD,20);

    boolean isOn = true;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);

        textfield = new JTextField();
        textfield.setBounds(90, 65, 200, 30);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setBackground(Color.LIGHT_GRAY);

        addButton = new JButton("+");
        addButton.setBackground(Color.PINK);
        subButton = new JButton("-");
        subButton.setBackground(Color.PINK);
        mulButton = new JButton("*");
        mulButton.setBackground(Color.PINK);
        divButton = new JButton("/");
        divButton.setBackground(Color.PINK);
        decButton = new JButton(".");
        decButton.setBackground(Color.PINK);
        equButton = new JButton("=");
        equButton.setBackground(Color.PINK);
        delButton = new JButton("Del");
        delButton.setBackground(Color.WHITE);
        clrButton = new JButton("Clr");
        clrButton.setBackground(Color.WHITE);
        negButton = new JButton("(-)");
        negButton.setBackground(Color.WHITE);
        powerButton = new JButton("Off/On");
        powerButton.setBackground(Color.LIGHT_GRAY);
        sqrButton = new JButton("x\u00B2");
        sqrButton.setBackground(Color.WHITE);
        invButton = new JButton("1/x");
        invButton.setBackground(Color.WHITE);
        sqrtButton = new JButton("<html>&radic;x</html>");
        sqrtButton.setBackground(Color.WHITE);


        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;
        functionButtons[9] = sqrButton;
        functionButtons[10] = invButton;
        functionButtons[11] = sqrtButton;


        for (int i = 0; i < 12; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.WHITE);
        }

        negButton.setBounds(90, 400, 100, 50);
        delButton.setBounds(190, 400, 100, 50);
        clrButton.setBounds(290, 400, 100, 50);
        powerButton.setBounds(340, 15, 100, 40);

        panel = new JPanel();
        panel.setBounds(90, 100, 300, 300);
        panel.setLayout(new GridLayout(6, 6, 15, 15));
        panel.setBackground(Color.GRAY);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);
        panel.add(sqrButton);
        panel.add(invButton);
        panel.add(sqrtButton);


        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.add(powerButton);

        powerButton.addActionListener(this);


        if (!isOn) {
            disableButtons();
        }

        frame.setVisible(true);
    }

    private void enableButtons() {
        for (JButton button : numberButtons) {
            button.setEnabled(true);
        }
        for (JButton button : functionButtons) {
            button.setEnabled(true);
        }
        powerButton.setEnabled(true);
    }

    private void disableButtons() {
        for (JButton button : numberButtons) {
            button.setEnabled(false);
        }
        for (JButton button : functionButtons) {
            button.setEnabled(false);
        }
        powerButton.setEnabled(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == powerButton) {
            isOn = !isOn;
            if (isOn) {
                enableButtons();
            } else {
                disableButtons();
            }
            return;
        }
        String input = textfield.getText();

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(input + i);
                return; // Exit method after handling number button event
            }
        }

        if (e.getSource() == decButton) {
            textfield.setText(input + ".");
        } else if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            num1 = Double.parseDouble(input);
            operator = e.getActionCommand().charAt(0);
            textfield.setText("");
        } else if (e.getSource() == equButton) {
            num2 = Double.parseDouble(input);
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        } else if (e.getSource() == clrButton) {
            textfield.setText("");
        } else if (e.getSource() == delButton) {
            if (!input.isEmpty()) {
                textfield.setText(input.substring(0, input.length() - 1));
            }
        } else if (e.getSource() == negButton) {
            double currentValue = Double.parseDouble(input);
            textfield.setText(String.valueOf(-currentValue));
        } else if (e.getSource() == sqrButton) {
            double currentValue = Double.parseDouble(textfield.getText());
            double squaredValue = currentValue * currentValue;
            textfield.setText(String.valueOf(squaredValue));
        } else if (e.getSource() == invButton) {
            double currentValue = Double.parseDouble(textfield.getText());
            if (currentValue != 0) {
                double inverseValue = 1 / currentValue;
                textfield.setText(String.valueOf(inverseValue));
            } else {
                textfield.setText("Error: Division by zero");
            }
        } else if (e.getSource() == sqrtButton) {
            double currentValue = Double.parseDouble(textfield.getText());
            if (currentValue >= 0) {
                double sqrtValue = Math.sqrt(currentValue);
                textfield.setText(String.valueOf(sqrtValue));
            } else {
                textfield.setText("Error: Square root of negative number");
            }
        }


    }
}