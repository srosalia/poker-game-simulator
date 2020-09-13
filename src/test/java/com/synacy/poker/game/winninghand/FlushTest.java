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

public class FlushTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    List<Card> flushJackHighCards = Arrays.asList(
        new Card(CardRank.JACK, CardSuit.SPADES),
        new Card(CardRank.EIGHT, CardSuit.SPADES),
        new Card(CardRank.FIVE, CardSuit.SPADES),
        new Card(CardRank.FOUR, CardSuit.SPADES),
        new Card(CardRank.TWO, CardSuit.SPADES)
    );

    List<Card> flushAceHighCards = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.SPADES),
        new Card(CardRank.EIGHT, CardSuit.SPADES),
        new Card(CardRank.SEVEN, CardSuit.SPADES),
        new Card(CardRank.FIVE, CardSuit.SPADES),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );

    //for straight
    List<Card> aceHighStraight = Arrays.asList(
        new Card(CardRank.JACK, CardSuit.SPADES),
        new Card(CardRank.TEN, CardSuit.CLUBS),
        new Card(CardRank.NINE, CardSuit.CLUBS),
        new Card(CardRank.EIGHT, CardSuit.SPADES),
        new Card(CardRank.SEVEN, CardSuit.HEARTS)
    );


	@Test
	public void calculateWinningHand_Flush() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new Flush(flushJackHighCards));
        playerHands.add(new Straight(aceHighStraight));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof Flush);
		assertEquals("Flush (J High)", winningHand.get().toString());
	}    

    @Test
	public void calculateWinningHand_twoFlush() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new Flush(flushJackHighCards));
        playerHands.add(new Flush(flushAceHighCards));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof Flush);
		assertEquals("Flush (A High)", winningHand.get().toString());
    } 

}