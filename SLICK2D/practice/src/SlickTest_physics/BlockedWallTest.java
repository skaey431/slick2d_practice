package SlickTest_physics;

import org.newdawn.slick.*;

public class BlockedWallTest extends BasicGame {

    private float x = 100, y = 100;
    private float velocityY = 0;
    private final float gravity = 0.5f;
    private final float speed = 0.3f;
    private final float jumpPower = -10;
    private final float groundY = 500;

    private final float playerWidth = 50;
    private final float playerHeight = 50;
    int wallHeight1 = 50;
    // 막힌 벽
    private final Rectangle wall = new Rectangle(300, groundY - 50 , 100, wallHeight1); // 높이 늘림
    private final Rectangle wall2 = new Rectangle(150, groundY , 100, wallHeight1); // 높이 늘림
    private boolean onGround = false;

    public BlockedWallTest() {
        super("벽 완전 막기");
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

        // 수직 이동 먼저 처리
        float nextY = y + velocityY;
        Rectangle nextYBounds = new Rectangle(x, nextY, playerWidth, playerHeight);

        if (nextY >= groundY) {
            y = groundY;
            velocityY = 0;
            onGround = true;
        }
// 벽과 충돌
        else if (intersects(nextYBounds, wall)) {
            if (velocityY > 0) {
                y = wall.getY() - playerHeight;
                onGround = true;
            }
            velocityY = 0;
        }else if (intersects(nextYBounds, wall2)) {
            if (velocityY > 0) {
                y = wall2.getY() - playerHeight;
                onGround = true;
            }
            velocityY = 0;
        }
// 아무 충돌 없음
        else {
            y = nextY;
            onGround = false;
        }

        // 수평 이동 처리
        float nextX = x + dx;
        Rectangle nextXBounds = new Rectangle(nextX, y, playerWidth, playerHeight);

        if (!intersects(nextXBounds, wall) && !intersects(nextXBounds, wall2)) {
            x = nextX;
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
        g.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        g.fillRect(wall2.getX(), wall2.getY(), wall2.getWidth(), wall2.getHeight());

        g.setColor(Color.white);
        g.drawString("← → : 이동 | SPACE : 점프 | 벽은 넘을 수 없음", 10, 30);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new BlockedWallTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
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
