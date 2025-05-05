import java.util.ArrayList;
import java.util.Scanner;

public class BlackJackGame {
  public static void main(String[] args) {


    
 ArrayList<Card> deck = new ArrayList<Card>();
 ArrayList<Card> hand = new ArrayList<Card>();
 ArrayList<Card> discard = new ArrayList<Card>();
 ArrayList<Card> handOne = new ArrayList <Card>();
 ArrayList<Card> handTwo = new ArrayList <Card>();
    
CardCenter theCardCenter = new CardCenter(deck, discard, handOne, handTwo);


theCardCenter.playGame();

    
  }
}
