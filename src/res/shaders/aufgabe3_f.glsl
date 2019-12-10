#version 330

in vec3 verticesFrag;
in vec3 color;
out vec3 pColor;


vec3 lightsource = vec3(-5,10, 3);

// N
vec3 normal = vec3(1,1,1);

// L
vec3 light = normalize(lightsource - verticesFrag);

// R
vec3 reflected = reflect(-light, normal);

// V
vec3 view = normalize(-verticesFrag);


float luminance = max(0, dot(normal,light)) + pow(dot(reflected, view),1);


void main() {

    pColor = luminance * color;
}