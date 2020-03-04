package science.skywhale.rccar;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class ControllerInput implements ControllerListener
{
	@Override
	public void connected (Controller controller)
	{
		System.out.println("Connected to " + controller);
	}
	
	@Override
	public void disconnected (Controller controller)
	{
		System.out.println("Disconnected from " + controller);
	}
	
	@Override
	public boolean buttonDown (Controller controller, int buttonCode)
	{
		System.out.println("Button code: " + buttonCode);
		return false;
	}
	
	@Override
	public boolean buttonUp (Controller controller, int buttonCode)
	{
		return false;
	}
	
	@Override
	public boolean axisMoved (Controller controller, int axisCode, float value)
	{
		System.out.println("Axis code: " + axisCode);
		return false;
	}
	
	@Override
	public boolean povMoved (Controller controller, int povCode, PovDirection value)
	{
		System.out.println("POV code: " + povCode);
		System.out.println("Direction: " + value);
		return false;
	}
	
	@Override
	public boolean xSliderMoved (Controller controller, int sliderCode, boolean value)
	{
		System.out.println("XSlider code: " + sliderCode);
		System.out.println("Value: " + value);
		return false;
	}
	
	@Override
	public boolean ySliderMoved (Controller controller, int sliderCode, boolean value)
	{
		System.out.println("YSlider code: " + sliderCode);
		System.out.println("Value: " + value);
		return false;
	}
	
	@Override
	public boolean accelerometerMoved (Controller controller, int accelerometerCode, Vector3 value)
	{
		System.out.println("Accelerometer code: " + accelerometerCode);
		System.out.println("Value: " + value);
		return false;
	}
}
