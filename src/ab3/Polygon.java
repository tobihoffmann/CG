package ab3;

public class Polygon {

    private float[] vertices;
    private float[] colors;
    private float[] vertexNormals;


    public Polygon(Vertex vertex1, Vertex vertex2, Vertex vertex3) {
        //assign vertex array
        this.vertices = new float[9];
        vertices[0] = vertex1.getX();
        vertices[1] = vertex1.getY();
        vertices[2] = vertex1.getZ();

        vertices[3] = vertex2.getX();
        vertices[4] = vertex2.getY();
        vertices[5] = vertex2.getY();

        vertices[6] = vertex3.getX();
        vertices[7] = vertex3.getY();
        vertices[8] = vertex3.getZ();

        //assign color array
        this.colors = new float[9];
        colors[0] = vertex1.getR();
        colors[1] = vertex1.getG();
        colors[2] = vertex1.getB();

        colors[3] = vertex2.getR();
        colors[4] = vertex2.getG();
        colors[5] = vertex2.getB();

        colors[6] = vertex3.getR();
        colors[7] = vertex3.getG();
        colors[8] = vertex3.getB();

        //assign normal array
        this.vertexNormals = new float[9];
        vertexNormals[0] = this.getNormal()[0];
        vertexNormals[1] = this.getNormal()[1];
        vertexNormals[2] = this.getNormal()[2];

        vertexNormals[3] = this.getNormal()[0];
        vertexNormals[4] = this.getNormal()[1];
        vertexNormals[5] = this.getNormal()[2];

        vertexNormals[6] = this.getNormal()[0];
        vertexNormals[7] = this.getNormal()[1];
        vertexNormals[8] = this.getNormal()[2];
    }

    public float[] getNormal() {
        float[] normal = new float[3];

        //TODO: Irgendwas mit kreuzprodukt und so

        return normal;
    }

    public float[] getVertexNomals() {
        return this.vertexNormals;
    }

}
