package scene_system;

import characters.GameCharacter;
import characters.NonPlayerCharacter;
import items.Item;

import characters.PlayerCharacter;
import combat_system.Combat;

import java.util.ArrayList;

/**
 * Represents a scene in the game.
 */
public class Scene {

    private String name;
    private ArrayList<NonPlayerCharacter> npc;
    private String area_description;
    private ArrayList<Scene> connectedAreas;
    private ArrayList<Item> items;

    /**
     * Constructor for the scene_system.Scene class.
     * @param name the name of the scene_system.Scene
     * @param npc the list of NPCs in the scene_system.Scene
     * @param area the text description of the location in the scene_system.Scene
     * @param items the items located in the scene_system.Scene
     */
    public Scene(String name, ArrayList<NonPlayerCharacter> npc, String area,
          ArrayList<Item> items) {
        this.name = name;
        this.npc = npc;
        this.area_description = area;
        this.connectedAreas = new ArrayList<>();
        this.items = items;
    }

    /**
     * Gets the description of the scene_system.Scene.
     * @return the text description of the scene_system.Scene
     */
    public String getDescription() {
        return this.area_description;
    }

    /**
     * Gets the name of the scene_system.Scene.
     * @return the name of the scene_system.Scene
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the list of connected areas.
     * @return the list of connected areas
     */
    public ArrayList<Scene> getConnectedAreas() {
        return connectedAreas;
    }

    /**
     * Gets the list of items in the scene_system.Scene.
     * @return the list of items in the scene_system.Scene
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Gets the list of NPCs in the scene_system.Scene.
     * @return the list of NPCs in the scene_system.Scene
     */
    public ArrayList<NonPlayerCharacter> getNpc() {
        return npc;
    }

    /**
     * Removes an item from the scene_system.Scene.
     * @param item the item to be removed
     */
    public void removeItem(Item item){
        this.items.remove(item);
    }

    /**
     * Adds a connection to another scene.
     * scene_system.Scene connections are unidirectional by default.
     * @param scene the scene_system.Scene to be connected
     */
    public void addScene(Scene scene){
        this.connectedAreas.add(scene);
    }

    /**
     * Removes dead npcs
     * This should update world state as well
     */
    public void remove_dead() {
        this.npc.removeIf(npc -> npc.getCurrentHealth() <= 0);
    }

    /**
     * This method is called when combat begins
     * After combat is resolved, it checks the npcs that are alive since many probably died.
     * This leaves room for expansion if there are non-lethal options in combat (check_alive can be expanded)
     * @param player the player character must be combined with the npcs in an ArrayList to start combat
     */
    public void start_combat(PlayerCharacter player) {
        ArrayList<GameCharacter> participants = new ArrayList<>(getNpc());
        participants.add(player);
        Combat scene_combat = new Combat(participants);
        scene_combat.combatLoop();
        remove_dead();
    }
}

