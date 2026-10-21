# CS-Tower-Defense

CS-Tower-Defense is a video game developed in Java using Java Swing. It is a _tower defense_ style game. The player places computers on a grid to defend themselves against enemies. Indeed, you start with **100 HP**. When an enemy reaches you, you lose some health. The game ends when your health drops to 0 HP. The computers can be bought using lines of code gained by killing enemies. You can't place them in the first column, on the enemies' goal, on top of another tower, or anywhere that would block every path to the enemies' goal.

This game was made for the 2026 TU/e 2IP90 Challenge-Based Learning project.

## Controls:

- Place a tower: Choose a tower at the bottom of the window, then click on a cell of the grid.
- Upgrade a tower: Right-click on a tower, then click upgrade. Each upgrade gives more attack damage, more range and less cooldown to the tower upgraded.

## Requirements:
- JDK 25 (Swing is included in the JDK)
- Apache Maven 3.9+
- Internet connection on the first build (Maven downloads plugins)

## Building:
From the folder that contains `pom.xml`:

    cd CS-Tower-Defense
    mvn clean compile

## Running:
    mvn exec:java

### From an IDE (NetBeans)
Open the `CS-Tower-Defense` folder (the one with `pom.xml`) as a Maven project,
then run `com.mycompany.cs_tower_defense.Main`.


## Documents:

Backlog: `CS-Tower-Defense/documents/Backlog_CBL.pdf`

Advanced topics: `CS-Tower-Defense/documents/Advanced_Topics.pdf`

## Credits:

- Grass block image by Mojang
- Computer image by Tour De Pizza
- Nerd face image by arthobbit
- Java logo by Joe Palrang and modified by Claude (Anthropic)
- Shower and Sun image made by Claude (Anthropic)
- Plants vs Zombies main theme by PopCap Games
- Determination (Game Over theme) by Toby Fox
- Base damage sound made by Paradoxum Games

