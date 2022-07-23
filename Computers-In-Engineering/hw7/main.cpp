// Programmer: Brandon Cano
// Date: 11/24/21
// Description: This code was originally written by Fam Trinli, and is being modified by me
//   to add on to the asteroids game that he had

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//// Part 4 Extra Credit Features:
////    refactoring  the  code  so  it  is  properly  formatted  and  follows  best
////    programming  practices  such  as  using  private  member  variables,  setter  and  getters,
////    proper  indentation with  curly  braces,  modifying  constructors  to  initialize  all  member  variables,
////    properly  following  the principle of least privilege using public, protected, and private, etc.
////
////    sounds for explosions amd bullets, I made them more on the quieter side so that it is not
////    too loud when testing/playing
////
////    death counter, just a little counter to help you keep track of how many times you had died
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>
#include <time.h>
#include <list>
#include <cmath>

using namespace sf;

// width and height of the window
const int W = 1200;
const int H = 800;

// degree to radian conversion factor
const float DEGTORAD = 0.017453f;

// animation class for explosions
class Animation {
public:
    // constructors
    Animation(){}
    explicit Animation (Texture &t, int x, int y, int w, int h, int count, float Speed) {
        // defines the values for the animation
	    Frame = 0;
        speed = Speed;

		for (int i=0; i<count; i++) {
            frames.push_back(IntRect(x + i * w, y, w, h));
        }

		sprite.setTexture(t);
		sprite.setOrigin(w/2,h/2);
        sprite.setTextureRect(frames[0]);
	}

    // getter and setter for sprite
    Sprite getSprite() const {
        return sprite;
    }
    void setSprite(float const& x, float const& y, float const& angle) {
        sprite.setPosition(x, y);
        sprite.setRotation(angle+90);
    }

    // runs the animation
	void update() {
    	Frame += speed;
		int n = frames.size();
		if (Frame >= n) { Frame -= n; }
		if (n>0) {
            sprite.setTextureRect(frames[int(Frame)]);
        }
	}

    // checks for the end of the animation
	bool isEnd() {
	    return Frame+speed >= frames.size();
	}

private:
    Sprite sprite;
    float speed;
    float Frame;
    std::vector<IntRect> frames;
};


class Entity {
public:
    // constructor
    explicit Entity(): life{1} {}

    // getters for x and y
    float getX() const {
        return x;
    }
    float getY() const {
        return y;
    }

    // setters for dx and dy
    void setDx(float const& dX) {
        dx = dX;
    }
    void setDy(float const& dY) {
        dy = dY;
    }

    // getter for radius
    float getRadius() const {
        return radius;
    }

    // getter and setter for angle
    float getAngle() const {
        return angle;
    }
    void setAngle(float const& a) {
        angle = a;
    }

    // getter and setter for life
    bool getLife() const {
        return life;
    }
    void setLife(bool const& l) {
        life = l;
    }

    // getter and setter for name
    std::string getName() const {
        return name;
    }
    void setName(std::string const& newName) {
        name = newName;
    }

    // getter and setter for anim
    Animation getAnimation() const {
        return anim;
    }
    void setAnimation(Animation const& animation) {
        anim = animation;
    }

    // this is only used so that the correct update can be called for the animation class
    // was the only way I was able to remove the member variable being used in main
    void updateAnimation() {
        anim.update();
    }

    // this can update the main values for the animation specifically all at once
    void settings(Animation &animation, int X, int Y, float Angle = 0, int newRadius = 1) {
        anim = animation;
        x = X;
        y = Y;
        angle = Angle;
        radius = newRadius;
    }

    // this will be overridden in the derived classes
    virtual void update(){};

    // draws the entity
    void draw(RenderWindow &app) {
        anim.setSprite(x, y, angle);
        app.draw(anim.getSprite());

        CircleShape circle(radius);
        circle.setFillColor(Color(255, 0, 0, 170));
        circle.setPosition(x, y);
        circle.setOrigin(radius, radius);
    }

