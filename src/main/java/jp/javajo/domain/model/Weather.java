package jp.javajo.domain.model;

import lombok.Data;

import java.util.List;

/**
 * Created by user on 2016/06/24.
 */
@Data
public class Weather {

    String title;
    List<Forecasts> forecasts;
}
