import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CardCenter {

//public Card drawCard(){}



private ArrayList<Card> deck; // the deck of cards that will lead to your downfall
private ArrayList<Card> discard; // just here
private ArrayList<Card> handOne; // your hand
private ArrayList<Card> handTwo; // opponent's hand
private ArrayList<Card> backup;
private String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
private String[] faceCards = {"Jack", "Queen", "King"};
int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10};
private boolean lose = false;
private boolean blackJack = false;
private int regMultiply = 2;
private int blackJackMultiply = 3;

private int handOneValue = 0; // total value of your hand
private int handTwoValue = 0; // total value of your opponent's hand
private int yourFinalValue = 0; // used to decide who is closer to 21
private int opponentFinalValue = 0; // used to decide who is closer to 21


private String winner; // basically just who won

private String response; // a response

private int yourMoney = 10000; // how much you will lose in the next 5 minutes
private int opponentMoney = 10000; // opponents money
private int gamblegamblegamble; // gamble.
private int randomGamble; // opponent's gamble
private int gambleAmount; // amount you're willing to part with


public CardCenter(ArrayList<Card> deck, ArrayList<Card> discard, ArrayList<Card> handOne, ArrayList<Card> handTwo) {
/**
* Object CardCenter to access the ArrayLists "deck", "hand", and "discard" in CardCenter.java
* @param deck, the ArrayList of cards used to store the cards in a deck.
* @param hand, the ArrayList of cards that you have in your hand.
* @param discard, the ArrayList of cards that are stored in a discard pile.
*/
this.deck = deck;
this.discard = discard;
this.handOne = handOne;
this.handTwo = handTwo;
    
deck = new ArrayList<Card>();
discard = new ArrayList<Card>();
handOne = new ArrayList<Card>();
handTwo = new ArrayList<Card>();
backup = new ArrayList<Card>();
  }


/**
* Initializes a new deck of cards with values and card types.
* This method populates the deck with standard 52 cards (including face cards and Aces).
*/
  
private void newDeck() {
  if(deck.size() > 0) {
    for(int i = 0; i < deck.size(); i++) {
      deck.remove(i);
    }
  }
  
for(String suit : suits) { //iterates once for every "suit" 
  
  for(int value : values) {
    deck.add(new Card(suit, value, false));
  }
  for(String face : faceCards) {
    deck.add(new Card(suit + " (" + face + ")", 10, true));
  }
  deck.add(new Card(suit, 1, false)); // ace
}
  this.shuffleDeck();
  this.shuffleDeck();
  //this.getDeck();

}

private int randomNumGen() {
  int randomNum = (int)(Math.random() * deck.size() - 1);
  return randomNum;
}
  
private void shuffleDeck() {

for(int i = 0; i < 1000; i++) {
  int randomNum = (int)(Math.random() * deck.size() - 1);
  Card currentCard = deck.get(randomNum);
  backup.add(new Card(currentCard.getCardType(), currentCard.getCardValue(), currentCard.getIsFaceCard()));
  deck.remove(randomNum);
  Card backupCard = backup.get(0);
  deck.add(new Card(backupCard.getCardType(), backupCard.getCardValue(), backupCard.getIsFaceCard()));
  backup.remove(0); 
  }
   // this.getDeck();
}


/**
* Starts a new game, handling user input, turns for both players, and determining the winner.
* The game continues based on player decision to play again.
*/

public void playGame() {



Scanner userInput = new Scanner(System.in);
Scanner userInputInt = new Scanner(System.in);
  
this.newDeck();
System.out.println(deck.size());
System.out.println("Drawing cards...");
System.out.println(" ");
System.out.println("Your hand: ");
System.out.println(" ");
  
this.transferCardYou();
this.transferCardYou();
this.transferCardOpponent();
this.transferCardOpponent();

System.out.println(" ");
System.out.println("Your total: " + handOneValue);
System.out.println(" ");
this.gamble();
if(lose = true) {

}

randomGamble = (int)(Math.random()*opponentMoney);

System.out.println("");
System.out.println("Do you want to hit or keep?");
System.out.println("");
  
  String hitOrKeep = userInput.nextLine();
  
  this.hitOrKeep(hitOrKeep);
  
  this.opponentTakeTurn();
  
  System.out.println("Opponent had " + handTwoValue);
  System.out.println(this.checkWinner() + " won!");
  
  this.payWinner(gamblegamblegamble);
  System.out.println("---------------------------------------------------------");
  System.out.println(" ");
  System.out.println("Play Again?");
  String retry = userInput.nextLine();
  if(retry.equals("Yes") || retry.equals("yes")) {
    this.resetGame();
    this.playGame();
}
  if(retry.equals("No") || retry.equals("no")) {
    System.out.println("99% of gamblers quit before winning big!");
  }

}
  
private String hitOrKeep(String response) {
  this.response = response;
  Scanner userInput = new Scanner(System.in);
  
if(response.equals("hit")) {   
    
  int deckSize = deck.size() - 1;
  int index = (int)(Math.random() * deckSize);
  Card drawnCard = deck.get(index);
    
  handOne.add(new Card(drawnCard.getCardType(), drawnCard.getCardValue(), drawnCard.getIsFaceCard()));
  handOneValue += drawnCard.getCardValue();
  System.out.println(drawnCard.getCardValue () + " of " + drawnCard.getCardType());
  System.out.println("Total: " + handOneValue);
  deck.remove(index);

  if(handOneValue < 21) {
    System.out.println("Do you want to hit or keep?");
    String hitOrKeep = userInput.nextLine();
    this.hitOrKeep(hitOrKeep);
    }
  }
  
  if(response.equals("keep")) { 
    System.out.println(" ");
    System.out.println("Turn ended!" + " The total of your cards is: " + handOneValue);
    System.out.println(" ");
  }
return "";  
}

