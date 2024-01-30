package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Constants.OperatorConstants;

public class Inputs {
    private static final XboxController driver1 = new XboxController(OperatorConstants.driver1ControllerPort);
    private static final CommandJoystick driver1Cmd = new CommandJoystick(OperatorConstants.driver1ControllerPort);
	private static final CommandGenericHID operatorCmd = new CommandGenericHID(OperatorConstants.operatorControllerPort);
    private static final CommandJoystick elevatorCmd = new CommandJoystick(OperatorConstants.elevatorControllerPort);
    
    
    private static double smoothInputs(double value, List<Double> list){
        list.add(value);
        list.remove(0);
        double total=0;
        for(int i=0;i<list.size();i++){
            total += list.get(i);
        }

        return total/list.size();
    }
    private static SlewRateLimiter translationXSlew = new SlewRateLimiter(10, -2, 0);
    
    private static List<Double> translationXList = new ArrayList<>(10); 
    public static double getTranslationX(){
		// return 0.0;
		double speed = driver1.getLeftX();
		if(Math.abs(speed) < .1){
			speed = 0;
		}
        // speed = translationXSlew.calculate(speed);
        // speed = smoothInputs(speed, translationXList);
        SmartDashboard.putNumber("X", speed);
        return speed;
    }


    private static SlewRateLimiter translationYSlew = new SlewRateLimiter(10, -2, 0);

    private static List<Double> translationYList = new ArrayList<>(10); 
    public static double getTranslationY(){
		// return 0.0;
		double speed = driver1.getLeftY();
		if(Math.abs(speed) < .1){
			speed = 0;
		}

        // speed = translationYSlew.calculate(speed);
        // speed = smoothInputs(speed, translationYList);
        SmartDashboard.putNumber("Y", speed);

        return speed;
    }

    private static List<Double> rotationList = new ArrayList<>(10); 
    public static double getRotation(){
		// return 0.0;
		double speed = driver1.getRightX();
		if(Math.abs(speed) < .2){
			speed = 0;
		}

        // speed = smoothInputs(speed, rotationList);
        SmartDashboard.putNumber("Z", speed);

        return speed;
    }

    public static boolean getOverride() {
        return driver1.getStartButtonPressed();
    }

    public static double getOverrideY() {
        return elevatorCmd.getY();
    }

    public static boolean elevatorMax() {
        return driver1.getRightBumperPressed();
    }

    public static boolean elevatorMin() {
        return driver1.getLeftBumperPressed();
    }

    public static boolean intake() {
        return driver1.getYButtonPressed();
    }

    
}
