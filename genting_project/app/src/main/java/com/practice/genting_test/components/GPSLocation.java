package com.practice.genting_test.components;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.location.LocationManager;

public class GPSLocation  {
    public class LocationData {
        private double latitude;
        private double longitude;

        public LocationData(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }


}
