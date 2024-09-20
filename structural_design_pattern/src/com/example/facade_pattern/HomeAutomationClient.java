package structural_design_pattern.src.com.example.facade_pattern;

public class HomeAutomationClient {
    public static void main(String[] args) {
        Light light = new Light();
        SecuritySystem security = new SecuritySystem();
        HeatingSystem heating = new HeatingSystem();

        HomeAutomation homeAutomation = new HomeAutomation(light, security, heating);

        homeAutomation.leaveHome();
        homeAutomation.returnHome();
    }
}
