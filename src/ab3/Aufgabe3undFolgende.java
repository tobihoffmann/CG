package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;

	private Matrix4 positionMatrix = new Matrix4();
	private Matrix4 projectionMatrix;

	private float angle = 1;

	private Objects3d gem;


	public static void main(String[] args) { new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700); }

	@Override
	protected void init() {
        gem = new Gem("marble_texture.png");
        projectionMatrix = new Matrix4(1f,1000f,70);

        shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

        float[] vertices = gem.getVertices();
		float[] normals = gem.getNormals();
		float[] color = gem.getColors();
		float[] uvCoords = gem.getUvCoords();

		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboVertices= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertices);

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(1);

		int vboColor= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboColor);

		glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
		glVertexAttribPointer(0,3, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(0);

		int vboNormals= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboNormals);

		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		glVertexAttribPointer(2,3, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(2);

		int vboUVs= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboUVs);

		glBufferData(GL_ARRAY_BUFFER, uvCoords, GL_STATIC_DRAW);
		glVertexAttribPointer(3,2, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(3);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren

		positionMatrix.translate(0,0,-2).scale(2);
    }

	@Override
	public void update() {
		positionMatrix.hover(.1f);
		Matrix4 transformationMatrix = new Matrix4();
		angle += 1;
		transformationMatrix.rotateY(angle);
		transformationMatrix.multiply(positionMatrix);
		int trans = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glUniformMatrix4fv(trans,false, transformationMatrix.getValuesAsArray());
	}

	@Override
	protected void render() {
		// VAOs zeichnen

		glClearColor(1.0f,0.89f,0.91f,1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glDrawArrays(GL_TRIANGLES,0,24);

		// Matrix an Shader übertragen
		int pro = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
		glUniformMatrix4fv(pro,false, projectionMatrix.getValuesAsArray());

		glBindTexture(GL_TEXTURE_2D, gem.getTexture().getId());

	}

	@Override
	public void destroy() {
	}
}