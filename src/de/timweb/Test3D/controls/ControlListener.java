package de.timweb.Test3D.controls;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import de.timweb.Test3D.Test3DMain;
import de.timweb.Test3D.entity.Player;
import de.timweb.Test3D.game.Test3DCanvas;

public class ControlListener implements MouseListener,KeyListener,MouseMotionListener{
	public static final ControlListener c = new ControlListener();
	private boolean isMouseGrabbed = false;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			Controls.c.forward = true;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			Controls.c.backward = true;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			Controls.c.left = true;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			Controls.c.rigth = true;
			break;
		case KeyEvent.VK_SHIFT:
			Controls.c.sprint = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
			Controls.c.forward = false;
			break;
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
			Controls.c.backward = false;
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			Controls.c.left = false;
			break;
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			Controls.c.rigth = false;
			break;
		case KeyEvent.VK_SHIFT:
			Controls.c.sprint = false;
			break;
		case KeyEvent.VK_ESCAPE:
			if(isMouseGrabbed){
				isMouseGrabbed = false;
				Test3DMain.showCurser();
			}
			else
				System.exit(0);
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!isMouseGrabbed){
			Test3DMain.hideCurser();
			isMouseGrabbed = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(!isMouseGrabbed)
			return;
			
		Controls.c.setMousePoition((Test3DMain.frame.getX()+Test3DCanvas.WIDTH/2 - e.getPoint().x-3),(Test3DMain.frame.getY()+Test3DCanvas.HEIGHT/2 - e.getPoint().y-25));
		
		//Grabs mouse back to Center of Window
		try {
			Robot robot = new Robot();
			robot.mouseMove(Test3DMain.frame.getX()+Test3DCanvas.WIDTH/2, Test3DMain.frame.getY()+Test3DCanvas.HEIGHT/2);
			
		} catch (AWTException ex) {
			ex.printStackTrace();
		}
		
	}

}
