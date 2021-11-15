package scene_system;

import characters.NonPlayerCharacter;
import items.ArmourItem;
import items.Item;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SceneTest {
    private Scene scene;

    @Before
    public void before() throws Exception {
        NonPlayerCharacter npc = new NonPlayerCharacter(5, "tim");
        ArrayList<NonPlayerCharacter> npcs = new ArrayList<>();
        npcs.add(npc);
        ArrayList<Item> items = new ArrayList<>();
        items.add(new ArmourItem("helmet", "head", 1, 1));
        scene = new Scene("place", npcs, "test area", items);
    }

    @After
    public void after() throws Exception {
        scene = null;
    }

    @Test
    public void testGetDescription() {
        Assert.assertEquals("test area", scene.getDescription());
    }

    @Test
    public void testTestGetName() {
        Assert.assertEquals("place", scene.getName());
    }

    @Test
    public void testGetConnectedAreas() {
        ArrayList<Scene> empty = new ArrayList<>();
        Assert.assertEquals(empty, scene.getConnectedAreas());
    }

    @Test
    public void testGetItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new ArmourItem("helmet", "head", 1, 1));
        Assert.assertEquals(items, scene.getItems());
    }

    @Test
    public void testGetNpc() {
        ArrayList<NonPlayerCharacter> npcs = new ArrayList<>();
        npcs.add( new NonPlayerCharacter(5, "tim"));
        Assert.assertEquals(npcs, scene.getNpc());
    }

    @Test
    public void testRemoveItem() {
        Item item = new ArmourItem("helmet", "head", 1, 1);
        scene.removeItem(item);
        ArrayList<Item> empty = new ArrayList<>();
        Assert.assertEquals(empty, scene.getItems());
    }

    @Test
    public void testAddScene() {
        Scene place = new Scene("top", new ArrayList<NonPlayerCharacter>(), "top",new ArrayList<Item>());
        scene.addScene(place);
        ArrayList<Scene> scenes = new ArrayList<>();
        scenes.add(place);
        Assert.assertEquals(scenes, scene.getConnectedAreas());
    }
}