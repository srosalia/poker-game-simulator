package com.synacy.poker.hand;

/**
 * The base class of the different Hands such as {@link com.synacy.poker.hand.types.Flush},
 * {@link com.synacy.poker.hand.types.FullHouse}, etc.
 */
public abstract class Hand implements Comparable<Hand>{

    /**
     * @return The {@link HandType}
     */
    public abstract HandType getHandType();

    public abstract int compareTo(Hand otherHand);

}
