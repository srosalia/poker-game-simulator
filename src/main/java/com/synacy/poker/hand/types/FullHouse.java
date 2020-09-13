package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Full_house">What is a Full House?</a>
 */
public class FullHouse extends Hand {

    private List<Card> threeOfAKindCards;
    private List<Card> pairCards;

    public FullHouse(List<Card> threeOfAKindCards, List<Card> pairCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.pairCards = pairCards;
    }

    public HandType getHandType() {
        return HandType.FULL_HOUSE;
    }

    public List<Card> getCards() {
        return threeOfAKindCards;
    }

    public List<Card> getPairs() {
        return pairCards;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        FullHouse otherHand = (FullHouse) o;

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
            FullHouse otherFullHouseHand = (FullHouse) otherHand;

            if (this.getCards().get(0).getRank().ordinal() > otherFullHouseHand.getCards().get(0).getRank().ordinal()) {
                return 1; 

            } else if (this.getCards().get(0).getRank().ordinal() < otherFullHouseHand.getCards().get(0).getRank().ordinal()) {
                return -1;

            } else {
                if (this.getPairs().get(0).getRank().ordinal() > otherFullHouseHand.getPairs().get(0).getRank().ordinal()) {
                    return 1;

                } else if (this.getPairs().get(0).getRank().ordinal() > otherFullHouseHand.getPairs().get(0).getRank().ordinal()) {
                    return -1;

                } 

                return 0;
            }
        }
    }

    /**
     * @return The name of the hand with rank of the three pair and two pair, e.g.
     * 444AA - Full House (4,A)
     */
    @Override
    public String toString() {
        String three = threeOfAKindCards.get(0).getRank().toString();
        String pair = pairCards.get(0).getRank().toString();

        return "Full House (" + three + "," + pair + ")"; //three.repeat(3) + pair.repeat(2) + 
                
    }

}
