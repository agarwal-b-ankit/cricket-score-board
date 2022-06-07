package com.score.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Player {
	private final String name;
	private ScoreCard scoreCard;
}