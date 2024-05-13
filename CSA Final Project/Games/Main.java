package Games;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.println("What game do you want to play?" + "\n************************************" +
         "\n [1] - Blackjack \n [2] - Hangman \n [3] - War");

        int input= scan.nextInt();

        switch(input){
            case 1: Blackjack.playMultipleGames();
            break;
            case 2: Hangman.playGame();
            break;
            case 3: War.playGame();
            break;
        }
        scan.close();
    }   
}