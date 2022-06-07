package com.score.board.service;

import java.util.List;

import com.score.board.model.Ball;
import com.score.board.model.Player;
import com.score.board.model.Team;

public interface InputService {
	public int getNoOfPlayersPerTeam();

	public int getNoOfOvers();

	public List<Player> getBattingOrder(Team team, int noOfPlayers);

	public Ball getNextBall();

}
