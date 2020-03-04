package science.skywhale.rccar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ControlScreen implements Screen
{
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch;
	BitmapFont font;
	InputMultiplexer inputMultiplexer;
	private double[] inputs;
	
	public ControlScreen ()
	{
		inputs = new double[] {0, 0};
		camera = new OrthographicCamera();
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new TouchInput(this));
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	@Override
	public void render (float delta)
	{
		Gdx.gl.glClearColor(0, .256f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		batch.begin();
		font.draw(batch, "Left: " + inputs[0] + "\nRight: " + inputs[1], 100, 100);
		batch.end();
	}
	
	public void modifyInput (int side, int value)
	{
		inputs[side] += value;
	}
	public void resetInput (int side)
	{
		inputs[side] = 0;
	}
	
	@Override
	public void show ()
	{
	
	}
	
	@Override
	public void resize (int width, int height)
	{
	
	}
	
	@Override
	public void pause ()
	{
	
	}
	
	@Override
	public void resume ()
	{
	
	}
	
	@Override
	public void hide ()
	{
	
	}
	
	@Override
	public void dispose ()
	{
	
	}
}
