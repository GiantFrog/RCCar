package science.skywhale.rccar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.IOException;

public class ControlScreen implements Screen
{
	private NetworkDude udp;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	private ShapeRenderer shapeRenderer;
	private InputMultiplexer inputMultiplexer;
	private byte[] inputs;
	private int[] initialY;
	private float timeSincePacket, timeBetweenPackets;
	private String toDraw;
	
	public ControlScreen ()
	{
		timeBetweenPackets = 0.1f;
		timeSincePacket = -60f;
		inputs = new byte[] {0, 0};
		initialY = new int[] {Gdx.graphics.getHeight()/2, Gdx.graphics.getHeight()/2};
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new TouchInput(this));
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		try
		{
			udp = new NetworkDude();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void render (float delta)
	{
		Gdx.gl.glClearColor(0, .256f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		if (Gdx.graphics.getDeltaTime() - timeSincePacket >= timeBetweenPackets)
		{
			toDraw = "Left: " + inputs[0] + "                              Right: " + inputs[1] + "\n";
			try
			{
				udp.send(inputs);
			}
			catch (IOException e)
			{
				toDraw += e.getMessage();
			}
		}
		
		batch.begin();
		font.draw(batch, toDraw, Gdx.graphics.getWidth()/2f - 100, Gdx.graphics.getHeight() - 100);
		batch.end();
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1, 1, 1, 1);	//white
		float halfWidth = Gdx.graphics.getWidth()/2f;
		//TODO these lines don't always draw for some reason?
		shapeRenderer.rect(0, initialY[0], halfWidth, 100);
		shapeRenderer.rect(halfWidth, initialY[0], halfWidth, 100);
		shapeRenderer.end();
	}
	
	public void setInitialY (int side, int value)
	{
		initialY[side] = value;
	}
	public void setInput (int side, byte value)
	{
		inputs[side] = value;
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
		shapeRenderer.setProjectionMatrix(camera.combined);
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
		inputMultiplexer.removeProcessor(0);
		batch.dispose();
		font.dispose();
	}
}
