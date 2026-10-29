# AI Lab Test 1 — Java

This repository contains three beginner-friendly Artificial Intelligence lab problems.

## Programs

1. **Water Jug Problem** — Breadth-First Search (BFS), user input, and numbered solution steps.
2. **Missionaries and Cannibals** — BFS finds a safe river-crossing sequence for 3 missionaries and 3 cannibals.
3. **Sudoku Solver** — backtracking solves a predefined 9×9 puzzle. It checks rows, columns, and 3×3 subgrids. `0` represents an empty cell.

## Requirements

- Java JDK 8 or later
- Terminal / Command Prompt, or an IDE such as IntelliJ IDEA or VS Code

Check Java installation:

```bash
java -version
javac -version
```

## Run the programs

Open a terminal in this folder.

### 1. Water Jug

```bash
javac WaterJug.java
java WaterJug
```

Example input:

```text
Enter capacity of Jug A: 4
Enter capacity of Jug B: 3
Enter target amount: 2
```

The program prints numbered states, the action at each step, and the amount of water in each jug. Step 0 is the initial state.

Try another test: capacities `2`, `6`, target `5` (should report no solution).

### 2. Missionaries and Cannibals

```bash
javac MissionariesCannibals.java
java MissionariesCannibals
```

The program prints each crossing, the number of missionaries and cannibals on both banks, and the boat's location. The boat carries one or two people. Missionaries must not be outnumbered by cannibals on either bank when missionaries are present.

### 3. Sudoku Solver

```bash
javac SudokuSolver.java
java SudokuSolver
```

The program prints the predefined puzzle and its solved grid. It verifies that the starting clues do not contain duplicate digits in any row, column, or 3×3 subgrid before solving.

## Algorithms explained

### Breadth-First Search (BFS)

BFS uses a queue. It explores states in increasing number of moves, so the first goal found uses the fewest moves when each move has equal cost. A `visited` set prevents revisiting states.

- **Water Jug state:** `(amountA, amountB)`
- **Missionaries and Cannibals state:** `(missionariesOnLeft, cannibalsOnLeft, boatOnLeft)`

### Backtracking

Sudoku backtracking finds an empty cell, tries digits 1 through 9, and checks whether each digit is safe. If a choice eventually fails, it resets that cell to `0` and tries another digit.

## Test checklist

- [ ] Water Jug: capacities `4` and `3`, target `2` — solution is found.
- [ ] Water Jug: capacities `2` and `6`, target `5` — no solution is found.
- [ ] Water Jug: target `0` — initial state satisfies the target.
- [ ] Missionaries and Cannibals — all six people reach the right bank without an unsafe state.
- [ ] Sudoku — solved grid contains digits 1–9 without duplicates in every row, column, and 3×3 subgrid.

## GitHub upload

1. Create a new GitHub repository named `AI-Lab-Test-1`.
2. Open a terminal inside this project folder.
3. Run:

```bash
git init
git add .
git commit -m "Add AI Lab Test 1 solutions in Java"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/AI-Lab-Test-1.git
git push -u origin main
```

Replace `YOUR_USERNAME` with your GitHub username. If Git says the remote already exists, run `git remote -v` and update it with `git remote set-url origin https://github.com/YOUR_USERNAME/AI-Lab-Test-1.git`.

## Viva preparation

Be ready to explain:
- Why BFS uses a queue and a visited set.
- What a state represents in each search problem.
- Why an unsafe missionaries/cannibals state is rejected.
- What backtracking means and why Sudoku cells are reset to `0`.
- Why Sudoku must check the row, column, and 3×3 subgrid.
