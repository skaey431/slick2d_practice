package test.physics;

import org.newdawn.slick.*;

public class MainTest extends BasicGame {

    private Moving moving;
    public MainTest() {
        super("main test");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        moving = new Moving();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        moving.update(gameContainer,i);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        moving.render(gameContainer,graphics);
    }
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
