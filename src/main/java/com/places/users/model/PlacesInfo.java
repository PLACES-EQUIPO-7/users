package com.places.users.model;

import com.places.users.utils.enums.PlaceRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlacesInfo {

    private List<PlaceUserRelation> relatedPlaces;


    public PlacesInfo() {}

    public PlacesInfo(List<PlaceUserRelation> relatedPlaces) {
        this.relatedPlaces = relatedPlaces;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PlaceUserRelation {

        public PlaceUserRelation(String id, PlaceRole role) {
            this.id = id;
            this.role = role;
        }

        private String id;

        private PlaceRole role;
    }
}
