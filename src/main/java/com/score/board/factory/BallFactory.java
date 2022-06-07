package com.score.board.factory;

import com.score.board.model.Ball;
import com.score.board.model.BallType;

public class BallFactory {
	public static Ball getBall(String ball) {
		Ball ballObj;
		switch (ball) {
		case "W":
			ballObj = new Ball(BallType.WICKET, 0);
			break;
		case "Wd":
			ballObj = new Ball(BallType.WIDE, 1);
			break;
		default:
			int runs = Integer.parseInt(ball);
			if (runs < 0 || runs > 6 || runs == 5) {
				throw new RuntimeException("Illegal Ball input!");
			}
			ballObj = new Ball(BallType.NORMAL, Integer.parseInt(ball));
		}
		return ballObj;
	}
}