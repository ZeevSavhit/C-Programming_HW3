import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddressBookJavaFx extends Application {
	final static int NUMBER_OF_OBJECTS = 3;
	final static int NUMBER_OF_SEC_PANE = 2;
	final static int NUMBER_OF_PRI_PANE = 1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		ArrayList<Stage> stages = new ArrayList<>();
		ArrayList<Scene> scenes = new ArrayList<>();
		ArrayList<AddressBookPane> DecoPanes = new ArrayList<>();
		try {
			for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {

				if (AddressBookPane.getNumOfObj() < NUMBER_OF_SEC_PANE) {
					AddressBookPane abp = AddressBookPane.getInstance();
					DecoPanes.add(abp);

				} else if (primAddressBookPane.getNum() < NUMBER_OF_PRI_PANE) {
					AddressBookPane abp = AddressBookPane.getInstance();
					myDecorator myDecoratorFX = primAddressBookPane.getInstance(abp);
					abp.SetPane(myDecoratorFX.GetPane());
					DecoPanes.add(abp);
				}

				scenes.add(new Scene(DecoPanes.get(i)));
				stages.add(new Stage());
				stages.get(i).setScene(scenes.get(i));
				stages.get(i).setTitle(STYLESHEET_CASPIAN);
				stages.get(i).setResizable(true);
				stages.get(i).show();
				stages.get(i).setAlwaysOnTop(true);
			}

		} catch (Exception ex) {
			System.out.println(ex.getClass());
		}
		// myDecorator[] panes = new AddressBookPane[NUMBER_OF_OBJECTS];
		// try {
		// for (int i = 0; i < 1 + NUMBER_OF_OBJECTS; i++) {
		// if (i >= NUMBER_OF_OBJECTS)
		// System.out.println("ni objects");
		// else {c
		// if (primAddressBookPane.getNum() < 1) {
		// panes.add(primAddressBookPane.getInstance());
		//// panes[i] = primAddressBookPane.getInstance();
		// //scenes.add( new Scene(primAddressBookPane.getInstance()));
		// stages[i] = new Stage();
		// stages[i].setTitle(STYLESHEET_CASPIAN);
		// stages[i].setScene(scenes.get(i));
		// stages[i].setResizable(true);
		// stages[i].show();
		// stages[i].setAlwaysOnTop(true);
		// }
		// if (secAddressBookPane.getNum() < 2) {
		// panes.add(secAddressBookPane.getInstance());
		// scenes.add( new Scene(secAddressBookPane.getInstance()));
		// stages[i] = new Stage();
		// stages[i].setTitle(STYLESHEET_CASPIAN);
		// stages[i].setScene(scenes.get(i));
		// stages[i].setResizable(true);
		// stages[i].show();
		// stages[i].setAlwaysOnTop(true);
		// }
		//
		// // if(NUMBER_OF_PRI_OBJECTS<1){
		// // panes[i] = AddressBookPane.getInstance();
		// // scenes[i] = new Scene(panes[i]);
		// // stages[i] = new Stage();
		// // stages[i].setTitle(STYLESHEET_CASPIAN);
		// // stages[i].setScene(scenes[i]);
		// // stages[i].setResizable(true);
		// // stages[i].show();
		// // stages[i].setAlwaysOnTop(true);
		// // // stages[i].setOnCloseRequest(event -> {
		// // // AddressBookPane.reduceNumberOfObjects();
		// // // });
		// }
		// }
		// } catch (Exception e) {
		// // AddressBookPane.resetNumberOfObjects();
		// }
	}
}

class primAddressBookPane implements myDecorator {

	// max numbers for the singeltin design
	final private static int NUMBER_OF_PRI_OBJECTS = 1;
	private static int number_of_objects = 0;
	// data members for the decorator
	protected myDecorator decorator;
	private FlowPane fp;
	private AddressBookPane abp = new AddressBookPane();
	// Decorated Button
	private Undo undoButton;
	private Reado readoButton;
	private AddButton jbtAdd;

	private primAddressBookPane(myDecorator decorator) {

		this.decorator = decorator;
		this.abp = decorator.getAdressPane();
		SetPane(decorator.GetPane());// the function that adds the buttons
		undoButton.setOnAction(e -> {
			undoButton.Execute();
		});
		readoButton.setOnAction(ae);
		jbtAdd.setOnAction(ae);

	}

	public static primAddressBookPane getInstance(myDecorator decorator) {

		if (number_of_objects >= NUMBER_OF_PRI_OBJECTS) {
			return null;
		} else {
			number_of_objects++;
			return new primAddressBookPane(decorator);
		}
	}

