package ha04;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ha04Test {

	@Test
	public void addListenerTest() {
		//keep changes
		List<ObjectChange> changeList = new ArrayList<>();

		//observe to add chages to the list
		UnitObserver unitObserver = objectChange -> {
			changeList.add(objectChange);
			System.out.println(objectChange);
		};

		//test unit
		Unit unit = new Unit("unit");

		//region test add listener functionality
		Assert.assertEquals(0, unit.getObserverList().size());
		unit.addListener(unitObserver);
		Assert.assertEquals(1, unit.getObserverList().size());
		Assert.assertSame(unit.getObserverList().get(0), unitObserver);
		//endregion
	}

	@Test
	public void objectChangeTest() {
		List<ObjectChange> changeList = new ArrayList<>();
		UnitObserver unitObserver = objectChange -> {
			changeList.add(objectChange);
			System.out.println(objectChange);
		};
		Unit unit = new Unit("unit");
		unit.addListener(unitObserver);

		//region test adding change
		Assert.assertEquals(0, changeList.size());
		unit.setKind("specialUnit");
		Assert.assertEquals(1, changeList.size());
		//endregion

		//region test change properties
		ObjectChange objectChange = changeList.get(0);
		Assert.assertEquals("unit", objectChange.getOldVal());
		Assert.assertEquals("specialUnit", objectChange.getNewVal());
		Assert.assertEquals(unit.getId(), objectChange.getObjectId());
		//endregion
	}

	@Test
	public void jsonTest() {
		List<ObjectChange> changeList = new ArrayList<>();
		UnitObserver unitObserver = objectChange -> {
			changeList.add(objectChange);
			System.out.println(objectChange);
		};
		Unit unit = new Unit("unit");
		unit.addListener(unitObserver);
		unit.setKind("specialUnit");
		unit.setDesc("newDesc");
		Gson gson = new Gson();
		String json = gson.toJson(changeList);
		System.out.println(json);
		try (FileWriter file = new FileWriter("test.json")) {
			file.write(json);
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (FileReader reader = new FileReader("test.json")) {
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			System.out.println(line);

			//compare the loaded json string with the previous string
			Assert.assertEquals(line, json);


			Type listType = new TypeToken<ArrayList<ObjectChange>>() {
			}.getType();
			List<ObjectChange> list = new Gson().fromJson(json, listType);

			for (int i = 0; i < list.size(); i++) {
				ObjectChange o1 = list.get(i);
				ObjectChange o2 = changeList.get(i);
				System.out.println(o1);
				System.out.println(o2);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
