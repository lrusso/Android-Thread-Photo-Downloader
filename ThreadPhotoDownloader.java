package com.website;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ThreadPhotoDownloader extends AsyncTask<String, Void, Bitmap>
	{
	String user;
    ImageView myImage;
    
    public ThreadPhotoDownloader(String user2, ImageView myImage2)
    	{
    	user = user2;
    	myImage = myImage2;
    	}
    
    public void onPostExecute(Bitmap result)
    	{
    	try
    		{
    	    AsyncHttpClient client = new AsyncHttpClient();
    	    client.addHeader("Referer", "http://www.website.com");
    	    client.setTimeout(5000);

    	    RequestParams params = new RequestParams();
    	    params.put("Referer", "http://www.website.com");
    	    

    	    client.get("http://www.website.com/" + user + "/" + user + ".jpg", params, new TextHttpResponseHandler()
    	    	{
    			@Override public void onSuccess(int arg0, cz.msebera.android.httpclient.Header[] arg1, byte[] arg2)
    				{
    	            Bitmap a = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
    	            if (a!=null)
    	            	{
    	            	if (a.getWidth()>10)
    	            		{
    	                	myImage.setImageBitmap(getRoundedCornerBitmap(a,10));
    	            	    }
    	            	}
    				}

    			@Override public void onSuccess(int arg0, cz.msebera.android.httpclient.Header[] arg1, String arg2)
    				{
    	    		}	

    			@Override public void onFailure(int arg0, cz.msebera.android.httpclient.Header[] arg1, String arg2, Throwable arg3)
    				{
    				}
    	    	});
    		}
			catch (RuntimeException e)
			{
			}
			catch(OutOfMemoryError e)
			{
			}
			catch (Exception e)
			{
			}
	    
	    /*
    	try
    		{
        	if (result!=null)
    			{
        		if (result.getWidth()>10)
        			{
        			myImage.setImageBitmap(result);
        			}
    			}
    		}
			catch(NullPointerException e)
			{
			}
			catch(OutOfMemoryError e)
			{
			}
			catch(Exception e)
			{
			}
		*/
    	}
    
	public Bitmap doInBackground(String... urls)
    	{
	    /*
	    Bitmap a = null;
    	try
    		{
			HttpURLConnection connection = (HttpURLConnection)new URL("http://www.website.com/" + user + "/" + user + ".jpg").openConnection();
    	    connection.setConnectTimeout(5000);
    	    connection.setReadTimeout(5000);
			connection.setRequestProperty("User-agent","Mozilla/4.0");
            connection.setRequestProperty("Referer", "http://www.website.com/");
    	    
    	    connection.connect();
    	    InputStream input = connection.getInputStream();
    	    if (connection.getResponseCode()!=-1)
    	    	{
        	    a = BitmapFactory.decodeStream(input);
        	    if (a!=null)
        	    	{
        	    	if (a.getWidth()>10)
        	    		{
        	    		connection.disconnect();
                	    return getRoundedCornerBitmap(a,10);
        	    		}
        	    		else
        	    		{
        	    		return null;
        	    		}
        	    	}
    	    	}
    		}
    		catch (MalformedURLException e)
    		{
    		return null;
    		}
    		catch (IOException e)
    		{
    		return null;
    		}
    		catch (RuntimeException e)
    		{
    		return null;
    		}
    		catch(OutOfMemoryError e)
    		{
    		return null;
    		}
    		catch (Exception e)
    		{
    		return null;
    		}
	    */
    	return null;
    	}
	
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels)
		{
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final int color = 0xff424242;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	    final float roundPx = pixels;

	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);

	    return output;
		}
	}