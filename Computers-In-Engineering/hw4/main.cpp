//
// Programmer: Fam Trinli
// Fam Trinli's Source Code from: https://www.youtube.com/watch?v=YzhhVHb0WVY
// Description: This program is a car racing game
//
// Modified by: <Brandon Cano>
// Date: 10/12/2021
//
#include <SFML/Graphics.hpp>
#include <cmath>

using namespace sf;

const int num = 8; //checkpoints
int points[num][2] = {300, 610,
                      1270, 430,
                      1380, 2380,
                      1900, 2460,
                      1970, 1700,
                      2550, 1680,
                      2560, 3150,
                      500, 3300};

class Car {
public:
    explicit Car(float uSpeed = 2, float uAngle = 0 ) {
        setSpeed(uSpeed);
        setAngle(uAngle);
        n = 0;
    }

    // x orientation for car
    void setX(float X) {
        x = X;
    }

    float getX() const {
        return x;
    }

    // y orientation for car
    void setY(float Y) {
        y = Y;
    }

    float getY() const {
        return y;
    }

    // car speed
    void setSpeed(float Speed) {
        speed = Speed;
    }

    float getSpeed() const {
        return speed;
    }

    // car angle when turning
    void setAngle(float Angle) {
        angle = Angle;
    }

    float getAngle() const {
        return angle;
    }

    /****************************************************
     * move is a class method meant for the computers
     * cars in order for them to be able to turn by
     * using trig functions to change the speed, in order
     * for the orientation of the cars to shift
     ***************************************************/
    void move() {
        x += sin(angle) * speed;
        y -= cos(angle) * speed;
    }

    /****************************************************
     * findTarget is a class method that will check each
     * car to see if they have reached on the checkpoints
     * that are initialized in the start of the program.
     * Once a target is met then the car will start to
     * turn and then will stop once it reaches a certain
     * position
     ***************************************************/
    void findTarget() {
        float tx = points[n][0]; // x target position
        float ty = points[n][1]; // y target position
        float beta = angle - atan2(tx - x, -ty + y); // find the difference in the angle
        if (sin(beta) < 0) {
            angle += 0.005 * speed; // turn car to the right
        }
        else {
            angle -= 0.005 * speed; // turn car to the left
        }
        if ((x - tx) * (x - tx) + (y - ty) * (y - ty) < 25 * 25) {
            n = (n + 1) % num;
        }
    }

private:
    // members variables
    float x;
    float y;
    float angle;
    float speed;
    int n;
};


int main() {
    // app is the main window of the game
    RenderWindow app(VideoMode(640, 480), "Car Racing Game!");
    // limit refresh rate to no more than 60 fps
    app.setFramerateLimit(60);

    // create texture objects and loads the textures and errors if files did not load
    Texture t1, t2, t3;
    t1.loadFromFile("images/background.png");
    if (!t1.loadFromFile("images/background.png")) {
        return EXIT_FAILURE;
    }
    t2.loadFromFile("images/car.png");
    if (!t2.loadFromFile("images/car.png")) {
        return EXIT_FAILURE;
    }

    // make textures smooth on screen (pixel boundaries are less visible)
    t1.setSmooth(true);
    t2.setSmooth(true);

    Sprite sBackground(t1), sCar(t2); // loads textures onto sprites
    sBackground.scale(2, 2); // scales background
    sCar.setOrigin(22, 22); // sets origin on sprite

    float carRadius = 22; // radius of the cars for turning

    const int N = 5; // number of opposing cars
    Car car[N]; // array of car objects
    // sets speed and starting position for each car
    for (int i = 0; i < N; i++) {
        car[i].setX(300 + i * 50);
        car[i].setY(1700 + i * 80);
        car[i].setSpeed(7 + i);
    }

    float speed = 0, angle = 0; // player speed and angle for turning
    float maxSpeed = 12.0; // max speed
    float acc = 0.2, dec = 0.3; // acceleration for speeding up and slowing down
    float turnSpeed = 0.08; // speed factor for turning

    int offsetX = 0, offsetY = 0; // used to offset locations

    while (app.isOpen()) {
        Event e;
        while (app.pollEvent(e)) {
            // checks the event of e to see if the application is still open
            if (e.type == Event::Closed) {
                app.close();
            }
        }

        // keyboard arrows to control the car players car
        bool Up = 0, Right = 0, Down = 0, Left = 0;
        if (Keyboard::isKeyPressed(Keyboard::Up)) { Up = 1; }
        if (Keyboard::isKeyPressed(Keyboard::Right)) { Right = 1; }
        if (Keyboard::isKeyPressed(Keyboard::Down)) { Down = 1; }
        if (Keyboard::isKeyPressed(Keyboard::Left)) { Left = 1; }

        // car movement (up arrow)
        if (Up && speed < maxSpeed) {
            if (speed < 0) {
                speed += dec;
            }
            else {
                speed += acc;
            }
        }

        // car movement (down arrow)
        if (Down && speed > -maxSpeed) {
            if (speed > 0) {
                speed -= dec;
            }
            else {
                speed -= acc;
            }
        }

        // if keys are not pressed then the car will slow down and stop
        if (!Up && !Down) {
            if (speed - dec > 0) {
                speed -= dec;
            }
            else if (speed + dec < 0) {
                speed += dec;
            }
            else {
                speed = 0;
            }
        }

        // turns the player either right or left depending on user input
        if (Right && speed != 0) {
            angle += turnSpeed * speed / maxSpeed;
        }
        if (Left && speed != 0) {
            angle -= turnSpeed * speed / maxSpeed;
        }

        // sets speed and angle for the player
        car[0].setSpeed(speed);
        car[0].setAngle(angle);

        // all cars will move
        for (int i = 0; i < N; i++) {
            car[i].move();
        }

        // checks key locations in order to turn at correct spots
        for (int i = 1; i < N; i++) {
            car[i].findTarget();
        }

        // collision
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int dx = 0, dy = 0; // more x and y values for changes positions
                while (dx * dx + dy * dy < 4 * carRadius * carRadius) {
                    car[i].setX(car[i].getX() + dx / 10.0);
                    car[i].setY(car[i].getY() + dy / 10.0);
                    car[j].setX(car[j].getX() - dx / 10.0);
                    car[j].setY(car[j].getY() - dy / 10.0);
                    dx = car[i].getX() - car[j].getX();
                    dy = car[i].getY() - car[j].getY();
                    if (!dx && !dy) {
                        // we set the values high enough to break out of the while loop
                        // without needing to use break;
                        dx = 100;
                        dy = 100;
                    }
                }
            }
        }

        // before redrawing you must clear white
        app.clear(Color::White);

        // prevents the screen from scrolling past its boundaries (for 2 sides)
        if (car[0].getX() > 320) {
            offsetX = car[0].getX() - 320;
        }
        if (car[0].getY() > 240) {
            offsetY = car[0].getY() - 240;
        }

        // sets position and draws the background in relation to the player
        sBackground.setPosition(-offsetX, -offsetY);
        app.draw(sBackground);

        // colors for the other cars
        Color colors[10] = {Color::Red, Color::Green, Color::Magenta, Color::Blue, Color::White};

        // the other car not controlled by the player are set in a specific path
        for (int i = 0; i < N; i++) {
            sCar.setPosition(car[i].getX() - offsetX, car[i].getY() - offsetY);
            sCar.setRotation(car[i].getAngle() * 180 / 3.141593);
            sCar.setColor(colors[i]);
            app.draw(sCar);
        }
        // displays the updated image
        app.display();
    }

    return 0;
}
