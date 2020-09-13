package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Three_of_a_kind">What is a Three of a Kind?</a>
 */
public class ThreeOfAKind extends Hand {

    private List<Card> threeOfAKindCards;
    private List<Card> otherCards;

    public ThreeOfAKind(List<Card> threeOfAKindCards, List<Card> otherCards) {
        this.threeOfAKindCards = threeOfAKindCards;
        this.otherCards = otherCards;
        this.otherCards.sort((x, y) -> y.getRank().compareTo(x.getRank()));
    }

    public HandType getHandType() {
        return HandType.THREE_OF_A_KIND;
    }

    public List<Card> getCards() {
        return threeOfAKindCards;
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

        ThreeOfAKind otherHand = (ThreeOfAKind) o;

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
            ThreeOfAKind otherThreeOfAKindHand = (ThreeOfAKind) otherHand;

            if (this.getCards().get(0).getRank().ordinal() > otherThreeOfAKindHand.getCards().get(0).getRank().ordinal()) {
                return 1; 

            } else if (this.getCards().get(0).getRank().ordinal() < otherThreeOfAKindHand.getCards().get(0).getRank().ordinal()) {
                return -1;

            } else {

                for (int i = 0; i < this.getKickers().size(); i++) {
                    if (this.getKickers().get(i).getRank().ordinal() > otherThreeOfAKindHand.getKickers().get(i).getRank().ordinal()) {
                        return 1;

                    } else if (this.getKickers().get(i).getRank().ordinal() < otherThreeOfAKindHand.getKickers().get(i).getRank().ordinal()) {
                        return -1;

                    } 

                }
                return 0;
            }
        }
    }

    /**
     * @return The name of the hand plus kickers in descending rank, e.g. Trips (4) - A,2 High
     */
    @Override
    public String toString() {
        String threeOfAKindCardsRankString = threeOfAKindCards.get(0).getRank().toString();
        String kickerCardRankString = otherCards.get(0).getRank().toString() + "," + otherCards.get(1).getRank().toString();

        return "Trips (" + threeOfAKindCardsRankString + ") - " + kickerCardRankString + " High";
    }

}
