package ab3;

import lenz.opengl.Texture;

public class Objects3d {

    public float[] vertices;
    public float[] normals;
    public float[] colors;
    public float[] uvCoords;
    public Texture texture;

    public Objects3d() {
    }

    public float[] getVertices() {
        return vertices;
    }
    public float[] getNormals() {
        return normals;
    }
    public float[] getColors() {
        return colors;
    }
    public float[] getUvCoords() {
        return uvCoords;
    }
    public Texture getTexture() { return texture;}

    public void setTexture(String fileName) {
        Texture texture = new Texture(fileName);
        this.texture = texture;
        }
}

