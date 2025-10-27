package kr.sparta.backend1.lunch.dto;

public class MenuOptionDto {

    private Long id;
    private Long roundId;
    private String menu;
    private String type;
    private Integer price;

    public MenuOptionDto() {}

    public MenuOptionDto(Long id, Long roundId, String menu, String type, Integer price) {
        this.id = id;
        this.roundId = roundId;
        this.menu = menu;
        this.type = type;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
