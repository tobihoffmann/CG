package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private Matrix4 transform = new Matrix4();
	private Matrix4 project= new Matrix4(3f,1000f,40);

	public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		float[] vertices = {
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

		float[] color = {
				0.8f,1.0f,0.94f,
				0.8f,1.0f,0.94f,
				0.8f,1.0f,0.94f,

				0.62f,0.97f,0.88f,
				0.62f,0.97f,0.88f,
				0.62f,0.97f,0.88f,

				0.62f,0.97f,0.88f,
				0.62f,0.97f,0.88f,
				0.62f,0.97f,0.88f,

				0.57f,0.88f,0.79f,
				0.57f,0.88f,0.79f,
				0.57f,0.88f,0.79f,

				0.57f,0.88f,0.79f,
				0.57f,0.88f,0.79f,
				0.57f,0.88f,0.79f,

				0.54f,0.81f,0.72f,
				0.54f,0.81f,0.72f,
				0.54f,0.81f,0.72f,

				0.54f,0.81f,0.72f,
				0.54f,0.81f,0.72f,
				0.54f,0.81f,0.72f,

				0.49f,0.74f,0.65f,
				0.49f,0.74f,0.65f,
				0.49f,0.74f,0.65f,

		};


		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboId1= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId1);

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(1);


		int vboId= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);

		glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
		glVertexAttribPointer(0,3, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(0);


		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		transform.rotateY(1f).rotateZ(.25f).scale(1.001f);
	}

	@Override
	protected void render() {
		// VAOs zeichnen
		glClearColor(1.f,0.89f,0.91f,1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glDrawArrays(GL_TRIANGLES,0,24);

		// Matrix an Shader übertragen
		int loc = glGetUniformLocation(shaderProgram.getId(), "transform");
		glUniformMatrix4fv(loc,false, transform.getValuesAsArray());

		int pro = glGetUniformLocation(shaderProgram.getId(), "project");
		glUniformMatrix4fv(pro,false, project.getValuesAsArray());
	}

	@Override
	public void destroy() {
	}
}

// Licht
// meine pos = ModelMatrix * Eckkoordinate
// normalize = >
// L vektor = normalize (lichtpos - meine Pos)
// R = reflect(...)
// V = normalize(-meine Position)