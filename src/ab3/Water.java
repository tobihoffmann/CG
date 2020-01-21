package ab3;

public class Water extends Object3d {

    public Water(String texture) {
        this.vertices = drawVertices();
        this.normals = drawNormals();
        this.colors = setColors();
        this.uvCoords = drawUvCoords();
        setTexture(texture);
    }

    private float[] drawVertices() {
        float[] vertices = new float[] {

        };
        return vertices;
    }

    private float[] drawNormals() {
        float[] normals = new float[] {

        };
        return normals;
    }
    private float[] setColors() {
        float[] colors = new float[] {

        };
        return colors;
    }

    private float[] drawUvCoords() {
        float[] uvCoords = new float[] {

        };
        return uvCoords;
    }
}
