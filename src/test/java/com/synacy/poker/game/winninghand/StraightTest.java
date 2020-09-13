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

public class StraightTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    List<Card> straightAceHighCards = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.SPADES),
        new Card(CardRank.KING, CardSuit.DIAMONDS),       
        new Card(CardRank.QUEEN, CardSuit.CLUBS),
        new Card(CardRank.JACK, CardSuit.HEARTS),
        new Card(CardRank.TEN, CardSuit.SPADES)
    );

    List<Card> straightKingHighCards = Arrays.asList(
        new Card(CardRank.KING, CardSuit.DIAMONDS),
        new Card(CardRank.QUEEN, CardSuit.CLUBS),
        new Card(CardRank.JACK, CardSuit.HEARTS),
        new Card(CardRank.TEN, CardSuit.SPADES),
        new Card(CardRank.NINE, CardSuit.SPADES)
    );

    //for three of a kind
    List<Card> trips = Arrays.asList(
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.FOUR, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );
    List<Card> kickers = Arrays.asList(
            new Card(CardRank.ACE, CardSuit.CLUBS),
            new Card(CardRank.TWO, CardSuit.CLUBS)
    );


    @Test
	public void calculateWinningHand_Straight() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new Straight(straightKingHighCards));
        playerHands.add(new ThreeOfAKind(trips, kickers));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof Straight);
		assertEquals("Straight (K High)", winningHand.get().toString());
    } 

	@Test
	public void calculateWinningHand_twoStraight() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new Straight(straightAceHighCards));
        playerHands.add(new Straight(straightKingHighCards));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof Straight);
		assertEquals("Straight (A High)", winningHand.get().toString());
	}    

}