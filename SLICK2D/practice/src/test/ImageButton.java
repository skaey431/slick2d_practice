package test;

import org.newdawn.slick.*;

public class ImageButton {
    private float x, y, width, height;
    private Image normalImage;
    private Image hoverImage;
    private boolean clicked = false;

    public ImageButton(float x, float y, Image normalImage, Image hoverImage) {
        this.x = x;
        this.y = y;
        this.normalImage = normalImage;
        this.hoverImage = hoverImage;
        this.width = normalImage.getWidth();
        this.height = normalImage.getHeight();
    }

    public void update(GameContainer container) {
        Input input = container.getInput();
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();

        if (isMouseOver(mouseX, mouseY) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            clicked = !clicked;
        }
    }

    public void render(GameContainer container, Graphics g) {
        Input input = container.getInput();
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();

        Image imgToDraw = isMouseOver(mouseX, mouseY) ? hoverImage : normalImage;
        g.drawImage(imgToDraw, x, y , 100,100, imgToDraw.getWidth()*16, imgToDraw.getHeight()*16);

        g.setColor(Color.white);
        g.drawString(clicked ? "클릭됨!" : "클릭해보세요", x + 20, y + height + 10);
    }

    public boolean isMouseOver(float mouseX, float mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    public boolean isClicked() {
        return clicked;
    }
}
