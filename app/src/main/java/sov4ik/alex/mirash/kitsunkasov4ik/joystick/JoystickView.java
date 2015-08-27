package sov4ik.alex.mirash.kitsunkasov4ik.joystick;

import android.content.Context;
import android.util.AttributeSet;

import sov4ik.alex.mirash.kitsunkasov4ik.R;
import sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState;
import sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickStateListener;
import sov4ik.alex.mirash.kitsunkasov4ik.joystick.view.JoystickDirectionView;

import static sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState.DEFAULT;
import static sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState.DOWN;
import static sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState.LEFT;
import static sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState.RIGHT;
import static sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool.JoystickState.UP;
import static sov4ik.alex.mirash.kitsunkasov4ik.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class JoystickView extends JoystickDirectionView {
    private JoystickButtonController mButtonController;

    private JoystickState mState = DEFAULT;

    private JoystickStateListener mListener;

    public JoystickView(Context context) {
        this(context, null);
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_joystick, this);
        mButtonController = new JoystickButtonController(
                findViewById(R.id.joystick_button_left),
                findViewById(R.id.joystick_button_right),
                findViewById(R.id.joystick_button_up),
                findViewById(R.id.joystick_button_down)
        );
    }

    @Override
    protected void onStateDefault() {
        updateState(DEFAULT);
    }

    @Override
    protected void onDirected(float angle, float power) {
        if (angle >= 315 || angle < 45) {
            updateState(RIGHT);
        } else if (angle >= 45 && angle < 135) {
            updateState(UP);
        } else if (angle >= 135 && angle < 225) {
            updateState(LEFT);
        } else {
            updateState(DOWN);
        }
    }

    public void setStateListener(JoystickStateListener listener) {
        mListener = listener;
    }

    private void updateState(JoystickState state) {
        if (mState != state) {
            log("updateState: " + mState + " > " + state);
            mState.updateJoystick(mButtonController, false);
            mState = state;
            mState.updateJoystick(mButtonController, true);
            if (mListener != null) {
                mListener.onStateChange(state);
            }
        }
    }
}
