package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Straight_flush">What is a Straight Flush?</a>
 */
public class StraightFlush extends Straight {

    public StraightFlush(List<Card> cards) {
        super(cards);
    }

    @Override
    public HandType getHandType() {
        return HandType.STRAIGHT_FLUSH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        StraightFlush otherHand = (StraightFlush) o;

        if (this.compareTo(otherHand) == 0)
            return true;

        return false;
    }

    /**
     * @return Royal Flush if the hand is a royal flush, or Straight Flush with the highest rank card,
     * e.g. Straight Flush (K High)
     */
    @Override
    public String toString() {
        String highCardRankString = super.getCards().get(0).getRank().toString();

        if (super.getCards().get(0).getRank() == CardRank.ACE) {
            return "Royal Flush";
        } else {
            return "Straight Flush (" + highCardRankString + " High)";
        }
    }

}
