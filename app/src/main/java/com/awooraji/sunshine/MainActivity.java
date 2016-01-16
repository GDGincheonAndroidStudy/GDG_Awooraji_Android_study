package com.awooraji.sunshine;

import android.os.Bundle;

// 라이브러리를 참고 자료 / 참조: http://stackoverflow.com/questions/30845033/cannot-resolve-method-addint-com-example-utkarsh-beatle-app-mainactivity-plac
// 지원하는 라이브러리 문제라는 것 / 참조: http://stackoverflow.com/questions/28849554/cannot-resolve-method-addint-new-tfragment-commit-in-android-studion-1-0
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        ArrayAdapter<String> mForecastAdapter;


        public PlaceholderFragment(){

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

            View rootView = inflater.inflate(R.layout.fragment_main, container,false);



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

}}
