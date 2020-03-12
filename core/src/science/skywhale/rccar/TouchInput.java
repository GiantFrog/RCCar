package science.skywhale.rccar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class TouchInput implements InputProcessor
{
	private ControlScreen screen;
	private int[] side, upperBoxSize, lowerBoxSize;	//2 for empty, 0 for left, 1 for right
	private int[] startY;
	private int max, min;
	
	public TouchInput (ControlScreen screen)
	{
		this.screen = screen;
		side = new int[] {2, 2};
		startY = new int[] {0, 0};
		upperBoxSize = new int[2];
		lowerBoxSize = new int[2];
		
		//TODO handle resizing
		max = (int)(Gdx.graphics.getHeight()*.9);
		min = (int)(Gdx.graphics.getHeight()*.1);
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
		//do not process the tap if it is the third+ finger or it is on the top or bottom tenth of the screen.
		//TODO limit one finger per side, not two total
		if (y <= min || y >= max)
			return false;
		if (pointer > 1)
			return false;
		
		if (x < (Gdx.graphics.getWidth()) / 2)
			side[pointer] = 0;
		else side[pointer] = 1;
		
		startY[pointer] = y;
		upperBoxSize[pointer] = (y) / 127;
		lowerBoxSize[pointer] = (Gdx.graphics.getHeight() - y) / 127;
		
		if (upperBoxSize[pointer] < 1) upperBoxSize[pointer] = 1;
		if (lowerBoxSize[pointer] < 1) lowerBoxSize[pointer] = 1;
		
		return true;
	}
	
	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button)
	{
		if (pointer > 1 || side[pointer] > 1)	//don't bother if this finger isn't assigned to a screen side.
			return false;
		
		screen.resetInput(side[pointer]);
		side[pointer] = 2;
		return true;
	}
	
	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer)
	{
		if (pointer > 1 || side[pointer] > 1)	//don't bother if this finger isn't assigned to a screen side.
			return false;
		
		int relativePos = startY[pointer] - screenY;
		byte output = 0;
		
		if (relativePos > 0)
		{
			output = (byte)(relativePos/upperBoxSize[pointer]);
			if (output < 0)
				output = 127;
		}
		else if (relativePos < 0)
		{
			output = (byte)(relativePos/lowerBoxSize[pointer]);
			if (output > 0 || output == -128)
				output = -127;
		}
		
		screen.setInput(side[pointer], output);
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
