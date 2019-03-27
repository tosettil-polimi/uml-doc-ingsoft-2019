import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = "map.json";

        InputStream is = Main.class.getResourceAsStream(path);

        List<Room> roomList = new ArrayList<>();

        try {
            if (is == null) {
                throw new FileNotFoundException("Cannot find resource file " + path);
            }
            JSONTokener tokener = new JSONTokener(is);
            JSONObject object = new JSONObject(tokener);

            JSONArray maps = object.getJSONArray("maps");
            for (int i = 0; i < maps.length(); i++) {
                JSONObject map = maps.getJSONObject(i);
                JSONArray rooms = map.getJSONArray("rooms");

                for (int j = 0; j < rooms.length(); j++) {
                    roomList.add(new Room(j));
                }

                for (int j = 0; j < rooms.length(); j++) {
                    JSONObject room = rooms.getJSONObject(j);

                    int temp;

                    roomList.get(j).setColor(Color.valueOf(room.getString("color")));

                    if (room.has("north")) {
                        temp = room.getInt("north");
                        roomList.get(j).setNorth(roomList.get(temp));
                    }
                    if (room.has("east")) {
                        temp = room.getInt("east");
                        roomList.get(j).setEast(roomList.get(temp));
                    }
                    if (room.has("south")) {
                        temp = room.getInt("south");
                        roomList.get(j).setSouth(roomList.get(temp));
                    }
                    if (room.has("west")) {
                        temp = room.getInt("west");
                        roomList.get(j).setWest(roomList.get(temp));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("NOT FOUND");
        }

        Room temp = roomList.get(0);
        while (temp != null) {
            printLine(temp);
            temp = temp.getSouth();
        }


    }

    private static void printLine(Room room) {
        printTop(room);
        System.out.println("");
        printMiddle(room);
        System.out.println("");
        printBottom(room);
        System.out.println("\n");
    }

    private static void printTop(Room room) {
        if (room != null) {
            System.out.print(room.getColor().getColor() + "+" + Color.valueOf("RESET").getColor());

            if (room.getNorth() != null) {
                if (room.getNorth().getColor().equals(room.getColor())) {
                    System.out.print(room.getColor().getColor() + " " + Color.valueOf("RESET").getColor());
                } else {
                    System.out.print(room.getColor().getColor() + "#" + Color.valueOf("RESET").getColor());
                }
            } else {
                System.out.print(room.getColor().getColor() + "-" + Color.valueOf("RESET").getColor());
            }
            System.out.print(room.getColor().getColor() + "+" + Color.valueOf("RESET").getColor());
            System.out.print(" ");
            printTop(room.getEast());
        } else {
            return;
        }
    }

    private static void printMiddle(Room room) {
        if (room != null) {
            if (room.getWest() != null) {
                if (room.getWest().getColor().equals(room.getColor())) {
                    System.out.print(room.getColor().getColor() + " " + Color.valueOf("RESET").getColor());
                } else {
                    System.out.print(room.getColor().getColor() + "#" + Color.valueOf("RESET").getColor());
                }
            } else {
                System.out.print(room.getColor().getColor() + "|" + Color.valueOf("RESET").getColor());
            }

            System.out.print(room.getColor().getColor() + " " + Color.valueOf("RESET").getColor());

            if (room.getEast() != null) {
                if (room.getEast().getColor().equals(room.getColor())) {
                    System.out.print(room.getColor().getColor() + " " + Color.valueOf("RESET").getColor());
                } else {
                    System.out.print(room.getColor().getColor() + "#" + Color.valueOf("RESET").getColor());
                }
            } else {
                System.out.print(room.getColor().getColor() + "|" + Color.valueOf("RESET").getColor());
            }

            System.out.print(" ");
            printMiddle(room.getEast());
        } else {
            return;
        }
    }

    private static void printBottom(Room room) {
        if (room != null) {
            System.out.print(room.getColor().getColor() + "+" + Color.valueOf("RESET").getColor());

            if (room.getSouth() != null) {
                if (room.getSouth().getColor().equals(room.getColor())) {
                    System.out.print(room.getColor().getColor() + " " + Color.valueOf("RESET").getColor());
                } else {
                    System.out.print(room.getColor().getColor() + "#" + Color.valueOf("RESET").getColor());
                }
            } else {
                System.out.print(room.getColor().getColor() + "-" + Color.valueOf("RESET").getColor());
            }
            System.out.print(room.getColor().getColor() + "+" + Color.valueOf("RESET").getColor());
            System.out.print(" ");

            printBottom(room.getEast());
        } else {
            return;
        }
    }
}
