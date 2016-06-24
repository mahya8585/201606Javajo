package jp.javajo.controller;

import jp.javajo.domain.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 接続テストクラス
 * Created by maaya
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    //天気API
    private final String tokyoUrl = "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";
    private final String osakaUrl = "http://weather.livedoor.com/forecast/webservice/json/v1?city=270000";

    //APIテンプレート
    private final RestTemplate rt = new RestTemplate();

    /**
     * 天気情報返却
     */
    @RequestMapping(method = RequestMethod.GET)
    String weather(@RequestParam String area) {
        logger.debug("天気メソッド");

        Weather weather;
        if (area.equals("tokyo")) {
            weather = rt.getForObject(tokyoUrl, Weather.class);
        } else if (area.equals("osaka")) {
            weather = rt.getForObject(osakaUrl, Weather.class);
        } else {
            return "その地域には対応してません★「tokyo」か「osaka」をパラメータに渡してね";
        }

        //リクエスト送信
        String weatherInfo = "【" + weather.getTitle() + "】：" + weather.getDescription().getText();

        return weatherInfo;
    }

}
