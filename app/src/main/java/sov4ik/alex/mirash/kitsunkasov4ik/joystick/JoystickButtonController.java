package sov4ik.alex.mirash.kitsunkasov4ik.joystick;

import android.view.View;

/**
 * @author Mirash
 */
public class JoystickButtonController {
    private View mLeftButton;
    private View mRightButton;
    private View mUpButton;
    private View mDownButton;

    public JoystickButtonController(View leftButton, View rightButton, View upButton, View downButton) {
        mLeftButton = leftButton;
        mRightButton = rightButton;
        mUpButton = upButton;
        mDownButton = downButton;
    }

    public void setLeftButtonActive(boolean isActive) {
        setButtonActive(mLeftButton, isActive);
    }

    public void setRightButtonActive(boolean isActive) {
        setButtonActive(mRightButton, isActive);
    }

    public void setUpButtonActive(boolean isActive) {
        setButtonActive(mUpButton, isActive);
    }

    public void setDownButtonActive(boolean isActive) {
        setButtonActive(mDownButton, isActive);
    }

    private void setButtonActive(View button, boolean isActive) {
        button.setSelected(isActive);
    }
}
