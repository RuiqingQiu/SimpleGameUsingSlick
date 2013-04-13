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
	private Animation sprite, forward, backward, attack;
	
	//the coordinate for x and y
	private float x = 125f, y = 48f;
	
	private static final int SIZE = 32;
	
	
	private String message;
	//Image array for move up action
	Image[] moveUp;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		map.render(0, 0);
		sprite.draw((int)x,(int)y);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		map = new TiledMap("res/GameMap.tmx");
		Image[] moveForward = {new Image("res/MoveForward/mf1.png"), new Image("res/MoveForward/mf2.png")};
		Image[] moveBackward = {new Image("res/MoveForward/mf4.png"), new Image("res/MoveForward/mf3.png")};
		Image[] attackForward = {new Image("res/Attack/attack1.png"), new Image("res/Attack/attack2.png"), new Image("res/Attack/attack3.png")};
		int[] duration = {200,200};
		int[] attackDuration = {200, 200, 200};
		
		forward = new Animation(moveForward, duration,false);
		backward = new Animation(moveBackward, duration, false);
		sprite = new Animation(moveForward, duration,false);
		attack = new Animation(attackForward,attackDuration,false);
		
		
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
		else if (input.isKeyPressed(Input.KEY_A))
		{
			sprite = attack;
			sprite.setAutoUpdate(true);
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
