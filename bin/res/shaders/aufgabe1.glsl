#version 330

out vec3 pColor;
vec2 canvas = gl_FragCoord.xy;

// convert Degree to Radiant
float degToRad(in float degree) {

    float pi = 3.141592653589793238462643383279;
    return pi / 180 * degree;
}


// Rotate Pixels
vec2 rotate(in vec2 coord, in float deg) {

    deg = degToRad(deg);
    mat2 rotate = mat2(cos(deg), sin(deg), -sin(deg), cos(deg));
    return coord * rotate;
}


// Painting a rectangle
void drawRectangle (in int left, in int right, in int bottom, in int top, in float deg) {

    canvas = rotate(canvas - vec2((left + right) / 2, (top + bottom) / 2), -deg );
    canvas = canvas + vec2((left + right) / 2, (top + bottom) / 2);

    if (canvas.x > left && canvas.x < right && canvas.y > bottom && canvas.y < top) {
            pColor = vec3(0.2, 0.7, 0.7);
        }

    canvas = rotate(canvas - vec2((left + right) / 2, (top + bottom) / 2), deg );
    canvas = canvas + vec2((left + right) / 2, (top + bottom) / 2);
}


// Painting a circle
void drawCircle(in vec2 center, in int radius) {

    if (distance(center, gl_FragCoord.xy) < radius) {
        pColor = vec3(0.2, 0.7, 0.7);
    }
}


void main() {
    pColor = vec3(1,.5,.5);
    drawCircle(vec2(350,400),200);
    drawRectangle(750,1200,750,1200, 45);


}


