package com.synacy.poker.game.winninghand;

import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardRank;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.types.*;
import com.synacy.poker.hand.WinningHandCalculator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoPairTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //for two pairs
    List<Card> firstPair_01 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS)
    );
    List<Card> secondPair_01 = Arrays.asList(
            new Card(CardRank.THREE, CardSuit.CLUBS),
            new Card(CardRank.THREE, CardSuit.DIAMONDS)
    );
    List<Card> kicker_01 = Arrays.asList(
            new Card(CardRank.ACE, CardSuit.CLUBS)
    );

    //for two pairs
    List<Card> firstPair_02 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.ACE, CardSuit.DIAMONDS)
    );
    List<Card> secondPair_02 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS)
    );
    List<Card> kicker_02 = Arrays.asList(
        new Card(CardRank.THREE, CardSuit.CLUBS)
    );

    //for two pairs
    List<Card> firstPair_03 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.ACE, CardSuit.DIAMONDS)
    );
    List<Card> secondPair_03 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS)
    );
    List<Card> kicker_03 = Arrays.asList(
        new Card(CardRank.KING, CardSuit.CLUBS)
    );

    //for one pair
    List<Card> pair = Arrays.asList(
        new Card(CardRank.TWO, CardSuit.CLUBS),
        new Card(CardRank.TWO, CardSuit.HEARTS)
    );
    List<Card> kickers = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.KING, CardSuit.DIAMONDS),
        new Card(CardRank.QUEEN, CardSuit.SPADES)
    );


    @Test
	public void calculateWinningHand_TwoPair() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new TwoPair(firstPair_01, secondPair_01, kicker_01));
        playerHands.add(new OnePair(pair, kickers));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
	assertTrue(winningHand.get() instanceof TwoPair);
	assertEquals("Two Pair (4,3) - A High", winningHand.get().toString());
    } 
    
    @Test
	public void calculateWinningHand_twoTwoPair() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new TwoPair(firstPair_01, secondPair_01, kicker_01));
        playerHands.add(new TwoPair(firstPair_02, secondPair_02, kicker_02));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
	assertTrue(winningHand.get() instanceof TwoPair);
	assertEquals("Two Pair (A,4) - 3 High", winningHand.get().toString());
    } 

    @Test
	public void calculateWinningHand_sameTwoPair() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new TwoPair(firstPair_02, secondPair_02, kicker_02));
        playerHands.add(new TwoPair(firstPair_03, secondPair_03, kicker_03));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
	assertTrue(winningHand.get() instanceof TwoPair);
	assertEquals("Two Pair (A,4) - K High", winningHand.get().toString());
    }
}