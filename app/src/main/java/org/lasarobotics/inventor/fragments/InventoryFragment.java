package org.lasarobotics.inventor.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.lasarobotics.inventor.R;
import org.lasarobotics.inventor.util.Constants;
import org.lasarobotics.inventor.util.Item;
import org.lasarobotics.inventor.util.Util;
import org.lasarobotics.inventor.views.ItemAdapter;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InventoryFragment extends Fragment {
    ListView l;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();
        l = (ListView)getView().findViewById(R.id.list);
        GetItems g = new GetItems();
        g.execute(Constants.ALL_ITEMS);
    }
    public class GetItems extends AsyncTask<String, String, ArrayList<Item>> {

        @Override
        protected ArrayList<Item> doInBackground(String... requestedurl) {
            try{
                URL url = new URL(requestedurl[0]);
                URLConnection conn = url.openConnection();

                HttpURLConnection httpConn = (HttpURLConnection) conn;
                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                InputStream is = httpConn.getInputStream();
                String parsedString = Util.convertinputStreamToString(is);

                Type listOfTestObject = new TypeToken<List<Item>>() {
                }.getType();

                return new Gson().fromJson(parsedString,listOfTestObject);
            } catch (Exception e) {
                Log.w("MyApp", "Download Exception : " + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Item> result) {
            // do something with result
            ItemAdapter adapter = new ItemAdapter(getActivity(),R.layout.row_item,result);
            l.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}
