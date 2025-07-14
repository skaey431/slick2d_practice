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

    private String frame1Source;
    private String frame2Source;

    private Image frame1;
    private Image frame2;

    private float width = 50, height = 50;

    // 애니메이션 관련
    private boolean isMoving = false;
    private boolean showFrame1 = true;
    private boolean facingLeft = true;
    private int animationTimer = 0;
    private final int animationInterval = 200; // 200ms마다 프레임 전환

    public Moving(String frame1Source, String frame2Source) {
        this.frame1Source = frame1Source;
        this.frame2Source = frame2Source;
    }

    private Image frame1Flipped;
    private Image frame2Flipped;

    public void init(GameContainer container) throws SlickException {
        frame1 = new Image(frame1Source);
        frame2 = new Image(frame2Source);
        frame1Flipped = frame1.getFlippedCopy(true, false);
        frame2Flipped = frame2.getFlippedCopy(true, false);
        width = frame1.getWidth();
        height = frame1.getHeight();
    }

    public void update(GameContainer container, int delta, List<Wall> walls) {
        Input input = container.getInput();
        float speed = 0.2f * delta;
        float nextX = x;
        float nextY = y;
        boolean blockedX = false;
        boolean blockedY = false;
        isMoving = false;

        // X축 이동
        if (input.isKeyDown(Input.KEY_LEFT)) {
            nextX -= speed;
            isMoving = true;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            nextX += speed;
            isMoving = true;
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

        // 점프
        if ((input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_UP)) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        // 중력 + Y축 이동
        velocityY += gravity;
        nextY += velocityY;

        for (Wall wall : walls) {
            if (wall.intersects(x, nextY, width, height)) {
                blockedY = true;

                if (velocityY > 0) {
                    y = wall.getY() - height;
                    onGround = true;
                } else if (velocityY < 0) {
                    y = wall.getY() + wall.getHeight();
                }

                velocityY = 0;
                break;
            }
        }

        if (!blockedY) {
            y = nextY;
            onGround = false;
        }

        // 바닥 충돌 처리
        if (y >= groundY - height) {
            y = groundY - height;
            velocityY = 0;
            onGround = true;
        }

        // --- 애니메이션 처리 ---
        if (isMoving) {
            animationTimer += delta;
            if (animationTimer >= animationInterval) {
                animationTimer = 0;
                showFrame1 = !showFrame1;
            }
            if (input.isKeyDown(Input.KEY_LEFT)) {
                nextX -= speed;
                facingLeft = true;
            }
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                nextX += speed;
                facingLeft = false;
            }
        } else {
            showFrame1 = true;
            animationTimer = 0;
        }

    }

    public void render(GameContainer container, Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(0, 0, container.getWidth(), container.getHeight());

        Image currentFrame;
        if (showFrame1) {
            currentFrame = facingLeft ? frame1Flipped : frame1;
        } else {
            currentFrame = facingLeft ? frame2Flipped : frame2;
        }

        currentFrame.draw(x, y);

        g.setColor(Color.white);
        g.drawLine(0, groundY, container.getWidth(), groundY);
    }

}
