package test.physics;

import org.newdawn.slick.*;

public class Wall {
    private float x, y, width, height;
    private String materialSource;
    private Image materialImage; // 추가: 이미지

    public Wall(float x, float y, float width, float height, String materialSource) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.materialSource = materialSource;
        try {
            this.materialImage = new Image(materialSource); // 이미지 로드
        } catch (SlickException e) {
            e.printStackTrace();
            this.materialImage = null;
        }
    }

    public void render(Graphics g) {
        if (materialImage != null) {
            int tileW = materialImage.getWidth();
            int tileH = materialImage.getHeight();

            for (float i = 0; i < width; i += tileW) {
                for (float j = 0; j < height; j += tileH) {
                    float drawW = Math.min(tileW, width - i);
                    float drawH = Math.min(tileH, height - j);
                    g.drawImage(materialImage.getSubImage(0, 0, (int) drawW, (int) drawH), x + i, y + j);
                }
            }
        } else {
            // 이미지 없으면 그냥 회색 박스로 렌더링
            g.setColor(Color.gray);
            g.fillRect(x, y, width, height);
        }
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
