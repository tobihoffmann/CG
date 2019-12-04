package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;

	private Matrix4 transMtrx = new Matrix4(2,1);

	public static void main(String[] args) {
		new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("aufgabe3");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen

		float[] vertices1 = {
				-0.5f, -0.5f, 0.5f,
				0.5f, -0.5f, 0.5f,
				0.0f, 0.5f, -0.8f,
		};

		float [] color = new float [] {
				1.0f,0.0f,0.0f,
				0.0f,1.0f,0.0f,
				0.0f,0.0f,1.0f
		};


		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboId= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);

		glBufferData(GL_ARRAY_BUFFER, vertices1, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT,false, 0, 0);
		glEnableVertexAttribArray(0);

		int vboId2= glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId2);

		glBufferData(GL_ARRAY_BUFFER, color, GL_STATIC_DRAW);
		glVertexAttribPointer(1,3, GL_FLOAT, false,0,0);
		glEnableVertexAttribArray(1);


		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		//glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		transMtrx.scale(0.999f).rotateZ(1);
	}

	@Override
	protected void render() {
		// VAOs zeichnen
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glDrawArrays(GL_TRIANGLES,0,3);

		// Matrix an Shader übertragen
		int loc = glGetUniformLocation(shaderProgram.getId(), "transMtrx");
		glUniformMatrix4fv(loc,false, transMtrx.getValuesAsArray());
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