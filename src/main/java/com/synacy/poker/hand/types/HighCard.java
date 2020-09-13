package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#High_card">What is a High Card?</a>
 */
public class HighCard extends Hand {

    private List<Card> cards;

    public HighCard(List<Card> cards) {
        this.cards = cards;
        this.cards.sort((x, y) -> y.getRank().compareTo(x.getRank()));
    }

    public HandType getHandType() {
        return HandType.HIGH_CARD;
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

        HighCard otherHand = (HighCard) o;

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
            HighCard otherHighCardHand = (HighCard) otherHand;

            for (int i=0; i < this.getCards().size(); i++) {
                if (this.getCards().get(i).getRank().ordinal() > otherHighCardHand.getCards().get(i).getRank().ordinal()) {
                    return 1;

                } else if (this.getCards().get(i).getRank().ordinal() < otherHighCardHand.getCards().get(i).getRank().ordinal()) {
                    return -1;

                } 
            }
            return 0;
        }
    }

    /**
     * @return The cards ordered by descending rank, e.g. A,K,Q,3,2
     */
    @Override
    public String toString() {
        String highCardString = "";
        
        for(int i = 0; i < cards.size(); i++) {
            highCardString = highCardString.concat(cards.get(i).getRank().toString() + ",");
        }

        if (highCardString.endsWith(",")) {
            highCardString = highCardString.substring(0, highCardString.length() - 1);
        }

        return highCardString;
    }

}
