package com.synacy.poker.hand;

import org.springframework.stereotype.Component;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import com.synacy.poker.hand.types.*;

/**
 * A service class used to calculate the winning hand.
 */
@Component
public class WinningHandCalculator {

	/**
	 * @param playerHands
	 * @return The winning {@link Hand} from a list of player hands.
	 */
	public Optional<Hand> calculateWinningHand(List<Hand> playerHands) {
		Optional<Hand> winningHand = Optional.empty(); 

		Collections.sort(playerHands, (Hand x, Hand y) ->  y.getHandType().compareTo(x.getHandType()));
		Map<HandType, Integer> handsCount = new HashMap<>(); //will use to check if 2 or more players have the same hand

		playerHands.forEach((hand) -> {
			if (handsCount.putIfAbsent(hand.getHandType(), 1) != null)
				handsCount.put(hand.getHandType(), handsCount.get(hand.getHandType()) + 1);
		});

		Hand possibleWinningHand = playerHands.get(0);
		HandType possibleWinningHandType = possibleWinningHand.getHandType();
		int sameWinningHandsCount = handsCount.get(possibleWinningHandType);

		if (sameWinningHandsCount > 1) { //two or more same hands found, figure out which one wins
			playerHands.removeIf((hand) -> hand.getHandType() != possibleWinningHandType);
			
			//check which same hand wins
			Collections.sort(playerHands, Collections.reverseOrder());
			possibleWinningHand = playerHands.get(0);
		}

		winningHand = Optional.of(possibleWinningHand);
		return winningHand;
	}
	
}
