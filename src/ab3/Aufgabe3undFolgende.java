package ab3;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private Matrix4 positionMatrix = new Matrix4();
	private Matrix4 projectionMatrix = new Matrix4(1f,1000f,70);
	private float angle = 1;


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

		positionMatrix.translate(0,0,-2);

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

		glClearColor(1.f,0.89f,0.91f,1f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glDrawArrays(GL_TRIANGLES,0,24);

		// Matrix an Shader übertragen
		int pro = glGetUniformLocation(shaderProgram.getId(), "projectionMatrix");
		glUniformMatrix4fv(pro,false, projectionMatrix.getValuesAsArray());
	}

	@Override
	public void destroy() {
	}
}

/** Licht
*
* 4 Vektoren für die Berechnung N,L,V,R
 *
 * N = Normale (Skalar von flächenaufspannenden Vetoren oder passende Einheitsvektoren wählen), kann mit Transformationsmatrix verwendet werden
 * Bei nicht proportionaler Skalierung wird Normal Matrix benötigt: Normalmatrix = invertse, transponierte Modelmatrix
 *
 *
 * L = normalize (Lichtpos - "meine Pos")
 * R = Reflektiertes Licht (reflect(-L, N) oder reflect(N, -L))
 * V = Camera normalize (0,0,0 - "meine Pos")
 *
 * Lichtpos = konstanter Vektor
 * meine Pos = Lineare Interpolation der Vertices des Dreiecks
 *
*
* N in Java eingeben > VBO > Vertexshader
* NormalMatrix * Normal > FragmentShader
*
 * dot(v3,v3) = skalarprodukt
 *
 * max(0, dot(N,L)) + pow(dot(R,V),n)
**/

// Licht
// meine pos = ModelMatrix * Eckkoordinate
// normalize = >
// L vektor = normalize (lichtpos - meine Pos)
// R = reflect(...)
// V = normalize(-meine Position)