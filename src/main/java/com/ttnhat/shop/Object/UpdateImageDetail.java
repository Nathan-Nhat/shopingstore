package com.ttnhat.shop.Object;

public class UpdateImageDetail {
    private String id;
    private Boolean image0;
    private Boolean image1;
    private Boolean image2;
    private Boolean image3;
    private Boolean image4;
    private Boolean image5;
    private Boolean image6;

    public UpdateImageDetail(String id, Boolean image0, Boolean image1, Boolean image2, Boolean image3, Boolean image4, Boolean image5, Boolean image6) {
        this.id = id;
        this.image0 = image0;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.image6 = image6;
    }

    public String getId() {
        return id;
    }

    public Boolean getImage0() {
        return image0;
    }

    public Boolean getImage1() {
        return image1;
    }

    public Boolean getImage2() {
        return image2;
    }

    public Boolean getImage3() {
        return image3;
    }

    public Boolean getImage4() {
        return image4;
    }

    public Boolean getImage5() {
        return image5;
    }

    public Boolean getImage6() {
        return image6;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage0(Boolean image0) {
        this.image0 = image0;
    }

    public void setImage1(Boolean image1) {
        this.image1 = image1;
    }

    public void setImage2(Boolean image2) {
        this.image2 = image2;
    }

    public void setImage3(Boolean image3) {
        this.image3 = image3;
    }

    public void setImage4(Boolean image4) {
        this.image4 = image4;
    }

    public void setImage5(Boolean image5) {
        this.image5 = image5;
    }

    public void setImage6(Boolean image6) {
        this.image6 = image6;
    }
}
