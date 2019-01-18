package P5;
/*
Features:
	  - We have from 1 to 3 players
	  - We have many questions grouped in 3 categories . Each player chooses a category before being asked. 
	  - Each player may be asked more than one question from different categories. 
	  - User can play many rounds of the game. 
	 
	 Focus: 
	 	- 2-D arrays 
	 
	 Aim:
	 	- Build more complex programs that involves the use of 2-D arrays

	 REQ (for bonus marks):
	 You should build on your code from part P4 and satisfy the following requirements:
	   1.  Create three categories of questions in a 2D array (rows=categories, col=questions). 
	        in each player's turn, ask the player about the required category and then ask a question from that category.
	        make all necessary changes to your program (e.g. shuffle method should now work for 2D array)
	   2.  If we are out of questions in a category (i.e., all questions in this category were offered and answered correctly), inform the player to choose another category.  
*/

public class Main {				
	static Game game;			

	static String[][] questions = {{"What is the capital of Canada?", "What is the university in Kelowna?", "What is the capital of British Columbia?","How many provinces in Canada?"}, // category 1
									{"What two colours make green?", "What of numeric value of pi?","What two elements make up water?","Organic chemistry is mainly based on what element?"},  //category 2
									{"What is 2 + 3 ?", "Who indroduced the law of Gravity?", "Who is the prime minister of Canada?","Who is recent NDP leader?"}};   //category 3
	static String[][] answers =   {{"Ottawa",   "UBC",   "Victoria","Ten"},   {"Blue and yellow",   "3.14",   "Hydrogen and Oxygen","Carbon"},
									{"5", "Newton",   "Justin Trudeau","Jagmeet Singh"}};
	
	public static void main(String[] args) {
		String ans;
		do{								
			//Reset the game
			game = new Game();			
			
			//Get number of players (from 1 to 3)
			int numPlayers = game.askForInt("How many players", 1, 3);

			//Add up to 3 players to the game
			for (int i = 0; i < numPlayers; i++) {
				String name = game.askForText("What is player " + (i+1) + " name?");
				game.addPlayer(name);				
			}
			shuffle(questions,answers);
			
			int maxquestions = questions.length*questions[0].length;
			int numQuestions = game.askForInt("How many questions per player", 1, maxquestions);
			
			
			int count = 0;
			int category = 0;
			
			while(count < questions[category].length) {
			 for(int i = 0; i < numQuestions; i++) {	
			   for(int j = 0; j < numPlayers ; j++) {
				   game.setCurrentPlayer(j);
				   category = game.askForInt("Choose a category: 1-First 2-Second 3-Third",1,3)-1;
				  
				  String givenAnswer = game.askForText(questions[category][count]);	
				    if(answers[category][count].equalsIgnoreCase(givenAnswer)) {
				    game.correct();
				
					
				   count++;
				}    
				else 
					game.incorrect();			
			   }
			 }
			}
			category = game.askForInt("Choose another category: 1-First 2-Second 3-Third",1,3)-1;
			   	
			
			//Do you want to play again? make sure you get valid input
			ans = game.askForText("Play again? (Y/N)"); 
			while(ans != null && !ans.toUpperCase().equals("Y") && !ans.toUpperCase().equals("N"))
				ans = game.askForText("Invalid input. Play again? (Y/N)");
		}while(ans.toUpperCase().equals("Y"));	//play again if the user answers "Y" or "y"
		
		System.exit(1); 	//This statement terminates the program
	}
	
	//Question are swapping according to the categories
	public static void shuffle(String[][] question, String[][] answer){
		for(int i = 0; i < question.length; i++) {
			for(int j = 0; j < question[i].length; j++) {
			int random = (int)(Math.random()*question[i].length);
			String temp = question[i][j];
			String tempans	= answer[i][j];
			question[i][j] = question[i][random];
			answer[i][j] = answer[i][random];
			question[i][random]  = temp;
			answer[i][random] = tempans;
		}
	}
}
}