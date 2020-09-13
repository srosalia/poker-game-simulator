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

public class FullHouseTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    //for full house
    List<Card> trips_01 = Arrays.asList(
        new Card(CardRank.TWO, CardSuit.CLUBS),
        new Card(CardRank.TWO, CardSuit.DIAMONDS),
        new Card(CardRank.TWO, CardSuit.SPADES)
    );
    List<Card> pair_01 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.DIAMONDS),
        new Card(CardRank.ACE, CardSuit.HEARTS)
    );

    List<Card> trips_02 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );
    List<Card> pair_02 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.DIAMONDS),
        new Card(CardRank.ACE, CardSuit.HEARTS)
    );

    //for flush
    List<Card> flushFiveHighCards = Arrays.asList(
        new Card(CardRank.TEN, CardSuit.DIAMONDS),
        new Card(CardRank.SEVEN, CardSuit.DIAMONDS),
        new Card(CardRank.THREE, CardSuit.DIAMONDS),
        new Card(CardRank.TWO, CardSuit.DIAMONDS),
        new Card(CardRank.ACE, CardSuit.DIAMONDS)
    );


	@Test
	public void calculateWinningHand_FullHouse() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new FullHouse(trips_01, pair_01));
        playerHands.add(new Flush(flushFiveHighCards));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof FullHouse);
		assertEquals("Full House (2,A)", winningHand.get().toString());
	}    

	@Test
	public void calculateWinningHand_twoFullHouse() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new FullHouse(trips_01, pair_01));
        playerHands.add(new FullHouse(trips_02, pair_02));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof FullHouse);
		assertEquals("Full House (4,A)", winningHand.get().toString());
	} 
}