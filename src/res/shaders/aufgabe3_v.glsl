#version 330

layout(location = 0) in vec3 colordata;
layout(location = 1) in vec3 vertices;
layout(location = 2) in vec3 normals;
layout(location = 3) in vec2 uvData;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

out vec3 color;
out vec4 pixelPosition;
out vec3 normalsFrag;
out vec2 uvCoords;



void main() {

    uvCoords = uvData;
    color = colordata;

    pixelPosition = transformationMatrix * vec4(vertices,1.0);
    gl_Position = projectionMatrix * pixelPosition;

    mat3 normalMatrix = inverse(transpose(mat3(transformationMatrix)));
    normalsFrag = normalize(normalMatrix * normals);

}
