#version 330

in vec4 pixelPosition;
in vec3 color;
in vec3 normalsFrag; // N
in vec2 uvCoords;

out vec3 pColor;

float intensity;
vec3 lightsource;

vec3 light;
vec3 reflected;
vec3 view;

float luminance;
vec3 lightColor;

uniform sampler2D smplr;
vec3 textureColor;

void main() {
    intensity = 1.5f;
    lightsource = vec3(-3.0f, 2.0f, 8.0f);

    // Phong
    light = normalize(lightsource - pixelPosition.xyz); // L
    reflected = reflect(-light, normalsFrag); // R
    view = normalize(-pixelPosition.xyz); // V

    luminance = intensity * (max(0, dot(normalsFrag,light)) + pow(dot(reflected, view),1));

    lightColor = luminance * color;
    textureColor = vec3(texture(smplr, uvCoords));

    pColor = (textureColor + lightColor) / 2;
}