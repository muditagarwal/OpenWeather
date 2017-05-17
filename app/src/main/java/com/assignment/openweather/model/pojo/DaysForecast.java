package com.assignment.openweather.model.pojo;

public class DaysForecast {

    private long dt;
    private Weather[] weather;
    private String dt_txt;
    private Main main;

    public long getDaysTimeInMs() {
        return dt * 1000;
    }

    public Main getMain() {
        return main;
    }

    public String getWeatherStatusIcon() {
        if (weather != null) {
            return weather[0].getIcon();
        }
        return "";
    }

    public String getDateVerbose() {
        return dt_txt;
    }

    @Override
    public String toString() {
        return "ClassPojo [weather = " + weather + ", dt_txt = " + dt_txt + ", main = " + main + "]";
    }

    public class Main {

        private double temp_kf;
        private double humidity;
        private double pressure;
        private double temp_max;
        private double sea_level;
        private double temp_min;
        private double temp;
        private double grnd_level;

        public double getTempMax() {
            return temp_max;
        }

        public double getTempMin() {
            return temp_min;
        }

        @Override
        public String toString() {
            return "ClassPojo [temp_kf = " + temp_kf + ", humidity = " + humidity + ", pressure = " + pressure + ", temp_max = " + temp_max + ", sea_level = " + sea_level + ", temp_min = " + temp_min + ", temp = " + temp + ", grnd_level = " + grnd_level + "]";
        }
    }

    public class Weather {

        private String id;
        private String icon;
        private String description;
        private String main;

        public String getId() {
            return id;
        }

        public String getIcon() {
            return icon;
        }

        public String getDescription() {
            return description;
        }

        public String getMain() {
            return main;
        }

        @Override
        public String toString() {
            return "ClassPojo [id = " + id + ", icon = " + icon + ", description = " + description + ", main = " + main + "]";
        }

    }
}

