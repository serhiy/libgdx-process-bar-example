package xyz.codingdaddy;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class LoadingBar extends ProgressBar {

	public LoadingBar(int width, int height) {
		super(0f, 1f, 0.01f, false, new ProgressBarStyle());
		
		setWidth(width);
		setHeight(height);

		setAnimateDuration(0.25f);
	}
}
