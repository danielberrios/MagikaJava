package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Entities.Creatures.Player;
import Main.Handler;
import Resources.Images;

public class Chest extends StaticEntity {
	private Rectangle ir = new Rectangle();
	public Boolean EP = false;
	private boolean open = false;
	private long lastOpened = 0;
	private boolean ready = true;

	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);
		health=10000000;
		bounds.x=0;
		bounds.y=0;
		bounds.width = 32;
		bounds.height = 32;

		ir.width = bounds.width + 8;
		ir.height = bounds.height + 8;
		int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
		int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+y);
		ir.y=iry;
		ir.x=irx;
	}

	@Override
	public void tick() {

		if(isBeinghurt()){
			setHealth(10000000);
		}
		if (System.currentTimeMillis() - lastOpened >= 1000) {
			ready = true;
			if(handler.getKeyManager().attbut){
				EP=true;
				lastOpened = System.currentTimeMillis();
			}else if(!handler.getKeyManager().attbut){
				EP=false;
			}
		} else {
			ready = false;
		}

	}

	@Override
	public void render(Graphics g) {
		if(open) {
			g.drawImage(Images.chest[1],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
			g.drawImage(Images.chestGUI, (int) (x) - (Images.chestGUI.getWidth()/2) + (width/2), (int) (y) - (Images.chestGUI.getHeight()) - 50, null);
		} else {
			g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}

		checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
	}

	private void checkForPlayer(Graphics g, Player p) {
		Rectangle pr = p.getCollisionBounds(0,0);
		//g.fillRect(pr.x, pr.y, pr.width, pr.height);
		//g.fillRect(ir.x, ir.y, ir.width, ir.height);

		if(ir.intersects(pr) && (pr.x > this.getX() - (ir.width/2)) && (pr.x < this.getX() + (this.width/2)) && (p.faceUp)){
			if(EP && ready) {
				if(!open) {
					g.drawImage(Images.EP,(int) x+width+10,(int) y-40,32,32,null);
					open = true;
				} else {
					open = false;
				}
				handler.getKeyManager().attbut = false;
			} else {
				if(!open) {
					g.drawImage(Images.E,(int) x+width+10,(int) y-40,32,32,null);
				}
			}
		}
	}

	@Override
	public void die() {

	}
	
	public boolean isOpen() {
		return open;
	}

}