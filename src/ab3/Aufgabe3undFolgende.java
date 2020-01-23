package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram phongShader;
	private ShaderProgram gouraudShader;
	private Matrix4 projectionMatrix;
	private Object3d gem;
	private Object3d water;
	private float angle;

	public static void main(String[] args) { new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700); }

	@Override
	protected void init() {
        gem = new Gem("marble_texture.png", 4);
		water = new Water("water_texture.png");

		phongShader = new ShaderProgram("phong");
		gouraudShader = new ShaderProgram("gouraud");

		angle = 0;
		projectionMatrix = new Matrix4(1f,1000f,70);

		drawObject(phongShader, gem, gem.getModelMatrix(),0, 0f,-5, 2.5f);
		drawObject(gouraudShader, water, water.getModelMatrix(),0, -2.0f, -5, 0.5f);

	}

	@Override
	public void update() {
		animateObject(phongShader, gem.getModelMatrix(),true,true);
		animateObject(gouraudShader, water.getModelMatrix(), false,false);
	}

	@Override
	protected void render() {
		// VAOs zeichnen

		glClearColor(1.0f,0.89f,0.91f,1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		renderObject(phongShader, gem, 24,0);
		renderObject(gouraudShader, water, 54,1);
	}

	@Override
	public void destroy() {
	}

	private void drawObject(ShaderProgram shaderProgram, Object3d object3d, Matrix4 modelMatrix, float xTranslation, float yTranslation, float zTranslation, float scale) {
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		float[] vertices = object3d.getVertices();
		float[] normals = object3d.getNormals();
		float[] color = object3d.getColors();
		float[] uvCoords = object3d.getUvCoords();

		glBindVertexArray(object3d.getVao());

		int vboVertices = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertices);

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(0);

		int vboColor = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboColor);

		glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
		glVertexAttribPointer(1,3, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(1);

		int vboNormals = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboNormals);

		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		glVertexAttribPointer(2,3, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(2);

		int vboUVs = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboUVs);

		glBufferData(GL_ARRAY_BUFFER, uvCoords, GL_STATIC_DRAW);
		glVertexAttribPointer(3,2, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(3);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren


		// Matrix an Shader übertragen
		int pro = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
		glUniformMatrix4fv(pro,false, projectionMatrix.getValuesAsArray());

		glUseProgram(0);

		modelMatrix.scale(scale).translate(xTranslation, yTranslation, zTranslation);
	}

	private void animateObject(ShaderProgram shaderProgram, Matrix4 modelMatrix, boolean isHovering, boolean rotatesClockWise) {
		glUseProgram(shaderProgram.getId());

		if (isHovering) modelMatrix.hover(0.1f);
		Matrix4 transformationMatrix = new Matrix4();
		angle += 0.25;
		if(rotatesClockWise) transformationMatrix.rotateY(angle);
		else transformationMatrix.rotateY(-angle);

        transformationMatrix.multiply(modelMatrix);
		int trans = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
		glUniformMatrix4fv(trans,false, transformationMatrix.getValuesAsArray());

		glUseProgram(0);
	}

	private void renderObject(ShaderProgram shaderProgram, Object3d object3d, int vertCount, int vao) {
		glUseProgram(shaderProgram.getId());

		glBindTexture(GL_TEXTURE_2D, object3d.getTexture().getId());

		glBindVertexArray(object3d.getVao());
		glDrawArrays(GL_TRIANGLES,0, vertCount);
		glUseProgram(0);
	}
}