package site.iway.javahelpers;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;

public class AddressHelper {

    public static abstract class AddressInfo implements Serializable {

        private static final long serialVersionUID = 1L;

        public String name;

    }

    public static class Area extends AddressInfo {

        private static final long serialVersionUID = 1L;

    }

    public static class City extends AddressInfo {

        private static final long serialVersionUID = 1L;

        public List<Area> areas;

        public Area findArea(String name) {
            for (Area area : areas) {
                if (area.name.contains(name)) {
                    return area;
                }
            }
            return null;
        }

    }

    public static class Province extends AddressInfo {

        private static final long serialVersionUID = 1L;

        public List<City> cities;

        public City findCity(String name) {
            for (City city : cities) {
                if (city.name.contains(name)) {
                    return city;
                }
            }
            return null;
        }

    }

    public static class Country extends AddressInfo {

        private static final long serialVersionUID = 1L;

        public List<Province> provinces;

        public Province findProvince(String name) {
            for (Province province : provinces) {
                if (province.name.contains(name)) {
                    return province;
                }
            }
            return null;
        }

    }

    public static Country loadChina() {
        try (InputStream inputStream = AddressHelper.class.getResourceAsStream("/china.dat");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream)) {
            Gson gson = new Gson();
            return gson.fromJson(inputStreamReader, Country.class);
        } catch (Exception e) {
            return null;
        }
    }

}
