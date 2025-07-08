package test.physics;

import org.newdawn.slick.*;

import java.util.List;

public class Moving {
    private float x, y;
    private float velocityY = 0;
    private final float gravity = 0.5f;
    private final float groundY = 500;
    private final float jumpPower = -10;
    private boolean onGround = false;

    // Moving.java
    private float width = 50, height = 50;

    // update 메서드 수정
    public void update(GameContainer container, int delta, List<Wall> walls) {
        Input input = container.getInput();
        float speed = 0.2f * delta;
        float nextX = x;
        float nextY = y;
        boolean blockedX = false;
        boolean blockedY = false;

        // ---- X축 이동 처리 ----
        if (input.isKeyDown(Input.KEY_LEFT)) {
            nextX -= speed;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            nextX += speed;
        }

        for (Wall wall : walls) {
            if (wall.intersects(nextX, y, width, height)) {
                blockedX = true;
                break;
            }
        }

        if (!blockedX) {
            x = nextX;
        }

        // ---- 점프 ----
        if ((input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_UP)) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        // ---- 중력 적용 및 Y축 이동 ----
        velocityY += gravity;
        nextY += velocityY;

        blockedY = false;
        for (Wall wall : walls) {
            if (wall.intersects(x, nextY, width, height)) {
                blockedY = true;

                if (velocityY > 0) {
                    y = wall.getY() - height; // 바닥 위로 고정
                    onGround = true;
                } else if (velocityY < 0) {
                    y = wall.getY() + wall.getHeight(); // 천장 아래로 고정
                }

                velocityY = 0;
                break;
            }
        }

        if (!blockedY) {
            y = nextY;
            onGround = false;
        }

        // ---- 바닥 충돌 처리 ----
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
