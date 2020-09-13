package com.synacy.poker.hand.types;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;

import java.util.List;

/**
 * @see <a href="https://en.wikipedia.org/wiki/List_of_poker_hands#Two_pair">What is a Two Pair?</a>
 */
public class TwoPair extends Hand {

    private List<Card> firstPairCards;
    private List<Card> secondPairCards;
    private List<Card> otherCards;

    public TwoPair(List<Card> firstPairCards, List<Card> secondPairCards, List<Card> otherCards) {
        this.firstPairCards = firstPairCards;
        this.secondPairCards = secondPairCards;
        this.otherCards = otherCards;
    }

    public HandType getHandType() {
        return HandType.TWO_PAIR;
    }

    public List<Card> getFirstPair() {
        return firstPairCards;
    } 

    public List<Card> getSecondPair() {
        return secondPairCards;
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

        TwoPair otherHand = (TwoPair) o;

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
            TwoPair otherTwoPairHand = (TwoPair) otherHand;
            int thisFirstPairRankOrdinal = this.getFirstPair().get(0).getRank().ordinal(), 
                thisSecondPairRankOrdinal = this.getSecondPair().get(0).getRank().ordinal(), 
                thisKickerRankOrdinal = this.getKickers().get(0).getRank().ordinal();
                
            int otherTwoPairHandFirstPairRankOrdinal = otherTwoPairHand.getFirstPair().get(0).getRank().ordinal(), 
                otherTwoPairHandSecondPairRankOrdinal = otherTwoPairHand.getSecondPair().get(0).getRank().ordinal(), 
                otherTwoPairHandKickerRankOrdinal = otherTwoPairHand.getKickers().get(0).getRank().ordinal();

            if (thisFirstPairRankOrdinal > otherTwoPairHandFirstPairRankOrdinal) {
                return 1; 

            } else if (thisFirstPairRankOrdinal < otherTwoPairHandFirstPairRankOrdinal) {
                return -1;

            } else {
                if (thisSecondPairRankOrdinal > otherTwoPairHandSecondPairRankOrdinal ) {
                    return 1; 
    
                } else if (thisSecondPairRankOrdinal < otherTwoPairHandSecondPairRankOrdinal ) {
                    return -1;
    
                } else {
                    if (thisKickerRankOrdinal > otherTwoPairHandKickerRankOrdinal) {
                        return 1;
    
                    } else if (thisKickerRankOrdinal < otherTwoPairHandKickerRankOrdinal) {
                        return -1;
    
                    } 
                    return 0;
                }
            }
        }
    }

    /**
     * @return The name of the hand with kicker ranked in descending order, e.g. Two Pair (4,3) - A High
     */
    @Override
    public String toString() {
        String firstPairCardsRankString = firstPairCards.get(0).getRank().toString();
        String secondPairCardsRankString = secondPairCards.get(0).getRank().toString();
        String kickerCardRankString = otherCards.get(0).getRank().toString();

        return "Two Pair (" + firstPairCardsRankString + "," + secondPairCardsRankString + ") - " + kickerCardRankString + " High" ;
    }

}
