package sov4ik.alex.mirash.kitsunkasov4ik.maze.tool;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * @author Mirash
 */
public abstract class MazeElement extends FrameLayout implements IMazeElement {
    public MazeElement(Context context) {
        super(context);
        init(context);
    }

    protected abstract void init(Context context);
}
