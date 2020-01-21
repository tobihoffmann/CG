#version 330

in vec3 color;
in vec2 uvCoords;

out vec3 pColor;

uniform sampler2D smplr;
vec3 textureColor = vec3(texture(smplr, uvCoords));

void main() {
    pColor = (textureColor + color) / 2;
}