    // destructor can be overridden in derived classes if defined there
    virtual ~Entity(){};

protected:
    float x, y;
    float dx, dy;
    float angle;

private:
    float radius;
    bool life;
    std::string name;
    Animation anim;
};


class asteroid: public Entity {
public:
    static int count;
    // constructor
    explicit asteroid() {
        // will randomly set orientation
        dx = rand()%8 - 4;
        dy = rand()%8 - 4;
        setName("asteroid");
        count++;
    }

    // destructor
    ~asteroid() {
        count--;
    }

    // updates asteroid
    void update() {
       x += dx;
       y += dy;

       if (x > W) { x = 0; }
       if (x < 0) { x = W; }
       if (y > H) { y = 0; }
       if (y < 0) { y = H; }
    }
};

int asteroid::count = 0;

class bullet: public Entity {
public:
    // constructor
    bullet() {
        setName("bullet");
    }

    // updates bullet
    void update() {
        dx = cos(angle*DEGTORAD)*6;
        dy = sin(angle*DEGTORAD)*6;
        x += dx;
        y += dy;

        if (x > W || x < 0 || y > H || y < 0) {
            setLife(0);
        }
    }
};


class player: public Entity {
public:
    // constructor
    player() {
         setName("player");
    }

    // getter and setter for thrust
    bool getThrust() const {
        return thrust;
    }
    void setThrust(bool const& t) {
        thrust = t;
    }

    // update values for player depending on movement
    void update() {
        if (thrust) {
            dx += cos(angle*DEGTORAD)*0.2;
            dy += sin(angle*DEGTORAD)*0.2;
        }
        else {
            dx *= 0.99;
            dy *= 0.99;
        }

        int maxSpeed = 15;
        float speed = sqrt(dx*dx + dy*dy);
        if (speed > maxSpeed) {
            dx *= maxSpeed/speed;
            dy *= maxSpeed/speed;
        }

        x += dx;
        y += dy;
        if (x>W) { x=0; }
        if (x<0) { x=W; }
        if (y>H) { y=0; }
        if (y<0) { y=H; }
    }

private:
    bool thrust;
};


class UFO: public Entity {
public:
    // constructor
    explicit UFO() {
        setName("UFO");
    }

    //updates the UFO
    void update() {
        x += 5;
        if (x > W+64) {
            setLife(0);
        }
    }
};

// checks for collisions between 2 entities
bool isCollide(Entity *a, Entity *b) {
  return (b->getX() - a->getX())*(b->getX() - a->getX())+
         (b->getY() - a->getY())*(b->getY() - a->getY())<
         (a->getRadius() + b->getRadius())*(a->getRadius() + b->getRadius());
}


