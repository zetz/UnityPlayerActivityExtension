
package com.UnityPlayerActivityExtension;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;

public class UnityPlayerActivityExtension extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        TextView  tv = new TextView(this);
        tv.setText("Hello World!");
        setContentView(tv);
    }
}
