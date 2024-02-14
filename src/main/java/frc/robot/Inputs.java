package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;

public class Inputs {
    private static final XboxController driver1 = new XboxController(OperatorConstants.driver1ControllerPort);
    private static final CommandXboxController driver1Cmd = new CommandXboxController(OperatorConstants.driver1ControllerPort);
	private static final CommandGenericHID operatorCmd = new CommandGenericHID(OperatorConstants.operatorControllerPort);
    private static final CommandJoystick elevatorCmd = new CommandJoystick(OperatorConstants.elevatorControllerPort);
    
    /*
	 * 
	 * Inputs:
	 * Driver
	 * 	Left X/Y - Swerve Drive Translation
	 * 	Right X - Swerve Drive Rotation
	 * 	Y - Robot Relative
	 *  X - Lock Wheels
	 * 	Start - Cancel Any Drivetrain Commands
	 * 	A - Shoot Speaker
     *  B - Outtake Amp
	 * 	Menu - Reset Gyro
	 *  Right Bumper - Fast Mode
	 *  Left  - Slow Mode
	 * 
	 * Operator
	 *  Button Board:
	 * 	 1 - Storage 
	 * 	  Auto:
	 *      - Intake
     *      - Transfer
     *      - Setup Speaker Score Close
     *      - Setup Speaker Score Far
     *      - Setup Amp Score
	 *    Intake:
	 *      - Intake Pivot Down
	 *      - Intake Pivot Up
     *      - Start Intake In
     *      - Start Intake Out
	 *      - Stop Intake
     *      - Stop Intake Pivot
     *    Elevator:
     *      - Elevator Base Position
     *      - Elevator Transfer Position
     *      - Elevator Amp Position
     *      - Elevator Speaker Position
     *      - Stop Elevator
	 *    Adjustment:
	 *      - Intake Pivot Up
	 *      - Intake Pivot Down
	 *      - Shooter Pivot Up
	 *      - Shooter Pivot Down
     *      - Elevator Up
     *      - Elevator Down
	 *      - Save Position
     *      - Stop Pivot
     *    
	 *   
	 */



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

    public static Trigger getOverride() {
        return driver1Cmd.start();
    }

    public static double getOverrideY() {
        return elevatorCmd.getY();
    }

    public static Trigger elevatorMax() {
        return driver1Cmd.rightBumper();
    }

    public static Trigger elevatorMin() {
        return driver1Cmd.leftBumper();
    }

    public static Trigger getLimelight() {
        // return driver1Cmd.a();
        return null;
    }

    public static Trigger getShoot() {
        return driver1Cmd.b();
    }

    public static Trigger getIntake() {
        return driver1Cmd.y();
    }

    public static Trigger getOuttake() {
        return driver1Cmd.x();
    }
    

    public static double getManualElevatorSpeed(){
        double speed = (driver1Cmd.getRightTriggerAxis()-driver1Cmd.getLeftTriggerAxis());
        if(Math.abs(speed)<.1){
            speed = 0;
        }
        return speed*.25;
    }
    
    public static double getManualIntakePivotSpeed(){
        double speed = driver1Cmd.getLeftX();
        if(Math.abs(speed)<.1){
            speed = 0;
        }
        return speed*.5;
    }

    public static double getManualShooterPivotSpeed(){
        double speed = driver1Cmd.getRightX();
        if(Math.abs(speed)<.2){
            speed = 0;
        }
        return speed*.5;
    }

    public static Trigger getFeed() {
        return driver1Cmd.a();
    }
}

