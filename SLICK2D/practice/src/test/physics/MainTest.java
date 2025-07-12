package test.physics;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class MainTest extends BasicGame {

    private Moving moving;
    private List<Wall> walls;
    public MainTest() {
        super("main test");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        moving = new Moving("resources/얼굴만들어.png",gameContainer);
        walls = new ArrayList<>();
        walls.add(new Wall(300, 450, 50, 100,"resources/bricks.png")); // 예시: (x, y, w, h)
        walls.add(new Wall(100, 500, 50, 50,"resources/bricks.png")); // 예시: (x, y, w, h)
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        moving.update(gameContainer,i,walls);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        moving.render(gameContainer,graphics);
        for (Wall wall : walls) {
            wall.render(graphics);
        }
    }
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
