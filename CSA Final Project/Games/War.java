package Games;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Yul Nam
 */
public class War {

    public static void playGame() {
        Scanner scan = new Scanner(System.in);
        printMenu();
	    int choice = scan.nextInt();
	    while (choice != 1) {
            dispatch(choice);
       	    printMenu();
            choice = scan.nextInt();
	    }
    }
    
    ArrayList<Integer> list = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> playerList = new ArrayList<ArrayList<Integer>>();
    int playerNum;
    int cardPer;
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    
    public War(int playerNum) {
        //initializes card order to 1, 1, 1, 1, 2, 2, 2, 2, etc.
        for(int i = 0; i < 13; i++) {
            list.add(i + 1);
            list.add(i + 1);
            list.add(i + 1);
            list.add(i + 1);
        }
        //initializes the number of players in the game, including the computer
        this.playerNum = playerNum;
        cardPer = 52/this.playerNum;
    }
    
    public void deal() {
        //shuffles the cards in the list
        for (int i = list.size() - 1; i >= 0; i--) {
            Integer index = rand.nextInt(i + 1);
            Integer temp = list.get(index);
            list.set(index, list.get(i));
            list.set(i, temp);
        }
        //disributes cards to every player
        for(int i = 1; i <= playerNum; i++) {
            playerList.add(new ArrayList<Integer>());
            playerList.set(i - 1, new ArrayList(list.subList((i - 1) * cardPer, (i - 1) * cardPer + (cardPer))));
        }
    }
    
