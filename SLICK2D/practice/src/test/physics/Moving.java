package test.physics;

import org.newdawn.slick.*;

public class Moving {
    private float x, y;
    private float velocityY = 0;
    private final float gravity = 0.5f;
    private final float groundY = 500;
    private final float jumpPower = -10;
    private boolean onGround = false;

    public void update(GameContainer container, int delta) {
        Input input = container.getInput();

        // 점프 (스페이스바) & 바닥에 있을 때만
        if ((input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_UP)) && onGround) {
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
    public void render(GameContainer container, Graphics g) {
        g.fillRect(x, y, 50, 50); // 플레이어 사각형
        g.drawLine(0, groundY + 50, 800, groundY + 50);
    }
}
