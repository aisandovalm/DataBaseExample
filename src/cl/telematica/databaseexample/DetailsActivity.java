package cl.telematica.databaseexample;

import java.util.ArrayList;
import java.util.List;


//import cl.telematica.databaseexample.adapters.RssAdapter;
import cl.telematica.databaseexample.adapters.TableAdapter;
import cl.telematica.databaseexample.database.DataBaseClass;
import cl.telematica.databaseexample.models.EarthQuakeDataModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
import android.os.Bundle;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;

public class DetailsActivity extends Activity {
	private DataBaseClass dbInstance;
	//private String url = null;
	
	private List<EarthQuakeDataModel> list = new ArrayList<EarthQuakeDataModel>();
	private ListView lstDetails;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		dbInstance = new DataBaseClass(this);
		
		getFromDataBase();
	}
	
	//private void getFromDataBase(String[] lst){
	private void getFromDataBase(){
		SQLiteDatabase db = dbInstance.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT * FROM alumnos LIMIT 7", null);
		

		if(c.moveToFirst()){
			do{
			   EarthQuakeDataModel model = new EarthQuakeDataModel();
			   model.title = c.getString(1);
			   model.magnitude = c.getString(2);
			   model.location = c.getString(3);
			   model.depth = c.getString(4);
			   model.latitude = c.getString(5);
			   model.longitude = c.getString(6);
			   model.dateTime = c.getString(7);
			   model.link = c.getString(8);
			   list.add(model);
			} while (c.moveToNext());
		}
		c.close();
		
		/*ArrayAdapter<String> adaptador =
			    new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, lst);*/
		TableAdapter adapter = new TableAdapter(getApplicationContext(), R.string.app_name, list);
		
		lstDetails = (ListView)findViewById(R.id.LstDetails);
		 
		lstDetails.setAdapter(adapter);
	}

}
