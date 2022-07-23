//
// Programmer: Fam Trinli
// Fam Trinli's Source Code from: https://www.youtube.com/watch?v=YzhhVHb0WVY
// Description: This program is a car racing game
//
// Modified by: <Brandon Cano>
// Date: 10/12/2021
//
#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>
#include <cmath>
#include <string>

using namespace sf;

const int num = 8; //checkpoints for cars
int points[num][2] = { 300, 610,
                       1270, 430,
                       1380, 2380,
                       1900, 2460,
                       1970, 1700,
                       2550, 1680,
                       2560, 3150,
                       500, 3300 };

const int cNum = 5; //checkpoints for cars/image rotation
int cPoints[cNum][3] = { 896, 320, 0,
                         1536, 2496, 0,
                         2120, 1480, 0,
                         2460, 3270, 135,
                         230, 2940, 270 };

class Car {
public:
    explicit Car(float uSpeed = 2, float uAngle = 0, int height = 3648, int width = 2880) {
        setSpeed(uSpeed);
        setAngle(uAngle);
        setHeight(height);
        setWidth(width);
    }

    // background height
    void setHeight(int height) {
        if(height < 0) {
            height = 0;
        }
        bHeight = height;
    }

    int getHeight() const {
        return bHeight;
    }

    // background width
    void setWidth(int width) {
        if(width < 0) {
            width = 0;
        }
        bWidth = width;
    }

    int getWidth() const {
        return bWidth;
    }

    // x orientation for car
    // this will prevent the car from being
    // able to drive off the map
    void setX(float X) {
        if(X<0) {
            X = 0;
        }
        if(X>bWidth) {
            X = bWidth;
        }
        x = X;
    }

    float getX() const {
        return x;
    }

    // y orientation for car
    // this will prevent the car from being
    // able to drive off the map
    void setY(float Y) {
        if(Y<0) {
            Y = 0;
        }
        if(Y>bHeight) {
            Y = bHeight;
        }
        y = Y;
    }

    float getY() const {
        return y;
    }

