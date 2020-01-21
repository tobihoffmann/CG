#version 330

layout(location = 0) in vec3 colordata;
layout(location = 1) in vec3 vertices;
layout(location = 2) in vec3 normals;
layout(location = 3) in vec2 uvData;

out vec3 color;
out vec2 uvCoords;


uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
vec4 pixelPosition;

// Phong
float intensity = 1.5f;
vec3 lightsource = vec3(-10.0f, 6.0f, 40.0f);

vec3 light = normalize(lightsource - pixelPosition.xyz); // L
vec3 reflected = reflect(-light, normals); // R
vec3 view = normalize(-pixelPosition.xyz); // V

float luminance = intensity * (max(0, dot(normals,light)) + pow(dot(reflected, view),1));

void main() {
    pixelPosition = transformationMatrix * vec4(vertices,1.0);
    gl_Position = projectionMatrix * pixelPosition;

    mat3 normalMatrix = inverse(transpose(mat3(transformationMatrix)));
    uvCoords = uvData;

    color = colordata * luminance;
}
