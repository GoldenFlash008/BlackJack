import java.util.ArrayList;
import java.util.Scanner;

public class Player {

Scanner input = new Scanner(System.in);
  
  private ArrayList<Card> hand;
  private int handValue;
  private ArrayList<Card> discard;

public int getHandValue() { // returns the value of the hand of a player
  return handValue;
}

public ArrayList<Card> getHand() { // returns the hand of a player
  return hand;
}

}