int main() {
    // sets a random seed
    srand(time(0));

    // defines the window
    RenderWindow app(VideoMode(W, H), "Asteroids!");
    app.setFramerateLimit(60);

    // defines and loads all the textures that will be used, will stop if files do not open
    Texture t1, t2, t3, t4, t5, t6, t7, t8;
    if (!t1.loadFromFile("images/spaceship.png")) { return EXIT_FAILURE; }
    if (!t2.loadFromFile("images/background.jpg")) { return EXIT_FAILURE; }
    if (!t3.loadFromFile("images/explosions/type_C.png")) { return EXIT_FAILURE; }
    if (!t4.loadFromFile("images/rock.png")) { return EXIT_FAILURE; }
    if (!t5.loadFromFile("images/fire_blue.png")) { return EXIT_FAILURE; }
    if (!t6.loadFromFile("images/rock_small.png")) { return EXIT_FAILURE; }
    if (!t7.loadFromFile("images/explosions/type_B.png")) { return EXIT_FAILURE; }

    // Flaticon License: Free for personal and commercial purpose with attribution
    // Obtained from: https://www.flaticon.com/free-icon/ufo_190276?term=ufo&page=1&position=4&page=1&position=4&related_id=190276&origin=search
    if (!t8.loadFromFile("images/ufo.png")) { return EXIT_FAILURE; }

    // smooths the spaceship and background image
    t1.setSmooth(true);
    t2.setSmooth(true);

    // defines the background sprite
    Sprite background(t2);

    // define all the information regarding each animation in the game
    Animation sExplosion(t3, 0,0,256,256, 48, 0.5);
    Animation sRock(t4, 0,0,64,64, 16, 0.2);
    Animation sRock_small(t6, 0,0,64,64, 16, 0.2);
    Animation sBullet(t5, 0,0,32,64, 16, 0.8);
    Animation sPlayer(t1, 40,0,40,40, 1, 0);
    Animation sPlayer_go(t1, 40,40,40,40, 1, 0);
    Animation sExplosion_ship(t7, 0,0,192,192, 64, 0.5);
    Animation sUFO(t8, 0, 0, 128, 128, 1, 1);

    // list of pointers of type Entity
    std::list<Entity*> entities;

    // dynamic memory allocation for the player
    player *p = new player();
    p->settings(sPlayer, 200, 200, 0, 20);
    entities.push_back(p);

    // load and sets the UFO entry sound
    SoundBuffer buffer;
    Sound sound;

    // UFO_Sound was created by myself using MuseScore3
    if (!buffer.loadFromFile("sounds/UFO_Sound.ogg")) {
        return EXIT_FAILURE;
    }
    sound.setBuffer(buffer);
    sound.setVolume(20);

    // loads and sets the explosion sound
    SoundBuffer eBuffer;
    Sound eSound;

    // explosion_sound by derplayer is under the Creative Commons 0 License
    // Obtained from: https://freesound.org/people/derplayer/sounds/587196/
    if (!eBuffer.loadFromFile("sounds/explosion_sound.ogg")) {
        return EXIT_FAILURE;
    }
    eSound.setBuffer(eBuffer);
    eSound.setVolume(3);

    // loads and sets the bullet sound
    SoundBuffer bBuffer;
    Sound bSound;

    // bullet_sound by Daleonfire, is under the Creative Commons 0 License
    // Obtained from: https://freesound.org/people/Daleonfire/sounds/376694/
    if (!bBuffer.loadFromFile("sounds/bullet_sound.ogg")) {
        return EXIT_FAILURE;
    }
    bSound.setBuffer(bBuffer);
    bSound.setVolume(1);

    // Night Ghost by Afika Studio, https://www.1001freefonts.com/, License: Free for personal use.
    Font font;
    if (!font.loadFromFile("fonts/NightGhost.otf")) {
        return EXIT_FAILURE;
    }

    // sets up the death counter text
    sf::Text text;
    text.setFont(font);
    text.setString("Number of Deaths: 0");
    text.setFillColor(Color::White);
    text.setOutlineColor(Color::Black);
    text.setStyle(sf::Text::Bold);
    text.setCharacterSize(30);
    text.setPosition(5,0);
    int deathCounter = 0;

    // will be used to prevent UFO from spawning when it should not
    bool ufoSpawn = false;
    Clock clock;
    float timer = 0;

    // main loop
    while (app.isOpen()) {
        // helps with ufo spawning
        timer += clock.getElapsedTime().asSeconds();

        Event event;
        while (app.pollEvent(event)) {
            // checks for when app is closed
            if (event.type == Event::Closed) { app.close(); }

            // checks for space bar being pressed to spawn in the bullets at the correct spot
            if (event.type == Event::KeyPressed) {
                if (event.key.code == Keyboard::Space) {
                    // dynamic memory allocation for each bullet
                    bullet *b = new bullet();
                    b->settings(sBullet, p->getX(), p->getY(), p->getAngle(), 10);
                    entities.push_back(b);
                    bSound.play();
                }
            }
        }

        // keyboard commands for movement
        if (Keyboard::isKeyPressed(Keyboard::Right)) { p->setAngle(p->getAngle() + 3); }
        if (Keyboard::isKeyPressed(Keyboard::Left)) { p->setAngle(p->getAngle() - 3); }
        if (Keyboard::isKeyPressed(Keyboard::Up)) { p->setThrust(true); }
        else { p->setThrust(false); }

        // iterates through each entity in double loops for testing certain conditions
        for (auto a:entities) {
            for (auto b: entities) {
                // checks for an asteroid and bullet collision
                if (a->getName() == "asteroid" && b->getName() == "bullet") {
                    if (isCollide(a, b)) {
                        // will setLife to false so that they are removed
                        a->setLife(false);
                        b->setLife(false);

                        // starts the correct explosion animation
                        Entity *ex = new Entity();
                        ex->settings(sExplosion, a->getX(), a->getY());
                        ex->setName("explosion");
                        entities.push_back(ex);
                        eSound.play();

                        // after explosion the new smaller asteroids will take its place
                        for (int i = 0; i < 2; i++) {
                            if (a->getRadius() == 15) { continue; }
                            Entity *e = new asteroid();
                            e->settings(sRock_small, a->getX(), a->getY(), rand() % 360, 15);
                            entities.push_back(e);
                        }
                    }
                }

                // checks for player and asteroid collision or a player and UFO collision
                if ((a->getName() == "player" && b->getName() == "asteroid") || (a->getName() == "player" && b->getName() == "UFO")) {
                    if (isCollide(a, b)) {
                        if (b->getName() == "UFO") { sound.stop(); }
                        // will remove the asteroid entirely
                        b->setLife(false);

                        // creates new explosion
                        Entity *e = new Entity();
                        e->settings(sExplosion_ship, a->getX(), a->getY());
                        e->setName("explosion");
                        entities.push_back(e);
                        eSound.play();

                        // respawns the player in the middle of the screen
                        p->settings(sPlayer, W / 2, H / 2, 0, 20);
                        p->setDx(0);
                        p->setDy(0);

                        deathCounter++;
                        text.setString("Number of Deaths: " + std::to_string(deathCounter));
                    }
                }

                // checks for a collision between a bullet and the UFO
                if (a->getName() == "UFO" && b->getName() == "bullet") {
                    if (isCollide(a, b)) {
                        sound.stop();
                        // will setLife to false so that they are removed
                        a->setLife(false);
                        b->setLife(false);

                        // starts the correct explosion animation
                        Entity *e = new Entity();
                        e->settings(sExplosion, a->getX(), a->getY());
                        e->setName("explosion");
                        entities.push_back(e);
                        eSound.play();
                    }
                }
            }
        }

        // if the player is moving then the thrust animation will show
        p->setAnimation(sPlayer);
        if (p->getThrust()) {
            p->setAnimation(sPlayer_go);
        }

        // will check for explosion entities and remove them once they are done
        for (auto e:entities) {
            if (e->getName() == "explosion") {
                if (e->getAnimation().isEnd()) {
                    e->setLife(0);
                }
            }
        }

        // determines when to reset the game essentially
        if (asteroid::count == 0) {
            // dynamic memory allocation for each asteroid spawning in, with
            // random aspects being thrown in for starting location, size, and angle
            for (int i=0; i<15; i++) {
                asteroid *a = new asteroid();
                a->settings(sRock, rand()%W, rand()%H, rand()%360, 25);
                entities.push_back(a);
            }
        }

        // will randomly spawn in a UFO
        // Note, these values make sure it does not spawn too soon or too often, but
        // it is not as rare for the sake of time when grading
        if (timer > 3000 && int(timer)%555 == 0 && !ufoSpawn) {
            ufoSpawn = true;
            sound.play();

            UFO *u = new UFO();
            u->settings(sUFO, -64,30, 270, 15);
            entities.push_back(u);
        }

        // updates every entity
        for (auto i = entities.begin(); i != entities.end();) {
            Entity *e = *i;
            e->update();
            e->updateAnimation();

            // will check the life value and delete it once it is false
            // otherwise move on to the next
            if (!(e->getLife())) {
                // makes it possible for a UFO to spawn again
                if (e->getName() == "UFO") { ufoSpawn = false; }

                // removes entity from vector and frees memory
                i=entities.erase(i);
                delete e;
            }
            else { i++; }
        }

        // draw the screen
        app.draw(background);
        app.draw(text);
        for (auto i:entities) { i->draw(app); }
        app.display();
    }
    return 0;
}