    // car speed
    // prevents the speed from being a negative value
    void setSpeed(float Speed) {
        speed = Speed;
        if(Speed < 0) {
            speed = 2;
        }
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
    int bHeight;
    int bWidth;
    int n = 0;
};

int main() {
    // app is the main window of the game
    RenderWindow app(VideoMode(640, 480), "Car Racing Game!");
    app.setFramerateLimit(60); // limit refresh rate to no more than 60 fps

    // create texture objects and loads the textures and errors if files did not load
    Texture t1, t2, t3, t4;
    t1.loadFromFile("images/background.png");
    if (!t1.loadFromFile("images/background.png")) {
        return EXIT_FAILURE;
    }
    t2.loadFromFile("images/car.png");
    if (!t2.loadFromFile("images/car.png")) {
        return EXIT_FAILURE;
    }
    t3.loadFromFile("images/checkpoint.png");
    if (!t3.loadFromFile("images/checkpoint.png")) {
        return EXIT_FAILURE;
    }
    t4.loadFromFile("images/StartScreen.png");
    if (!t4.loadFromFile("images/StartScreen.png")) {
        return EXIT_FAILURE;
    }

    // Night Ghost by Afika Studio, https://www.1001freefonts.com/, License: Free for personal use.
    Font font;
    font.loadFromFile("NightGhost.otf");
    if (!font.loadFromFile("NightGhost.otf")) {
        return EXIT_FAILURE;
    }

    // loads and plays the starting sound
    sf::SoundBuffer buffer;
    sf::Sound sound;
    // StartTheme was created by myself using MuseScore3
    buffer.loadFromFile("sounds/StartTheme.ogg");
    if (!buffer.loadFromFile("sounds/StartTheme.ogg")) {
        return EXIT_FAILURE;
    }
    sound.setBuffer(buffer);
    sound.setVolume(40);
    sound.play();

    // load and sets the race music
    sf::SoundBuffer buffer1;
    sf::Sound sound1;
    // RaceMusic was created by myself using MuseScore3
    buffer1.loadFromFile("sounds/RaceMusic.ogg");
    if (!buffer1.loadFromFile("sounds/RaceMusic.ogg")) {
        return EXIT_FAILURE;
    }
    sound1.setBuffer(buffer1);
    sound1.setVolume(40);
    sound1.setLoop(true);

    // make textures smooth on screen (pixel boundaries are less visible)
    t1.setSmooth(true);
    t2.setSmooth(true);
    t3.setSmooth(true);
    t4.setSmooth(true);

    Sprite sBackground(t1), sCar(t2), startBackground(t4); // loads textures onto sprites
    sBackground.scale(2, 2); // scales background
    sCar.setOrigin(22, 22); // sets origin on sprite

    // create all the checkpoint objects similar to the cars
    bool deleteSprite[cNum] = {0,0,0,0,0};
    Sprite checkpoints[cNum];
    for(int i=0; i<cNum; i++) {
        checkpoints[i].setTexture(t3);
    }

    float carRadius = 22; // radius of the cars for turning
    const int N = 5; // number of opposing cars
    Car car[N]; // array of car objects

    // sets speed and starting position for each car
    for (int i = 0; i < N; i++) {
        car[i].setHeight(2*1824);
        car[i].setWidth(2*1440);
        car[i].setX(300 + i * 50);
        car[i].setY(1900 + i * 80);
        car[i].setSpeed(7 + i);
    }

    float speed = 0, angle = 0; // player speed and angle for turning
    float maxSpeed = 12.0; // max speed
    float acc = 0.2, dec = 0.3; // acceleration for speeding up and slowing down
    float turnSpeed = 0.08; // speed factor for turning
    int offsetX = 0, offsetY = 0; // used to offset locations

    // sets up the checkpoint counter text
    sf::Text text;
    text.setFont(font);
    text.setString("Checkpoints: 0");
    text.setFillColor(Color::White);
    text.setOutlineColor(Color::Black);
    text.setStyle(sf::Text::Bold);
    text.setCharacterSize(30);
    text.setPosition(5,0);

    // timer text
    sf::Text tText;
    tText.setFont(font);
    tText.setString("Time: 0.000");
    tText.setFillColor(Color::White);
    tText.setOutlineColor(Color::Black);
    tText.setStyle(sf::Text::Bold);
    tText.setCharacterSize(30);
    tText.setPosition(5,50);

    // create clock object/set up for timer
    Clock clock;
    float timer = 0;
    float time;
    bool gameOver = false;
    bool startScreen = true;

    while (app.isOpen()) {
        // starts timer
        if(!gameOver) {
            time = clock.getElapsedTime().asSeconds();
            clock.restart();
            timer += time;
        }

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

        float cx = car[0].getX();
        float cy = car[0].getY();

        // checkpoint one
        if((cx>896 && cx<996) && (cy>320 && cy<510)) {
            deleteSprite[0] = true;
        }
        // checkpoint two
        if((cx>1536 && cx<1636) && (cy>2496 && cy<2686)) {
            deleteSprite[1] = true;
        }
        // checkpoint three
        if((cx>2120 && cx<2220) && (cy>1480 && cy<1670)) {
            deleteSprite[2] = true;
        }
        // checkpoint four
        if((cx>2256 && cx<2460) && (cy>3134 && cy<3336)) {
            deleteSprite[3] = true;
        }
        // checkpoint five
        if((cx>228 && cx<418) && (cy>2838 && cy<2938)) {
            deleteSprite[4] = true;
        }

        // before redrawing you must clear white
        app.clear(Color::White);

        // displays the start screen/welcome screen
        // This is one of the extra features that I added.
        // All I did was add a different background that would
        // display until the Space key is pressed then starts the game.
        while(startScreen) {
            app.draw(startBackground);
            app.display();
            if(Keyboard::isKeyPressed(Keyboard::Space)) {
                clock.restart();
                startScreen = false;
                sound.stop();
                sound1.play();
            }
        }

        // prevents the screen from scrolling past its boundaries (for 2 sides)
        // the two new conditions will help prevent showing whitespace outside
        // the background
        if (car[0].getX() > 320) {
            offsetX = car[0].getX() - 320;
        }
        if (car[0].getX() > car[0].getWidth()-320) {
            offsetX = car[0].getWidth()-(2*320);
        }
        if (car[0].getY() > 240) {
            offsetY = car[0].getY() - 240;
        }
        if (car[0].getY() > car[0].getHeight()-240) {
            offsetY = car[0].getHeight()-(2*240);
        }

        // sets position and draws the background in relation to the player
        sBackground.setPosition(-offsetX, -offsetY);
        app.draw(sBackground);

        // gets the position of the background to place the checkpoints in the correct spot
        sf::Vector2<float> pos = sBackground.getPosition();
        int checkpointCounter = 0;
        for(int i=0; i<cNum; i++) {
            if(!deleteSprite[i]) {
                checkpoints[i].setPosition(pos.x+cPoints[i][0], pos.y+cPoints[i][1]);
                checkpoints[i].setRotation(cPoints[i][2]);
                app.draw(checkpoints[i]);
            }
            if(deleteSprite[i]) {
                checkpointCounter++;
            }
        }

        // update checkpoint counter
        std::string cpText = "Checkpoints " + std::to_string(checkpointCounter);
        text.setString(cpText);

        // update timer
        if(checkpointCounter != 5) {
            std::string timeText = "Time: " + std::to_string(timer);
            tText.setString(timeText);
        }
        else {
            // remove this info from screen
            text.setString("");
            tText.setString("");
            gameOver = true;
            sf::Text end;
            std::string endMessage = "Finishing Time: " + std::to_string(timer) + "\nPress R to Play Again.";
            end.setString(endMessage);
            end.setFont(font);
            end.setFillColor(Color::White);
            end.setOutlineColor(Color::Black);
            end.setStyle(sf::Text::Bold);
            end.setCharacterSize(50);
            end.setPosition(120,170);
            app.draw(end);

            // resets the game after completion
            if (Keyboard::isKeyPressed(Keyboard::R)) {
                // this method probably is not approved of so this will likely change
                sound1.stop();
                app.close();
                main();
            }
        }
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
        app.draw(text);
        app.draw(tText);
        app.display();
    }
    return 0;
}