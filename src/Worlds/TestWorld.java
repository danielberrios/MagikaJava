package Worlds;
import Game.Entities.EntityManager;
import Game.Entities.Creatures.Player;
import Game.Entities.Creatures.SkelyEnemy;
import Game.Entities.Creatures.SkelyEnemy2;
import Game.Entities.Statics.Bush;
import Game.Items.ItemManager;
import Main.Handler;

/**
 * Created by Elemental on 2/10/2017.
 */
public class TestWorld extends BaseWorld{
    private Handler handler;
    private Player player;
 

    


    public TestWorld(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;
        
        
        entityManager.addEntity(new Bush(handler, 200, 350));
        entityManager.addEntity(new Bush(handler, 500, 400));
        entityManager.addEntity(new Bush(handler, 800, 500));
        entityManager.addEntity(new SkelyEnemy(handler, 1250, 500));
        entityManager.addEntity(new SkelyEnemy2(handler, 1000, 700));
        
        
    
    }


}