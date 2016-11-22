
package com.UnityPlayerActivityExtension;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;

public class UnityPlayerActivityExtension extends Activity
{
    /** 작업이 처음 만들어질 때 호출됩니다. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /* TextView를 만들고 텍스트를 "Hello world"로 설정합니다.*/
        TextView  tv = new TextView(this);
        tv.setText("Hello World!");
        setContentView(tv);
    }
}
