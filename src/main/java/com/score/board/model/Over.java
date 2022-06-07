package com.score.board.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Over {
	private final int overNo;
	private final List<Ball> balls;

	public Over(int overNo) {
		this.overNo = overNo;
		balls = new ArrayList<>();
	}

	public void addBall(Ball ball) {
		balls.add(ball);
	}
}