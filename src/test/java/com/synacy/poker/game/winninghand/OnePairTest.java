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

public class OnePairTest {

    private WinningHandCalculator winningHandCalculator = new WinningHandCalculator();

    //making list of hands
    //for one pair
    List<Card> pair_01 = Arrays.asList(
        new Card(CardRank.TWO, CardSuit.CLUBS),
        new Card(CardRank.TWO, CardSuit.HEARTS)
    );
    List<Card> kickers_01 = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.KING, CardSuit.DIAMONDS),
        new Card(CardRank.JACK, CardSuit.SPADES)
    );

    //for one pair
    List<Card> pair_02 = Arrays.asList(
        new Card(CardRank.TWO, CardSuit.CLUBS),
        new Card(CardRank.TWO, CardSuit.HEARTS)
    );
    List<Card> kickers_02 = Arrays.asList(
        new Card(CardRank.SEVEN, CardSuit.CLUBS),
        new Card(CardRank.FIVE, CardSuit.DIAMONDS),
        new Card(CardRank.FOUR, CardSuit.SPADES)
    );

    //for high card
    List<Card> highCards = Arrays.asList(
        new Card(CardRank.ACE, CardSuit.CLUBS),
        new Card(CardRank.KING, CardSuit.DIAMONDS),
        new Card(CardRank.QUEEN, CardSuit.SPADES),
        new Card(CardRank.TWO, CardSuit.CLUBS),
        new Card(CardRank.THREE, CardSuit.HEARTS)
    );


    @Test
	public void calculateWinningHand_OnePair() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new OnePair(pair_02, kickers_02));
        playerHands.add(new HighCard(highCards));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof OnePair);
		assertEquals("One Pair (2) - 7,5,4 High", winningHand.get().toString());
    } 
    
    @Test
	public void calculateWinningHand_twoOnePair() {
        
        List<Hand> playerHands = new ArrayList<>();

        playerHands.add(new OnePair(pair_01, kickers_01));
        playerHands.add(new OnePair(pair_02, kickers_02));

        Optional<Hand> winningHand = winningHandCalculator.calculateWinningHand(playerHands);
		
		assertTrue(winningHand.get() instanceof OnePair);
		assertEquals("One Pair (2) - A,K,J High", winningHand.get().toString());
    } 

}