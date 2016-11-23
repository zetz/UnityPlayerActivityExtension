
package com.UnityPlayerActivityExtension;

import com.unity3d.player.*;
import android.app.*;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import java.io.*;

public class UnityPlayerActivityExtension extends UnityPlayerActivity
{
	public static final int SELECT_PICTURE_ID = 1000;

	String receiveGameObjectName;
	String errorCallbackMethod;
	String endSelectPictureMethod;

	byte[] selectedPictureBytes;

	//
	public void Init(String receiveObject, String errorCallback) {
		receiveGameObjectName = receiveObject;
		errorCallbackMethod = errorCallback;
	}

	// 
	public void startSelectPicture(String callbackMethod)
	{
		endSelectPictureMethod = callbackMethod;

		Intent intent = new Intent();
		Uri uri = Uri.parse("content://media/internal/image/media");
		
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		intent.setType("image/jpeg");
		Intent chooser = Intent.createChooser(intent, "Select Picture");
		this.startActivityForResult(chooser, SELECT_PICTURE_ID);
	}

	public byte[] getSelectedPictureBytes()
	{
		return selectedPictureBytes;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		receiveGameObjectName = "EventSystem";
		errorCallbackMethod = "OnActivityError";
		endSelectPictureMethod = "";
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != RESULT_OK){
			UnitySendMessage(errorCallbackMethod, requestCode + ", " + resultCode);
			return;
		}

		switch (requestCode) {
		case SELECT_PICTURE_ID:
			Uri uri = data.getData();
			selectedPictureBytes = null;
			try {
				selectedPictureBytes = readBytes(uri);
				UnitySendMessage(endSelectPictureMethod, "byte[] getSelectedPictureBytes()");
			} catch (FileNotFoundException e) {
				UnitySendMessage(errorCallbackMethod, e.getMessage());
			} catch (IOException e) {
				UnitySendMessage(errorCallbackMethod, e.getMessage());
			}
			break;
		default:
			break;
		}
	}
	

	void UnitySendMessage(String method, String message) {
		UnityPlayer.UnitySendMessage(receiveGameObjectName, method, message);
	}

	byte[] readBytes(Uri uri) throws FileNotFoundException, IOException {
		InputStream inputStream = getContentResolver().openInputStream(uri);
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		final int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}
		return byteBuffer.toByteArray();
	}
}
