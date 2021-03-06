import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 500, HEIGHT = 500;

    private Thread thread;

    private boolean running;

    private boolean right = true, left = false, up = false, down = false;

    private BodyPart b;
    private ArrayList<BodyPart> snake;

    private Apple apple;
    private ArrayList<Apple> apples;

    private Random r;

    //where the head of the snake, length of it
    private int xCoor = 10, yCoor = 10, size = 10;
    private double ticks = 0;


    public GamePanel(){

        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);

        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();

        r = new Random();

        start();
    }


    public void start(){
        running = true;

        //"this" because Runnable interface
        thread = new Thread(this);

        //run()
        thread.start();
    }

    public void stop(){
        running = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //updating
    public void tick(){
        if(snake.size()==0){
            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);
        }

        //manipulate the speed
        ticks=ticks+0.4;

        if(ticks>250000){
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 10);
            snake.add(b);

            if(snake.size()>size){
                snake.remove(0);
            }
        }
        if(apples.size()==0){
            int xCoor = r.nextInt(49);
            int yCoor = r.nextInt(49);

            apple = new Apple(xCoor, yCoor, 10);
            apples.add(apple);
        }

        //snake eats
        for(int i=0; i<apples.size(); i++){
            if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()){
                size++;
                apples.remove(i);
                i++;
            }
        }

        //collision on snake body part
        for(int i=0; i<snake.size(); i++){
            if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){

                //checking the head and head is always equal the position of the head
                if(i != snake.size()-1){
                    System.out.println("Game Over");
                    stop();
                }
            }
        }

        //collision on border
        if(xCoor<0 || xCoor>49 || yCoor<0 || yCoor>49 ){
            System.out.println("Game Over");
            stop();

        }

    }

    //painting everything
    public void paint(Graphics g){
        g.clearRect(0,0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);

        //drawing the grid
        for(int i=0; i<WIDTH/10; i++){
            g.drawLine(i*10, 0,i*10, HEIGHT);
        }
        for(int i=0; i<HEIGHT/10; i++){
            g.drawLine(0, i*10, HEIGHT, i*10);
        }


        for (int i=0; i<snake.size(); i++){
            snake.get(i).draw(g);
        }
        for(int i = 0; i<apples.size(); i++){
            apples.get(i).draw(g);
        }

    }

    @Override
    public void run(){
        while(running){
            tick();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left){
            right = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_LEFT && !right){
            left = true;
            up = false;
            down = false;
        }
        if(key == KeyEvent.VK_UP && !down){
            up = true;
            left = false;
            right = false;
        }
        if(key == KeyEvent.VK_DOWN && !up){
            down = true;
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0){

    }

    @Override
    public void keyTyped(KeyEvent arg0){

    }

}
