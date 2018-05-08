package Worlds;
import Game.Entities.Creatures.Player;
import Game.Entities.Statics.Bush;
import Game.Entities.Statics.Door;
import Main.Handler;

/**
 * Created by Elemental on 2/10/2017.
 */
public class CaveWorld extends BaseWorld{
    private Handler handler;
    private Player player;
    private BaseWorld testWorld;

    public CaveWorld(Handler handler, String path, Player player) {
        super(handler,path,player);
        this.handler = handler;
        this.player=player;
        
        testWorld = new TestWorld(handler,"res/Maps/test.map",player);
        entityManager.addEntity(new Door(handler, 300, 0,testWorld));
        
    }


}