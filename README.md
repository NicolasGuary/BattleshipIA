# BattleshipIA
## 3 game modes available
### Launch class Battleship to pick-up the game mode you want:
* 1 = Human versus Human
* 2 = CPU versus Human
* 3 = CPU versus CPU

## To compete the 3 AI available use the class TestIA to launch 100 battles
* 1 = Easy versus Medium
* 2 = Easy versus Hard
* 3 = Medium versus Hard

## How to compile and execute BattleshipIA?
###### Please note: ai_proof.csv will be written into the src folder.
### First Method: Use the provided .sh scripts

If rights are not granted to execute the .sh scripts use: `chmod +x Battleship.sh` et `chmod +x TestAI.sh`
Then you can launch the project using `./Battleship.sh` and/or `TestAI.sh` that you will both find at root of the project
After execution, scripts will clean the project from the .class files.

### Second Method: Compile and Launch in Terminal

From the root folder, execute the Unix command line: `javac src/guary/nicolas/*.java src/fr/battleship/*.java src/fr/igpolytech/guary/exceptions/*.java` in order to compile the whole project.
Now move to the src folder with `cd src`
Pick up the program you wanna launch by doing eiter `guary.nicolas.Battleship` or `fr.battleship.testIA`

###Third Method: Import the projet into Eclipse IDE.


## Strategy
* Easy AI will pick random Coordinates
* Medium AI will pick random Coordinates but won't shoot at a Coordinates he already aimed at.
This will allow Medium AI to win the game in 100 turns in the worse case, which makes it way more powerful than the Easy AI.
* Hard AI will shoot particuliar Coordinates in order to find a Ship and then it will aim it until it's destroyed.
HardAI Strategy follows the following method:
	- If there is no hit coordinate, the AI hunts for a new Ship to destroy.
	- When a Ship has been found, the AI add the 4 surrounding coordinates to his Stack of targets (if the coordinates are available and not already destroyed).
	- When the stack is empty, the AI goes back to hunt.


###For more details, refer to the project report.