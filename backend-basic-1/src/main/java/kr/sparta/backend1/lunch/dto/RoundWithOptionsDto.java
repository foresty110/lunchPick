package kr.sparta.backend1.lunch.dto;


import kr.sparta.backend1.lunch.enums.RoundStatus;

import java.time.LocalDateTime;
import java.util.List;

public class RoundWithOptionsDto {
    private Long id;
    private Long teamId;
    private String teamName;
    private LocalDateTime date;
    private RoundStatus status;
    private List<MenuOptionDto> options;

    public RoundWithOptionsDto(){}

    public RoundWithOptionsDto(Long id, Long teamId, String teamName, LocalDateTime date, RoundStatus status, List<MenuOptionDto> options) {
        this.id = id;
        this.teamId = teamId;
        this.teamName = teamName;
        this.date = date;
        this.status = status;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public RoundStatus getStatus() {
        return status;
    }

    public void setStatus(RoundStatus status) {
        this.status = status;
    }

    public List<MenuOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<MenuOptionDto> options) {
        this.options = options;
    }
}