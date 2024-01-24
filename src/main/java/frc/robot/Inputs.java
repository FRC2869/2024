package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Constants.OperatorConstants;

public class Inputs {
    private static final XboxController driver1 = new XboxController(OperatorConstants.driver1ControllerPort);
    private static final CommandJoystick driver1Cmd = new CommandJoystick(OperatorConstants.driver1ControllerPort);
	private static final CommandGenericHID operatorCmd = new CommandGenericHID(OperatorConstants.operatorControllerPort);
    
    
    private static double smoothInputs(double value, List<Double> list){
        list.add(value);
        list.remove(0);
        double total=0;
        for(int i=0;i<list.size();i++){
            total += list.get(i);
        }

        return total/list.size();
    }

    
    private static List<Double> translationXList = new ArrayList<>(10); 
    public static double getTranslationX(){
		// return 0.0;
		double speed = -driver1.getLeftX();
		if(Math.abs(speed) < .1){
			speed = 0;
		}

        speed = smoothInputs(speed, translationXList);

        return speed;
    }

    private static List<Double> translationYList = new ArrayList<>(10); 
    public static double getTranslationY(){
		// return 0.0;
		double speed = -driver1.getLeftY();
		if(Math.abs(speed) < .1){
			speed = 0;
		}

        speed = smoothInputs(speed, translationYList);

        return speed;
    }

    private static List<Double> rotationList = new ArrayList<>(10); 
    public static double getRotation(){
		// return 0.0;
		double speed = -driver1.getRightX();
		if(Math.abs(speed) < .1){
			speed = 0;
		}

        speed = smoothInputs(speed, rotationList);

        return speed;
    }


}