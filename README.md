# Java-Binary-Search-Tree

A Java Swing desktop app that visually demonstrates tree data structures — Binary Search Trees (BST) and AVL trees — including insertion, deletion, traversal, and a graphical display of the tree.

**A ready-to-use build is available in [`/dist`](./dist) — `TreeTest.jar` (requires a Java Runtime) and `TreeTest BETA.exe` (Windows shortcut/launcher for the jar).**

## Description

TreeTest is an educational, GUI-based tool for visualizing how tree data structures work. The main window (`Trees2`) lets you create a BST or AVL tree, insert and delete numeric values, and see the tree redrawn graphically after each operation. The project also includes earlier/alternate versions of the same concept, including a simpler console-based BST traversal demo (`Node.java`) and standalone BST form variants (`Trees.java`, `BS.java`).

Per the project's own distributed README (Build Version 1.0 Beta, 2016): this is freeware, made for educational purposes, and the author advises running it from a drive root (e.g. `C:\`, `D:\`) rather than a restricted folder like `Program Files`.

## Features

- Create a new **Binary Search Tree (BST)**
- Insert a value into the BST (validated to numeric input only)
- Delete a value from the BST
- Graphically display the current tree structure
- Create, insert into, and delete from an **AVL tree** (self-balancing BST)
- About / credits dialog
- A separate console-based demo (`XmasTree/Node.java`) that builds a BST from user input and prints pre-order, in-order, and post-order traversals to the console, along with an ASCII-art tree display

## Tech Stack

- **Java** (Swing/AWT for the GUI)
- Built with **NetBeans 8.1** (Ant-based build, see `build.xml` / `nbproject/`)

## Prerequisites

- **Java Runtime Environment (JRE)** — to run `TreeTest.jar` or the built classes
- **Java Development Kit (JDK)** + NetBeans (or another Java IDE/Ant) — only needed if building from source

## Installation

Clone the repository:

```bash
git clone https://github.com/paoradox/Java-Binary-Search-Tree.git
cd Java-Binary-Search-Tree
```

To build from source, open the project in NetBeans (or run Ant directly using `build.xml`).

## Usage

### Option 1: Run the packaged build (recommended)

From [`/dist`](./dist):

```bash
java -jar "TreeTest.jar"
```

Or, on Windows, run `TreeTest BETA.exe` directly.

> Per the author's note: run the app from a drive root (e.g. `C:\`, `D:\`) rather than a subfolder that requires special permissions (e.g. `Program Files`).

### Option 2: Run the console BST demo from source

`XmasTree/Node.java` includes a standalone `main()` that builds a BST from console/dialog input and prints its structure and traversals:

```bash
cd src/XmasTree
javac Node.java
java TreeTraversal
```

You'll be prompted for the number of elements and each value; the tree structure and pre-order/in-order/post-order traversals are then printed.

## Project Structure

```
Java-Binary-Search-Tree/
├── dist/
│   ├── TreeTest.jar             # Packaged runnable jar
│   ├── TreeTest BETA.exe         # Windows launcher for the jar
│   └── README.txt                # Original author's distribution notes
├── src/
│   ├── XmasTree/                 # Original package
│   │   ├── Trees.java            # BST + AVL tree GUI (JFrame)
│   │   ├── Trees2.java           # Refined BST + AVL tree GUI
│   │   ├── BS.java               # Standalone BST form variant
│   │   └── Node.java             # Console-based BST demo with traversals
│   └── HappyTreeFriends/
│       └── Trees2.java           # Alternate/updated version of the main GUI
├── build/                        # Compiled classes (Ant build output)
├── nbproject/                    # NetBeans project configuration
├── build.xml                     # Ant build script
└── manifest.mf                   # Jar manifest
```

## License

Apache License 2.0
