package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Straight">What is a Straight?</a>
 */
public class Straight extends Hand {

    private List<Card> cards;

    public Straight(List<Card> cards) {
        this.cards = cards;
    }

    public HandType getHandType() {
        return HandType.STRAIGHT;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Straight otherHand = (Straight) o;

        if (this.compareTo(otherHand) == 0)
            return true;

        return false;
    }

    @Override   
    public int compareTo(Hand otherHand) {
        if(this.getHandType().ordinal() > otherHand.getHandType().ordinal()) {
            return 1;

        } else if (this.getHandType().ordinal() < otherHand.getHandType().ordinal()) {
            return -1;

        } else {
            Straight otherStraightHand = (Straight) otherHand;

            if (this.getCards().get(0).getRank().ordinal() > otherStraightHand.getCards().get(0).getRank().ordinal()) {
                return 1; 

            } else if (this.getCards().get(0).getRank().ordinal() < otherStraightHand.getCards().get(0).getRank().ordinal()) {
                return -1;

            } else {
                return 0;
                
            }
        }
    }

    /**
     * @return The name of the hand and the high card, e.g. Straight (A High)
     */
    @Override
    public String toString() {
        String highCardRankString = cards.get(0).getRank().toString();
        
        return "Straight (" + highCardRankString + " High)";
    }

}
