package com.example.finalyearproject;
public class POJOCategorywiseDish {
    String id,categoryname,restaurantname,dishcategory,dishimage,
            dishname,dishprice,dishrating,dishoffer,dishdescription;

    public POJOCategorywiseDish(String id, String categoryname, String restaurantname, String dishcategory, String dishimage,
                                String dishname, String dishprice, String dishrating, String dishoffer, String dishdescription) {
        this.id = id;
        this.categoryname = categoryname;
        this.restaurantname = restaurantname;
        this.dishcategory = dishcategory;
        this.dishimage = dishimage;
        this.dishname = dishname;
        this.dishprice = dishprice;
        this.dishrating = dishrating;
        this.dishoffer = dishoffer;
        this.dishdescription = dishdescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getDishcategory() {
        return dishcategory;
    }

    public void setDishcategory(String dishcategory) {
        this.dishcategory = dishcategory;
    }

    public String getDishimage() {
        return dishimage;
    }

    public void setDishimage(String dishimage) {
        this.dishimage = dishimage;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getDishprice() {
        return dishprice;
    }

    public void setDishprice(String dishprice) {
        this.dishprice = dishprice;
    }

    public String getDishrating() {
        return dishrating;
    }

    public void setDishrating(String dishrating) {
        this.dishrating = dishrating;
    }

    public String getDishoffer() {
        return dishoffer;
    }

    public void setDishoffer(String dishoffer) {
        this.dishoffer = dishoffer;
    }

    public String getDishdescription() {
        return dishdescription;
    }

    public void setDishdescription(String dishdescription) {
        this.dishdescription = dishdescription;
    }
}
