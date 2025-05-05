import java.util.ArrayList;
import java.util.Scanner;

public class Card {

private String cardType;
private int cardValue;
private boolean isFaceCard;

public Card(String cardType, int cardValue, boolean isFaceCard) {
  this.cardType = cardType;
  this.cardValue = cardValue;
  this.isFaceCard = isFaceCard;
}

public String getCardType() { // returns the type of the current card
  return cardType;
}

public int getCardValue() { // returns the value of the current card
  return cardValue;
}
  
public boolean getIsFaceCard() { // returns if the current card is a face card
  return isFaceCard;
}
  public String getCard() {
  return cardType + cardValue + isFaceCard;
}
}
