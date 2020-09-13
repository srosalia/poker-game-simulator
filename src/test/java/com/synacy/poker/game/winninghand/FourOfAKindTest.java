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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FourOfAKindTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    //for four of a kind
    List<Card> quads_01 = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES),
        new Card(CardRank.FOUR, CardSuit.HEARTS)
    );
    List<Card> quadKicker_01 = Collections.singletonList(
            new Card(CardRank.ACE, CardSuit.CLUBS)
    );

    //for four of a kind
    List<Card> quads_02 = Arrays.asList(
        new Card(CardRank.JACK, CardSuit.CLUBS),
        new Card(CardRank.JACK, CardSuit.DIAMONDS),
        new Card(CardRank.JACK, CardSuit.SPADES),
        new Card(CardRank.JACK, CardSuit.HEARTS)
    );
    List<Card> quadKicker_02 = Collections.singletonList(
            new Card(CardRank.ACE, CardSuit.CLUBS)
    );

    //for full house 
    List<Card> trips = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );
    List<Card> pair = Arrays.asList(
            new Card(CardRank.ACE, CardSuit.CLUBS),
            new Card(CardRank.ACE, CardSuit.HEARTS)
    );

	@Test
	public void calculateWinningHand_FourOfAKind() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new FourOfAKind(quads_01, quadKicker_01));
        playerHands.add(new FullHouse(trips, pair));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof FourOfAKind);
		assertEquals("Quads (4) - A High", winningHand.get().toString());
	}    

	@Test
	public void calculateWinningHand_twoFourOfAKind() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new FourOfAKind(quads_01, quadKicker_01));
        playerHands.add(new FourOfAKind(quads_02, quadKicker_02));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof FourOfAKind);
		assertEquals("Quads (J) - A High", winningHand.get().toString());
	} 
}