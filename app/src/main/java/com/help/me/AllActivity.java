package com.help.me;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.Manifest;
import android.content.pm.PackageManager;

public class AllActivity extends Activity {
	
	
	private ArrayList<HashMap<String, Object>> status = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> date = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> times = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> number = new ArrayList<>();
	
	private TextView textview1;
	private ListView listview1;
	
	private AlertDialog.Builder dialogue;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.all);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
			}
			else {
				initializeLogic();
			}
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		textview1 = (TextView) findViewById(R.id.textview1);
		listview1 = (ListView) findViewById(R.id.listview1);
		dialogue = new AlertDialog.Builder(this);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
			}
		});
	}
	private void initializeLogic() {
		status = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/status.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		date = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/date.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		times = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/times.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		number = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/number.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		if (times.size() == 0) {
			listview1.setVisibility(View.GONE);
		}
		else {
			listview1.setAdapter(new Listview1Adapter(date));
			textview1.setText("Всего отправлено: ".concat(String.valueOf((long)(number.size()))));
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _view, ViewGroup _viewGroup) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _view;
			if (_v == null) {
				_v = _inflater.inflate(R.layout.item, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
			final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
			final LinearLayout linear6 = (LinearLayout) _v.findViewById(R.id.linear6);
			final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
			final LinearLayout linear4 = (LinearLayout) _v.findViewById(R.id.linear4);
			final LinearLayout linear5 = (LinearLayout) _v.findViewById(R.id.linear5);
			final TextView textview9 = (TextView) _v.findViewById(R.id.textview9);
			final TextView id = (TextView) _v.findViewById(R.id.id);
			final TextView stat = (TextView) _v.findViewById(R.id.stat);
			final TextView status = (TextView) _v.findViewById(R.id.status);
			final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
			final TextView date = (TextView) _v.findViewById(R.id.date);
			final TextView textview7 = (TextView) _v.findViewById(R.id.textview7);
			final TextView time = (TextView) _v.findViewById(R.id.time);
			
			id.setText(String.valueOf((long)(_position + 1)));
			date.setText(_data.get((int)_position).get("date").toString());
			time.setText(times.get((int)_position).get("times").toString());
			status.setText("завершен");
			status.setTextColor(0xFF4CAF50);
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					dialogue.setTitle("Вы были");
					dialogue.setMessage("Вы находились в ".concat(number.get((int)_position).get("number").toString()));
					dialogue.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					dialogue.create().show();
				}
			});
			
			return _v;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
