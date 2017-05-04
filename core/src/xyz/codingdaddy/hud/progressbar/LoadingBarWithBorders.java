package xyz.codingdaddy.hud.progressbar;

import xyz.codingdaddy.util.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Progress bar which reassembles the behaviour of the loading bar (with left and right borders).
 * 
 * @author serhiy
 */
public class LoadingBarWithBorders extends ProgressBar {

	private TextureRegion leftBorder;
	private TextureRegion rightBorder;
	
	public LoadingBarWithBorders(int width, int height) {
		super(0f, 1f, 0.01f, false, new ProgressBarStyle());
		
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("progress-bars.pack"));
		getStyle().background = new TextureRegionDrawable(textureAtlas.findRegion("loading-bar-2-background"));
		getStyle().knob = Utils.getColoredDrawable(0, height, Color.GREEN);
		getStyle().knobBefore = new TextureRegionDrawable(textureAtlas.findRegion("loading-bar-2-knobbefore"));
		
		leftBorder = textureAtlas.findRegion("loading-bar-2-left");
		rightBorder = textureAtlas.findRegion("loading-bar-2-right");
		
		setWidth(width);
		setHeight(height);

		setAnimateDuration(5f);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// First draw the left border.
		batch.draw(leftBorder, getX(), getY());
		// Save variables to restore their state after drawing
		float prevX = getX();
		float prevWidth = getWidth();
		// Set the variables which are used to draw the background
		setX(prevX + leftBorder.getRegionWidth());
		setWidth(prevWidth - leftBorder.getRegionWidth() - rightBorder.getRegionWidth());
		// Draw the progress bar as it would be without borders
		super.draw(batch, parentAlpha);
		// Set the variables to draw the right border
		setX(getX() + getWidth());
		// Draw the right border
		batch.draw(rightBorder, getX(), getY());
		// Reset the state of the variables so next cycle the drawing is done at correct position
		setX(prevX);
		setWidth(prevWidth);
	}
}
