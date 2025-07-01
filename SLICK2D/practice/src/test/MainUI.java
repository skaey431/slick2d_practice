package test;

import org.newdawn.slick.*;

public class MainUI extends BasicGame {

    private ImageButton button;

    public MainUI() {
        super("Slick2D 버튼 분리 예제");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        Image normal = new Image("resources/64yA7ZGc1.jpg");
        Image hover = new Image("resources/img_19975_1.jpg");

        button = new ImageButton(300, 250, normal, hover);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        button.update(container);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        button.render(container, g); // ✅ GameContainer도 같이 넘김
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainUI());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
