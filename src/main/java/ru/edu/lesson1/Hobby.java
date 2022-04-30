package ru.edu.lesson1;

import java.util.ArrayList;
import java.util.Collection;

public class Hobby {

        private String id;
        private String name;
        private String description;

        public Hobby(String id, String name, String description) {
             this.id = id;
             this.name = name;
             this.description = description;
        }

        public Hobby(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return "User {" +
                    "Id: " + getId()
                    + ", Name: " + getName()
                    + ", Description: " + getDescription()
                    + "}";
        }
}
