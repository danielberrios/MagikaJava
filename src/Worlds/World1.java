package Worlds;

import Game.Entities.EntityManager;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Creatures.SkelyEnemy2;
import Game.Entities.Statics.*;
import Game.Items.ItemManager;
import Main.Handler;

/**
 * Created by Elemental on 1/2/2017.
 */
public class World1 extends BaseWorld{

    private Handler handler;
    private BaseWorld caveWorld;
    Chest chest;
    Door door;

    public World1(Handler handler, String path, Player player){
        super(handler,path,player);
        this.handler = handler;
        caveWorld = new CaveWorld(handler,"res/Maps/caveMap.map",player);
        chest = new Chest(handler, 300, 300);
        door = new Door(handler, 100, 0,caveWorld);
        //new
       
        entityManager.addEntity(new Bush(handler, 200, 350));
        entityManager.addEntity(new Bush(handler, 500, 400));
        entityManager.addEntity(new Bush(handler, 800, 500));
        //
        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Rock(handler, 100, 450));
        entityManager.addEntity(new Tree(handler, 533, 276));
        entityManager.addEntity(new Rock(handler, 684, 1370));
        entityManager.addEntity(new Tree(handler, 765, 888));
        entityManager.addEntity(new Rock(handler, 88, 1345));
        entityManager.addEntity(new Tree(handler, 77, 700));
        entityManager.addEntity(new Rock(handler, 700, 83));
        entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
        entityManager.addEntity(new SkelyEnemy2(handler, 1000, 700));
        entityManager.addEntity(chest);
        entityManager.addEntity(door);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
        
       
    }
    
    public Chest getChest() {
    	return chest;
    }
    
    public void tick() {
    	handler.setWorld1(this);
    	super.tick();
    }
    
    public Door getDoor() {
    	return door;
    }
}