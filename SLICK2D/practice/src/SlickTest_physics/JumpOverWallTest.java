package SlickTest_physics;

import org.newdawn.slick.*;

public class JumpOverWallTest extends BasicGame {

    private float x = 100, y = 100;
    private float velocityY = 0;
    private final float gravity = 0.5f;
    private final float speed = 0.3f;
    private final float jumpPower = -10;
    private final float groundY = 500;

    private final float playerWidth = 50;
    private final float playerHeight = 50;

    // 벽: 높이를 낮게 설정해서 점프로 넘을 수 있게
    private final Rectangle lowWall = new Rectangle(300, groundY + 10, 100, 50);

    private boolean onGround = false;

    public JumpOverWallTest() {
        super("점프로 넘는 벽");
    }

    @Override
    public void init(GameContainer container) {}

    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();

        float dx = 0;

        if (input.isKeyDown(Input.KEY_LEFT)) {
            dx -= speed * delta;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            dx += speed * delta;
        }

        if (input.isKeyPressed(Input.KEY_SPACE) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        // 중력
        velocityY += gravity;
        y += velocityY;

        // 바닥 충돌
        if (y >= groundY) {
            y = groundY;
            velocityY = 0;
            onGround = true;
        }

        // 플레이어 경계 사각형
        Rectangle nextBounds = new Rectangle(x + dx, y, playerWidth, playerHeight);

        // 수평 충돌만 체크 (위에서 내려오는 건 허용)
        if (!isSideCollision(nextBounds, lowWall)) {
            x += dx;
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        // 플레이어
        g.setColor(Color.white);
        g.fillRect(x, y, playerWidth, playerHeight);

        // 바닥
        g.setColor(Color.gray);
        g.drawLine(0, groundY + playerHeight, 800, groundY + playerHeight);

        // 벽
        g.setColor(Color.red);
        g.fillRect(lowWall.getX(), lowWall.getY(), lowWall.getWidth(), lowWall.getHeight());

        g.setColor(Color.white);
        g.drawString("← → : 이동 | SPACE : 점프 | 점프로 벽 넘기", 10, 30);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new JumpOverWallTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }

    // 수평 충돌만 검사 (위로 점프하는 건 허용)
    private boolean isSideCollision(Rectangle a, Rectangle wall) {
        return intersects(a, wall) && a.getY() + a.getHeight() > wall.getY();
    }

    // AABB 충돌
    private boolean intersects(Rectangle a, Rectangle b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

    // 사각형 클래스
    private static class Rectangle {
        private float x, y, width, height;
        public Rectangle(float x, float y, float w, float h) {
            this.x = x; this.y = y; this.width = w; this.height = h;
        }
        public float getX() { return x; }
        public float getY() { return y; }
        public float getWidth() { return width; }
        public float getHeight() { return height; }
    }
}
