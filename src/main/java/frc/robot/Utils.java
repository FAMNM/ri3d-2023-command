package frc.robot;

public class Utils {
    public static double deadzone(double value, double deadzone) {
        return (Math.abs(value) < deadzone) ? 0 : value;
    }
}
