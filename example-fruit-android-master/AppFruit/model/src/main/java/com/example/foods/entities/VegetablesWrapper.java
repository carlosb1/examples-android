package com.example.foods.entities;

import com.example.foods.common.utils.Constants;

import java.io.Serializable;
import java.util.List;

/**
 * Created by carlos on 9/11/15.
 */
public class VegetablesWrapper implements Serializable {
    private List<Vegetable> vegetables;

    public VegetablesWrapper(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public class Vegetable extends Food implements Serializable{
        //TODO PENDING TO SERIALIZE
        //   private String import;
        private String link;
        private List<String > months;
        private String name;
        private String type;


        public Vegetable(String link, List<String> months, String name, String name_resource, String type) {
            this.link = link;
            this.months = months;
            this.name = name;
            super.name_resource = name_resource;
            this.type = type;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public List<String> getMonths() {
            return months;
        }

        public void setMonths(List<String> months) {
            this.months = months;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImagePath() {
            return Constants.FOOD_DB_HOST+Constants.BASIC_STATIC_URL_VEGETABLES+this.name_resource+".jpg";
        }
    }
}
