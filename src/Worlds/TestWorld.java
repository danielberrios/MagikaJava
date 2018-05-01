package Worlds;
import Game.Entities.Creatures.Player;
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

    }


}