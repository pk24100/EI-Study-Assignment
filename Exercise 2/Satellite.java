import java.util.logging.Logger;

public class Satellite {
    private static final Logger LOGGER = Logger.getLogger(Satellite.class.getName());

    private String orientation;
    private boolean solarPanelsActive;
    private int dataCollected;

    public Satellite() {
        this.orientation = "North";
        this.solarPanelsActive = false;
        this.dataCollected = 0;
    }

    public void rotate(String direction) {
        try {
            LOGGER.info("Rotating satellite to " + direction);
            if (direction.equals("North") || direction.equals("South") || direction.equals("East") || direction.equals("West")) {
                this.orientation = direction;
            } else {
                throw new IllegalArgumentException("Invalid direction: " + direction);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Invalid direction: " + direction);
        }
    }

    public void activatePanels() {
        LOGGER.info("Activating solar panels");
        this.solarPanelsActive = true;
    }

    public void deactivatePanels() {
        LOGGER.info("Deactivating solar panels");
        this.solarPanelsActive = false;
    }

    public void collectData() {
        int retries = 0;
        while (retries < 3) {
            try {
                LOGGER.info("Collecting data");
                if (this.solarPanelsActive) {
                    this.dataCollected += 10;
                    break;
                } else {
                    throw new Exception("Solar panels are inactive");
                }
            } catch (Exception e) {
                LOGGER.warning("Transient error collecting data, retrying... (Attempt " + retries + ")");
                retries++;
            }
        }
    }

    public String getOrientation() {
        return this.orientation;
    }

    public boolean isSolarPanelsActive() {
        return this.solarPanelsActive;
    }

    public int getDataCollected() {
        return this.dataCollected;
    }

    public void printState() {
        System.out.println("Orientation: " + this.orientation);
        System.out.println("Solar Panels: " + (this.solarPanelsActive ? "Active" : "Inactive"));
        System.out.println("Data Collected: " + this.dataCollected);
    }

    public static void main(String[] args) {
        Satellite satellite = new Satellite();

        System.out.println("Initializing Satellite...");
        System.out.println("Initial State:");
        satellite.printState();

        satellite.rotate("South");
        satellite.activatePanels();
        satellite.collectData();

        System.out.println("Final State:");
        satellite.printState();
    }
}