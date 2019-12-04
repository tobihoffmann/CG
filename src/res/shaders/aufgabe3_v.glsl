#version 330

layout(location = 0) in vec3 vertices1;
uniform mat4 transMtrx;
layout(location = 1) in vec3 colordata;

out vec3 color;


void main() {

    color = colordata;
    // hier kann Transformation erfolgen
    gl_Position = transMtrx * vec4(vertices1,1.0);

    // warum nicht als out wie im F.Shader?!}
    // warum WAS nicht als out?
}
