package ha02;

import ha02.model.Feature;
import ha02.model.Task;
import ha02.model.Unit;
import ha02.visitor.FeatureCounterVisitor;
import ha02.visitor.StoryPointCounterVisitor;
import ha02.visitor.TaskCounterVisitor;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

	@Test
	public void fulibTest() {

		//region model
		Unit project = new Unit("project", "Fulib");

		//region release1
		Unit release1 = new Unit("release", "WT1819");
		Unit sprint11 = new Unit("sprint", "s11");
		Feature feature111 = new Feature("f111", 8);
		Feature feature112 = new Feature("f112", 8);

		Unit sprint12 = new Unit("sprint", "s12");
		Feature feature121 = new Feature("f121", 8);
		Feature feature122 = new Feature("f122", 8);
		Feature feature123 = new Feature("f123", 8);

		Unit sprint13 = new Unit("sprint", "s13");
		Feature feature131 = new Feature("f131", 8);
		Feature feature132 = new Feature("f132", 8);
		release1.addChildren(sprint11, sprint12, sprint13);
		sprint11.addChildren(feature111, feature112);
		sprint12.addChildren(feature121, feature122, feature123);
		sprint13.addChildren(feature131, feature132);
		//endregion

		//region release2
		Unit release2 = new Unit("release", "ST19");
		Unit sprint21 = new Unit("sprint", "s21");
		Feature feature211 = new Feature("f211", 8);
		Feature feature212 = new Feature("f212", 8);
		Feature feature213 = new Feature("f213", 8);

		Unit sprint22 = new Unit("sprint", "s22");
		Feature feature221 = new Feature("f221", 8);
		Feature feature222 = new Feature("f222", 8);
		Feature feature223 = new Feature("f223", 8);
		Unit sprint23 = new Unit("sprint", "s23");
		Feature feature231 = new Feature("f231", 8);
		Feature feature232 = new Feature("f232", 8);

		release2.addChildren(sprint21, sprint22, sprint23);
		sprint21.addChildren(feature211, feature212, feature213);
		sprint22.addChildren(feature221, feature222, feature223);
		sprint23.addChildren(feature231, feature232);
		//endregion

		Assert.assertEquals(0, project.getChildren().size());
		project.addChildren(release1, release2);
		Assert.assertEquals(2, project.getChildren().size());
		//endregion


		//region test1
		//release1
		//2+3+2=7
		//release2
		//3+3+2=8
		//number of features 15
		//each feature has 8 storyPoints
		//expected storyPoints = 15 * 8 = 120
		StoryPointCounterVisitor visitor = new StoryPointCounterVisitor();
		int sum = project.accept(visitor);
		Assert.assertEquals(120, sum);
		//endregion


		//region test2
		//remove releases from project
		project.removeChildren(release1, release2);
		Assert.assertEquals(0, project.getChildren().size());


		Unit epic = new Unit("epic", "Epic1");

		//add releases to epic
		Assert.assertEquals(0, epic.getChildren().size());
		epic.addChildren(release1, release2);
		Assert.assertEquals(2, epic.getChildren().size());

		//add epic to project
		project.addChildren(epic);
		Assert.assertEquals(1, project.getChildren().size());

		//set storyPoints of feature232 to 0
		Assert.assertEquals(8, feature232.getStoryPoints());
		feature232.setStoryPoints(0);
		Assert.assertEquals(0, feature232.getStoryPoints());

		//new tasks with 2 points each
		Task task1 = new Task("T1", 2);
		Task task2 = new Task("T2", 2);
		Task task3 = new Task("T3", 2);
		Task task4 = new Task("T4", 2);

		//add tasks to feature feature232
		Assert.assertEquals(0, feature232.getChildren().size());
		feature232.addChildren(task1, task2, task3, task4);
		Assert.assertEquals(4, feature232.getChildren().size());

		//add docu to epic
		Task docu = new Task("Docu", 4);
		Assert.assertEquals(2, epic.getChildren().size());
		epic.addChildren(docu);
		Assert.assertEquals(3, epic.getChildren().size());

		//test2
		Assert.assertEquals(((7 + 8) * 8) + 4, project.accept(visitor));
		//endregion

		//region test3
		TaskCounterVisitor taskCounterVisitor = new TaskCounterVisitor();
		//4 tasks + docu
		Assert.assertEquals(5, project.accept(taskCounterVisitor));
		//endregion

		//region test 4
		FeatureCounterVisitor featureCounterVisitor = new FeatureCounterVisitor();
		Assert.assertEquals(15, project.accept(featureCounterVisitor));
		//endregion
	}
}
