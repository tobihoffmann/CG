#version 330

layout(location = 1) in vec3 vertices;

uniform mat4 transform;
uniform mat4 project;
layout(location = 0) in vec3 colordata;

out vec3 color;


void main() {

    color = colordata;
    // hier kann Transformation erfolgen
    gl_Position = transform * vec4(vertices,1.0);


    // warum nicht als out wie im F.Shader?!}
    // warum WAS nicht als out?
}
