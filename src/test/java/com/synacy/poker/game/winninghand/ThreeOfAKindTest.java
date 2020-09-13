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

public class ThreeOfAKindTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    //for three of a kind
    List<Card> trips1 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );
    List<Card> kicker1 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.TWO, CardSuit.HEARTS)
    );

    //for three of a kind
    List<Card> trips2 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );
    List<Card> kicker2 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.QUEEN, CardSuit.CLUBS)
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
	public void calculateWinningHand_ThreeOfAKind() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new ThreeOfAKind(trips1, kicker1));
        playerHands.add(new OnePair(pair, kickers));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof ThreeOfAKind);
		assertEquals("Trips (4) - A,2 High", winningHand.get().toString());
    } 
    
    @Test
	public void calculateWinningHand_twoThreeOfAKind() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new ThreeOfAKind(trips1, kicker1));
        playerHands.add(new ThreeOfAKind(trips2, kicker2));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof ThreeOfAKind);
		assertEquals("Trips (4) - A,Q High", winningHand.get().toString());
    } 
}