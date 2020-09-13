package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#One_pair">What is a One Pair?</a>
 */
public class OnePair extends Hand{

    private List<Card> pairCards;
    private List<Card> otherCards;

    public OnePair(List<Card> pairCards, List<Card> otherCards) {
        this.pairCards = pairCards;
        this.otherCards = otherCards;
        
        if (this.otherCards != null && this.otherCards.size() > 1)
            this.otherCards.sort((x, y) -> y.getRank().compareTo(x.getRank()));
    }

    public HandType getHandType() {
        return HandType.ONE_PAIR;
    }

    public List<Card> getCards() {
        return pairCards;
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

        OnePair otherHand = (OnePair) o;

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
            OnePair otherOnePairHand = (OnePair) otherHand;

            if (this.getCards().get(0).getRank().ordinal() > otherOnePairHand.getCards().get(0).getRank().ordinal()) {
                return 1; 

            } else if (this.getCards().get(0).getRank().ordinal() < otherOnePairHand.getCards().get(0).getRank().ordinal()) {
                return -1;

            } else {

                for (int i=0; i < this.getKickers().size(); i++) {
                    if (this.getKickers().get(i).getRank().ordinal() > otherOnePairHand.getKickers().get(i).getRank().ordinal()) {
                        return 1;

                    } else if (this.getKickers().get(i).getRank().ordinal() < otherOnePairHand.getKickers().get(i).getRank().ordinal()) {
                        return -1;

                    } 
                }
                return 0;
            }
        }
    }

    /**
     * @return The name of the hand plus kickers ordered by descending rank, e.g. One Pair (2) - A,K,Q High,
     * or the name of the hand and rank if there are no community cards yet in play, e.g. One Pair (2)
     */
    @Override
    public String toString() {
        String kickerString = "";
        
        if (otherCards != null && otherCards.size() == 3) {
            kickerString = " - " + 
                otherCards.get(0).getRank().toString() + "," + 
                otherCards.get(1).getRank().toString() + "," +
                otherCards.get(2).getRank().toString() + " High";
        }
        
        String onePairCardsRankString = pairCards.get(0).getRank().toString();

        return "One Pair (" + onePairCardsRankString + ")" + kickerString;
    }

}
