#version 330

layout(location = 0) in vec3 colordata;
layout(location = 1) in vec3 vertices;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;

out vec3 color;
out vec4 pixelPosition;
out vec3 verticesFrag;



void main() {


    verticesFrag = vertices;
    color = colordata;
    pixelPosition = transformationMatrix * vec4(vertices,1.0);
    gl_Position = projectionMatrix * pixelPosition;


}
