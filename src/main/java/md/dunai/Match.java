package md.dunai;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Match {
    @SerializedName(value = "home_team")
    private String homeTeam;
    @SerializedName(value = "away_team")
    private String awayTeam;
    @SerializedName(value = "home_goals")
    private int homeGoals;
    @SerializedName(value = "away_goals")
    private int awayGoals;
    private Date date;
    private String analyst;
    private Status status;

    public Match(String homeTeam, String awayTeam, int homeGoals, int awayGoals, Date date, String analyst, Status status) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.date = date;
        this.analyst = analyst;
        this.status = status;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    @Override
    public String toString() {
        return "Match{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeGoals=" + homeGoals +
                ", awayGoals=" + awayGoals +
                ", date='" + date + '\'' +
                ", analyst='" + analyst + '\'' +
                ", status=" + status +
                '}';
    }
}
