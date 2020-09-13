package com.synacy.poker.hand.utils;

import com.synacy.poker.hand.HandType;
import com.synacy.poker.card.Card;
import com.synacy.poker.card.CardSuit;
import com.synacy.poker.card.CardRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Collections;


enum GroupsOf {

    TWOS(2),
    THREES(3),
    FOURS(4);

    private final long value;
    private GroupsOf(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

}

public class Utils { 
    
    public static Map<CardSuit, List<Card>> groupCardsByCardSuits(List<Card> dealtCards) {
        //example input
        // [QS, 10D, 8D, 8H, 7C, 2S, 2H]

        Map<CardSuit, List<Card>> suitsDictionary = dealtCards.stream().collect(Collectors.groupingBy(Card::getSuit));

        //example output
        // suitsDictionary { CardSuit.CLUBS: [7C], CardSuit.SPADES: [QS, 2S], CardSuit.DIAMONDS: [10D, 8D], CardSuit.HEARTS: [8H, 2H] }
        return suitsDictionary;
    }

    public static Map<CardRank, List<Card>> groupCardsByCardRanks(List<Card> dealtCards) {
        //example input
        // [QS, 10D, 8D, 8H, 7C, 2S, 2H]

        Map<CardRank, List<Card>> rankDictionary = dealtCards.stream().collect(Collectors.groupingBy(Card::getRank));

        //example output
        // rankDictionary { CardRank.QUEEN: [QS], CardRank.TEN: [10D], CardRank.EIGHT: [8D, 8H], CardRank.SEVEN: [7C], CardRank.TWO: [2S, 2H] }
        return rankDictionary;
    } 

    public static Map<CardRank, Long> countOccurrencesOfEachCardRank(List<Card> dealtCards) {
        //example input
        // [QS, 10D, 8D, 8H, 7C, 2S, 2H]

        Map<CardRank, Long> rankDictionary = dealtCards.stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        
        //example output
        // rankDictionary { CardRank.QUEEN: 1, CardRank.TEN: 1, CardRank.EIGHT: 2, CardRank.SEVEN: 1, CardRank.TWO: 2 }
        return rankDictionary;
    } 

    public static Map<Long, Integer> groupByCountPerNumOfOccurence(List<Card> dealtCards) {
        //example input
        // [QS, 10D, 8D, 8H, 7C, 2S, 2H]

        Map<CardRank, Long> cardCountByCardRankDictionary = countOccurrencesOfEachCardRank(dealtCards);
        //example output
        //cardCountByCardRankDictionary { CardRank.QUEEN: 1, CardRank.TEN: 1, CardRank.EIGHT: 2, CardRank.SEVEN: 1, CardRank.TWO: 2 }

        Map<Long, Integer> countPerNumOfOccurence = new HashMap<>();
        cardCountByCardRankDictionary.forEach((k, v) -> {
            if(countPerNumOfOccurence.get(v) != null) {
                countPerNumOfOccurence.put(v, countPerNumOfOccurence.get(v) + 1 );
            } else {
                countPerNumOfOccurence.put(v, 1 );
            }  
        });
        
        //example output 
        // countKindByInstance { 2 : 2, 1 : 3 }
        return countPerNumOfOccurence;
    } 

    public static List<List<Card>> findGroupsOfNCards(GroupsOf n, List<Card> dealtCards) {
        Map<CardRank, List<Card>> cardsByCardRankDictionary = groupCardsByCardRanks(dealtCards);
        Map<Long, Integer> countKindByInstance = groupByCountPerNumOfOccurence(dealtCards);

        if (countKindByInstance.containsKey(n.getValue())) {
            CardRank cardRank = null;
            List<List<Card>> listOfListOfNKindCards = new ArrayList<>();

            List<CardRank> cardRankList = new ArrayList<CardRank>(cardsByCardRankDictionary.keySet());
            Collections.sort(cardRankList, Collections.reverseOrder());

            for(int i = 0; i < cardRankList.size(); i++) {
                cardRank = cardRankList.get(i);
                List<Card> listOfCards = cardsByCardRankDictionary.get(cardRank);
        
                if (listOfCards.size() == n.getValue()) {
                    listOfListOfNKindCards.add(listOfCards);
                }
            }
            return listOfListOfNKindCards;

        } else {
            return null;
        }
       
    }

    public static List<Card> findKickerCards(HandType handFound, List<Card> remainingCards) {
        
        if (remainingCards.isEmpty()) {
            return null;
        }
        
        Map<CardRank, List<Card>> cardsByCardRankDictionary = groupCardsByCardRanks(remainingCards);
        List<CardRank> cardRankList = new ArrayList<CardRank>(cardsByCardRankDictionary.keySet());
        Collections.sort(cardRankList, Collections.reverseOrder());

        List<Card> kickerCards = new ArrayList<>();

        if( handFound == HandType.FOUR_OF_A_KIND || handFound == HandType.TWO_PAIR) {
            kickerCards.addAll(cardsByCardRankDictionary.get(cardRankList.get(0)));

        } else if ( handFound == HandType.THREE_OF_A_KIND ) {
            kickerCards.addAll(cardsByCardRankDictionary.get(cardRankList.get(0)));
            kickerCards.addAll(cardsByCardRankDictionary.get(cardRankList.get(1)));                       

        } else if (handFound == HandType.ONE_PAIR) {
            kickerCards.addAll(cardsByCardRankDictionary.get(cardRankList.get(0)));
            kickerCards.addAll(cardsByCardRankDictionary.get(cardRankList.get(1)));
            kickerCards.addAll(cardsByCardRankDictionary.get(cardRankList.get(2)));

        } 

        return kickerCards;
    }

    public static List<Card> getListofSequentialCards(List<Card> cardList) {
        Map<CardRank, List<Card>> cardsByCardRankDictionary = Utils.groupCardsByCardRanks(cardList);
        List<CardRank> rankList = new ArrayList<CardRank>(cardsByCardRankDictionary.keySet());

        if (rankList.size() < 5) {
            return null;

        } else {
            Collections.sort(rankList);

            int currentHighestRankIndex = rankList.size() - 1;
            for(int i = currentHighestRankIndex; i > 3; i--) {
                if ( rankList.get(i).ordinal() == rankList.get(i-1).ordinal() + 1 
                        && rankList.get(i-1).ordinal() == rankList.get(i-2).ordinal() + 1 
                        && rankList.get(i-2).ordinal() == rankList.get(i-3).ordinal() + 1
                        && rankList.get(i-3).ordinal() == rankList.get(i-4).ordinal() + 1
                    ) {
                        List<Card> listOfSequentialCards = new ArrayList<>();
                        for(int j = i; j > i - 5; j--) {
                            listOfSequentialCards.add(cardsByCardRankDictionary.get(rankList.get(j)).get(0));
                        }
                        return listOfSequentialCards;
                }
            }
        }
        return null;
    }
}

