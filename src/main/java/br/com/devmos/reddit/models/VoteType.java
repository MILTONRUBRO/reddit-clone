package br.com.devmos.reddit.models;

import java.util.Arrays;

import br.com.devmos.reddit.exceptions.SpringRedditException;

public enum VoteType {
	UPVOTE(1), DOWNVOTE(-1),
	;
	
	private int direction;
	
	void setDirection(int direction) {
		this.direction = direction;
	}

	private VoteType(int direction) {
		this.direction = direction;
	}
	
	public static VoteType lookup(Integer direction) {
		return Arrays.stream(VoteType.values())
				.filter(value -> value.getDirection().equals(direction))
				.findAny()
				.orElseThrow(() -> new SpringRedditException("value not found"));
				
	}
	
    public Integer getDirection() {
        return direction;
    }
	

}
