package science.skywhale.rccar;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.controllers.Controllers;

public class RCCar extends Game
{
	
	@Override
	public void create()
	{
		Controllers.addListener(new ControllerInput());
		this.setScreen(new ControlScreen());
	}

	@Override
	public void render()
	{
		super.render();
	}
	
	@Override
	public void dispose()
	{
		this.getScreen().dispose();
	}
}
