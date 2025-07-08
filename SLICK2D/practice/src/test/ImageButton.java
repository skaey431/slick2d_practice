package test;

import org.newdawn.slick.*;

public class ImageButton {
    private float x, y, width, height;
    private Image normalImage;
    private Image hoverImage;
    private boolean clicked = false;
    private boolean wasPressed = false;

    public ImageButton(float x, float y, float targetWidth, Image normalImage, Image hoverImage) {
        this.x = x;
        this.y = y;
        this.normalImage = normalImage;
        this.hoverImage = hoverImage;

        float ratio = normalImage.getHeight() / (float) normalImage.getWidth();
        this.width = targetWidth;
        this.height = targetWidth * ratio;
    }

    public void update(GameContainer container) {
        Input input = container.getInput();
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();

        boolean over = isMouseOver(mouseX, mouseY);
        boolean pressed = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);

        // 클릭 순간만 판별 (누른 상태가 아니라 처음 누를 때)
        if (over && pressed && !wasPressed) {
            clicked = true;
        } else {
            clicked = false;
        }

        wasPressed = pressed;
    }

    public void render(GameContainer container, Graphics g) {
        Input input = container.getInput();
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();

        Image imgToDraw = isMouseOver(mouseX, mouseY) ? hoverImage : normalImage;
        g.drawImage(imgToDraw, x, y, x + width, y + height, 0, 0, imgToDraw.getWidth(), imgToDraw.getHeight());
    }

    public boolean isClicked() {
        return clicked;
    }

    public boolean isMouseOver(float mx, float my) {
        return mx >= x && mx <= x + width && my >= y && my <= y + height;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
