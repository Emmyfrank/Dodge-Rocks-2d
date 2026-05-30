# Simple 2D Game (Java)

A small arcade-style dodge game built with Java Swing — no external libraries required.

## How to play

- **Move:** `←` `→` or `A` `D`
- **Restart:** `R` (after game over)
- Dodge the falling rocks. Your score increases the longer you survive.

## Run

```bash
./run.sh
```

Or manually:

```bash
export JAVA_HOME=$(/usr/libexec/java_home)
javac -d out src/com/game/*.java
java -cp out com.game.Main
```

## Project layout

```
src/com/game/
  Main.java          — entry point
  GameWindow.java    — window setup
  GamePanel.java     — game loop, input, drawing
  Player.java        — player ship
  FallingRock.java   — obstacles
```

## Open in NetBeans

NetBeans needs a **project folder**, not a single `.java` or `.class` file.

### Option A — Maven (recommended)

1. **File → Open Project**
2. Select the **`2d game`** folder (the one that contains `pom.xml`)
3. Click **Open Project**
4. Wait for NetBeans to load Maven (first time may take a minute)
5. Right-click the project → **Run** (or press F6)

Main class is already set: `com.game.Main`

### Option B — NetBeans Java (Ant)

Same folder also has `nbproject/` and `build.xml`. Use **File → Open Project** on the same folder.

If NetBeans says **“no JDK”** or **“platform not found”**:

1. **Tools → Java Platforms** → add your JDK (you have JDK 26 at `/Library/Java/JavaVirtualMachines/jdk-26.jdk`)
2. Right-click project → **Properties → Libraries** → set **Java Platform** to JDK 17 or newer

### Common mistakes

| Problem | Fix |
|--------|-----|
| Opens `out/` or a `.class` file | Open the **project root** folder with `pom.xml` |
| “Not a project” | Ensure `pom.xml` or `nbproject/project.xml` exists |
| Run button disabled | Set **Main Class** to `com.game.Main` in project properties |
| Folder name with a space | OK — open `2d game` as-is |

## Requirements

- Java 17+ (tested with JDK 26)
