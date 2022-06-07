package com.score.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ball {
	private final BallType ballType;
	private final int runs;

	public boolean isExtra() {
		return BallType.WIDE == ballType;
	}

	public boolean isWicket() {
		return BallType.WICKET == ballType;
	}
}