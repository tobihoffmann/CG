package ab3;

public class Gem extends Object3d {

    public Gem(String texture) {
        this.vertices = drawVertices();
        this.normals = drawNormals();
        this.colors = setColors();
        this.uvCoords = drawUvCoords();
        setTexture(texture);
    }

    private float[] drawVertices() {
        float[] vertices = new float[] {
                // 1s triangle
                0.0f, 0.4f, 0.0f,
                -0.2f, 0.0f, -0.2f,
                0.2f, 0.0f, -0.2f,
                // 2nd triangle
                0.0f, -0.4f, 0.0f,
                -0.2f, 0.0f, -0.2f,
                0.2f, 0.0f, -0.2f,
                // 3rd triangle
                0.0f, 0.4f, 0.0f,
                0.2f, 0.0f, -0.2f,
                0.2f, 0.0f, 0.2f,
                // 4th triangle
                0.0f, -0.4f, 0.0f,
                0.2f, 0.0f, -0.2f,
                0.2f, 0.0f, 0.2f,
                // 5th triangle
                0.0f, 0.4f, 0.0f,
                0.2f, 0.0f, 0.2f,
                -0.2f, 0.0f, 0.2f,
                // 6th triangle
                0.0f, -0.4f, 0.0f,
                0.2f, 0.0f, 0.2f,
                -0.2f, 0.0f, 0.2f,
                // 7th triangle
                0.0f, 0.4f, 0.0f,
                -0.2f, 0.0f, 0.2f,
                -0.2f, 0.0f, -0.2f,
                // 8th triangle
                0.0f, -0.4f, 0.0f,
                -0.2f, 0.0f, 0.2f,
                -0.2f, 0.0f, -0.2f,
        };
        return vertices;
    }

    private float[] drawNormals() {
        float[] normals = new float[] {
                // 1s triangle
                0.0f, 1.0f, -1.0f,
                0.0f, 1.0f, -1.0f,
                0.0f, 1.0f, -1.0f,
                // 2nd triangle
                0.0f, -1.0f, -1.0f,
                0.0f, -1.0f, -1.0f,
                0.0f, -1.0f, -1.0f,
                // 3rd triangle
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 0.0f,
                // 4th triangle
                1.0f,-1.0f, 0.0f,
                1.0f,-1.0f, 0.0f,
                1.0f,-1.0f, 0.0f,
                // 5th triangle
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                0.0f, 1.0f, 1.0f,
                // 6th triangle
                0.0f, -1.0f, 1.0f,
                0.0f, -1.0f, 1.0f,
                0.0f, -1.0f, 1.0f,
                // 7th triangle
                -1.0f, 1.0f, 0.0f,
                -1.0f, 1.0f, 0.0f,
                -1.0f, 1.0f, 0.0f,
                // 8th triangle
                -1.0f,-1.0f,0.0f,
                -1.0f,-1.0f,0.0f,
                -1.0f,-1.0f,0.0f
        };
        return normals;
    }
    private float[] setColors() {
        float[] colors = new float[] {
                // 1s triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 2nd triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 3rd triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 4th triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 5th triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 6th triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 7th triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                // 8th triangle
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
                0.62f, 0.97f, 0.88f,
        };
        return colors;
    }

    private float[] drawUvCoords() {
        float[] uvCoords = new float[] {
                // 1s triangle
                0.4f, 0.0f,
                0.0f, 1.0f,
                0.8f, 1.0f,
                // 2nd triangle
                0.4f, 0.0f,
                0.0f, -1.0f,
                0.8f, -1.0f,
                // 3rd triangle
                0.4f, 0.0f,
                0.0f, 1.0f,
                0.8f, 1.0f,
                // 4th triangle
                0.4f, 0.0f,
                0.0f, -1.0f,
                0.8f, -1.0f,
                // 5th triangle
                0.4f, 0.0f,
                0.0f, 1.0f,
                0.8f, 1.0f,
                // 6th triangle
                0.4f, 0.0f,
                0.0f, -1.0f,
                0.8f, -1.0f,
                // 7th triangle
                0.4f, 0.0f,
                0.0f, 1.0f,
                0.8f, 1.0f,
                // 8th triangle
                0.4f, 0.0f,
                0.0f, -1.0f,
                0.8f, -1.0f,
        };
        return uvCoords;
    }
}
