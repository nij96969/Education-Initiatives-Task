package structural_design_pattern.src.com.example.facade_pattern;

public class HomeAutomation {
    private Light light;
    private SecuritySystem security;
    private HeatingSystem heating;

    public HomeAutomation(Light light , SecuritySystem security , HeatingSystem heating){
        this.light = light;
        this.security = security;
        this.heating = heating;
    }

    public void leaveHome(){
        light.turnOff();
        security.arm();
        heating.turnOff();
    }

    public void returnHome(){
        light.turnOn();
        security.disarm();
        heating.turnOn();;
    }
}
