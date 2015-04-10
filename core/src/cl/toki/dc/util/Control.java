package cl.toki.dc.util;

import cl.toki.dc.interfaces.IAccion;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 *
 * @author Francisco Garcia
 */
public class Control extends InputListener {
	private IAccion actor;
	float originalX = 0;
	private float originalY;

	public Control(IAccion actor) {
		this.actor = actor;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		originalY = y;
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {

	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		
		if (originalY < y) {
			actor.saltar();
			return;
		}

		if (originalY > y) {
			actor.agacharse();
			return;
		}

	}

}
