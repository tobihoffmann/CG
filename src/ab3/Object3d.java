package ab3;

import lenz.opengl.Texture;

import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public abstract class Object3d {

    public float[] vertices;
    public float[] normals;
    public float[] colors;
    public float[] uvCoords;
    private Texture texture;
    private Matrix4 modelMatrix;
    private int vao;

    public Object3d() {
        this.vao = glGenVertexArrays();
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
    public Texture getTexture() { return texture; }
    public Matrix4 getModelMatrix() { return modelMatrix; }
    public int getVao() { return vao; }

    public void setTexture(String fileName) {
        Texture texture = new Texture(fileName);
        this.texture = texture;
    }

    public void setTexture(String fileName, int mipMapLevels) {
        Texture texture = new Texture(fileName, mipMapLevels);
        this.texture = texture;
        }

    public void setModelMatrix() { this.modelMatrix = new Matrix4(); }

}

