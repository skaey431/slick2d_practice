package SlickTest_physics;

import org.newdawn.slick.*;

public class KeyboardTest extends BasicGame {

    private Image player;
    private float x = 100, y = 100; // 이미지 좌표

    public KeyboardTest() {
        super("키보드 입력 테스트");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        player = new Image("resources/64yA7ZGc1.jpg"); // 테스트용 이미지
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        float speed = 0.2f * delta; // delta를 곱해서 프레임 독립 속도 구현

        if (input.isKeyDown(Input.KEY_UP)) {
            y -= speed;
        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            y += speed;
        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            x -= speed;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            x += speed;
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawString("화살표 키로 이동하세요", 10, 30);
        player.draw(x, y , 0.05f);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new KeyboardTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
