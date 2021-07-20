package it.academy.hotel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.ToString;

@ToString
public enum Gender {
    MALE("Мужчина"),
    FEMALE("Женщина");

    String transfer;
    Gender(String transfer) {
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
