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
    public boolean open = false;

	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, 32, 32);
		health=10000000;
        bounds.x=0;
        bounds.y=0;
        bounds.width = 32;
        bounds.height = 32;
		
		ir.width = bounds.width;
        ir.height = bounds.height;
        int irx=(int)(bounds.x-handler.getGameCamera().getxOffset()+x);
        int iry= (int)(bounds.y-handler.getGameCamera().getyOffset()+height);
        ir.y=iry;
        ir.x=irx;
	}

	@Override
	public void tick() {
		
		if(isBeinghurt()){
            setHealth(10000000);
        }

        if(handler.getKeyManager().attbut){
            EP=true;

        }else if(!handler.getKeyManager().attbut){
            EP=false;
        }
		
	}

	@Override
	public void render(Graphics g) {
		if(open) {
			g.drawImage(Images.chest[1],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		} else {
			g.drawImage(Images.chest[0],(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),width,height,null);
		}
		
        checkForPlayer(g, handler.getWorld().getEntityManager().getPlayer());
	}
	
	private void checkForPlayer(Graphics g, Player p) {
        Rectangle pr = p.getCollisionBounds(0,0);

        if(ir.intersects(pr) && !EP){
        	System.out.println("touching");
        	if(!open) {
        		g.drawImage(Images.E,(int) x+width,(int) y+10,32,32,null);
        	}
        }else if(ir.intersects(pr) && EP){
        	System.out.println("pressing");
        	if(!open) {
        		g.drawImage(Images.EP,(int) x+width,(int) y+10,32,32,null);
        		open = true;
        	} else {
        		open = false;
        	}
        }
	}

	@Override
	public void die() {
		
	}

}
