package xyz.codingdaddy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LoadingBarWithBorders extends ProgressBar {

	private TextureRegion leftBorder;
	private TextureRegion rightBorder;
	
	public LoadingBarWithBorders(int width, int height) {
		super(0f, 1f, 0.01f, false, new ProgressBarStyle());
		
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("progress-bars.pack"));
		getStyle().background = new TextureRegionDrawable(textureAtlas.findRegion("loading-bar-2-background"));
		getStyle().knob = getColoredDrawable(0, height, Color.GREEN);
		getStyle().knobBefore = new TextureRegionDrawable(textureAtlas.findRegion("loading-bar-2-knobbefore"));
		
		leftBorder = textureAtlas.findRegion("loading-bar-2-left");
		rightBorder = textureAtlas.findRegion("loading-bar-2-right");
		
		setWidth(width);
		setHeight(height);

		setAnimateDuration(0.25f);
	}
	
	private static Drawable getColoredDrawable(int width, int height, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		
		pixmap.dispose();
		
		return drawable;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(leftBorder, getX(), getY());
		float prevX = getX();
		float prevWidth = getWidth();
		setX(prevX + leftBorder.getRegionWidth());
		setWidth(prevWidth - leftBorder.getRegionWidth() - rightBorder.getRegionWidth());
		super.draw(batch, parentAlpha);
		setX(getX() + getWidth());
		batch.draw(rightBorder, getX(), getY());
		setX(prevX);
		setWidth(prevWidth);
	}
}
