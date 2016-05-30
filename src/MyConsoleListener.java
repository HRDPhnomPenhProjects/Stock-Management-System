import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JRootPane;

public class MyConsoleListener {
	
	public static final int BACK_SPACE = 8;
	public static final int SHIFT = 16;
	
	
 	public static int ascii;
	public static int keyboardCode;
	public static String option="";
	public static void getCh() {  
        final JFrame frame = new JFrame();  
        synchronized (frame) {  
            frame.setUndecorated(true);  
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);  
            frame.addKeyListener(new KeyListener() {
                @Override 
                public void keyPressed(KeyEvent e) {  
              
                	keyboardCode = e.getKeyCode();
                	if((keyboardCode < 37 && keyboardCode != SHIFT && keyboardCode != BACK_SPACE) || keyboardCode > 40 ){
                   //   	System.out.println("KEYBOARD: " + e.getKeyCode());
                		ascii = e.getKeyChar();
                		option += e.getKeyChar();
                		System.out.print((char)ascii);
                	}            
                	synchronized (frame) {  
                        frame.setVisible(true);  
                        frame.dispose();  
                        frame.notify();  
                    }  
                }  
                @Override 
                public void keyReleased(KeyEvent e) {  
                 
                }   
                @Override 
                public void keyTyped(KeyEvent e) {  
                	
                 }  
            });  
            frame.setVisible(true);  
            try {  
                frame.wait();  
            } catch (InterruptedException e1) {
            	System.out.println();
            }  
        }  
    }
}
