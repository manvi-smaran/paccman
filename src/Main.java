import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import javax.swing.*;
public class Main extends JPanel implements ActionListener, KeyListener {

    class Block {
     int x;
     int y;
     int width;
     int height;
     Image image;

    int Startx;
     int Starty;
        char direction='U';//U D L R
        int velocityX=0;
        int velocityY=0;

     Block(Image image,int x, int y, int width, int height) {
         this.image = image;
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
         this.Startx = x;
         this.Starty = y;
     }
     void updateDirection(char direction) {
         char prevDirection = this.direction;
         int prevX = this.x;
         int prevY = this.y;
         this.direction = direction;
         updateVelocity();
         this.x += this.velocityX;
         this.y += this.velocityY;
         boolean wallhit=false;
         for (Block wall :walls){
             if(collision(this, wall)){
                 wallhit=true;
             }
         }
         if(wallhit){
             this.x=prevX;
             this.y=prevY;
             this.direction = prevDirection;
             updateVelocity();
         }
         else {
             this.x=prevX;
             this.y=prevY;

         }
     }
    void updateVelocity(){
         if(this.direction=='U'){
             this.velocityX=0;
             this.velocityY=-tilesize/4; // speed is 8 pixels
         }
         else if(this.direction=='D'){
             this.velocityY=tilesize/4;
             this.velocityX=0;
         }
         else if(this.direction=='L'){
             this.velocityX=-tilesize/4;
             this.velocityY=0;
         }
         else if(this.direction=='R'){
             this.velocityX=tilesize/4;
             this.velocityY=0;
         }
    }
   void reset(){
         this.x=this.Startx;
         this.y=this.Starty;
   }
 }

