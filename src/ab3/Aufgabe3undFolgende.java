package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram gemShaders;
	private ShaderProgram waterShaders;
	private Matrix4 posMatrixGem;
	private Matrix4 posMatrixWater;
	private Matrix4 projectionMatrix;
	private Object3d gem;
	private Object3d water;
	private float angle;

	public static void main(String[] args) { new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700); }

	@Override
	protected void init() {
        gem = new Gem("marble_texture.png");
		water = new Gem("water_texture.png");

		posMatrixGem = new Matrix4();
		posMatrixWater = new Matrix4();

		gemShaders = new ShaderProgram("phong");
		waterShaders = new ShaderProgram("gouraud");

		angle = 1;
		projectionMatrix = new Matrix4(1f,1000f,70);

        drawObject(gemShaders, gem, posMatrixGem,0, -2, 2);
		drawObject(waterShaders, water, posMatrixWater,1, -3, 2);
    }

	@Override
	public void update() {
		animateObject(gemShaders, posMatrixGem);
		animateObject(waterShaders, posMatrixWater);
	}

	@Override
	protected void render() {
		// VAOs zeichnen

		glClearColor(1.0f,0.89f,0.91f,1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		renderObject(gemShaders, gem, 24);
		renderObject(waterShaders, water, 24);
	}

	@Override
	public void destroy() {
	}

	private void drawObject(ShaderProgram shaderProgram, Object3d object3d, Matrix4 positionMatrix, float xTranslation, float zTranslation, float scale) {
		glUseProgram(shaderProgram.getId());
		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		float[] vertices = object3d.getVertices();
		float[] normals = object3d.getNormals();
		float[] color = object3d.getColors();
		float[] uvCoords = object3d.getUvCoords();

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

		positionMatrix.translate(xTranslation,0, zTranslation).scale(scale);

		// Matrix an Shader übertragen
		int pro = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
		glUniformMatrix4fv(pro,false, projectionMatrix.getValuesAsArray());
	}


	private void animateObject(ShaderProgram shaderProgram, Matrix4 positionMatrix) {
		glUseProgram(shaderProgram.getId());
		positionMatrix.hover(.1f);
		Matrix4 transformationMatrix = new Matrix4();
		angle += 1;
		transformationMatrix.rotateY(angle);
		transformationMatrix.multiply(positionMatrix);
		int trans = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glUniformMatrix4fv(trans,false, transformationMatrix.getValuesAsArray());
	}

	private void renderObject(ShaderProgram shaderProgram, Object3d object3d, int faces) {
			glUseProgram(shaderProgram.getId());
			glBindTexture(GL_TEXTURE_2D, object3d.getTexture().getId());
			glDrawArrays(GL_TRIANGLES,0, faces);
	}
}