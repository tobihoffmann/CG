#version 330

in vec4 pixelPosition;
in vec3 color;
in vec3 normalsFrag; // N
in vec2 uvCoords;

out vec3 pColor;

// Phong
float intensity = 1.5f;
vec3 lightsource = vec3(-10.0f, 6.0f, 40.0f);
vec3 light = normalize(lightsource - pixelPosition.xyz); // L
vec3 reflected = reflect(-light, normalsFrag); // R
vec3 view = normalize(-pixelPosition.xyz); // V

float luminance = intensity * (max(0, dot(normalsFrag,light)) + pow(dot(reflected, view),1));


uniform sampler2D smplr;
vec3 textureColor = vec3(texture(smplr, uvCoords));


void main() {

    vec3 lightColor = luminance * color;
    pColor = (textureColor + lightColor) / 2;
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
 * R = Reflektiertes Licht (reflect(-L, N)
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