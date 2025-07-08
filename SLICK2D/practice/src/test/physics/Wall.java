package test.physics;

import org.newdawn.slick.*;

public class Wall {
    private float x, y, width, height;

    public Wall(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }

    public boolean intersects(float px, float py, float pwidth, float pheight) {
        return px < x + width && px + pwidth > x &&
                py < y + height && py + pheight > y;
    }

    // Getter
    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
}
