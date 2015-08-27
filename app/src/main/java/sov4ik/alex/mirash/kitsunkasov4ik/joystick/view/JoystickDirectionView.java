package sov4ik.alex.mirash.kitsunkasov4ik.joystick.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import sov4ik.alex.mirash.kitsunkasov4ik.utils.SovkaUtils;

/**
 * @author Mirash
 */
public abstract class JoystickDirectionView extends FrameLayout {
    protected float mDefaultAreaSizeRatio = 0.25f;

    public JoystickDirectionView(Context context) {
        super(context);
    }

    public JoystickDirectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void analyzePosition(float eventX, float eventY) {
        int size = getWidth();
        float inactiveRadius = size * mDefaultAreaSizeRatio;
        float halfSize = size * 0.5f;
        float distance = Math.min(SovkaUtils.distance(eventX, eventY, halfSize, halfSize), halfSize);
        if (distance < inactiveRadius) {
            onStateDefault();
        } else {
            //TODO a bit of magic, don't remember all de fkn formulas xD
            float x = eventX - halfSize;
            float y = halfSize - eventY;
            float angle;
            if (x == 0) {
                angle = y > 0 ? 90 : 270;
            } else {
                if (y == 0) {
                    angle = x > 0 ? 0 : 180;
                } else {
                    angle = (float) Math.toDegrees(Math.atan(y / x));
                    if (x < 0) {
                        angle += 180;
                    } else if (y < 0) {
                        angle += 360;
                    }
                }
            }
            float power = (distance - inactiveRadius) / (halfSize - inactiveRadius);
            onDirected(angle, power);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.max(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(size, size);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            onStateDefault();
        } else {
            analyzePosition(event.getX(), event.getY());
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() == MotionEvent.ACTION_DOWN || super.onTouchEvent(event);
    }

    protected abstract void onStateDefault();

    /**
     * @param angle [0, 360}
     * @param power [0, 1]
     */
    protected abstract void onDirected(float angle, float power);
}