private boolean gamble() {
  Scanner userInputInt = new Scanner(System.in);
  System.out.println("What is your bet?");
  gamblegamblegamble = userInputInt.nextInt();
  lose = false;
  if(gamblegamblegamble > yourMoney) {
    if(yourMoney > 0) {
    System.out.println("You can't bet that much!");
    System.out.println(" ");
    this.gamble();
    }
    else{
      System.out.println("Out of money! SKILL ISSUE");
      lose = true;
    }
  }
  return lose;
}
/**
* Transfers a card to the player's hand (handOne).
* A card is drawn from the deck, added to the player's hand, and the card's value is added to the player's total.
*/
private String transferCardYou() {

int deckSize = deck.size() - 1;

int index = (int)(Math.random() * deckSize);
Card drawnCard = deck.get(index);
String drawnCardType = drawnCard.getCardType();
int drawnCardValue = drawnCard.getCardValue();

handOne.add(new Card (drawnCard.getCardType(), drawnCard.getCardValue(), drawnCard.getIsFaceCard()));
handOneValue += drawnCard.getCardValue();
System.out.println(drawnCardValue + " of " + drawnCardType);
deck.remove(index);
return "";
}

/**
* Transfers a card to the opponent's hand (handTwo).
* A card is drawn from the deck, added to the opponent's hand, and the card's value is added to the opponent's total.
*/
private String transferCardOpponent() {

int deckSize = deck.size() - 1;

int index = (int)(Math.random() * deckSize);
Card drawnCard = deck.get(index);
String drawnCardType = drawnCard.getCardType();
int drawnCardValue = drawnCard.getCardValue();

handTwo.add(new Card (drawnCard.getCardType(), drawnCard.getCardValue(), drawnCard.getIsFaceCard()));
handTwoValue += drawnCard.getCardValue();
deck.remove(index);
return "";
}

private String opponentTakeTurn() {
  while(handTwoValue <= 17) {
    this.transferCardOpponent();
  }
  System.out.println(" ");
  System.out.println("Opponent has ended turn.");
  System.out.println(" ");
  return "";
}


private String checkWinner() {
  opponentFinalValue = 21 - handTwoValue;
  yourFinalValue = 21 - handOneValue;
if(yourFinalValue != opponentFinalValue) {
  
  if(handOneValue < 22) {
    if(yourFinalValue < opponentFinalValue || yourFinalValue == 0) {
    winner = "You";
  }
}

  
  if(handTwoValue < 22) {
    if(opponentFinalValue < yourFinalValue || opponentFinalValue == 0) {
      winner = "Opponent";
    }
  }
}

if(handOneValue > 21) {
  if(handTwoValue < 22) {
    winner = "Opponent";
  }
}

if(handTwoValue > 21) {
  if(handOneValue < 22) {
    winner = "You";
  }
}
  
if(yourFinalValue == opponentFinalValue) {
  if(handOneValue < 22) {
      winner = "opponent";
  }
}


if(handOneValue > 21) {
  if(handTwoValue > 21) {
    winner = "No one";
  }
  for(int i = 0; i < handOne.size(); i++) {
    Card selectedCard = handOne.get(i);
    int faceCardCount = 0;
    int aceCount = 0;
    if(selectedCard.getIsFaceCard() == true) { // checks if you have a face card in your hand when your hand is less than 22
      if(handOneValue < 22) {
        faceCardCount++;
      }
    }
    if(selectedCard.getCardValue() == 1) { // checks if you have an ace in  your hand when your hand is less than 22
      if(handOneValue < 22) {
          aceCount++;
      }
    }
    if(faceCardCount > 0 && aceCount > 0) {
      winner = "You";
      blackJack = true;
    }
  }
}
  return winner;
}

private void payWinner(int gambleAmount) {
  
this.gambleAmount = gamblegamblegamble;
  
  if(winner.equals("You")) {
    if(blackJack == true) {
      int wonMoney = gambleAmount * blackJackMultiply;
    yourMoney += wonMoney;
    System.out.println("You now have " + yourMoney + "$ after winning " + wonMoney + "$");
    }
    else{
    int wonMoney = gambleAmount * regMultiply;
    yourMoney += wonMoney;
    System.out.println("You now have " + yourMoney + "$ after winning " + wonMoney + "$");
    }
  }
  if(winner.equals("Opponent")) {
    int wonMoney2 = randomGamble * regMultiply;
    opponentMoney += wonMoney2;
    System.out.println("Opponent now has " + opponentMoney + "$");
  }


  if(!(winner.equals("You"))) {
    yourMoney -= gambleAmount;
    System.out.println(" ");
    System.out.println("You have " + yourMoney + "$ left");
  }
  
}

public ArrayList<Card> printDeck() {

  for(int index = 0; index < deck.size(); index++) {
Card returnedCard = deck.get(index);
System.out.println(returnedCard.getCardType() + " of " + returnedCard.getCardValue());
}
  return deck;
}

public ArrayList<Card> getDeck() {
  for(int i = 0; i < deck.size(); i++) {
    Card currentCard = deck.get(i);
    System.out.println(currentCard.getCard());
  }
  return deck;
}


private void resetGame() {
this.newDeck();
  
for(int index = 0; index < handOne.size(); index++) {
    handOne.remove(index);
  }
  for(int index2 = 0; index2 < handOne.size(); index2++) {
    handTwo.remove(index2);
  }

handOneValue = 0;
handTwoValue = 0;

  }
}

