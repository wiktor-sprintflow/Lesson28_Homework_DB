package pl.javastart.sellegro.auction;

public class AuctionFilters {

    private String title;
    private String carMaker;
    private String carModel;
    private String color;

//    public AuctionFilters(String title, String carMaker, String carModel, String color) {
//        this.title = "";
//        this.carMaker = "";
//        this.carModel = "";
//        this.color = "";
//    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public void setCarMaker(String carMaker) {
        this.carMaker = carMaker;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
