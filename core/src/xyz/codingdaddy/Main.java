package xyz.codingdaddy;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class Main extends ApplicationAdapter {
	private Stage stage;
	private ProgressBar healthBar;
	private ProgressBar loadingIndicator;
	
	private long lastUpdate = 0L;
	
	@Override
	public void create () {
		stage = new Stage();
		
		healthBar = new HealthBar(100, 10);
		healthBar.setPosition(10, Gdx.graphics.getHeight() - 20);
		
		stage.addActor(healthBar);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (System.currentTimeMillis() - lastUpdate > TimeUnit.SECONDS.toMillis(5)) {
			healthBar.setValue(healthBar.getValue() - 0.1f);
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
