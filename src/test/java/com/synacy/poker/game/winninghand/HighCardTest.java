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

public class HighCardTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    //for high card
    List<Card> highCards_01 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.KING, CardSuit.DIAMONDS),
        new Card(CardRank.QUEEN, CardSuit.SPADES),
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.THREE, CardSuit.HEARTS)
    );

    //for high card
    List<Card> highCards_02 = Arrays.asList(
        new Card(CardRank.QUEEN, CardSuit.SPADES), 
        new Card(CardRank.EIGHT, CardSuit.CLUBS),
        new Card(CardRank.FIVE, CardSuit.HEARTS),
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.THREE, CardSuit.HEARTS)

    );

    //for high card
    List<Card> highCards_03 = Arrays.asList(
        new Card(CardRank.QUEEN, CardSuit.SPADES),
        new Card(CardRank.EIGHT, CardSuit.CLUBS),
        new Card(CardRank.FIVE, CardSuit.HEARTS),
        new Card(CardRank.FOUR, CardSuit.CLUBS),
        new Card(CardRank.TWO, CardSuit.HEARTS)
    );


    @Test
	public void calculateWinningHand_HighCard_01() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new HighCard(highCards_01));
        playerHands.add(new HighCard(highCards_02));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof HighCard);
		assertEquals("A,K,Q,4,3", winningHand.get().toString());
    } 
    
    @Test
	public void calculateWinningHand_HighCard_02() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new HighCard(highCards_02));
        playerHands.add(new HighCard(highCards_03));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof HighCard);
		assertEquals("Q,8,5,4,3", winningHand.get().toString());
    } 

}