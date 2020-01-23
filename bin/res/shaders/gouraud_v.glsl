#version 330

layout(location = 0) in vec3 vertices;
layout(location = 1) in vec3 colordata;
layout(location = 2) in vec3 normals;
layout(location = 3) in vec2 uvData;

out vec3 color;
out vec2 uvCoords;


uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

vec4 vertexPosition;
float intensity;
vec3 lightsource;

vec3 normal;
vec3 light;
vec3 reflected;
vec3 view;

float luminance;
mat3 normalMatrix;


void main() {
    vertexPosition = transformationMatrix * vec4(vertices,1.0);
    gl_Position = projectionMatrix * vertexPosition;

    normalMatrix = inverse(transpose(mat3(transformationMatrix)));

    intensity = 1.5f;
    lightsource = vec3(-3.0f, 2.0f, 8.0f);

    // Phong
    normal = normalize(normalMatrix * normals);
    light = normalize(lightsource - vertexPosition.xyz); // L
    reflected = reflect(-light, normal); // R
    view = normalize(-vertexPosition.xyz); // V

    luminance = intensity * (max(0, dot(normal,light)) + pow(dot(reflected, view),1));

    uvCoords = uvData;
    color = colordata * luminance;
}