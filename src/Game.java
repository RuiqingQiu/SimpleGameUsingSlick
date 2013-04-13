import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame {
	
	//Instance variable for map
	private TiledMap map;
	
	//Instance variable for animation
	private Animation sprite, forward, backward;
	
	//the coordinate for x and y
	private float x = 125f, y = 48f;
	
	private boolean [][] blocked;
	
	private static final int SIZE = 32;
	
	//Image array for move up action
	Image[] moveUp;
	
	private int tileID = map.getTileId(200, 200, 0);
	

	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		map.render(0, 0);
		sprite.draw((int)x,(int)y);
		g.drawString(map.getTileProperty(tileID, "blocked", "false"), 200, 200);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		map = new TiledMap("res/GameMap.tmx");
		Image[] moveForward = {new Image("res/MoveForward/mf1.png"), new Image("res/MoveForward/mf2.png")};
		Image[] moveBackward = {new Image("res/MoveForward/mf4.png"), new Image("res/MoveForward/mf3.png")};
		int[] duration = {200,200};
		
		forward = new Animation(moveForward, duration,false);
		backward = new Animation(moveBackward, duration, false);
		sprite = new Animation(moveForward, duration,false);
		
		blocked = new boolean[map.getWidth()][map.getHeight()];
		 
	    for (int xAxis=0;xAxis<map.getWidth(); xAxis++)
	    {
	      for (int yAxis=0;yAxis<map.getHeight(); yAxis++)
	      {
	        int tileID = map.getTileId(xAxis, yAxis, 0);
	        String value = map.getTileProperty(tileID, "blocked", "false");
	        if ("true".equals(value))
	        {
	          blocked[xAxis][yAxis] = true;
	        }
	      }
	    }
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = forward;
			
				sprite.update(delta);
				x += delta * 0.1f;
				checkOutOfBounds();
			
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = backward;
			
				sprite.update(delta);
				x -= delta * 0.1f;
				checkOutOfBounds();
			
		}
		else if(input.isKeyDown(Input.KEY_SPACE))
		{
			sprite = forward;
			sprite.update(delta);
			for(int i = 0; i < 30; i++)
			{
				y--;
			}
		}
	}
	
	private boolean isBlocked(float x, float y)
    {
        int xBlock = (int)x / SIZE;
        int yBlock = (int)y / SIZE;
        return blocked[xBlock][yBlock];
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
