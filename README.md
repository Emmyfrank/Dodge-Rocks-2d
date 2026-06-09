# Masters Of Science And Information Technology (MSCIT)
# 🎮 Simple 2D Game (Java)

## 📖 Project Overview

This project is a simple 2D arcade-style game developed using **Java Swing** without any external libraries. The player controls a character (ship) and must avoid falling rocks for as long as possible. The score increases based on survival time, making the game challenging and engaging.

The project was developed as a **Java Group Assignment** to demonstrate object-oriented programming concepts, graphical user interface (GUI) development, event handling, game loops, and collision detection in Java.

---

# 👨‍🏫 Lecturer

**Dr. Josbert N. N.**

---

#👥 Group Members

| No. | Student Name | Registration Number |
|-----|-------------|---------------------|
| 1 | INGABIRE Daria | 2409000610 |
| 2 | Eliezer NSENGI | 26017363 |
| 3 | Irakoze Ritha Theonestine | 26016751 |
| 4 | Ngendahimana Emmanuel | 26015965 |
| 5 | Hatangimbabazi Hilaire | 25093937 |
| 6 | Muhinda Johnson | 26016265 |
| 7 | NKURUNZIZA C. Frank | 26017179 |

---

# 🎯 Objectives

The main objectives of this project are:

- Apply Java Object-Oriented Programming (OOP) principles.
- Build a graphical application using Java Swing.
- Implement keyboard event handling.
- Develop a simple game loop.
- Handle collision detection between game objects.
- Practice teamwork and software development collaboration.

---

# 🕹️ How to Play

### Controls

| Key | Action |
|------|---------|
| ← | Move Left |
| → | Move Right |
| A | Move Left |
| D | Move Right |
| R | Restart Game (after Game Over) |

### Rules

1. Control the player using the keyboard.
2. Avoid the falling rocks.
3. Every second you survive increases your score.
4. The game ends when a rock collides with the player.
5. Press **R** to restart after a game over.

---

# 🚀 Running the Project

## Option 1: Using the Provided Script

```bash
./run.sh
```

## Option 2: Compile and Run Manually

### Step 1: Set Java Home

```bash
export JAVA_HOME=$(/usr/libexec/java_home)
```

### Step 2: Compile the Project

```bash
javac -d out src/com/game/*.java
```

### Step 3: Run the Application

```bash
java -cp out com.game.Main
```

---

# 📂 Project Structure

```text
src/
└── com/
    └── game/
        ├── Main.java
        ├── GameWindow.java
        ├── GamePanel.java
        ├── Player.java
        └── FallingRock.java
```

### File Descriptions

| File | Purpose |
|--------|----------|
| Main.java | Application entry point |
| GameWindow.java | Creates and configures the game window |
| GamePanel.java | Handles game loop, rendering, and keyboard input |
| Player.java | Represents the player character |
| FallingRock.java | Represents obstacles that fall from the top |

---

# 🖥️ Opening the Project in NetBeans

NetBeans requires a project folder rather than individual Java files.

## Option A: Maven Project (Recommended)

1. Open NetBeans.
2. Click **File → Open Project**.
3. Select the project root folder containing `pom.xml`.
4. Click **Open Project**.
5. Wait for Maven dependencies to load.
6. Right-click the project.
7. Select **Run** or press **F6**.

### Main Class

```text
com.game.Main
```

---

## Option B: Ant Project

If the project contains:

```text
nbproject/
build.xml
```

You can:

1. Open NetBeans.
2. Select **File → Open Project**.
3. Choose the project folder.
4. Click **Open**.
5. Run the project.

---

# ⚠️ Common Issues and Solutions

| Problem | Solution |
|----------|----------|
| Project does not open | Open the project root folder, not individual files |
| "Not a Project" error | Ensure `pom.xml` or `nbproject/project.xml` exists |
| Run button disabled | Set Main Class to `com.game.Main` |
| Java Platform not found | Configure a valid JDK in NetBeans |
| Compilation errors | Verify Java 17 or newer is installed |

---

# ☕ Java Requirements

- Java JDK 17 or newer
- Tested successfully with JDK 26

To verify Java installation:

```bash
java -version
```

Expected output:

```bash
java version "17" or higher
```

---

# 🏆 Features Implemented

- Player movement using keyboard controls
- Falling obstacle generation
- Collision detection
- Score tracking
- Game over screen
- Restart functionality
- Java Swing graphical interface

---

# 🤝 Acknowledgement

This project was developed as part of a Java programming assignment under the guidance of **Dr. Josbert N. N.** The project demonstrates teamwork, problem-solving skills, and practical application of Java programming concepts.
