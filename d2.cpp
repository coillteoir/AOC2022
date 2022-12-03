/*
 * ADVENT OF CODE 2022 
 *
 * Day 2 : C++
 *
 * David Lynch
 *
 * RULES : 
 * OPPONENT MOVES : A (Rock) B (Paper) C (Scissors)
 * PLAYER MOVES   : X (Rock) Y (Paper) Z (Scissors)
 *
 * MOVE POINTS : ROCK (1) SCISSORS (2) PAPER (3)
 * GAME POINTS : LOSS (1) DRAW (2) WIN (3)
 *
 *
 */

#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std;

class gameEvaluator
{
	public:
	vector <string> game;


		/*constructor copies data into the game evaluator*/

		gameEvaluator(string filePath)
		{
			string line;
			ifstream dataFile(filePath);

			if(dataFile.is_open())
				while(getline(dataFile,line))
					game.push_back({line[0],line[2],line[3]});

			dataFile.close();
		}

		int eval_round(const string round)
		{
			char E = -1; //Elf Move
			char P = -1; //Player Move
			char scores[3][3] = {
								 {3,6,0},
								 {0,3,6},
								 {6,0,3}
								};
			
			E = round[0] - 65;
			P = round[1] - 88;
			return (P + 1) + scores[E][P];

		}
		int forceRound(const string round)
		{
			char E = -1; //Elf Move
			char P = -1; //Player Move
			char scores[3][3] = {
								 {3,6,0},
								 {0,3,6},
								 {6,0,3}
								};
			
			E = round[0] - 65;
			P = round[1] - 88;

			switch(P)
			{
				case 1:
					P = E;
					break;
				case 0:
					P = (E + 2) % 3;
					break;
				case 2:
					P = (E + 1) % 3;
					break;
			}

			return (P + 1) + scores[E][P];
		}
		
		int evalGame()
		{
			int total = 0;
			for(auto i = game.begin(); i < game.end(); i++)
			{
				total += eval_round(*i);
			}
			return total;
		}

		int forceGame()
		{
			int total = 0;
			for(auto i = game.begin(); i < game.end(); i++)
			{
				total += forceRound(*i);
			}
			return total;
		}

		void printGame()
		{
			for(auto i = game.begin(); i < game.end(); i++)
			{
				cout << *i << endl;
			}
		}
};


int main(int argc, char **argv)
{
	if(argc != 2)
	{
		return 1;
	}

	gameEvaluator e(argv[1]);
	cout << e.evalGame() << endl << endl;
	cout << "PART 1 SOLVED" << endl << endl;

	cout << e.forceGame() << endl << endl;
	cout << "PART 2 SOLVED" << endl;

}
