
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {
	
	//Instance variable for map
	private TiledMap map;
	
	private Image bgImage;
	
	//Instance variable for animation
	private Animation sprite, forward, backward, attackRight, attackLeft;
	
	private Animation enemy;
	
	//the coordinate for x and y
	private float x = 48f, y = 324f;
	
	//the coordinate for enemy
	private float enemyx = 10f, enemyy = 324f;
	
	private int PlayerOffset = 13;
	
	private boolean facingLeft;
	
	private static final int SIZE = 32;
	private static final int SCREEN_WIDTH = 768;
	private static final int SCREEN_HEIGHT = 512;
	
	
	public Game(String title) {
		super(title);
	}



	@Override
	public void init(GameContainer arg0) throws SlickException {
	  
	    
	    bgImage = new Image("res/background.png");
		map = new TiledMap("res/SingleLevelMap.tmx");
		
		//Image array for different motion
		Image[] moveForward = {new Image("res/MoveForward/mf1.png"), new Image("res/MoveForward/mf2.png")};
		Image[] moveBackward = {new Image("res/MoveForward/mf4.png"), new Image("res/MoveForward/mf3.png")};
		Image[] attackLeftImage = {new Image("res/Attack/attack1.png"), new Image("res/Attack/attack2.png"), new Image("res/Attack/attack3.png")};
		Image[] enemyForward = {new Image("res/Enemy/enemy1.png"), new Image("res/Enemy/enemy2.png")};
		Image[] attackRightImage = {new Image("res/Attack/attackL1.png"), new Image("res/Attack/attackL2.png"), new Image("res/Attack/attackL3.png")};
		//integer array for attack duration
		int[] duration = {200,200};
		int[] attackDuration = {200, 200, 200};
		
		forward = new Animation(moveForward, duration,false);
		backward = new Animation(moveBackward, duration, false);
		sprite = new Animation(moveForward, duration,false);
		
		attackLeft = new Animation(attackLeftImage,attackDuration,false);
		attackRight = new Animation(attackRightImage, attackDuration,false);
		
		enemy = new Animation(enemyForward, duration,false);
		
		
	}
	
 @Override
    public void render(GameContainer arg0, Graphics g) throws SlickException {        
	    g.drawImage(bgImage, 0, 0);
	    map.render(0, 0);
	    sprite.draw((int)x,(int)y + PlayerOffset);
	    enemy.draw((int)enemyx, (int)enemyy);
	}

	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		//Movement for player RIGHT
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = forward;
			facingLeft = true;
			sprite.update(delta);
			x += delta * 0.1f;
			checkOutOfBounds();
		}
		//Movement for player LEFT
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = backward;
			facingLeft = false;
			sprite.update(delta);
			x -= delta * 0.1f;
			checkOutOfBounds();
		}
		
		//Attack Mode
		if (input.isKeyPressed(Input.KEY_A))
		{
		  if(facingLeft)
		  {
			sprite = attackLeft;
			sprite.setAutoUpdate(true);
		  }
		  else
		  {
		    sprite = attackRight;
		    sprite.setAutoUpdate(true);
		  }
		}
		
		//Jump Movement
		
		/**
		 * The below codes are responsible for enemey movement
		 */
		enemy.update(delta);
		enemyx+=0.1;
		
	}
	
	private void checkOutOfBounds()
	{
		if( x < 0)
			x++;
		if( x >= 740 )
			x--;
		if( y < 0)
			y++;
		if( y > 512)
			y--;
	}
	
	public static void main(String[]args){
		Game game = new Game("A Simple Game Using Slick");
		try{
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(768, 512, false);
			container.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
	

}
