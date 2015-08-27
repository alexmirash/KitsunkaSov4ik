package sov4ik.alex.mirash.kitsunkasov4ik.joystick.tool;

import sov4ik.alex.mirash.kitsunkasov4ik.joystick.JoystickButtonController;

/**
 * @author Mirash
 */
public enum JoystickState {
    DEFAULT,
    LEFT {
        @Override
        public void updateJoystick(JoystickButtonController buttonController, boolean isActive) {
            buttonController.setLeftButtonActive(isActive);
        }
    },
    RIGHT {
        @Override
        public void updateJoystick(JoystickButtonController buttonController, boolean isActive) {
            buttonController.setRightButtonActive(isActive);
        }
    },
    UP {
        @Override
        public void updateJoystick(JoystickButtonController buttonController, boolean isActive) {
            buttonController.setUpButtonActive(isActive);
        }
    },
    DOWN {
        @Override
        public void updateJoystick(JoystickButtonController buttonController, boolean isActive) {
            buttonController.setDownButtonActive(isActive);
        }
    };

    public void updateJoystick(JoystickButtonController buttonController, boolean isActive) {
    }
}
