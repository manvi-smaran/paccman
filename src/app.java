import javax.swing.JFrame;
public class app {
    public static void main(String[] args) {
        int rowcount =21;
        int colcount =19;
        int tilesize =32;
        int boardwidth = colcount * tilesize;
        int boardheight = rowcount * tilesize;
        JFrame frame =new JFrame("pacc mann");
       // frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Main pacmangame = new Main();
        frame.add(pacmangame);
        frame.pack();
        pacmangame.requestFocus();
        frame.setVisible(true);
    }
}
