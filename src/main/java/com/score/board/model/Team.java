package com.score.board.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Team {
	private final String name;
	private final List<Inning> innings;

	public Team(String name) {
		this.name = name;
		innings = new ArrayList<>();
	}

	public void addInnings(Inning inning) {
		innings.add(inning);
	}

	public int getTotalScore() {
		int score = 0;
		for (Inning inning: innings) {
			score += inning.getTotalScore();
		}
		return score;
	}

}
