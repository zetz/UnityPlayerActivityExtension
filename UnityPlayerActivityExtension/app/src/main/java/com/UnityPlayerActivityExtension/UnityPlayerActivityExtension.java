
package com.UnityPlayerActivityExtension;

import com.unity3d.player.*;
import android.os.Bundle;
import android.content.Intent;

public class UnityPlayerActivityExtension extends UnityPlayerActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		UnityPlayer.UnitySendMessage("EventSystem", "CallFromNative", "UnityPlayerActivityExtension Created.");
    }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		UnityPlayer.UnitySendMessage("EventSystem", "CallActivityResult", requestCode + ", " + resultCode);
	}

	public String Hello(String msg)
	{
		return "Hello " + msg;
	}

	public static String HelloStatic(String msg)
	{
		return "Hello Static" + msg;
	}
}
