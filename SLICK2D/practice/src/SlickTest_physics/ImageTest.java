package SlickTest_physics;

import org.newdawn.slick.*;

public class ImageTest extends BasicGame {

    private Image myImage;

    public ImageTest() {
        super("Slick2D 이미지 출력 테스트");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        // 이미지 로드 (경로는 프로젝트 폴더 기준)
        myImage = new Image("resources/64yA7ZGc1.jpg");
    }

    @Override
    public void update(GameContainer container, int delta) {
        // 매 프레임마다 호출됨 (아직은 아무 것도 안 함)
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        g.drawString("이미지 로드 성공!", 100, 50);
        myImage.draw(100, 100 , 0.5f);  // (x, y) 위치에 이미지 그리기
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new ImageTest());
        app.setDisplayMode(800, 600, false);
        app.start();
    }
}
