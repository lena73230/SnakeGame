import javax.swing.*;

public class Main {

    public Main (){

       // JFrame frame1 = new JFrame();
       // Start start = new Start();

        JFrame frame = new JFrame();
        GamePanel gamepanel = new GamePanel();

        //frame1.add(start);

        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SNAKECODING");


        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main();
    }

}
