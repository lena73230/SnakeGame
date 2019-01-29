import javax.swing.*;

public class Main {

    //constructor in a class
    public Main (){

        JFrame frame = new JFrame();
        GamePanel gamepanel = new GamePanel();

        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SNAKECODING");

        //frame will be the size of the gamepanel
        frame.pack();

        //frame visible
        frame.setVisible(true);

        //frame on the center of the screen
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main();
    }

}
