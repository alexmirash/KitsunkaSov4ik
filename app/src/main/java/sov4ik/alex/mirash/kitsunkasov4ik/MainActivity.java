package sov4ik.alex.mirash.kitsunkasov4ik;

import android.app.Activity;
import android.os.Bundle;

import sov4ik.alex.mirash.kitsunkasov4ik.joystick.JoystickView;
import sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState;
import sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickStateListener;
import sov4ik.alex.mirash.kitsunkasov4ik.utils.SovkaUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SovkaUtils.hideActionBarTitle(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((JoystickView) findViewById(R.id.joystick)).setStateListener(new JoystickStateListener() {
            @Override
            public void onStateChange(JoystickState state) {
                if (state != JoystickState.DEFAULT) {
                    SovkaUtils.vibrate(MainActivity.this, 25);
                }
            }
        });
    }
}
