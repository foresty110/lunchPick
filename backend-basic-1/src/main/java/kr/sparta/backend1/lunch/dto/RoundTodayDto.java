package kr.sparta.backend1.lunch.dto;

import java.time.LocalDate;

public class RoundTodayDto {

    private Long roundId;
    private Long teamId;
    private String teamName;
    private LocalDate date;
    private String status;

    public RoundTodayDto() {}

    public RoundTodayDto(Long roundId, Long teamId, String teamName, LocalDate date, String status) {
        this.roundId = roundId;
        this.teamId = teamId;
        this.teamName = teamName;
        this.date = date;
        this.status = status;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}