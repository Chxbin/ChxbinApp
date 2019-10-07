package com.example.chxbinapp.Model;
import java.util.List;

public class RestSports {


        private int count;
        private String next;
        private String previous;
        private List<AllSport> sports;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public List<AllSport> getsports() { return sports; }

        public void setResults(List<AllSport> results) {
            this.sports = sports;
        }
}
