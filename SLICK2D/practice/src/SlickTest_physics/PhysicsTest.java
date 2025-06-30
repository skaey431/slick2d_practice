package SlickTest_physics;

import org.newdawn.slick.*;

public class PhysicsTest extends BasicGame {

    private float x = 100, y = 100;
    private float velocityY = 0;
    private final float gravity = 0.5f;
    private final float groundY = 500;
    private final float jumpPower = -10;
    private boolean onGround = false;

    public PhysicsTest() {
        super("물리 테스트");
    }

    @Override
    public void init(GameContainer container) {}

    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();

        // 점프 (스페이스바) & 바닥에 있을 때만
        if (input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_UP) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        float speed = 0.2f * delta; // delta를 곱해서 프레임 독립 속도 구현

        if (input.isKeyDown(Input.KEY_LEFT)) {
            x -= speed;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            x += speed;
        }

        // 중력 적용
        velocityY += gravity;
        y += velocityY;

        // 바닥 충돌 처리
        if (y >= groundY) {
            y = groundY;
            velocityY = 0;
            onGround = true;
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        g.drawString("스페이스바로 점프!", 10, 30);
        g.fillRect(x, y, 50, 50); // 플레이어 사각형
        g.drawLine(0, groundY + 50, 800, groundY + 50); // 바닥 선
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new PhysicsTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
