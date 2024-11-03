
---

# SoccerX

## Overview
The `SoccerX` project is a simple soccer game with a graphical user interface, featuring core classes and relationships that define the game's elements and interactions. Each class has specific roles and relationships that facilitate gameplay, game levels, and visual elements.

## Class Relationships
- **Polygon**: `SoccerBall`, `Goal`, `Defender`, and `SoccerX` have an “is-a” relationship with `Polygon`, relying on its methods and initialization to draw their shapes.
- **MoveableCharacter Interface**: `SoccerBall` implements this interface, allowing it to have methods for movement, painting, and collision handling.
- **Defender and Level Inner Class**: `Defender` has a relationship with its inner class, `Level`, where the quantity and speed of `Defender` instances are based on the user's chosen level.
- **SoccerX (Main Class)**: The main class responsible for running the game. It has a composition relationship with `HomeScreen`, `SoccerBall`, `Goal`, and `Defender`, signifying that `SoccerX` contains these essential game components.
- **HomeScreen and GUI**: `HomeScreen` interacts with `SoccerX` to handle GUI functionalities, while `LevelGUI` interacts with the `Level` inner class to determine the game's chosen level.
- **Game Class Inheritance**: `SoccerX` inherits from the abstract `Game` class, implementing method signatures to utilize and extend `Game` functionalities.
- **Point Class Association**: Classes such as `SoccerBall`, `SoccerX`, `Defender`, and `Goal` have an association relationship with `Point`, which is used to create shapes or define positions.
- **Polygon and Point Composition**: `Polygon` uses an array of `Point` objects to define polygon shapes, demonstrating a "has-a" relationship.

## Project Reflections

### What Went Well
The project’s design phase was thorough, ensuring logical interconnections among components, making it easier to trace errors and maintain the code.

### Challenges
Collision detection between the soccer ball and goal didn’t work as expected. The collision is only detected on the edge of the goal, likely due to an issue with the `contains` method in `Polygon`. This method uses the center point of the `Polygon`, not considering other points of the shape.

### Future Improvements
If given more time, potential improvements include:
- **Enhanced Levels**: For increased difficulty, medium and hard levels could feature fewer but wider defenders, making gameplay more challenging without crowding the screen.
- **Game Instructions and Decoration**: Adding an instruction screen to the home screen and enhancing game aesthetics would improve the user experience.

## Lessons Learned
This project underscored the importance of proper documentation, especially when collaborating. Clear and concise comments are crucial to communicate the purpose, functionality, and reasoning behind code, making teamwork smoother and future modifications easier.

--- 

