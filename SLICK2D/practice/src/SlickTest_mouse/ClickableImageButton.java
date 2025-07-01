import org.newdawn.slick.*;

public class ClickableImageButton extends BasicGame {

    private Rectangle button;
    private boolean clicked = false;
    private boolean hovering = false;

    private Image normalImage;
    private Image hoverImage;

    public ClickableImageButton() {
        super("이미지 버튼 클릭 & 호버");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        // 버튼 위치 및 크기
        button = new Rectangle(300, 250, 200, 100);

        // 이미지 로딩 (res 폴더 또는 프로젝트 루트에 이미지 넣어주세요)
        normalImage = new Image("resources/64yA7ZGc1.jpg"); // 예: 회색 버튼
        hoverImage = new Image("resources/img_19975_1.jpg");   // 예: 파란색 버튼
    }

    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        hovering = button.contains(mouseX, mouseY);

        if (hovering && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            clicked = !clicked;
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        // 버튼 이미지 출력
        Image buttonImage = hovering ? hoverImage : normalImage;
        g.drawImage(buttonImage, button.x, button.y);

        // 상태 텍스트
        g.setColor(Color.white);
        g.drawString(hovering ? "마우스 올림" : "마우스를 올려보세요", button.x + 20, button.y - 20);
        g.drawString(clicked ? "클릭됨!" : "클릭해보세요", button.x + 60, button.y + 40);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new ClickableImageButton());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }

    // 사각형 도우미 클래스
    private static class Rectangle {
        float x, y, width, height;
        public Rectangle(float x, float y, float width, float height) {
            this.x = x; this.y = y; this.width = width; this.height = height;
        }
        public boolean contains(float px, float py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }
    }
}
