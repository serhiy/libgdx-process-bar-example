package xyz.codingdaddy;

import java.util.concurrent.TimeUnit;

import xyz.codingdaddy.hud.progressbar.HealthBar;
import xyz.codingdaddy.hud.progressbar.LoadingBarWithBorders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Application rendering.
 * 
 * @author serhiy
 */
public class Main extends ApplicationAdapter {
	private Stage stage;
	private HealthBar healthBar;
	private LoadingBarWithBorders loadingBarWithBorders;
	
	private long lastUpdate = 0L;
	
	@Override
	public void create () {
		stage = new Stage();
		
		healthBar = new HealthBar(100, 10);
		healthBar.setPosition(10, Gdx.graphics.getHeight() - 20);
		stage.addActor(healthBar);
		
		loadingBarWithBorders = new LoadingBarWithBorders(170, 20);
		loadingBarWithBorders.setPosition(10, Gdx.graphics.getHeight() - 50);
		stage.addActor(loadingBarWithBorders);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (System.currentTimeMillis() - lastUpdate > TimeUnit.SECONDS.toMillis(5)) {
			healthBar.setValue(healthBar.getValue() - 0.1f);
			loadingBarWithBorders.setValue(loadingBarWithBorders.getValue() + 0.1f);
			lastUpdate = System.currentTimeMillis();
		}
		
		stage.draw();
		stage.act();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
}
