package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Four_of_a_kind">What is a Four of a Kind?</a>
 */
public class FourOfAKind extends Hand {

    private List<Card> fourOfAKindCards;
    private List<Card> otherCards;

    public FourOfAKind(List<Card> fourOfAKindCards, List<Card> otherCards) {
        this.fourOfAKindCards = fourOfAKindCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.FOUR_OF_A_KIND;
    }

    public List<Card> getCards() {
        return fourOfAKindCards;
    }

    public List<Card> getKickers() {
        return otherCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        FourOfAKind otherHand = (FourOfAKind) o;

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
            FourOfAKind otherFourOfAKindHand = (FourOfAKind) otherHand;

            if (this.getCards().get(0).getRank().ordinal() > otherFourOfAKindHand.getCards().get(0).getRank().ordinal()) {
                return 1; 

            } else if (this.getCards().get(0).getRank().ordinal() < otherFourOfAKindHand.getCards().get(0).getRank().ordinal()) {
                return -1;

            } else {
                //do you need to check kicker?
                if (this.getKickers().get(0).getRank().ordinal() > otherFourOfAKindHand.getKickers().get(0).getRank().ordinal()) {
                    return 1;

                } else if (this.getKickers().get(0).getRank().ordinal() < otherFourOfAKindHand.getKickers().get(0).getRank().ordinal()) {
                    return -1;
                    
                } 
                return 0;
            }
        }
    }

    /**
     * @return Returns the name of the hand plus kicker, e.g. Quads (4) - A High
     */
    @Override
    public String toString() {
        String fourOfAKindCardsRankString = fourOfAKindCards.get(0).getRank().toString();
        String kickerCardRankString = otherCards.get(0).getRank().toString();

        return "Quads (" + fourOfAKindCardsRankString + 
            ") - " + kickerCardRankString + " High";
    }

}
