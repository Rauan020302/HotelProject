package it.academy.hotel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.ToString;

@ToString
public enum RoomType {
    SINGLE("одноместный"),
    DOUBLE("двухместный"),
    CHILD("с ребенком"),
    LUX("люкс"),
    EXTRA_LUX("экстра_люкс");
    String transfer;
    RoomType(String transfer) {
        this.transfer = transfer;
    }

    public String getTransfer() {
        return transfer;
    }

    @JsonCreator
    public static Gender transferGender(String transfer){
        for (Gender gender : Gender.values()){
            if (gender.getTransfer().equals(transfer)){
                return gender;
            }
        }
        throw new IllegalArgumentException();
    }

}
