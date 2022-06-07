package com.score.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
	private final Team teamOne;
	private final Team teamTwo;
	private int currentBattingIndex;
	private final int noOfPlayers;
	private final int totalOvers;
	private int currentOver;

	public Match(int noOfPlayers, int noOfOvers) {
		this.noOfPlayers = noOfPlayers;
		this.totalOvers = noOfOvers;
		teamOne = new Team("Team - 1");
		teamTwo = new Team("Team - two");
		currentBattingIndex = 0;
	}

	public void switchBattingTeam() {
		currentBattingIndex = 1 - currentBattingIndex;
	}

	public Team getCurrentBattingTeam() {
		return currentBattingIndex == 0 ? teamOne : teamTwo;
	}

	public Team getCurrentBowlingTeam() {
		return currentBattingIndex == 0 ? teamTwo : teamOne;
	}

	public void displayResult() {
		System.out.println();
		System.out.print("Result: ");
		Team team1 = getTeamOne();
		Team team2 = getTeamTwo();
		// TODO: Show result winner by no of runs or by no of wickets based on the
		// scenario
		int diff = team1.getTotalScore() - team2.getTotalScore();
		if (diff > 0) {
			System.out.println(team1.getName() + " won the match by " + diff + " runs.");
		} else if (diff < 0) {
			System.out.println(team2.getName() + " won the match by " + Math.abs(diff) + " runs.");
		} else {
			System.out.println("Tie");
		}
	}

}