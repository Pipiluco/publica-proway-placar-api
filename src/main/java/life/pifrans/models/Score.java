package life.pifrans.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scores")
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer points;
	private Integer minimumSeason;
	private Integer maximumSeason;
	private Integer minimumRecord;
	private Integer maximumRecord;

	@ManyToOne
	@JoinColumn(name = "fk_player")
	private Player player;

	@ManyToOne
	@JoinColumn(name = "fk_game")
	private Game game;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getMinimumSeason() {
		return minimumSeason;
	}

	public void setMinimumSeason(Integer minimumSeason) {
		this.minimumSeason = minimumSeason;
	}

	public Integer getMaximumSeason() {
		return maximumSeason;
	}

	public void setMaximumSeason(Integer maximumSeason) {
		this.maximumSeason = maximumSeason;
	}

	public Integer getMinimumRecord() {
		return minimumRecord;
	}

	public void setMinimumRecord(Integer minimumRecord) {
		this.minimumRecord = minimumRecord;
	}

	public Integer getMaximumRecord() {
		return maximumRecord;
	}

	public void setMaximumRecord(Integer maximumRecord) {
		this.maximumRecord = maximumRecord;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
