package xyz.codingdaddy.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Utilities for GFX components.
 * 
 * @author sboychen
 */
public class Utils {
	private Utils() {}
	
	/**
	 * Creates an image of determined size filled with determined color.
	 * 
	 * @param width of an image.
	 * @param height of an image.
	 * @param color of an image fill.
	 * @return {@link Drawable} of determined size filled with determined color.
	 */
	public static Drawable getColoredDrawable(int width, int height, Color color) {
		Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
		pixmap.setColor(color);
		pixmap.fill();
		
		TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		
		pixmap.dispose();
		
		return drawable;
	}
}
