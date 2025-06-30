import org.newdawn.slick.*;

public class ClickInteractionTest extends BasicGame {

    private Rectangle button;
    private boolean clicked = false;

    public ClickInteractionTest() {
        super("마우스 클릭 상호작용");
    }

    @Override
    public void init(GameContainer container) {
        // 버튼 영역 설정
        button = new Rectangle(300, 250, 200, 100); // x, y, width, height
    }

    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            int mouseX = input.getMouseX();
            int mouseY = input.getMouseY();

            if (button.contains(mouseX, mouseY)) {
                clicked = !clicked; // 상태 토글
            }
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        // 버튼 배경
        g.setColor(clicked ? Color.green : Color.red);
        g.fillRect(button.x, button.y, button.width, button.height);

        // 텍스트
        g.setColor(Color.white);
        g.drawString(clicked ? "clicked!" : "click it", button.x + 50, button.y + 40);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new ClickInteractionTest());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }

    // 간단한 사각형 클래스
    private static class Rectangle {
        float x, y, width, height;

        public Rectangle(float x, float y, float width, float height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean contains(float px, float py) {
            return px >= x && px <= x + width &&
                    py >= y && py <= y + height;
        }
    }
}
