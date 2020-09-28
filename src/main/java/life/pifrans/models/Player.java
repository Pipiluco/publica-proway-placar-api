package life.pifrans.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "players")
public class Player extends User {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "player")
	private List<Score> scores;

	@ManyToOne
	@JoinColumn(name = "fk_game")
	private Game game;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	private List<Role> roles;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
