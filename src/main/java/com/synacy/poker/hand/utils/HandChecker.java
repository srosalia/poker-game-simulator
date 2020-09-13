package com.synacy.poker.hand.utils;

import com.synacy.poker.hand.Hand;
import com.synacy.poker.hand.HandType;
import com.synacy.poker.hand.types.*;
import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.card.CardRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class HandChecker {

    public static Hand checkForStraight(List<Card> playerCards, List<Card> communityCards) {     
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);
  
        if (dealtCards.size() < 5) {
            return null;

        } else {

            List<Card> possibleStraight = Utils.getListofSequentialCards(dealtCards);

            if (possibleStraight != null) 
                return new Straight(possibleStraight);
        }

        return null;
    }

    public static Hand checkForFlush(List<Card> playerCards, List<Card> communityCards) {        
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        Map<CardSuit, List<Card>> cardsByCardSuitDictionary = Utils.groupCardsByCardSuits(dealtCards);
        List<Card> flushCards = new ArrayList<>();

        cardsByCardSuitDictionary.forEach((k,v) -> {
            if (v.size() >= 5) {
                Collections.sort(v, (cardOne, cardTwo) -> {
                    return cardTwo.getRank().compareTo(cardOne.getRank());
                });
                flushCards.addAll(v.subList(0, 5));
            }
        });

        if (!flushCards.isEmpty()) {
            return new Flush(flushCards);
        } else {
            return null;
        }
    }

    public static Hand checkForStraightFlush(List<Card> playerCards, List<Card> communityCards) {      
        //check for flush        
        Hand possibleFlush = checkForFlush(playerCards, communityCards);
        if (possibleFlush != null) {

            Flush definitelyFlush = (Flush) possibleFlush;
            List<Card> possibleStraightFlush = Utils.getListofSequentialCards(definitelyFlush.getCards());

            if (possibleStraightFlush != null) 
                return new StraightFlush(possibleStraightFlush);
        }
        return null;
    }

    public static Hand checkForFourOfAKind(List<Card> playerCards, List<Card> communityCards) {       
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        List<List<Card>> listOfFoundFour = Utils.findGroupsOfNCards(GroupsOf.FOURS, dealtCards);

        Map<CardRank, List<Card>> cardsByCardRankDictionary = Utils.groupCardsByCardRanks(dealtCards);

        if (listOfFoundFour != null) {
            List<Card> remainingCards = new ArrayList<Card>();
            cardsByCardRankDictionary.values().forEach((list) -> remainingCards.addAll(list));

            remainingCards.removeAll(listOfFoundFour.get(0));
            List<Card> kickers = Utils.findKickerCards(HandType.FOUR_OF_A_KIND, remainingCards);

            return new FourOfAKind(listOfFoundFour.get(0),kickers);

        } else {
            return null;

        }
    }

    public static Hand checkForThreeOfAKind(List<Card> playerCards, List<Card> communityCards) {
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        List<List<Card>> listOfFoundThrees = Utils.findGroupsOfNCards(GroupsOf.THREES, dealtCards);
        List<List<Card>> listOfFoundPairs = Utils.findGroupsOfNCards(GroupsOf.TWOS, dealtCards);

        Map<CardRank, List<Card>> cardsByCardRankDictionary = Utils.groupCardsByCardRanks(dealtCards);

        if (listOfFoundThrees != null && listOfFoundThrees.size() == 1 && listOfFoundPairs == null) {

            List<Card> remainingCards = new ArrayList<Card>();
            cardsByCardRankDictionary.values().forEach((list) -> remainingCards.addAll(list));

            remainingCards.removeAll(listOfFoundThrees.get(0));
            List<Card> kickers = Utils.findKickerCards(HandType.THREE_OF_A_KIND, remainingCards);

            return new ThreeOfAKind(listOfFoundThrees.get(0),kickers);

        } else {
            return null;

        }
    }

    public static Hand checkForTwoPair(List<Card> playerCards, List<Card> communityCards) {
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        List<List<Card>> listOfFoundPairs = Utils.findGroupsOfNCards(GroupsOf.TWOS, dealtCards);
        List<List<Card>> listOfFoundThrees = Utils.findGroupsOfNCards(GroupsOf.THREES, dealtCards);
        
        Map<CardRank, List<Card>> cardsByCardRankDictionary = Utils.groupCardsByCardRanks(dealtCards);

        if (listOfFoundPairs != null && listOfFoundPairs.size() > 1 && listOfFoundThrees == null) {
            List<Card> remainingCards = new ArrayList<Card>();
            cardsByCardRankDictionary.values().forEach((list) -> remainingCards.addAll(list));

            remainingCards.removeAll(listOfFoundPairs.get(0));
            remainingCards.removeAll(listOfFoundPairs.get(1));
            
            List<Card> kickers = Utils.findKickerCards(HandType.TWO_PAIR, remainingCards);

            return new TwoPair(listOfFoundPairs.get(0), listOfFoundPairs.get(1), kickers);

        } else {
            return null;

        }
    }


    public static Hand checkForOnePair(List<Card> playerCards, List<Card> communityCards) {        
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        List<List<Card>> listOfFoundPairs = Utils.findGroupsOfNCards(GroupsOf.TWOS, dealtCards);
        List<List<Card>> listOfFoundThrees = Utils.findGroupsOfNCards(GroupsOf.THREES, dealtCards);
        
        Map<CardRank, List<Card>> cardsByCardRankDictionary = Utils.groupCardsByCardRanks(dealtCards);

        if (listOfFoundPairs != null && listOfFoundPairs.size() == 1 && listOfFoundThrees == null) {
            List<Card> remainingCards = new ArrayList<Card>();
            cardsByCardRankDictionary.values().forEach((list) -> remainingCards.addAll(list));

            remainingCards.removeAll(listOfFoundPairs.get(0));
            
            List<Card> kickers = Utils.findKickerCards(HandType.ONE_PAIR, remainingCards);

            return new OnePair(listOfFoundPairs.get(0), kickers);

        } else {
            return null;

        }
    }

    public static Hand checkForFullHouse(List<Card> playerCards, List<Card> communityCards) {
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        List<List<Card>> listOfFoundPairs = Utils.findGroupsOfNCards(GroupsOf.TWOS, dealtCards);
        List<List<Card>> listOfFoundThrees = Utils.findGroupsOfNCards(GroupsOf.THREES, dealtCards);

        if (listOfFoundThrees != null && listOfFoundPairs != null) {
            return new FullHouse(listOfFoundThrees.get(0), listOfFoundPairs.get(0));
        } 
        return null;
    }

    public static Hand getHighCard(List<Card> playerCards, List<Card> communityCards) {
        List<Card> dealtCards = new ArrayList<>(playerCards);
        dealtCards.addAll(communityCards);

        Map<CardRank, List<Card>> cardsByCardRankDictionary = Utils.groupCardsByCardRanks(dealtCards);

        if (dealtCards.size() == cardsByCardRankDictionary.size()) {
            List<CardRank> cardRankList = new ArrayList<CardRank>(cardsByCardRankDictionary.keySet());
            Collections.sort(cardRankList, Collections.reverseOrder());

            List<Card> highCardList = new ArrayList<>();
       
            for(int i = 0; i < cardRankList.size() && i < 5; i++) {
                highCardList.addAll(cardsByCardRankDictionary.get(cardRankList.get(i)));
            }

            return new HighCard(highCardList);

        } else {
            return null;

        }
    }
}