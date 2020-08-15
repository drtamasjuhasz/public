package getweather;

import java.util.TreeMap;

public class WeatherDownloadMain {

    public static TreeMap<String, Object> cityMap = new TreeMap<>();

    public static void main(String[] args) {
        System.out.println("TESZTELÉS:");
        cityMap.put("Wellington,NZ", WeatherDownloadModel.weatherdownloadmodel("Wellington,NZ"));
        System.out.println("NZ" + WeatherDownloadModel.getCityList());

        cityMap.put("Vienna,AT", WeatherDownloadModel.weatherdownloadmodel("Vienna,AT"));
        System.out.println("AT" + WeatherDownloadModel.getCityList());

        cityMap.put("Helsinki,FIN", WeatherDownloadModel.weatherdownloadmodel("Helsinki,FIN"));
        System.out.println("FIN" + WeatherDownloadModel.getCityList());
        System.out.println(WeatherDownloadModel.getCityList().get(0).get(0));
        System.out.println(WeatherDownloadModel.getCityList().get(1).get(0));
        System.out.println(WeatherDownloadModel.getTimeList().get(0));

        cityMap.put("Budapest,HUN", WeatherDownloadModel.weatherdownloadmodel("Budapest,HUN"));
        System.out.println("HUN" + WeatherDownloadModel.getCityList());
        System.out.println("");
        System.out.println(WeatherDownloadModel.getCityList().get(0).get(0) + "-kor a hőmérséklet és az időjárás Budapesten:");
        System.out.println(WeatherDownloadModel.getCityList().get(1).get(0) + " C");
        System.out.println(WeatherDownloadModel.getCityList().get(10).get(0));
        System.out.println("");
        System.out.println(WeatherDownloadModel.getCityList().get(0).get(17) + "-kor a hőmérséklet és az időjárás Budapesten:");
        System.out.println(WeatherDownloadModel.getCityList().get(1).get(17) + " C");
        System.out.println(WeatherDownloadModel.getCityList().get(10).get(17));
        System.out.println("");

        System.out.println(cityMap);

        System.out.println("");

        System.out.println(cityMap.get("Helsinki,FIN"));
        System.out.println(cityMap.get("Wellington,NZ"));
    }
}
