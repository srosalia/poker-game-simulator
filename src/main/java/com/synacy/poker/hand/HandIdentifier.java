package com.synacy.poker.hand;

import com.synacy.poker.card.Card;
import com.synacy.poker.hand.utils.HandChecker;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A service that is to used to identify the {@link Hand} given the player's cards and the community
 * cards.
 */
@Component
public class HandIdentifier {

    /**
     * Given the player's cards and the community cards, identifies the player's hand.
     *
     * @param playerCards
     * @param communityCards
     * @return The player's {@link Hand} or `null` if no Hand was identified.
     */
    public Hand identifyHand(List<Card> playerCards, List<Card> communityCards) {
        Hand playerCurrentBestHand = null;

        //first, check if there's a straight hand (ascending value)
        playerCurrentBestHand = HandChecker.checkForStraightFlush(playerCards, communityCards);
        if (playerCurrentBestHand != null) { //i.e., straight hand found 
            return playerCurrentBestHand;
        }

        playerCurrentBestHand = HandChecker.checkForFourOfAKind(playerCards, communityCards);
        if (playerCurrentBestHand != null) { //i.e., four of a kind found 
            return playerCurrentBestHand;
        }

        playerCurrentBestHand = HandChecker.checkForFullHouse(playerCards, communityCards); 
        if (playerCurrentBestHand != null) { //i.e., full house found 
            //best hand found
            return playerCurrentBestHand;
        }

        playerCurrentBestHand = HandChecker.checkForFlush(playerCards, communityCards); 
        if (playerCurrentBestHand != null) { //i.e., flush found 
            //best hand found
            return playerCurrentBestHand;
        }

        playerCurrentBestHand = HandChecker.checkForStraight(playerCards, communityCards); 
        if (playerCurrentBestHand != null) { //i.e., straight hand found             
            return playerCurrentBestHand;
        }

        playerCurrentBestHand = HandChecker.checkForThreeOfAKind(playerCards, communityCards);
        if (playerCurrentBestHand != null) { //i.e., three of a kind 
            //best hand found
            return playerCurrentBestHand;
        }       
        
        playerCurrentBestHand = HandChecker.checkForTwoPair(playerCards, communityCards);
        if (playerCurrentBestHand != null) { //i.e., two pairs 
            //best hand found
            return playerCurrentBestHand;
        }        
        
        playerCurrentBestHand = HandChecker.checkForOnePair(playerCards, communityCards);
        if (playerCurrentBestHand != null) { //i.e., two pairs 
            //best hand found
            return playerCurrentBestHand;
        }

        playerCurrentBestHand = HandChecker.getHighCard(playerCards, communityCards);
        if (playerCurrentBestHand != null) { //i.e., two pairs 
            //best hand found
            return playerCurrentBestHand;
        }       
        return null;
    }

}