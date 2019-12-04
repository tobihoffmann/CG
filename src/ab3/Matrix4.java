package ab3;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

	private float[][] matrix;

	public Matrix4() {
		matrix = new float[][]{
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1},
		};
	}


	private Matrix4(Matrix4 copy) {
		for (int i = 0; i < 4; i++) this.matrix[i] = copy.matrix[i].clone();
	}

	// projection matrix
	public Matrix4(float near, float far, float fov) {
		float scale = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));

		this.matrix = new float[][] {
			{ scale, 0, 0, 0},
			{ 0, scale, 0, 0},
			{ 0, 0, ((-near + far) / (far - near)),((-2 * far * near) / (far - near)) },
			{ 0, 0, -1, 0}
		};


	}

	private Matrix4 multiply(Matrix4 other) {
		float[][] m = new float[4][4];
		for(int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				for (int i = 0; i < 4; i++) {
					m[row][col] += other.matrix[row][i] * this.matrix[i][col];
				}
			}
		}
		this.matrix = m;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		Matrix4 t = new Matrix4();
		t.matrix = new float[][] {
				{1, 0, 0, x},
				{0, 1, 0, y},
				{0, 0, 1, z},
				{0, 0, 0, 1}
		};
		this.multiply(t);
		return this;
	}

	public Matrix4 scale(float uniformFactor) {
		Matrix4 t = new Matrix4();
		t.matrix = new float[][] {
				{uniformFactor, 0, 0, 0},
				{0, uniformFactor, 0, 0},
				{0, 0, uniformFactor, 0},
				{0, 0, 0, 1}
		};
		this.multiply(t);
		return this;
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		Matrix4 t = new Matrix4();
		t.matrix = new float[][] {
				{sx, 0, 0, 0},
				{0, sy, 0, 0},
				{0, 0, sz, 0},
				{0, 0, 0, 1}
		};
		this.multiply(t);
		return this;
	}

	public Matrix4 rotateX(float angle) {
		double rad = angle * Math.PI / 180;
		float c = (float) Math.cos(rad);
		float s = (float) Math.sin(rad);

		Matrix4 t = new Matrix4();
		t.matrix = new float[][] {
				{1, 0, 0, 0},
				{0, c, -s, 0},
				{0, s, c, 0},
				{0, 0, 0, 1}
		};
		this.multiply(t);
		return this;
	}

	public Matrix4 rotateY(float angle) {
		double rad = angle * Math.PI / 180;
		float c = (float) Math.cos(rad);
		float s = (float) Math.sin(rad);

		Matrix4 t = new Matrix4();
		t.matrix = new float[][] {
				{c, 0, -s, 0},
				{0, 1, 0, 0},
				{s, 0, c, 0},
				{0, 0, 0, 1}
		};
		this.multiply(t);
		return this;
	}

	public Matrix4 rotateZ(float angle) {
		double rad = angle * Math.PI / 180;
		float c = (float) Math.cos(rad);
		float s = (float) Math.sin(rad);

		Matrix4 t = new Matrix4();
		t.matrix = new float[][] {
				{c, -s, 0, 0},
				{s, c, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
		};
		this.multiply(t);
		return this;
	}

	public float[] getValuesAsArray() {
		float[] matrix1d = new float[16];
		for (int col = 0; col < 4; col++) {
			for (int row = 0; row < 4; row++) {
				matrix1d[row * 4 + col] = this.matrix[row][col];
			}
		}
		return matrix1d;
	}
}