   private int rowcount =21;
    private int colcount =19;
    private int tilesize =32;
  private   int boardwidth = colcount * tilesize;
    private int boardheight = rowcount * tilesize;
 private Image wallImage;
 private Image blueGhostImage;
    private Image pinkGhostImage;
    private Image redGhostImage;
    private Image orangeGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;


    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };
    HashSet<Block> walls;
    HashSet<Block> Food;
    HashSet<Block> Ghosts;
    Block pacman;
 char[] directions={'U','D','L','R'};
 Random random = new Random();

    Timer gameLoop;
    int score=0;
    int lives=3;
    boolean gameOver=false;
    boolean gamepaused=false;

    Main(){
        setPreferredSize(new Dimension(boardwidth, boardheight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);


        wallImage = new ImageIcon(getClass().getResource("wall.png")).getImage();
        blueGhostImage = new ImageIcon(getClass().getResource("blueGhost.png")).getImage();
        pinkGhostImage = new ImageIcon(getClass().getResource("pinkGhost.png")).getImage();
        redGhostImage = new ImageIcon(getClass().getResource("redGhost.png")).getImage();
        orangeGhostImage = new ImageIcon(getClass().getResource("orangeGhost.png")).getImage();

        pacmanUpImage = new ImageIcon(getClass().getResource("pacmanUp.png")).getImage();
        pacmanDownImage = new ImageIcon(getClass().getResource("pacmanDown.png")).getImage();
        pacmanLeftImage = new ImageIcon(getClass().getResource("pacmanLeft.png")).getImage();
        pacmanRightImage = new ImageIcon(getClass().getResource("pacmanRight.png")).getImage();

        loadMap();
        for(Block ghost:Ghosts){
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
        gameLoop = new Timer(50, this);
        gameLoop.start();


    }
    public void loadMap(){
        walls = new HashSet<Block>();
        Food = new HashSet<Block>();
        Ghosts = new HashSet<Block>();

        for(int r=0;r<rowcount;r++){
            for(int c=0;c<colcount;c++){
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x=c*tilesize;
                int y=r*tilesize;
                if (tileMapChar == 'X'){
                    //block wall
                    Block wall=new Block(wallImage, x, y, tilesize, tilesize);
                    walls.add(wall);
                    
                } else if (tileMapChar == 'b') {//blue ghost
                    Block ghost = new Block(blueGhostImage,x,y, tilesize, tilesize);
                    Ghosts.add(ghost);
                    
                }
                else if (tileMapChar == 'o') {//orange ghost
                    Block ghost = new Block(orangeGhostImage,x,y, tilesize, tilesize);
                    Ghosts.add(ghost);

                }
                else if (tileMapChar == 'p') {//pink ghost
                    Block ghost = new Block(pinkGhostImage,x,y, tilesize, tilesize);
                    Ghosts.add(ghost);

                }
                else if (tileMapChar == 'r') {//red ghost
                    Block ghost = new Block(redGhostImage,x,y, tilesize, tilesize);
                    Ghosts.add(ghost);

                }
                else if (tileMapChar == 'P') {//pacman
                    pacman = new Block(pacmanRightImage,x,y, tilesize, tilesize);
                }
                else if (tileMapChar == ' ') {//food
                    Block food = new Block(null,x+14,y+14, 4, 4);
                  Food.add(food);
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        for(Block wall:walls){
            g.drawImage(wall.image,wall.x,wall.y,wall.width,wall.height, null);
        }
        g.setColor(Color.PINK);
        for(Block food:Food){
            g.fillRect(food.x,food.y,food.width,food.height);
        }
        for(Block ghost:Ghosts){
            g.drawImage(ghost.image,ghost.x,ghost.y,ghost.width,ghost.height, null);
        }

        g.drawImage(pacman.image,pacman.x, pacman.y, pacman.width, pacman.height, null);
        g.setFont(new Font("times new roman", Font.PLAIN, 20));
         if(gameOver){
             g.drawString("Game over:"+String.valueOf(score),tilesize/2,tilesize/2);
         }
         else{
             g.drawString("x"+String.valueOf(lives)+"Score:"+String.valueOf(score),tilesize/2,tilesize/2);
         }
    }
     public void move(){
        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;

        for (Block wall:walls){
            if(collision(pacman,wall)){
                pacman.x -= pacman.velocityX;
                pacman.y -= pacman.velocityY;
                break;
            }}


            for(Block ghost:Ghosts){
                if(collision(pacman,ghost)){
                    lives-=1;
                    if(lives == 0){
                        gameOver = true;
                        return;
                    }
                    resetpositions();
                }
                if (ghost.y==tilesize*9 &&ghost.direction!='U'&&ghost.direction!='D'){
                    ghost.updateDirection('U');
                }
                ghost.x += ghost.velocityX;
                ghost.y += ghost.velocityY;
                for (Block wallg : walls){
                if(collision(ghost,wallg)||ghost.x<=0||ghost.x + ghost.width>=boardwidth ){
                    ghost.x -= ghost.velocityX;
                    ghost.y -= ghost.velocityY;
                    char newDirection = directions[random.nextInt(4)];
                    ghost.updateDirection(newDirection);
                }}
            }
            Block foodeaten=null;
         for (Block food:Food){
             if(collision(pacman,food)){
                 foodeaten=food;
                 score +=10;

             }
         }
         Food.remove(foodeaten);
         if (Food.isEmpty()){
             loadMap();
             resetpositions();
         }

     }
     public boolean collision(Block a,Block b){
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;

     }
     public void resetpositions(){
        pacman.reset();
        pacman.velocityX=0;
        pacman.velocityY=0;
        for(Block ghost:Ghosts){
            ghost.reset();
            char newDirection = directions[random.nextInt(4)];
            ghost.updateDirection(newDirection);
        }
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
      repaint();
      if(gameOver){
          gameLoop.stop();
      }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameOver){
            loadMap();
            resetpositions();
            lives =3;
            score = 0;
            gameOver = false;
            gameLoop.start();
        }
        if(e.getKeyCode()==KeyEvent.VK_P){
            gameLoop.stop();
            gamepaused = true;

        }
        if (gamepaused){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                gameLoop.start();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_UP){
            pacman.updateDirection('U');
        } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            pacman.updateDirection('D');

        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            pacman.updateDirection('L');
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            pacman.updateDirection('R');
        }
        if (pacman.direction=='U'){
            pacman.image=pacmanUpImage;
        }
        if (pacman.direction=='D'){
            pacman.image=pacmanDownImage;
        }
        if (pacman.direction=='L'){
            pacman.image=pacmanLeftImage;
        }
        if (pacman.direction=='R'){
            pacman.image=pacmanRightImage;
        }
    }

}