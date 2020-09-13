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

public class StraightFlushTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    List<Card> royalFlushCards = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.SPADES),
        new Card(CardRank.KING, CardSuit.SPADES),       
        new Card(CardRank.QUEEN, CardSuit.SPADES),
        new Card(CardRank.JACK, CardSuit.SPADES),
        new Card(CardRank.TEN, CardSuit.SPADES)
    );

    List<Card> straightFlushKingHighCards = Arrays.asList(
        new Card(CardRank.KING, CardSuit.SPADES),
        new Card(CardRank.QUEEN, CardSuit.SPADES),
        new Card(CardRank.JACK, CardSuit.SPADES),
        new Card(CardRank.TEN, CardSuit.SPADES),
        new Card(CardRank.NINE, CardSuit.SPADES)
    );

    List<Card> straightFlushFiveHighCards = Arrays.asList(
        new Card(CardRank.FIVE, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.THREE, CardSuit.DIAMONDS),
        new Card(CardRank.TWO, CardSuit.DIAMONDS),
        new Card(CardRank.ACE, CardSuit.DIAMONDS)
    );

    List<Card> straightFlushSixHighCards = Arrays.asList(
        new Card(CardRank.SIX, CardSuit.DIAMONDS),
        new Card(CardRank.FIVE, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.THREE, CardSuit.DIAMONDS),
        new Card(CardRank.TWO, CardSuit.DIAMONDS)
    );

    //for four of a kind
    List<Card> quads = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES),
        new Card(CardRank.FOUR, CardSuit.HEARTS)
    );
    List<Card> quadKicker = Collections.singletonList(
            new Card(CardRank.ACE, CardSuit.CLUBS)
    );

	@Test
	public void calculateWinningHand_royalFlush() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new StraightFlush(straightFlushKingHighCards));
        playerHands.add(new StraightFlush(royalFlushCards));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof StraightFlush);
		assertEquals("Royal Flush", winningHand.get().toString());
	}    

    @Test
	public void calculateWinningHand_StraightFlush() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new StraightFlush(straightFlushFiveHighCards));
        playerHands.add(new FourOfAKind(quads, quadKicker));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);

		assertTrue(winningHand.get() instanceof StraightFlush);
		assertEquals("Straight Flush (5 High)", winningHand.get().toString());
    } 

    @Test
	public void calculateWinningHand_twoStraightFlush() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new StraightFlush(straightFlushSixHighCards));
        playerHands.add(new StraightFlush(straightFlushFiveHighCards));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof StraightFlush);
		assertEquals("Straight Flush (6 High)", winningHand.get().toString());
    } 

}