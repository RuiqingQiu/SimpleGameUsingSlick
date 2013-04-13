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
	private float x = 34f, y = 34f;
	
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
		Image[] moveBackward = {new Image("res/MoveForward/mf1.png"), new Image("res/MoveForward/mf3.png")};
		int[] duration = {300,300};
		
		forward = new Animation(moveForward, duration,false);
		backward = new Animation(moveBackward, duration, false);
		sprite = new Animation(moveForward, duration,false);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = forward;
			x += delta * 0.1f;
		}
		else if (input.isKeyDown(Input.KEY_LEFT))
		{
			sprite = backward;
			x -= delta * 0.1f;
		}
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
