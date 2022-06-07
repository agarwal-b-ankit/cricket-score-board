package com.score.board.model;

import lombok.Getter;

@Getter
public class ScoreCard {
	private int noOfRuns;
	private int noOfFours;
	private int noOfSixes;
	private int noOfBalls;
	private boolean out;

	public void updateScore(int runs) {
		noOfRuns += runs;
		noOfBalls++;
		if (runs == 4) {
			noOfFours++;
		} else if (runs == 6) {
			noOfSixes++;
		}
	}

	public void markOutStatus() {
		out = true;
	}
}
