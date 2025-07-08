package test;

import org.newdawn.slick.*;

public class MainUI extends BasicGame {

    private ImageButton button1;
    private ImageButton button2;

    public MainUI() {
        super("Slick2D 버튼 분리 예제");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        Image normal = new Image("resources/click1.png");
        Image hover = new Image("resources/click2.png");

        button1 = new ImageButton(100, 250, 300 , normal, hover);
        button2 = new ImageButton(100, 50, 300 , normal, hover);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        button1.update(container);
        button2.update(container);
        if (button1.isClicked()) {
            System.out.println("버튼 1 클릭됨");
            // 예: 화면 전환, 상태 플래그 변경 등
        }

        if (button2.isClicked()) {
            System.out.println("버튼 2 클릭됨");
            // 예: 버튼에 따라 다른 기능 수행
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        button1.render(container, g); // ✅ GameContainer도 같이 넘김
        button2.render(container, g); // ✅ GameContainer도 같이 넘김
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainUI());
        app.setDisplayMode(800, 600, false);
        app.setTargetFrameRate(60);
        app.start();
    }
}
