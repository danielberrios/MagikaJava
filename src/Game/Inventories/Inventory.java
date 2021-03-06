package Game.Inventories;

import Game.Items.Item;
import Game.SpellCast.FireBallSpell;
import Resources.Images;
import UI.UIInventory;
import UI.UIManager;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Elemental on 1/3/2017.
 */
public class Inventory {

	private Handler handler;
	private boolean active = false;
	private UIManager uiManager;
	private ArrayList<Item> inventoryItems;
	private int[] rows = new int[5];
	private int[] columns = new int[5];
	private ArrayList<Integer> xValues;
	private ArrayList<Integer> yValues;

	public Inventory(Handler handler){

		for (int i = 0; i < 5; i++) {
			columns[i] = 24 + (60 * i) + i;
			rows[i] = 24 + (60 * i);
		}

		this.handler=handler;
		inventoryItems = new ArrayList<>();
		xValues = new ArrayList<>();
		yValues = new ArrayList<>();

		uiManager = new UIManager(handler);

		uiManager.addObjects(new UIInventory(0,0, 329, 265, Images.inventory,() -> {
		}));
	}

	public void tick() {

		for(Item i : inventoryItems){
			if(i.getCount()==0){
				inventoryItems.remove(inventoryItems.indexOf(i));
				return;
			}
		}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
			active=!active;
			handler.getWorld().getEntityManager().getPlayer().getSpellGUI().setActive(false);

		}

		if(!active){
			return;
		}

		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

		if(handler.getWorld1().getChest().isOpen()) {
			inventoryToChest(Item.bananaItem, 4);
			inventoryToChest(Item.stickItem, 3);
		}

	}

	public void render(Graphics g) {

		if(!active){
			uiManager.isActive(uiManager.getObjects(),false);
			return;
		}



		uiManager.isActive(uiManager.getObjects(),true);
		uiManager.Render(g);
		g.setColor(Color.white);
		renderItems(g);


	}

	//Inventory Methods
	private void renderItems(Graphics g) {

		if(inventoryItems.size() > 0) {
			int fullRows = 0;
			int lastColumn = 0;
			if(inventoryItems.size() % 5 == 0) {
				fullRows = inventoryItems.size() / 5;
				lastColumn = 5;
			} else {
				fullRows = (inventoryItems.size() / 5);
				lastColumn = inventoryItems.size() % 5;
			}

			int currentRow = 0;
			int currentItem = 0;

			if(fullRows > 0) {
				for(int i = 0; i < fullRows; i++) {
					for(int j = 0; j < 5; j++) {
						g.drawImage(inventoryItems.get(currentItem).getTexture(), columns[j], rows[i], inventoryItems.get(currentItem).getWidth(), inventoryItems.get(currentItem).getHeight(), null);
						g.drawString(String.valueOf(inventoryItems.get(currentItem).getCount()), columns[j]+33,rows[i]+35);
						xValues.add(columns[j]);
						yValues.add(rows[i]);
						currentItem++;
					}
					currentRow++;
				}
			}

			if(lastColumn < 5) {
				for(int k = 0; k < lastColumn; k++) {
					g.drawImage(inventoryItems.get(currentItem).getTexture(), columns[k], rows[currentRow], inventoryItems.get(currentItem).getWidth(), inventoryItems.get(currentItem).getHeight(), null);
					g.drawString(String.valueOf(inventoryItems.get(currentItem).getCount()), columns[k]+33,rows[currentRow]+35);
					xValues.add(columns[k]);
					yValues.add(rows[currentRow]);
					currentItem++;
				}
			}
		}

		//        if (inventoryItems.size() == 1) {
		//            g.drawImage(inventoryItems.get(0).getTexture(), 25, 24, inventoryItems.get(0).getWidth(), inventoryItems.get(0).getHeight(), null);
		//            g.drawString(String.valueOf(inventoryItems.get(0).getCount()), 25+33,25+35);
		//        }else if(inventoryItems.size() == 2) {
		//            g.drawImage(inventoryItems.get(0).getTexture(), 25, 24, inventoryItems.get(0).getWidth(), inventoryItems.get(0).getHeight(), null);
		//            g.drawString(String.valueOf(inventoryItems.get(0).getCount()), 25+33,25+35);
		//            g.drawImage(inventoryItems.get(1).getTexture(), 86, 24, inventoryItems.get(1).getWidth(), inventoryItems.get(1).getHeight(), null);
		//            g.drawString(String.valueOf(inventoryItems.get(1).getCount()), 86+33,24+35);
		//        }else if(inventoryItems.size() == 3) {
		//            g.drawImage(inventoryItems.get(0).getTexture(), 25, 24, inventoryItems.get(0).getWidth(), inventoryItems.get(0).getHeight(), null);
		//            g.drawString(String.valueOf(inventoryItems.get(0).getCount()), 25+33,25+35);
		//            g.drawImage(inventoryItems.get(1).getTexture(), 86, 24, inventoryItems.get(1).getWidth(), inventoryItems.get(1).getHeight(), null);
		//            g.drawString(String.valueOf(inventoryItems.get(1).getCount()), 86+33,24+35);
		//            g.drawImage(inventoryItems.get(2).getTexture(), 147, 24, inventoryItems.get(2).getWidth(), inventoryItems.get(2).getHeight(), null);
		//            g.drawString(String.valueOf(inventoryItems.get(2).getCount()), 147+33,24+35);
		//        }


	}

	public void addItem(Item item){
		for(Item i : inventoryItems){
			if(i.getId() == item.getId()){
				i.setCount(i.getCount()+item.getCount());
				return;
			}
		}
		if(item.getId()==2){
			handler.getWorld().getEntityManager().getPlayer().getSpellGUI().addSpell(new FireBallSpell(handler));
		}
		inventoryItems.add(item);

	}

	//GET SET
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Item> getInventoryItems(){
		return inventoryItems;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void inventoryToChest(Item item, int id) {
		if(handler.getMouseManager().isLeftPressed()) {
			if((id == 3 && handler.getWorld1().getChest().getSticks() < 3) || (id == 4 && handler.getWorld1().getChest().getBananas() < 3)) { 
				int x = 0;
				int y = 0;
				int index = 0;
				int amount = 0;
				int bananas = handler.getWorld1().getChest().getBananas();
				int sticks = handler.getWorld1().getChest().getSticks();

				for(Item i : inventoryItems) {
					if(i.getId() == id) {
						index = inventoryItems.indexOf(i);
						x = xValues.get(index);
						y = yValues.get(index);
						amount++;
					}
				}

				if(amount > 0) {
					if((handler.getMouseManager().getMouseX() >= x) && (handler.getMouseManager().getMouseX() <= x + item.getWidth())) {
						if((handler.getMouseManager().getMouseY() >= y) && (handler.getMouseManager().getMouseY() <= y + item.getWidth())) {
							if(item.getCount() >= 3) {
								if(id == 3 && sticks < 3) {
									handler.getWorld1().getChest().setSticks(sticks + 3);
								} else if(id == 4 && bananas < 3){
									handler.getWorld1().getChest().setBananas(bananas + 3);
								}
								inventoryItems.get(index).setCount(item.getCount() - 3);
							} else {
								if(id == 3 && sticks < 3) {
									handler.getWorld1().getChest().setSticks(sticks + inventoryItems.get(index).getCount());
									inventoryItems.get(index).setCount(0);
								} else if(id == 4 && bananas < 3){
									handler.getWorld1().getChest().setBananas(bananas + inventoryItems.get(index).getCount());
									inventoryItems.get(index).setCount(0);
								}
							}
						}
					}
				}
			}
		}
	}
}
