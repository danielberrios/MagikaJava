package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys,justPressed,cantPress;
	public boolean up=false, down=false, left=false, right=false;
	public boolean attbut=false;
	public boolean fattbut=false;
	public boolean pbutt=false;
	public boolean nbutt=false;
	public boolean ibutt=false;
	public long lastSkip = 0;

	public KeyManager(){

		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];

	}

	public void tick(){
		for(int i =0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i]=false;
				long waitTime = System.currentTimeMillis() - lastSkip;
				if(waitTime < 3000) {
					cantPress[KeyEvent.VK_N] = false;
				} else {
					cantPress[KeyEvent.VK_N] = true;
				}

			}else if(justPressed[i]){
				cantPress[i]=true;
				justPressed[i]=false;
				if(i == KeyEvent.VK_N) {keys[i] = false;}
			}

			if(!cantPress[i] && keys[i]){
				justPressed[i]=true;
				if(i == KeyEvent.VK_N) {lastSkip = System.currentTimeMillis();}
			}

			up = keys[KeyEvent.VK_W];
			down = keys[KeyEvent.VK_S];
			left = keys[KeyEvent.VK_A];
			right = keys[KeyEvent.VK_D];

			attbut = keys[KeyEvent.VK_E];
			fattbut = keys[KeyEvent.VK_C];
			pbutt = keys[KeyEvent.VK_ESCAPE];

			nbutt = keys[KeyEvent.VK_N];
			ibutt = keys[KeyEvent.VK_I];
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
}

