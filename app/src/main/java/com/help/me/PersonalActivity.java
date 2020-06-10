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
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.Manifest;
import android.content.pm.PackageManager;

public class PersonalActivity extends Activity {
	
	
	private HashMap<String, Object> users = new HashMap<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private TextView textview2;
	private TextView textview1;
	private EditText edittext1;
	private TextView textview3;
	private TextView textview4;
	private EditText edittext2;
	private TextView textview5;
	private TextView textview6;
	private EditText edittext3;
	private Button button1;
	private Button button2;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.personal);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			|| checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview1 = (TextView) findViewById(R.id.textview1);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		textview3 = (TextView) findViewById(R.id.textview3);
		textview4 = (TextView) findViewById(R.id.textview4);
		edittext2 = (EditText) findViewById(R.id.edittext2);
		textview5 = (TextView) findViewById(R.id.textview5);
		textview6 = (TextView) findViewById(R.id.textview6);
		edittext3 = (EditText) findViewById(R.id.edittext3);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				edittext1.setVisibility(View.VISIBLE);
				edittext2.setVisibility(View.VISIBLE);
				edittext3.setVisibility(View.VISIBLE);
				textview1.setVisibility(View.GONE);
				textview4.setVisibility(View.GONE);
				textview6.setVisibility(View.GONE);
				button2.setVisibility(View.VISIBLE);
				button1.setVisibility(View.GONE);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				users = new HashMap<>();
				users.put("name", edittext1.getText().toString());
				users.put("phone", edittext2.getText().toString());
				users.put("email", edittext3.getText().toString());
				FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/inf.ks", new Gson().toJson(users));
				edittext1.setVisibility(View.GONE);
				edittext2.setVisibility(View.GONE);
				edittext3.setVisibility(View.GONE);
				textview1.setVisibility(View.VISIBLE);
				textview4.setVisibility(View.VISIBLE);
				textview6.setVisibility(View.VISIBLE);
				button2.setVisibility(View.GONE);
				button1.setVisibility(View.VISIBLE);
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.help.me/inf.ks")) {
					users = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/inf.ks"), new TypeToken<HashMap<String, Object>>(){}.getType());
					textview1.setText(users.get("name").toString());
					textview4.setText(users.get("phone").toString());
					textview6.setText(users.get("email").toString());
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Пожалуйста заполните данные о себе");
				}
			}
		});
	}
	private void initializeLogic() {
		edittext1.setVisibility(View.GONE);
		edittext2.setVisibility(View.GONE);
		edittext3.setVisibility(View.GONE);
		button2.setVisibility(View.GONE);
		if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.help.me/inf.ks")) {
			users = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/inf.ks"), new TypeToken<HashMap<String, Object>>(){}.getType());
			textview1.setText(users.get("name").toString());
			textview4.setText(users.get("phone").toString());
			textview6.setText(users.get("email").toString());
		}
		else {
			SketchwareUtil.showMessage(getApplicationContext(), "Пожалуйста заполните данные о себе");
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
