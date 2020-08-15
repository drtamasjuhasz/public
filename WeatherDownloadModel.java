package getweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDownloadModel {

    public WeatherDownloadModel(String telepules1) {
    }

    private static final String APPID = "e2bb56f4a10a82c79a894a79dcb9803a";             
    private static String url1
            = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static String url2 = "&units=metric&appid=" + APPID;
    private static StringBuilder jsonPuffer;

    private static String telepules = null;
    private static String time = null;
    private static Double temp = null;
    private static Double temp_min = null;
    private static Double temp_max = null;
    private static Double pressure = null;
    private static Double sea_level = null;
    private static Double grnd_level = null;
    private static Double humidity = null;
    private static Double temp_kf = null;
    private static String weather_main = null;
    private static String weather_desc = null;
    private static Double clouds = null;
    private static Double wind_speed = null;
    private static Double wind_deg = null;

    private static ArrayList<String> timeList = new ArrayList<>();
    private static ArrayList<Double> tempList = new ArrayList<>();
    private static ArrayList<Double> temp_minList = new ArrayList<>();
    private static ArrayList<Double> temp_maxList = new ArrayList<>();
    private static ArrayList<Double> pressureList = new ArrayList<>();
    private static ArrayList<Double> sea_levelList = new ArrayList<>();
    private static ArrayList<Double> grnd_levelList = new ArrayList<>();
    private static ArrayList<Double> humidityList = new ArrayList<>();
    private static ArrayList<Double> temp_kfList = new ArrayList<>();
    private static ArrayList<String> weather_mainList = new ArrayList<>();
    private static ArrayList<String> weather_descList = new ArrayList<>();
    private static ArrayList<Double> cloudsList = new ArrayList<>();
    private static ArrayList<Double> wind_speedList = new ArrayList<>();
    private static ArrayList<Double> wind_degList = new ArrayList<>();
    private static ArrayList<ArrayList> cityList = new ArrayList<>();

    public static WeatherDownloadModel weatherdownloadmodel(String telepules1) {
        WeatherDownloadModel.setTelepules(telepules1);
        WeatherDownloadModel getCity = new WeatherDownloadModel(telepules1);

        timeList.clear();
        tempList.clear();
        temp_minList.clear();
        temp_maxList.clear();
        pressureList.clear();
        sea_levelList.clear();
        grnd_levelList.clear();
        humidityList.clear();
        temp_kfList.clear();
        weather_mainList.clear();
        weather_descList.clear();
        cloudsList.clear();
        wind_speedList.clear();
        wind_degList.clear();
        cityList.clear();

        try {
            setJsonPuffer();
        } catch (IOException ex) {
            Logger.getLogger(WeatherDownloadModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getCity;
    }

    public static void setJsonPuffer() throws IOException {

        String url = url1 + telepules + url2;
        //System.out.println(url);
        jsonPuffer = new StringBuilder();

        try {
            HttpURLConnection c = (HttpURLConnection) (new URL(url)).openConnection();
            c.setRequestMethod("GET");
            c.connect();
            InputStream is = c.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while (((line = br.readLine()) != null)) {
                jsonPuffer.append(line).append("\r\n");
            }
            is.close();
            c.disconnect();
        } catch (MalformedURLException e) {
            System.out.println("hiba: MalformedURLException");
        } catch (ProtocolException e) {
            System.out.println("hiba: ProtocolException");
        } catch (IOException e) {
            System.out.println("hiba: IOException");
        }

        jsonFeldolgoz1();

    }

    private static void jsonFeldolgoz1() throws IOException {

        try {
            JSONObject json = new JSONObject(jsonPuffer.toString());
            JSONArray jArray = json.getJSONArray("list");
            //System.out.println(json);
            //System.out.println(jArray);
            int count1 = jArray.length();

            for (int i = 0; i < count1; i++) {

                JSONObject jsonObject = jArray.getJSONObject(i);
                JSONArray jArray2 = jsonObject.getJSONArray("weather");
                JSONObject jsonObject2 = jArray2.getJSONObject(0);

                time = (jsonObject.getString("dt_txt"));
                temp = (jsonObject.getJSONObject("main").getDouble("temp"));
                temp_min = (jsonObject.getJSONObject("main").getDouble("temp_min"));
                temp_max = (jsonObject.getJSONObject("main").getDouble("temp_max"));
                pressure = (jsonObject.getJSONObject("main").getDouble("pressure"));
                sea_level = (jsonObject.getJSONObject("main").getDouble("sea_level"));
                grnd_level = (jsonObject.getJSONObject("main").getDouble("grnd_level"));
                humidity = (jsonObject.getJSONObject("main").getDouble("humidity"));
                temp_kf = (jsonObject.getJSONObject("main").getDouble("temp_kf"));
                weather_main = (jsonObject2.getString("main"));
                weather_desc = (jsonObject2.getString("description"));
                clouds = (jsonObject.getJSONObject("clouds").getDouble("all"));
                wind_speed = (jsonObject.getJSONObject("wind").getDouble("speed"));
                wind_deg = (jsonObject.getJSONObject("wind").getDouble("deg"));

                timeList.add(time);
                tempList.add(temp);
                temp_minList.add(temp_min);
                temp_maxList.add(temp_max);
                pressureList.add(pressure);
                sea_levelList.add(sea_level);
                grnd_levelList.add(grnd_level);
                humidityList.add(humidity);
                temp_kfList.add(temp_kf);
                weather_mainList.add(weather_main);
                weather_descList.add(weather_desc);
                cloudsList.add(clouds);
                wind_speedList.add(wind_speed);
                wind_degList.add(wind_deg);

            }

            cityList.add(timeList);
            cityList.add(tempList);
            cityList.add(temp_minList);
            cityList.add(temp_maxList);
            cityList.add(pressureList);
            cityList.add(sea_levelList);
            cityList.add(grnd_levelList);
            cityList.add(humidityList);
            cityList.add(temp_kfList);
            cityList.add(weather_mainList);
            cityList.add(weather_descList);
            cityList.add(cloudsList);
            cityList.add(wind_speedList);
            cityList.add(wind_degList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static String getTelepules() {
        return telepules;
    }

    public static void setTelepules(String telepules) {
        WeatherDownloadModel.telepules = telepules;
    }

    public static String getUrl1() {
        return url1;
    }

    public static void setUrl1(String url1) {
        WeatherDownloadModel.url1 = url1;
    }

    public static String getUrl2() {
        return url2;
    }

    public static void setUrl2(String url2) {
        WeatherDownloadModel.url2 = url2;
    }

    public static StringBuilder getJsonPuffer() {
        return jsonPuffer;
    }

    public static void setJsonPuffer(StringBuilder jsonPuffer) {
        WeatherDownloadModel.jsonPuffer = jsonPuffer;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        WeatherDownloadModel.time = time;
    }

    public static Double getTemp() {
        return temp;
    }

    public static void setTemp(Double temp) {
        WeatherDownloadModel.temp = temp;
    }

    public static Double getTemp_min() {
        return temp_min;
    }

    public static void setTemp_min(Double temp_min) {
        WeatherDownloadModel.temp_min = temp_min;
    }

    public static Double getTemp_max() {
        return temp_max;
    }

    public static void setTemp_max(Double temp_max) {
        WeatherDownloadModel.temp_max = temp_max;
    }

    public static Double getPressure() {
        return pressure;
    }

    public static void setPressure(Double pressure) {
        WeatherDownloadModel.pressure = pressure;
    }

    public static Double getSea_level() {
        return sea_level;
    }

    public static void setSea_level(Double sea_level) {
        WeatherDownloadModel.sea_level = sea_level;
    }

    public static Double getGrnd_level() {
        return grnd_level;
    }

    public static void setGrnd_level(Double grnd_level) {
        WeatherDownloadModel.grnd_level = grnd_level;
    }

    public static Double getHumidity() {
        return humidity;
    }

    public static void setHumidity(Double humidity) {
        WeatherDownloadModel.humidity = humidity;
    }

    public static Double getTemp_kf() {
        return temp_kf;
    }

    public static void setTemp_kf(Double temp_kf) {
        WeatherDownloadModel.temp_kf = temp_kf;
    }

    public static String getWeather_main() {
        return weather_main;
    }

    public static void setWeather_main(String weather_main) {
        WeatherDownloadModel.weather_main = weather_main;
    }

    public static String getWeather_desc() {
        return weather_desc;
    }

    public static void setWeather_desc(String weather_desc) {
        WeatherDownloadModel.weather_desc = weather_desc;
    }

    public static Double getClouds() {
        return clouds;
    }

    public static void setClouds(Double clouds) {
        WeatherDownloadModel.clouds = clouds;
    }

    public static Double getWind_speed() {
        return wind_speed;
    }

    public static void setWind_speed(Double wind_speed) {
        WeatherDownloadModel.wind_speed = wind_speed;
    }

    public static Double getWind_deg() {
        return wind_deg;
    }

    public static void setWind_deg(Double wind_deg) {
        WeatherDownloadModel.wind_deg = wind_deg;
    }

    public static ArrayList<String> getTimeList() {
        return timeList;
    }

    public static void setTimeList(ArrayList<String> timeList) {
        WeatherDownloadModel.timeList = timeList;
    }

    public static ArrayList<Double> getTempList() {
        return tempList;
    }

    public static void setTempList(ArrayList<Double> tempList) {
        WeatherDownloadModel.tempList = tempList;
    }

    public static ArrayList<Double> getTemp_minList() {
        return temp_minList;
    }

    public static void setTemp_minList(ArrayList<Double> temp_minList) {
        WeatherDownloadModel.temp_minList = temp_minList;
    }

    public static ArrayList<Double> getTemp_maxList() {
        return temp_maxList;
    }

    public static void setTemp_maxList(ArrayList<Double> temp_maxList) {
        WeatherDownloadModel.temp_maxList = temp_maxList;
    }

    public static ArrayList<Double> getPressureList() {
        return pressureList;
    }

    public static void setPressureList(ArrayList<Double> pressureList) {
        WeatherDownloadModel.pressureList = pressureList;
    }

    public static ArrayList<Double> getSea_levelList() {
        return sea_levelList;
    }

    public static void setSea_levelList(ArrayList<Double> sea_levelList) {
        WeatherDownloadModel.sea_levelList = sea_levelList;
    }

    public static ArrayList<Double> getGrnd_levelList() {
        return grnd_levelList;
    }

    public static void setGrnd_levelList(ArrayList<Double> grnd_levelList) {
        WeatherDownloadModel.grnd_levelList = grnd_levelList;
    }

    public static ArrayList<Double> getHumidityList() {
        return humidityList;
    }

    public static void setHumidityList(ArrayList<Double> humidityList) {
        WeatherDownloadModel.humidityList = humidityList;
    }

    public static ArrayList<Double> getTemp_kfList() {
        return temp_kfList;
    }

    public static void setTemp_kfList(ArrayList<Double> temp_kfList) {
        WeatherDownloadModel.temp_kfList = temp_kfList;
    }

    public static ArrayList<String> getWeather_mainList() {
        return weather_mainList;
    }

    public static void setWeather_mainList(ArrayList<String> weather_mainList) {
        WeatherDownloadModel.weather_mainList = weather_mainList;
    }

    public static ArrayList<String> getWeather_descList() {
        return weather_descList;
    }

    public static void setWeather_descList(ArrayList<String> weather_descList) {
        WeatherDownloadModel.weather_descList = weather_descList;
    }

    public static ArrayList<Double> getCloudsList() {
        return cloudsList;
    }

    public static void setCloudsList(ArrayList<Double> cloudsList) {
        WeatherDownloadModel.cloudsList = cloudsList;
    }

    public static ArrayList<Double> getWind_speedList() {
        return wind_speedList;
    }

    public static void setWind_speedList(ArrayList<Double> wind_speedList) {
        WeatherDownloadModel.wind_speedList = wind_speedList;
    }

    public static ArrayList<Double> getWind_degList() {
        return wind_degList;
    }

    public static void setWind_degList(ArrayList<Double> wind_degList) {
        WeatherDownloadModel.wind_degList = wind_degList;
    }

    public static ArrayList<ArrayList> getCityList() {
        return cityList;
    }

    public static void setCityList(ArrayList<ArrayList> cityList) {
        WeatherDownloadModel.cityList = cityList;
    }

}