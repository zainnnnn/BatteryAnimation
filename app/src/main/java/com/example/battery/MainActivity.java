package com.example.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {
  ImageView iv,iv2;
  TextView tv;
 BatteryReceiver batteryReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.iv);
       batteryReceiver=new BatteryReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(batteryReceiver);
        super.onStop();
    }

    class BatteryReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
              int level =intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
              int chrgplug=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
               boolean Charge = chrgplug == BatteryManager.BATTERY_PLUGGED_USB ||chrgplug == BatteryManager.BATTERY_PLUGGED_AC;
                if(Charge==true){
            iv.setImageDrawable(getDrawable(R.drawable.chrging));}
               else if(level >= 80 && level<=100){
                   iv.setImageDrawable(getDrawable(R.drawable.ful));
               } else if(level >=41 && level<=79){
                   iv.setImageDrawable(getDrawable(R.drawable.midlplus));
               }else if(level >=16 && level<=40){
                   iv.setImageDrawable(getDrawable(R.drawable.hlf));
               }else if(level >=5 && level <=15){
                  iv.setImageDrawable(getDrawable(R.drawable.low));
               }else  if(level >0 && level <=4){
                   iv.setImageDrawable(getDrawable(R.drawable.empty));
            }
               }

            }
          
        }


