package SlickTest_physics;

import org.newdawn.slick.*;

public class WallCollisionTest extends BasicGame {

    private float x = 100, y = 100;
    private float velocityX = 0;
    private float velocityY = 0;
    private final float gravity = 0.5f;
    private final float speed = 0.3f;
    private final float jumpPower = -10;
    private final float groundY = 500;

    private final float playerWidth = 50;
    private final float playerHeight = 50;

    private final Rectangle leftWall = new Rectangle(200, 0, 20, 600);
    private final Rectangle rightWall = new Rectangle(600, 0, 20, 600);

    private boolean onGround = false;

    public WallCollisionTest() {
        super("벽 충돌 테스트");
    }

    @Override
    public void init(GameContainer container) {}

    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();
        float dx = 0;

        // 좌우 이동
        if (input.isKeyDown(Input.KEY_LEFT)) {
            dx -= speed * delta;
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            dx += speed * delta;
        }

        // 점프
        if (input.isKeyPressed(Input.KEY_SPACE) && onGround) {
            velocityY = jumpPower;
            onGround = false;
        }

        // 중력
        velocityY += gravity;
        y += velocityY;

        // 수직 충돌 (바닥)
        if (y >= groundY) {
            y = groundY;
            velocityY = 0;
            onGround = true;
        }

        // 수평 이동 전 충돌 체크
        float nextX = x + dx;
        Rectangle nextBounds = new Rectangle(nextX, y, playerWidth, playerHeight);

        if (!intersects(nextBounds, leftWall) && !intersects(nextBounds, rightWall)) {
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
        g.fillRect(leftWall.getX(), leftWall.getY(), leftWall.getWidth(), leftWall.getHeight());
        g.fillRect(rightWall.getX(), rightWall.getY(), rightWall.getWidth(), rightWall.getHeight());

        g.setColor(Color.white);
        g.drawString("← → : 이동 | SPACE : 점프", 10, 30);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new WallCollisionTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }

    // 간단한 AABB 충돌 체크 함수
    private boolean intersects(Rectangle a, Rectangle b) {
        return a.getX() < b.getX() + b.getWidth() &&
                a.getX() + a.getWidth() > b.getX() &&
                a.getY() < b.getY() + b.getHeight() &&
                a.getY() + a.getHeight() > b.getY();
    }

    // 간단한 사각형 클래스 정의
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
