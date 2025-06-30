import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class SlickTest extends BasicGame {

    public SlickTest() {
        super("Slick2D 설치 테스트");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        System.out.println("init 호출됨!");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        // 매 프레임 업데이트 (아무것도 안 해도 OK)
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        g.drawString("Slick2D 설치 완료!", 100, 100);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new SlickTest());
            app.setDisplayMode(800, 600, false);
            app.start();  // 게임 루프 실행
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
