package science.skywhale.rccar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class TouchInput implements InputProcessor
{
	private ControlScreen screen;
	private int[] side;	//2 for empty, 0 for left, 1 for right
	private int[] lastY;
	
	public TouchInput (ControlScreen screen)
	{
		this.screen = screen;
		side = new int[] {2, 2};
		lastY = new int[] {0, 0};
	}
	
	@Override
	public boolean keyDown (int keycode)
	{
		return false;
	}
	
	@Override
	public boolean keyUp (int keycode)
	{
		return false;
	}
	
	@Override
	public boolean keyTyped (char character)
	{
		return false;
	}
	
	@Override
	public boolean touchDown (int x, int y, int pointer, int button)
	{
		if (pointer >= 2)
			return false;
		
		if (x < (Gdx.graphics.getWidth()) / 2)
			side[pointer] = 0;
		else side[pointer] = 1;
		lastY[pointer] = y;
		return true;
	}
	
	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button)
	{
		screen.resetInput(side[pointer]);
		side[pointer] = 2;
		return true;
	}
	
	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer)
	{
		screen.modifyInput(side[pointer], screenY - lastY[pointer]);
		lastY[pointer] = screenY;
		return true;
	}
	
	@Override
	public boolean mouseMoved (int screenX, int screenY)
	{
		return false;
	}
	
	@Override
	public boolean scrolled (int amount)
	{
		return false;
	}
}
