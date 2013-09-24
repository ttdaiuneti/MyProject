package com.example.boccaccio;

import java.util.HashMap;
import java.util.Map;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.example.Entity.ENews;
import com.example.Entity.ListNewsAdapter;
import com.google.gson.Gson;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static class GsonTransformer implements Transformer {

		public <T> T transform(String url, Class<T> type, String encoding,
				byte[] data, AjaxStatus status) {
			Gson g = new Gson();
			return g.fromJson(new String(data), type);
		}
	}
	
	AQuery aq;
	//String data = "[{\"news_id\":\"176\",\"news_title\":\"new test with url\", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377633413-4049_Zodiac-signs-on-the-sky-HD-wallpaper.jpg\", \"news_content\":\"thu test with url http://eirenemobile.com/index.php thu xem sao\" },{ \"news_id\":\"174\", \"news_title\":\"Corsi Mini Nuoto 3 - 5 anni\", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377629356-mininuoto.png\", \"news_content\":\"\" },{ \"news_id\":\"173\", \"news_title\":\"Corsi Bambini 6 - 14 anni \", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377629301-bambini.png\", \"news_content\":\"\" },{ \"news_id\":\"172\", \"news_title\":\"Corsi Adulti 2013 - 2014\", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377629207-adulti.png\", \"news_content\":\"\" },{ \"news_id\":\"171\", \"news_title\":\"Corsi Baby nuoto e Gestanti 2013 - 2014\", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377629153-baby.png\", \"news_content\":\"\" },{ \"news_id\":\"169\", \"news_title\":\"Corsi Gym - Acquafitness - Nuoto Sincronizzato\", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377628981-gymeacquaenuoto.png\", \"news_content\":\"\" },{ \"news_id\":\"168\", \"news_title\":\"Corsi Idrobyke - Pallanuoto 2013 -2014\", \"news_date\":\"27/08/2013\", \"image\":\"http://eirenemobile.com/images/image/2013/08/27/1377628870-IDROBYKEECT.png\", \"news_content\":\"\" }]";
	Context context;
	ListView listView;
	//List<ENews> listNews;
	ListNewsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		aq=new AQuery(context);
		this.loadData();
		/*
		try {
			init();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//adapter = new ListNewsAdapter(this, listNews);
		//listView = (ListView) findViewById(R.id.list_news);
		//listView.setAdapter(adapter);
		//Toast.makeText(this, String.valueOf(listNews.size()),
		//Toast.LENGTH_LONG).show();
	}
	
	protected void loadData() {
		String url = "http://eirenemobile.com/index.php?r=site/service";
		GsonTransformer t = new GsonTransformer();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("api_type", "get_news");
		params.put("page", "1");
		aq.transformer(t).ajax(url, params, ENews[].class, new AjaxCallback<ENews[]>() {

			@SuppressLint("ShowToast")
			@Override
			public void callback(String url, ENews[] data, AjaxStatus status) {

				if (data != null) {
					Toast.makeText(getApplicationContext(), String.valueOf(data.length), Toast.LENGTH_LONG).show();
					adapter = new ListNewsAdapter(context, data);
					listView = (ListView) findViewById(R.id.list_news);
					listView.setAdapter(adapter);
					
					//Log.e("data from server", data.toString());

					//Gson gson = new Gson();

					//EClub eclub = gson.fromJson(data, EClub.class);

					//Log.i("eclub data", eclub.toString());
				}

			}
		});

	}
	
	/*

	public void init() throws JSONException {
		listNews = new ArrayList<ENews>();
		JSONArray jsonarray = new JSONArray(data);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);
			String news_id = String.valueOf(jsonObject.getString("news_id"));
			//Toast.makeText(this, news_id, Toast.LENGTH_LONG).show();
			String news_date = jsonObject.getString("news_date");
			//Toast.makeText(this, news_date, Toast.LENGTH_LONG).show();
			String image = jsonObject.getString("image");
			//Toast.makeText(this, image, Toast.LENGTH_LONG).show();
			ENews temp = new ENews(news_id, news_date, image);
			listNews.add(temp);
		}

	}*/

}
