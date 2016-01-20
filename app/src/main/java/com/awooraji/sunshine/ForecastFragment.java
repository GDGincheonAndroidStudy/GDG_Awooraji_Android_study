package com.awooraji.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Awooraji on 2016-01-20.
 */
public class ForecastFragment extends Fragment {
    private ArrayAdapter<String> mForecastAdapter;


    public ForecastFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {

        String[] forecastArray = {
                "Today - Sunny - 88/33",
                "Tomorrow - Foggy - 70/46",
                "Weds - Cloudy - 72/63",
                "Thurs - Rainy - 64/51",
                "Fri - Foggy - 70/46",
                "Sat - Sunny - 76/68",
                "Sun - Sunny - 80/48"
        };
        List<String> weekForecast = new ArrayList<String>(
                Arrays.asList(forecastArray)
        );


            /*
            ArrayList<String> listViewValues = new ArrayList<>();
            listViewValues.add("Today");
            listViewValues.add("Tomorrow");
            listViewValues.add("Weds");
            listViewValues.add("Thurs");
            listViewValues.add("Fri");
            listViewValues.add("Sat");

            ArrayAdapter<String> Adapter;
            Adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listViewValues);

            ListView list = (ListView)findViewById(R.id.list);
            list.setAdapter(Adapter);
*/



            /*
            ArrayList<ThreeD> listViewValues = new ArrayList<ThreeD>();
            listViewValues.add(new ThreeD("row title", "row details","sad"));

            ArrayList<ThreeD> mArraylist = new ArrayList<>();
            mArraylist.add(new ThreeD("Today","Sunny","88/33"));
            mArraylist.add(new ThreeD("Tomorrow","Foggy","70/46"));
            mArraylist.add(new ThreeD("Weds","Cloudy","72/63"));
            mArraylist.add(new ThreeD("Thurs","Rainy","64/51"));
            mArraylist.add(new ThreeD("Fri","Foggy","70/46"));
            mArraylist.add(new ThreeD("Sat","Sunny","76/68"));

            ArrayAdapter<ThreeD> Adapter;
            Adapter = new ArrayAdapter<ThreeD>(this, android.R.layout.simple_list_item_1,mArraylist);

           */


        mForecastAdapter =
                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.list_item_forecast,
                        R.id.list_item_forecast_textview,
                        weekForecast
                );

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



            /*
            **
            * *
            * *
            * *
            * *
            * * rootView 를 사용한 이유가 정확히 무엇일까
            * * 위에 것은 fragment 를 사용하기 위해서 한 것 아닌가
            * *
            * *  정확한 위치를 알려주기 위해서 사용하는 것인가?
            *    실제 객체화 하기 위해서 사용
            * *  참조: http://aroundck.tistory.com/39
             */

        ListView listView = (ListView) rootView.findViewById(R.id.listView_forecast);


        listView.setAdapter(mForecastAdapter);




        return rootView;

    }


    // AsyncTask 배경스레드를 만들기 위해서
    public class FetchWeatherTask extends AsyncTask<Void, Void, Void> {

        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();

        @Override
        protected Void doInBackground(Void... params) {

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
                String apiKey = "&APPID=" + BuildConfig.OPEN_WEATHER_MAP_API_KEY;
                URL url = new URL(baseUrl.concat(apiKey));

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            return null;


        }

    }
}





/*
        // 참조: http://androidhuman.com/205
        // 참조: http://stackoverflow.com/questions/12258837/android-listview-arrayadapter-and-2-d-array
        public class ThreeD {
            private String TD_week;
            private String TD_weather;
            private String TD_temperature;


            public ThreeD(String TD_week, String TD_weather,String TD_temperature) {
                this.TD_week = TD_week;
                this.TD_weather = TD_weather;
                this.TD_temperature = TD_temperature;
            }

            public String getTD_week() {
                return TD_week;
            }
            public String getTD_weather() {
                return TD_weather;
            }
            public String getTD_temperature() {
                return TD_temperature;
            }



        }
*/
