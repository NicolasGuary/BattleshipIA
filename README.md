# BattleshipIA
## 3 game modes available
### Launch class Main to pick-up the game mode you want:
* 1 = Human versus Human
* 2 = CPU versus Human
* 3 = CPU versus CPU

## To compete the 3 AI available use the class TournamentAI to launch 100 battles
* 1 = Easy versus Medium
* 2 = Easy versus Hard
* 3 = Medium versus Hard

## Strategy
* Easy AI will pick random Coordinates
* Medium AI will pick random Coordinates but won't shoot at a Coordinates he already aimed at.
This will allow Medium AI to win the game in 100 turns in the worse case, which makes it way more powerful than the Easy AI.
* Hard AI will shoot particuliar Coordinates in order to find a Ship and then it will aim it until it's destroyed.
HardAI Strategy follows the following method:
	- If there is no hit coordinate, the AI hunts for a new Ship to destroy.
	- When a Ship has been found, the AI add the 4 surrounding coordinates to his Stack of targets (if the coordinates are available and not already destroyed).
	- When the stack is empty, the AI goes back to hunt.