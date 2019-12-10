package ab3;

public class Vertex {

    private float x, y, z;
    private float r,g,b;

    public Vertex(float x, float y, float z, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = (float) r / 255;
        this.g = (float) g / 255;
        this.b = (float) b / 255;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }
}
