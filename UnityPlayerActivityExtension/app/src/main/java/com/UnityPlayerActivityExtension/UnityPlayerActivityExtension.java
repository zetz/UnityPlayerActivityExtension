
package com.UnityPlayerActivityExtension;

import com.unity3d.player.*;
import android.os.Bundle;

public class UnityPlayerActivityExtension extends UnityPlayerActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