    public void war(int player1, int player2) {
        System.out.println("War!\n");
        ArrayList<Integer> prize = new ArrayList<Integer>();
        ArrayList<Integer> compare = new ArrayList<Integer>();
        ArrayList<Integer> warringPlayers = new ArrayList<Integer>();
        
        if(playerList.get(player1).size() <= 1) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player2).add(prize.get(i));
                playerList.get(player2).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player2)) + " wins the war!\n");
            return;
        }
        else if(playerList.get(player2).size() <= 1) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player1).add(prize.get(i));
                playerList.get(player1).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player1)) + " wins the war!\n");
            return;
        }
        
        prize.add(playerList.get(player1).get(0));
        playerList.get(player1).remove(0);
        prize.add(playerList.get(player2).get(0));
        playerList.get(player2).remove(0);
        
        compare.add(playerList.get(player1).get(0)); //causes error
        playerList.get(player1).remove(0);
        compare.add(playerList.get(player2).get(0));
        playerList.get(player2).remove(0);
        
        Integer max = compare.get(0);
        for (int i = 1; i < compare.size(); i++) {
            if (max < compare.get(i)) {
                max = compare.get(i);
            }
            else if (max == compare.get(i)) {
                war(player1, player2);
            }
        }
        
        if(compare.indexOf(max) == 0) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player1).add(prize.get(i));
                playerList.get(player1).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player1)) + " wins the war!\n");
        }
        else if(compare.indexOf(max) == 1) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player2).add(prize.get(i));
                playerList.get(player2).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player2)) + " wins the war!\n");
        }
    }
    
    public void war(int player1, int player2, int player3) {
        System.out.println("Three Way War!\n");
        ArrayList<Integer> prize = new ArrayList<Integer>();
        ArrayList<Integer> compare = new ArrayList<Integer>();
        ArrayList<Integer> warringPlayers = new ArrayList<Integer>();
        
        if(playerList.get(player1).size() <= 1) {
            war(player2, player3);
        }
        else if(playerList.get(player2).size() <= 1) {
            war(player1, player3);
        }
        else if(playerList.get(player3).size() <= 1) {
            war(player1, player2);
        }
        
        prize.add(playerList.get(player1).get(0));
        playerList.get(player1).remove(0);
        prize.add(playerList.get(player2).get(0));
        playerList.get(player2).remove(0);
        prize.add(playerList.get(player3).get(0));
        playerList.get(player3).remove(0);
        
        compare.add(playerList.get(player1).get(0));
        playerList.get(player1).remove(0);
        compare.add(playerList.get(player2).get(0));
        playerList.get(player2).remove(0);
        compare.add(playerList.get(player3).get(0));
        playerList.get(player3).remove(0);
        
        Integer max = compare.get(0);
        for (int i = 1; i < compare.size(); i++) {
            if (max < compare.get(i)) {
                max = compare.get(i);
                for(int j = 0; j < warringPlayers.size(); j++) {
                    warringPlayers.remove(0);
                }
            }
            else if (max == compare.get(i)) {
                warringPlayers.add(i);
                warringPlayers.add(compare.indexOf(max));
            }
        }
        switch(warringPlayers.size()) {
            case 2: 
                war(warringPlayers.get(0), warringPlayers.get(1));
                break;
            case 3: 
                war(warringPlayers.get(0), warringPlayers.get(1), warringPlayers.get(2));
                break;
            default: 
                break;
        }
        
        if(compare.indexOf(max) == 0) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player1).add(prize.get(i));
                playerList.get(player1).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player1)) + " wins the war!\n");
        }
        else if(compare.indexOf(max) == 1) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player2).add(prize.get(i));
                playerList.get(player2).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player2)) + " wins the war!\n");
        }
        else if(compare.indexOf(max) == 2) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player3).add(prize.get(i));
                playerList.get(player3).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player3)) + " wins the war!\n");
        }
    }
    
    public void war(int player1, int player2, int player3, int player4) {
        System.out.println("Four Way War!\n");
        ArrayList<Integer> prize = new ArrayList<Integer>();
        ArrayList<Integer> compare = new ArrayList<Integer>();
        ArrayList<Integer> warringPlayers = new ArrayList<Integer>();
        
        if(playerList.get(player1).size() <= 1) {
            war(player2, player3, player4);
        }
        else if(playerList.get(player2).size() <= 1) {
            war(player1, player3, player4);
        }
        else if(playerList.get(player3).size() <= 1) {
            war(player1, player2, player4);
        }
        else if(playerList.get(player4).size() <= 1) {
            war(player1, player2, player3);
        }
        
        prize.add(playerList.get(player1).get(0));
        playerList.get(player1).remove(0);
        prize.add(playerList.get(player2).get(0));
        playerList.get(player2).remove(0);
        prize.add(playerList.get(player3).get(0));
        playerList.get(player3).remove(0);
        prize.add(playerList.get(player4).get(0));
        playerList.get(player4).remove(0);
        
        compare.add(playerList.get(player1).get(0));
        playerList.get(player1).remove(0);
        compare.add(playerList.get(player2).get(0));
        playerList.get(player2).remove(0);
        compare.add(playerList.get(player3).get(0));
        playerList.get(player3).remove(0);
        compare.add(playerList.get(player4).get(0));
        playerList.get(player4).remove(0);
        
        Integer max = compare.get(0);
        for (int i = 1; i < compare.size(); i++) {
            if (max < compare.get(i)) {
                max = compare.get(i);
                for(int j = 0; j < warringPlayers.size(); j++) {
                    warringPlayers.remove(0);
                }
            }
            else if (max == compare.get(i)) {
                warringPlayers.add(i);
                warringPlayers.add(compare.indexOf(max));
            }
        }
        switch(warringPlayers.size()) {
            case 2: 
                war(warringPlayers.get(0), warringPlayers.get(1));
                break;
            case 3: 
                war(warringPlayers.get(0), warringPlayers.get(1), warringPlayers.get(2));
                break;
            case 4:
                war(warringPlayers.get(0), warringPlayers.get(1), warringPlayers.get(2), warringPlayers.get(3));
                break;
            default: 
                break;
        }
        
        if(compare.indexOf(max) == 0) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player1).add(prize.get(i));
                playerList.get(player1).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player1)) + " wins the war!\n");
        }
        else if(compare.indexOf(max) == 1) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player2).add(prize.get(i));
                playerList.get(player2).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player2)) + " wins the war!\n");
        }
        else if(compare.indexOf(max) == 2) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player3).add(prize.get(i));
                playerList.get(player3).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player3)) + " wins the war!\n");
        }
        else if(compare.indexOf(max) == 3) {
            for(int i = 0; i < compare.size(); i++) {
                playerList.get(player4).add(prize.get(i));
                playerList.get(player4).add(compare.get(i));
            }
            System.out.println("Player " + playerList.indexOf(playerList.get(player4)) + " wins the war!\n");
        }
    }
    
    public void oneTurn() {
        ArrayList<Integer> compare = new ArrayList<Integer>();
        ArrayList<Integer> warringPlayers = new ArrayList<Integer>();
        //"draws" the first card of every player into the compare ArrayList
        for(int i = 0; i < playerNum; i++) {
            compare.add(playerList.get(i).get(0));
            playerList.get(i).remove(0);
        }
        for(int j = 0; j < warringPlayers.size(); j++) {
            warringPlayers.remove(0);
        }
        //finds the card of highest value
        Integer max = compare.get(0);
        for (int i = 1; i < compare.size(); i++) {
            if (max < compare.get(i)) {
                max = compare.get(i);
                for(int j = 0; j < warringPlayers.size(); j++) {
                    warringPlayers.remove(0);
                }
            }
            else if (max == compare.get(i)) {
                warringPlayers.add(i);
                warringPlayers.add(compare.indexOf(max));
            }
        }
        switch(warringPlayers.size()) {
            case 2: 
                war(warringPlayers.get(0), warringPlayers.get(1));
                break;
            case 3: 
                war(warringPlayers.get(0), warringPlayers.get(1), warringPlayers.get(2));
                break;
            case 4:
                war(warringPlayers.get(0), warringPlayers.get(1), warringPlayers.get(2), warringPlayers.get(3));
                break;
            default: 
                break;
        }
        //gives the player with the highest value card all the cards
        for(int i = 0; i < compare.size(); i++) {
            playerList.get(compare.indexOf(max)).add(compare.get(i));
        }
    }
    
    public boolean isDone() {
        //checks if the player has less than 1 card, in which case he is out
        for(int i = playerNum - 1; i >= 0; i--) {
            if(playerList.get(i).size() <= 1) {
                playerList.remove(i);
                playerNum -= 1;
            }
        }
        //if there is one player remaining, they win
        if(playerNum == 1) {
            return true;
        }
        return false;
    }
    
    public void printStart() {
        //prints out all hands of the players
        System.out.print("Starting hands: \n");
        for(int i = 0; i < playerList.size(); i++) {
            System.out.print("Player " + i + ": [ ");
            for(int j = 0; j < playerList.get(i).size(); j++) {
                System.out.print(String.valueOf(playerList.get(i).get(j)) + " ");
            }
            System.out.print("]\n");
        }
        System.out.println();
    }
    
    public void print() {
        String output = "Drawn cards: \n";
        for(int i = 0; i < playerList.size(); i++) {
            output += "Player " + i + ": " + String.valueOf(playerList.get(i).get(0)) + "\n";
        }
        System.out.println(output);
        System.out.println("Continue? [Y/N]");
        output = scan.nextLine();
        switch(output.toLowerCase()) {
            case "y": 
                if(!isDone()) {
                    oneTurn();
                    print();
                    break;
                }
            default:
                int max = 0;
                for (int i = 1; i < playerList.size(); i++) {
                    if (playerList.get(max).size() < playerList.get(i).size()) {
                        max = i;
                    }
                }
                System.out.println("The winner is player " + max + " with " + String.valueOf(playerList.get(max).size()) + " cards!");
                break;
        }
    }
    
    //copied method from internet to introduce delays in the game between turns
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void printMenu() {
	System.out.println("\n   Menu   ");
	System.out.println("   ====================================");
	System.out.println("[1]: Quit");
	System.out.println("[2]: Simulate a game of War with the computer");
	System.out.println("[3]: Rules of War");
	System.out.print("\nEnter your choice: ");
    }
    
    public static void dispatch(int choice) {
	switch(choice) {
	    case 1: 
                break;
	    case 2:
		int num;
                Scanner scan = new Scanner(System.in);
                System.out.print("Input the number of players: ");
                num = scan.nextInt();
                War war = new War(num);
                //starts the game
                war.deal();
                war.printStart();
                war.oneTurn();
                war.print();
                break;
	    case 3:
		System.out.println("\n   Rules   ");
                System.out.println("   ====================================");
                System.out.println("The deck is divided evenly, with each player receiving 26 cards, dealt one at");
                System.out.println("a time, face down. Each player turns up a card at the same time and the player");
                System.out.println("with the higher card takes both cards and puts them, face down, on the bottom");
                System.out.println("of his stack.");
                System.out.println("If the cards are the same rank, it is War. Each player turns up one card face");
                System.out.println("up. The player with the higher cards takes both piles. If the turned-up cards");
                System.out.println("are again the same rank, each player places another card face down and turns"); 
                System.out.println("another card face up. The game ends when one player has won all the cards.");
                break;
            default:
		System.out.println("Sorry, invalid choice");
	    }
    }
}
