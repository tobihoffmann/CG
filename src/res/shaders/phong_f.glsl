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