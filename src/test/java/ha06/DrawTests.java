package ha06;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
// HA06: 10/10
public class DrawTests extends ApplicationTest {


	@Before
	public void before() throws Exception {
		ApplicationTest.launch(Main.class);

	}

	@Override
	public void start(Stage stage) {
		stage.show();
	}

	@Test
	public void drawHouses() {

		String[] list = new String[]{"line a 20 20 30 10", "line b 40 20 30 10", "line c 20 40 20 20", "line d 40 20 40 40", "line e 40 40 20 40", "line f 40 40 20 20", "line g 20 40 40 20", "line h 20 20 40 20", "group h1 a b c d e f g h", "clone h1 h2 50 0", "clone h1 h3 0 50"};

		for (String s : list) {
			textClick();
			textEquals("");
			write(s);
			textEquals(s);
			buttonClick();
			textEquals("");
		}

		sleep(4000);


	}

	private void textEquals(String text) {
		verifyThat("#textField", (TextField t) -> {
			return t.getText().equals(text);
		});
	}

	private void buttonClick() {
		clickOn("#button");
	}

	private void textClick() {
		clickOn("#textField");
	}

	@After
	public void tearDown() throws Exception {
		FxToolkit.hideStage();
		release(new KeyCode[]{});
		release(new MouseButton[]{});
	}


}
