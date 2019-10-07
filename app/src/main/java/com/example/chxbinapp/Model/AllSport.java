package com.example.chxbinapp.Model;

public class AllSport {
        private int idSport;
        private String strSport;
        private String strSportThumb;
        private String strSportDescription;

        public int getIdSport(){ return idSport; }

        public void setIdSport(){ this.idSport = idSport;}

        public String getStrSport() {
            return strSport;
        }

        public void setName(String name) {
            this.strSport = strSport;
        }

        public String getStrSportThumb() {
            return strSportThumb;
        }

        public void setStrSportThumb(String url) {
            this.strSportThumb = strSportThumb;
        }

        public String getStrSportDescription() { return strSportDescription; }

        public void setStrSportDescription(String strSportDescription) { this.strSportDescription = strSportDescription; }

}
