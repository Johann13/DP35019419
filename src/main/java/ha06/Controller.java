/*package ha06;

import ha06.commands.draw.GroupCommand;
import ha06.commands.draw.LineCommand;
import ha06.commands.other.ClearCommand;
import ha06.commands.other.CloneCommand;
import ha06.commands.unredo.RedoCommand;
import ha06.commands.unredo.UndoCommand;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

	public Button button;
	@FXML
	Canvas canvas;

	@FXML
	TextField textField;

	@FXML
	ListView listView;
	@FXML
	ListView listView2;

	private Store store;
	private GraphicsContext gc;
	private long lastDraw = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		store = new Store();
		store.addCommand(new LineCommand(), new UndoCommand(), new RedoCommand(), new ClearCommand(), new CloneCommand(), new GroupCommand());

		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			//System.out.println("textfield changed from " + oldValue + " to " + newValue);

		});

		listView.setItems(store.getCommands());
		listView2.setItems(store.getUndoCommands());
		starDraw();
	}

	@FXML
	public void onEnter(ActionEvent a) {
		System.out.println("test" + textField.getText());
		store.handleCommand(textField.getText());
		textField.setText("");
		store.printDraws();
	}


	private void starDraw() {
		gc = canvas.getGraphicsContext2D();
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				lastDraw = now;
				//GraphicsContext gc = canvas.getGraphicsContext2D();
				//gc.setFill(Color.CORNSILK);
				//gc.rect(0, 0, 300, 200);
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				store.draw(gc);
			}
		};
		timer.start();
	}

	public Store getStore() {
		return store;
	}
}
*/