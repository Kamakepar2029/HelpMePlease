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
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.widget.ImageView;
import android.widget.EditText;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.ClipData;
import android.view.View;
import android.content.ClipboardManager;
import com.google.gson.Gson;
import java.text.DecimalFormat;
import com.google.gson.reflect.TypeToken;
import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends Activity {
	
	public final int REQ_CD_IMAGES = 101;
	private Timer _timer = new Timer();
	
	private HashMap<String, Object> parlams = new HashMap<>();
	private String latitude = "";
	private String apt = "";
	private HashMap<String, Object> pg = new HashMap<>();
	private String ap = "";
	private String lon = "";
	private String windy = "";
	private HashMap<String, Object> policy = new HashMap<>();
	private HashMap<String, Object> maping = new HashMap<>();
	private String addresse = "";
	private double send = 0;
	private String filepa = "";
	private String dates = "";
	private String timing = "";
	private String name = "";
	private String phones = "";
	private String email = "";
	private HashMap<String, Object> headers = new HashMap<>();
	private HashMap<String, Object> users = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> number = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> status = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> date = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> times = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	
	private LinearLayout linear2;
	private Button button1;
	private TextView textview1;
	private WebView webview1;
	private LinearLayout linear1;
	private MapView mapview1;
	private GoogleMapController _mapview1_controller;
	private ImageView imageview2;
	private ImageView imageview3;
	private EditText edittext1;
	private ImageView imageview1;
	
	private LocationManager location;
	private LocationListener _location_location_listener;
	private RequestNetwork request;
	private RequestNetwork.RequestListener _request_request_listener;
	private RequestNetwork locatios;
	private RequestNetwork.RequestListener _locatios_request_listener;
	private LocationManager lman;
	private LocationListener _lman_location_listener;
	private AlertDialog.Builder ask;
	private RequestNetwork requests;
	private RequestNetwork.RequestListener _requests_request_listener;
	private Intent phone = new Intent();
	private TimerTask time;
	private Calendar datey = Calendar.getInstance();
	private AlertDialog.Builder phoned;
	private Intent images = new Intent(Intent.ACTION_GET_CONTENT);
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		if (Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			|| checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
			|| checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
				requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
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
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		button1 = (Button) findViewById(R.id.button1);
		textview1 = (TextView) findViewById(R.id.textview1);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		mapview1 = (MapView) findViewById(R.id.mapview1);
		mapview1.onCreate(_savedInstanceState);
		
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		request = new RequestNetwork(this);
		locatios = new RequestNetwork(this);
		lman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		ask = new AlertDialog.Builder(this);
		requests = new RequestNetwork(this);
		phoned = new AlertDialog.Builder(this);
		images.setType("image/*");
		images.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		net = new RequestNetwork(this);
		
		linear2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_prompt(phoned);
			}
		});
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		_mapview1_controller = new GoogleMapController(mapview1, new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap _googleMap) {
				_mapview1_controller.setGoogleMap(_googleMap);
				_mapview1_controller.zoomTo(60);
			}
		});
		
		_mapview1_controller.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker _param1) {
				final String _id = _param1.getTag().toString();
				_mapview1_controller.setMarkerIcon(_id, R.drawable.ic_pin_drop_black);
				if (Build.VERSION.SDK_INT >= 23) {if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
						lman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, _lman_location_listener);
					}
				}
				else {
					lman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, _lman_location_listener);
				}
				return false;
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				phone.setClass(getApplicationContext(), AllActivity.class);
				startActivity(phone);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				phone.setClass(getApplicationContext(), PersonalActivity.class);
				startActivity(phone);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				phone.setClass(getApplicationContext(), UploadActivity.class);
				startActivity(phone);
			}
		});
		
		_location_location_listener = new LocationListener() {
			@Override
			public void onLocationChanged(Location _param1) {
				final double _lat = _param1.getLatitude();
				final double _lng = _param1.getLongitude();
				final double _acc = _param1.getAccuracy();
				_mapview1_controller.addMarker("fakt", _lat, _lng);
				_mapview1_controller.setMarkerInfo("fakt", "Ваше местоположение по спутнику", "Спутник");
				_mapview1_controller.setMarkerColor("fakt", BitmapDescriptorFactory.HUE_GREEN, _acc);
				lon = String.valueOf(_lng);
				ap = String.valueOf(_lat);
				requests.startRequestNetwork(RequestNetworkController.GET, "https://idealpoisedtechnology.kamakepar.repl.co/?lat=".concat(lon.concat("&lon=".concat(ap))), "B", _requests_request_listener);
				SketchwareUtil.showMessage(getApplicationContext(), "GPS");
			}
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {}
			@Override
			public void onProviderEnabled(String provider) {}
			@Override
			public void onProviderDisabled(String provider) {}
		};
		
		_request_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				SketchwareUtil.showMessage(getApplicationContext(), _response);
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", _response));
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_locatios_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				parlams = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				latitude = parlams.get("longitude").toString();
				apt = parlams.get("latitude").toString();
				_mapview1_controller.addMarker("location", Double.parseDouble(parlams.get("latitude").toString()), Double.parseDouble(parlams.get("longitude").toString()));
				_mapview1_controller.setMarkerInfo("location", "Ваше местоположение", "Интернет");
				_mapview1_controller.setMarkerColor("location", BitmapDescriptorFactory.HUE_ORANGE, 326);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_lman_location_listener = new LocationListener() {
			@Override
			public void onLocationChanged(Location _param1) {
				final double _lat = _param1.getLatitude();
				final double _lng = _param1.getLongitude();
				final double _acc = _param1.getAccuracy();
				_mapview1_controller.addMarker("nt", _lat, _lng);
				_mapview1_controller.setMarkerInfo("nt", "Ваше местоположение", "По сети");
				_mapview1_controller.setMarkerColor("nt", BitmapDescriptorFactory.HUE_GREEN, _acc);
				ap = String.valueOf(_lat);
				lon = String.valueOf(_lng);
				requests.startRequestNetwork(RequestNetworkController.GET, "https://idealpoisedtechnology.kamakepar.repl.co/?lat=".concat(String.valueOf(_lat).concat("&lon=".concat(String.valueOf(_lng)))), "B", _requests_request_listener);
				SketchwareUtil.showMessage(getApplicationContext(), "");
				_mapview1_controller.moveCamera(_lat, _lng);
			}
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {}
			@Override
			public void onProviderEnabled(String provider) {}
			@Override
			public void onProviderDisabled(String provider) {}
		};
		
		_requests_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				textview1.setText(_response);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _response = _param2;
				filepa = _response;
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	private void initializeLogic() {
		datey = Calendar.getInstance();
		dates = new SimpleDateFormat("MM.dd.YYYY").format(datey.getTime());
		timing = new SimpleDateFormat("hh:mm:ss").format(datey.getTime());
		locatios.startRequestNetwork(RequestNetworkController.GET, "https://api.ipgeolocation.io/ipgeo?apiKey=7634e348c5174d09a516c137bcc6c412", "A", _locatios_request_listener);
		if (Build.VERSION.SDK_INT >= 23) {if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
				location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, _location_location_listener);
			}
		}
		else {
			location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, _location_location_listener);
		}
		if (Build.VERSION.SDK_INT >= 23) {if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
				lman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3, 50, _lman_location_listener);
			}
		}
		else {
			lman.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3, 50, _lman_location_listener);
		}
		webview1.setVisibility(View.GONE);
		_mapview1_controller.zoomIn();
		send = 1;
		if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.help.me/times.ks")) {
			number = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/number.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			status = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/status.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			date = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/date.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			times = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/times.ks"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		}
		else {
			imageview2.setVisibility(View.GONE);
		}
		if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.help.me/inf.ks")) {
			users = new HashMap<>();
			users = new Gson().fromJson(FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/inf.ks"), new TypeToken<HashMap<String, Object>>(){}.getType());
			name = users.get("name").toString();
			phones = users.get("phone").toString();
			email = users.get("email").toString();
		}
		else {
			name = "";
			phones = "";
			email = "";
			SketchwareUtil.showMessage(getApplicationContext(), "Пожалуйста заполните данные о себе");
		}
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_IMAGES:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				headers = new HashMap<>();
				headers.put("filepath", _filePath.get((int)(0)));
				headers.put("filename", Uri.parse(_filePath.get((int)(0))).getLastPathSegment());
				headers.put("upload_files", _filePath.get((int)(0)));
				net.setHeaders(headers);
				net.startRequestNetwork(RequestNetworkController.POST, "https://upload--konsttlt86.repl.co/up.php", "U", _net_request_listener);
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		SketchwareUtil.showMessage(getApplicationContext(), " ");
		if (!FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/web.txt").equals("")) {
			filepa = FileUtil.readFile("/storage/emulated/0/Android/data/com.help.me/web.txt");
		}
		else {
			filepa = "";
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapview1.onDestroy();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		mapview1.onStart();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mapview1.onPause();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		mapview1.onStop();
	}
	private void _N () {
		datey = Calendar.getInstance();
		number.get((int)number.size()).put("number", String.valueOf((long)(SketchwareUtil.getRandom((int)(1000), (int)(1999)))));
		status.get((int)status.size()).put("status", "завершен");
		date.get((int)date.size()).put("date", new SimpleDateFormat("dd:mm:yy").format(datey.getTime()));
		times.get((int)times.size()).put("times", new SimpleDateFormat("hh:mm:ss").format(datey.getTime()));
		FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/number.ks", new Gson().toJson(number));
		FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/status.ks", new Gson().toJson(status));
		FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/date.ks", new Gson().toJson(date));
		FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/times.ks", new Gson().toJson(times));
	}
	
	
	private void _prompt (final AlertDialog.Builder _dialog) {
		_dialog.setTitle("Вызвать помощь");
		_dialog.setMessage("Message");
		_dialog.setPositiveButton("Вызвать скорую помощь 🚑", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_Send("s");
			}
		});
		_dialog.setNegativeButton("Вызвать полицию 🚓", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_Send("m");
			}
		});
		_dialog.setNeutralButton("Вызвать пожарных 🚒", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				_Send("p");
			}
		});
		_dialog.create().show();
	}
	
	
	private void _Send (final String _str) {
		if (filepa.equals("")) {
			if (send == 1) {
				webview1.loadUrl("https://send-server.p1eas1ng.repl.co/?apt=".concat(apt.concat("&long=".concat(latitude.concat("&ap=".concat(ap.concat("&lon=".concat(lon.concat("&address=".concat(textview1.getText().toString().concat("&c=".concat(edittext1.getText().toString().concat("&ph=".concat(phones.concat("&nm=".concat(name.concat("&ema=".concat(email.concat("&type=".concat(_str))))))))))))))))))));
				SketchwareUtil.showMessage(getApplicationContext(), "Сообщение отправлено. \nСледующее вы сможете отправить через 15 секунд.");
				button1.setBackgroundColor(0xFFE57373);
				send = 2;
				datey = Calendar.getInstance();
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("number", textview1.getText().toString());
					number.add(_item);
				}
				
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("status", "завершен");
					status.add(_item);
				}
				
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("date", dates);
					date.add(_item);
				}
				
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("times", timing);
					times.add(_item);
				}
				
				FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/number.ks", new Gson().toJson(number));
				FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/status.ks", new Gson().toJson(status));
				FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/date.ks", new Gson().toJson(date));
				FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/times.ks", new Gson().toJson(times));
			}
			else {
				SketchwareUtil.showMessage(getApplicationContext(), "Таймер запущен. Подождите 15 секунд пожалуйста. При каждом нажатии до 15 секунд таймер будет перезапущен.");
				time = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								send = 1;
								SketchwareUtil.showMessage(getApplicationContext(), "Можете повторить отправку");
								button1.setBackgroundColor(0xFFF44336);
							}
						});
					}
				};
				_timer.schedule(time, (int)(15000));
			}
		}
		else {
			webview1.loadUrl("https://send-server.p1eas1ng.repl.co/?apt=".concat(apt.concat("&long=".concat(latitude.concat("&ap=".concat(ap.concat("&lon=".concat(lon.concat("&address=".concat(textview1.getText().toString().concat("&c=".concat(edittext1.getText().toString().concat("&photo=".concat(filepa.concat("&ph=".concat(phones.concat("&nm=".concat(name.concat("&ema=".concat(email.concat("&type=".concat(_str))))))))))))))))))))));
			SketchwareUtil.showMessage(getApplicationContext(), "Сообщение отправлено. \nСледующее вы сможете отправить через 15 секунд.");
			button1.setBackgroundColor(0xFFE57373);
			send = 2;
			datey = Calendar.getInstance();
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("number", textview1.getText().toString());
				number.add(_item);
			}
			
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("status", "завершен");
				status.add(_item);
			}
			
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("date", dates);
				date.add(_item);
			}
			
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("times", timing);
				times.add(_item);
			}
			
			FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/number.ks", new Gson().toJson(number));
			FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/status.ks", new Gson().toJson(status));
			FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/date.ks", new Gson().toJson(date));
			FileUtil.writeFile("/storage/emulated/0/Android/data/com.help.me/times.ks", new Gson().toJson(times));
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
