# 2D Fruit Engine
![COVER IMAGE](https://i.imgur.com/GygvTBU.png)

## About

### Purpose
This application is a basic game engine meant to serve as the foundation for future personal projects. You quest for fruit, find them, and win.

### Context
The basic architecture of this engine is heavily influenced/inspired by [RyiSnow](https://www.youtube.com/@RyiSnow) and his [2D Game in Java](https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1) tutorial series. My time in coding bootcamp was spent largely learning about full-stack development (servers, clients, APIs, Agile Development cycles, et alia) but to learn about the intricacies of Java as a language, I had to study elsewhere. I've read many pages of documentation, but [YouTube](https://www.youtube.com/) proved an invaluable resource for learning, especially when it came to something like Game Design.

I played games whenever I got the chance as a child. As an adult, I've very much enjoyed coding my own. I coded one with my friend [Ian Bird](), whose GitHub is linked, which largely inspired this project. Throughout RyiSnow's tutorial series, he accesses properties in Java directly - that is to say, rather than something like <code> getClassProperty().doSomeFunction()</code>, he will do <code>class.property.action()</code> to access and adjust the property directly. I learned that this is a rather unsafe way to code, and though architecturally this project is very similar, the actual classes and the way they are configured is far more professional and secure. There are several aspects in the architecture itself (movement, debug, credits) that are uniquely my own as well. I have no intention to claim more credit than I deserve for this project.

Similarly, the current art assets are borrowed from old games - [Pokemon Gold](https://www.spriters-resource.com/game_boy_gbc/pokemongoldsilver/), [Pac-Man](https://www.spriters-resource.com/custom_edited/pacmancustoms/), and [Kirby](https://www.spriters-resource.com/game_boy_advance/kirbynim/). They are here purely for debugging purposes and I by no means intend to present them as my own work. The next update to this engine will replace all existing assets with original art and remove this aspect from the conversation entirely.

This project serves only as the architectural foundation for future endeavors. The game my friend and I coded, [GemQuest](https://github.com/birdmandeveloper/GemQuest), is a small sample of the grand designs I have for this moving forward.

## Installation
Download and open via IDE

## Controls
![BASIC NAVIGATION](https://i.imgur.com/dfMC02B.png)
```
W - Move up
A - Move left
S - Move down
D - Move right

Shift - Dash (faster movement)
Enter - Pause game

P - Debug
```
![DEBUG](https://i.imgur.com/0THJiAb.png)

## Future Plans
- Custom graphics (currently borrowed from ancient games for testing purposes)
- Combat system
- Title screen
- Character menu
- And much, **much** more!

## Technical Details

### Notes
1. **FULL CREDIT TO RYISNOW AND THE 1999 GAME FREAK LINEUP**
2. Errors currently only fire when images fail to load. When exceptions are thrown, <code>System.out.println("Error Code")</code> will print the error. You can find the particular error in the following files:
    - 001: GamePanel.java
    - 002: /entity/Entity.java
    - 003: /tile/TileManager.java
    - 004: /tile/TileManager.java
    - 005: /object/OBJ_Apple.java
    - 006: /object/OBJ_Banana.java
    - 007: /object/OBJ_Cherry.java
    - 008: UI.java

### Version Differences
```
Version 0.9.0 - Base state, no changes
```