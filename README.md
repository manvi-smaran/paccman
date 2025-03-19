# Pac-Man Game Clone

A Java implementation of the classic Pac-Man arcade game, built using Java Swing for graphics rendering.

<img src="game screenshot.png" alt="Screenshot of the game" width="600"/>
## Overview

This project is a simplified clone of the iconic Pac-Man game, featuring the classic maze navigation, food collection mechanics, and ghost avoidance gameplay that made the original so beloved. The game is implemented entirely in Java using the Swing framework for graphics and user interaction.

## Features

- Complete Pac-Man movement mechanics with directional control
- Four distinct ghost characters with different sprites
- Collision detection between Pac-Man, ghosts, walls, and food
- Score tracking and multiple lives
- Classic maze design
- Level reset when all food is collected
- Game over state and restart capability

## Requirements

- Java Development Kit (JDK) 8 or higher
- Any IDE that supports Java development (Eclipse, IntelliJ IDEA, etc.)

## How to Run

1. Clone this repository:
   ```
   git clone https://github.com/yourusername/pacman-game.git
   ```

2. Open the project in your Java IDE

3. Ensure the resource files (images) are in the correct location

4. Run the `Main.java` file

Alternatively, compile and run from the command line:
```
javac Main.java
java Main
```

## Controls

- **Arrow Up**: Move Pac-Man upward
- **Arrow Down**: Move Pac-Man downward
- **Arrow Left**: Move Pac-Man left
- **Arrow Right**: Move Pac-Man right
- Press any key after game over to restart

## Game Logic

- Pac-Man moves through the maze collecting food dots
- Each food dot is worth 10 points
- Ghosts roam the maze and change direction randomly when hitting walls
- If a ghost catches Pac-Man, a life is lost
- Game ends when all lives are lost
- Level is completed when all food is collected

## Code Structure

- `Main.java`: Contains the game loop and rendering logic
- `Block` class: Base class for all game entities (Pac-Man, ghosts, walls, food)
- Movement systems with velocity and collision detection
- Sprite management for direction changes

## Future Improvements

- [ ] Add power pellets and ghost vulnerability states
- [ ] Implement more sophisticated ghost AI
- [ ] Add sound effects and music
- [ ] Create a high score system
- [ ] Add additional levels with different maze layouts

## Credits

- Original Pac-Man game created by Namco
- Game sprites sourced from [source/attribution if applicable]

## License

This project is licensed under the MIT License - see the LICENSE file for details.