	public static int getNum() {
		return number_of_objects;
	}

	public FlowPane GetPane() {

		return fp;
	}

	@Override
	public void SetPane(FlowPane fp) {

		this.fp = decorator.GetPane();
		undoButton = new Undo(this.abp, abp.getFile());
		readoButton = new Reado(this.abp, abp.getFile());
		jbtAdd = new AddButton(this.abp, abp.getFile());
		fp.getChildren().addAll(undoButton, readoButton, jbtAdd);
	}

	@Override
	public AddressBookPane getAdressPane() {

		return this.abp;
	}

	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

}

class AddressBookPane extends GridPane implements myDecorator, myFinals {
	private static final int MAX_OBJECTS = 3;
	private static int numOfObj = 0;
	private RandomAccessFile raf;
	// Text fields
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();
	// Buttons
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	protected FlowPane jpButton;

	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

	public static int getNumOfObj() {
		return numOfObj;
	}

	public AddressBookPane() { // Open or create a random access file
		try {
			raf = new RandomAccessFile("address7.dat", "rw");
		} catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}
		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(25);
		jtfZip.setPrefWidth(60);
		jbtFirst = new FirstButton(this, raf);
		jbtNext = new NextButton(this, raf);
		jbtPrevious = new PreviousButton(this, raf);
		jbtLast = new LastButton(this, raf);

		Label state = new Label("State");
		Label zp = new Label("Zip");
		Label name = new Label("Name");
		Label street = new Label("Street");
		Label city = new Label("City");
		// Panel p1 for holding labels Name, Street, and City
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);
		// City Row
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);
		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);
		// Panel p4 for holding jtfName, jtfStreet, and p3
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		// Place p1 and p4 into jpAddress
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		// Set the panel with line border
		jpAddress.setStyle("-fx-border-color: grey;" + " -fx-border-width: 1;" + " -fx-border-style: solid outside ;");
		// Add buttons to a panel
		jpButton = new FlowPane();
		jpButton.setHgap(5);
		jpButton.getChildren().addAll(jbtFirst, jbtNext, jbtPrevious, jbtLast);
		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		// Add jpAddress and jpButton to the stage
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);
		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbtFirst.Execute();

	}

	public static AddressBookPane getInstance() {
		if (numOfObj >= MAX_OBJECTS) {
			return null;
		} else {
			numOfObj++;
			return new AddressBookPane();
		}
	}

	public RandomAccessFile getFile() {
		return this.raf;
	}

	public void actionHandled(ActionEvent e) {
		((Command) e.getSource()).Execute();
	}

	public void SetName(String text) {
		jtfName.setText(text);
	}

	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}

	public void SetCity(String text) {
		jtfCity.setText(text);
	}

	public void SetState(String text) {
		jtfState.setText(text);
	}

	public void SetZip(String text) {
		jtfZip.setText(text);
	}

	public String GetName() {
		return jtfName.getText();
	}

	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String GetCity() {
		return jtfCity.getText();
	}

	public String GetState() {
		return jtfState.getText();
	}

	public String GetZip() {
		return jtfZip.getText();
	}

	@Override
	public FlowPane GetPane() {

		return this.jpButton;
	}

	@Override
	public void SetPane(FlowPane fp) {

		this.jpButton = fp;

	}

	@Override
	public AddressBookPane getAdressPane() {

		return this;
	}

}

interface Command {
	public void Execute();
}

class CommandButton extends Button implements Command {
	public final static int NAME_SIZE = 32;
	public final static int STREET_SIZE = 32;
	public final static int CITY_SIZE = 20;
	public final static int STATE_SIZE = 2;
	public final static int ZIP_SIZE = 5;
	public final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
	protected AddressBookPane p;
	protected RandomAccessFile raf;
	Originator originator = new Originator();
	CareTaker caretaker = new CareTaker();

	public CommandButton(AddressBookPane pane, RandomAccessFile r) {
		super();
		p = pane;
		raf = r;
	}

	public void Execute() {
	}

