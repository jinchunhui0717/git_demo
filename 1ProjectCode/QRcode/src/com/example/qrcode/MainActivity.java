package com.example.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.cordova.DroidGap;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends DroidGap {

	private String localVersion;
	private UpdataInfo info;
	private final int UPDATA_NONEED = 0;
	private final int UPDATA_CLIENT = 1;
	private final int GET_UNDATAINFO_ERROR = 2;
	private final int SDCARD_NOMOUNTED = 3;
	private final int DOWN_ERROR = 4;
	private boolean isExit;
	private WebView webView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.setFullScreen();//全屏显示这行需要加在super.loadUrl之前
		super.setIntegerProperty("splashscreen", R.drawable.splash);
		super.loadUrl("file:///android_asset/www/index.html", 3000);// 3s后splash关闭

		// 获取phonegap的webview
		webView = this.appView;
		WebSettings webseting = webView.getSettings();
		String appCachePath = this.getContext().getDir("webview", Context.MODE_PRIVATE).getPath()+"/Application Cache"; // Set path to appcache
		Toast.makeText(getApplicationContext(), appCachePath,
				Toast.LENGTH_LONG).show();
		webseting.setAppCachePath(appCachePath);
		webseting.setAllowFileAccess(true); // Let browser write files - doesn't work without this
		webseting.setAppCacheEnabled(true); // Enable app cache
        webseting.setJavaScriptEnabled(true);	
		webView.addJavascriptInterface(new JSHook(), "rafael");
		//WebView.setWebContentsDebuggingEnabled(true);
	}

	public final class JSHook {
		@JavascriptInterface
		public void clearAppCache() {
			File file =new File(MainActivity.this.getContext().getDir("webview", Context.MODE_PRIVATE).getPath()+"/Cache");
			deleteFile(file);
			
			File file2 =new File(MainActivity.this.getContext().getDir("webview", Context.MODE_PRIVATE).getPath()+"/Application Cache");
			deleteFile(file2);
		}
		
		public void deleteFile(File file) { 
	        if(file.exists()) { 
	            if(file.isFile()) { 
	                file.delete(); 
	            }else if(file.isDirectory()) { 
	                File files[] = file.listFiles(); 
	                for(int i = 0; i < files.length; i++) { 
	                    deleteFile(files[i]); 
	                } 
	            } 
	            file.delete(); 
	        }else{ 
	            Log.e("delete===","delete file no exists "+ file.getAbsolutePath()); 
	        } 
	    } 

		
		
		@JavascriptInterface
		public int getAPNType() {
			return NetWorkUtils.getAPNType(MainActivity.this.getContext());
		}
		
		@JavascriptInterface
		public boolean isNetworkConnected() {
			return NetWorkUtils.isNetworkConnected(MainActivity.this.getContext());
		}
		
		
		
		@JavascriptInterface
		public String GetThumbnailBase64(String base64Data) {
			Bitmap bitmap = base64ToBitmap( base64Data);
			if (bitmap!=null) {
				return bitmapToBase64(getImageThumbnail(bitmap));
			}else {
				
				return "";
			}
		}
		/**
	     * 获取缩略图
	     * @param imagePath:文件路径
	     * @param width:缩略图宽度
	     * @param height:缩略图高度
	     * @return
	     */
	    private  Bitmap getImageThumbnail(Bitmap bitmap) {
	    	return ThumbnailUtils.extractThumbnail(bitmap, 60, 60);
	    }
	    private Bitmap base64ToBitmap(String base64Data) {  
	        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);  
	        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);  
	    }  
	    /** 
	     * bitmap转为base64 
	     * @param bitmap 
	     * @return 
	     */  
	    private  String bitmapToBase64(Bitmap bitmap) {  
	    	
	        String result = null;  
	        ByteArrayOutputStream baos = null;  
	        try {  
	            if (bitmap != null) {  
	                baos = new ByteArrayOutputStream();  
	                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
	      
	                baos.flush();  
	                baos.close();  
	      
	                byte[] bitmapBytes = baos.toByteArray();  
	                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if (baos != null) {  
	                    baos.flush();  
	                    baos.close();  
	                }  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	   
	    
	    
	    
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// UpdateCheck();
	}

	public void setFullScreen() {
		getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);// 清除FLAG
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	public void UpdateCheck() {
		try {
			localVersion = getVersionName();
			CheckVersionTask cv = new CheckVersionTask();
			new Thread(cv).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 获取已安装apk的版本
	 */
	private String getVersionName() throws Exception {
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),
				0);
		return packInfo.versionName;
	}

	/*
	 * 从服务器获取xml解析并进行比对版本号
	 */
	public class CheckVersionTask implements Runnable {

		public void run() {
			try {
				// 从资源文件获取服务器 地址
				String path = getResources().getString(R.string.serverurl);
				// 包装成url的对象
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				InputStream is = conn.getInputStream();
				info = UpdataInfoParser.getUpdataInfo(is);

				if (info.getVersion().equals(localVersion)) {
					Log.i(TAG, "版本号相同无需升级");
					// LoginMain();
				} else {
					Log.i(TAG, "版本号不同 ,提示用户升级 ");
					Message msg = new Message();
					msg.what = UPDATA_CLIENT;
					handler.sendMessage(msg);
				}
			} catch (Exception e) {
				// 待处理
				Message msg = new Message();
				msg.what = GET_UNDATAINFO_ERROR;
				handler.sendMessage(msg);
				e.printStackTrace();
			}
		}
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case UPDATA_NONEED:
				Toast.makeText(getApplicationContext(), "不需要更新",
						Toast.LENGTH_SHORT).show();
			case UPDATA_CLIENT:
				// 对话框通知用户升级程序
				showUpdataDialog();
				break;
			case GET_UNDATAINFO_ERROR:
				// 服务器超时
				Toast.makeText(getApplicationContext(), "获取服务器更新信息失败", 1)
						.show();
				break;
			case DOWN_ERROR:
				// 下载apk失败
				Toast.makeText(getApplicationContext(), "下载新版本失败", 1).show();
				break;
			}
		}
	};

	/*
	 * 
	 * 弹出对话框通知用户更新程序
	 * 
	 * 弹出对话框的步骤： 1.创建alertDialog的builder. 2.要给builder设置属性, 对话框的内容,样式,按钮
	 * 3.通过builder 创建一个对话框 4.对话框show()出来
	 */
	protected void showUpdataDialog() {
		AlertDialog.Builder builer = new Builder(this);
		builer.setTitle("版本升级");
		builer.setMessage(info.getDescription());
		// 当点确定按钮时从服务器上下载 新的apk 然后安装 װ
		builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "下载apk,更新");
				downLoadApk();
			}
		});
		builer.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// do sth
			}
		});
		AlertDialog dialog = builer.create();
		dialog.show();
	}

	/*
	 * 从服务器中下载APK
	 */
	protected void downLoadApk() {
		final ProgressDialog pd; // 进度条对话框
		pd = new ProgressDialog(this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("正在下载更新,请稍后...");
		pd.show();
		new Thread() {
			@Override
			public void run() {
				try {
					File file = DownLoadManager.getFileFromServer(
							info.getUrl(), pd);
					sleep(3000);
					installApk(file);
					pd.dismiss(); // 结束掉进度条对话框
				} catch (Exception e) {
					Message msg = new Message();
					msg.what = DOWN_ERROR;
					handler.sendMessage(msg);
					e.printStackTrace();
				}
			}
		}.start();
	}

	/*
	 * 安装
	 */
	protected void installApk(File file) {
		Intent intent = new Intent();
		// 执行动作
		intent.setAction(Intent.ACTION_VIEW);
		// 执行的数据类型
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

	// 重写返回按钮
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {
			exit();
		}
		return super.dispatchKeyEvent(event);
	}

	// 再按一次退出程序,退出后需要重新登录！
	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序！",
					Toast.LENGTH_SHORT).show();
			mHandler.sendEmptyMessageDelayed(0, 2000); // 超过两秒的两次返回，需要重新计算。
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			System.exit(0);
		}
	}

	// 超过两秒的两次返回，需要重新计算。
	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			isExit = false;
		}
	};

}
