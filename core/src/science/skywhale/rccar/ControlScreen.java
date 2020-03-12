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
import java.net.SocketException;

public class ControlScreen implements Screen
{
	private NetworkDude udp;
	private OrthographicCamera camera;
	//private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private BitmapFont font;
	private InputMultiplexer inputMultiplexer;
	private byte[] inputs;
	private float timeSincePacket, timeBetweenPackets;
	private String toDraw;
	
	public ControlScreen ()
	{
		
		timeBetweenPackets = 0.1f;
		timeSincePacket = -60f;
		inputs = new byte[] {0, 0};
		camera = new OrthographicCamera();
		//shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(new TouchInput(this));
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		//TODO confirm 192.168.0.1 is the car before spamming it with tiny packets
		try
		{
			udp = new NetworkDude();
		}
		catch (SocketException e)
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
			toDraw = "Left: " + inputs[0] + "\nRight: " + inputs[1] + "\n";
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
		font.draw(batch, toDraw, 100, 100);
		batch.end();
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