	/** Write a record at the end of the file */
	public void writeAddress() {

		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(p.GetName(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetStreet(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetCity(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetState(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetZip(), ZIP_SIZE, raf);
			System.out.println(p.GetName() + p.GetStreet() + p.GetCity());
			originator.setState(p.GetName(), p.GetStreet(), p.GetCity(), p.GetState(), p.GetZip());
			caretaker.add(originator.saveStateToMemento());
			System.out.println(caretaker.index());

			System.out.println(originator.getName() + originator.getStreet() + originator.getZip());
			System.out.println("saved to memento");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {

		raf.seek(position);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		p.SetName(name);
		p.SetStreet(street);
		p.SetCity(city);
		p.SetState(state);
		p.SetZip(zip);
	}
}

class AddButton extends CommandButton {

	public AddButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Add");
	}

	@Override
	public void Execute() {
		writeAddress();
	}
}

class NextButton extends CommandButton {
	
	public NextButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Next");
	}

	@Override
	public void Execute() {
		try {
			long currentPosition = raf.getFilePointer();
			if (currentPosition < raf.length())
				readAddress(currentPosition);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class PreviousButton extends CommandButton {
	public PreviousButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Previous");
	}

	@Override
	public void Execute() {
		System.out.println("working");
		try {
			long currentPosition = raf.getFilePointer();
			if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
				readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
			else
				;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class LastButton extends CommandButton {
	public LastButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Last");
	}

	@Override
	public void Execute() {
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0)
				readAddress(lastPosition - 2 * RECORD_SIZE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class FirstButton extends CommandButton {
	public FirstButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("First");
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() > 0)
				readAddress(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class Reado extends CommandButton {

	public Reado(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Reado");
	}

	@Override
	public void Execute() {

	}
}

class Undo extends CommandButton {

	public Undo(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText("Undo");
	}

	@Override
	public void Execute() {

		System.out.println("working");
//		originator.getStateFromMemento(caretaker.get(caretaker.index()));
		// originator.getStateFromMemento(caretaker.getPrev());
		originator.saveStateToMemento().getCity();
		System.out.println(originator.saveStateToMemento().getCity());
		try {
			long lastpos = raf.length();
			if (lastpos > 0) {
				raf.seek(lastpos - (2 * RECORD_SIZE));
			}
			FixedLengthStringIO.writeFixedLengthString("", 0, raf);

		} catch (Exception ex) {

		}
		p.SetName(originator.getName());
		p.SetStreet(originator.getStreet());
		p.SetCity(originator.getCity());
		p.SetState(originator.getState());
		p.SetZip(originator.getZip());
	}
}

class Memento {
	private String Name;
	private String Street;
	private String City;
	private String State;
	private String Zip;

	public Memento(String Name, String Street, String City, String State, String Zip) {

		this.Name = Name;
		this.Street = Street;
		this.City = City;
		this.State = State;
		this.Zip = Zip;
	}

	public String getNmae() {
		return Name;
	}

	public String getStreet() {
		return Street;
	}

	public String getCity() {
		return City;
	}

	public String getState() {
		return State;
	}

	public String getZip() {
		return Zip;
	}
}

class CareTaker {

	private List<Memento> mementoList = new ArrayList<Memento>();
	private int index;

	public CareTaker() {
		index = mementoList.size();
	}

	public void add(Memento state) {
		if (state != null) {
			System.out.println("added"+state.getCity());
			mementoList.add(state);
			index = mementoList.size() - 1;
		}
	}

	public Memento getPrev() {
		if (mementoList.isEmpty() || index < 0) { // הורדתי את המינוס לבדיקה
			return null;
		}
		System.out.println("idex is " + this.index);
		return mementoList.get(--index);
	}

	public Memento getNext() {
		if (mementoList.isEmpty() || index >= mementoList.size() - 1) {
			return null;
		}
		return mementoList.get(++index);
	}

	public Memento get(int i) {
		return mementoList.get(i);
	}

	public int index() {

		return this.index;
	}
}

class Originator {
	private String Name;
	private String Street;
	private String City;
	private String State;
	private String Zip;

	public String getName() {
		return Name;
	}

	public String getStreet() {
		return Street;
	}

	public String getCity() {
		return City;
	}

	public String getState() {
		return State;
	}

	public String getZip() {
		return Zip;
	}

	public void setState(String Name, String Street, String City, String State, String Zip) {
		this.Name = Name;
		this.Street = Street;
		this.City = City;
		this.State = State;
		this.Zip = Zip;
	}

	public Memento saveStateToMemento() {
		return new Memento(Name, Street, City, State, Zip);
	}

	public void getStateFromMemento(Memento memento) {

		if (memento != null) {
			System.out.println("shows memeno");
			this.Name = memento.getNmae();
			this.Street = memento.getStreet();
			this.City = memento.getCity();
			this.State = memento.getState();
			this.Zip = memento.getZip();
		}
	}
}
